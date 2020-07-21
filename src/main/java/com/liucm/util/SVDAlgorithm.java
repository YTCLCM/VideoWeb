package com.liucm.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.liucm.bean.UAIModel;
import com.liucm.dao.CommentedStarMapper;
import com.liucm.dao.UserMapper;
import com.liucm.dao.VideoMapper;

@Configuration
public class SVDAlgorithm {
	@Autowired
	private CommentedStarMapper commentedStarMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private VideoMapper videoMapper;

	private int N;// 用户的数目
	private int M;// 产品的数目

	// 用户数对应的下标
	protected int mUserNum;
	// 项数对应的下标
	protected int mVideoNum;

	private int K = 8;// 特征的数目
	protected Map<Integer, Integer> userIdMap;
	protected Map<Integer, Integer> videoIdMap;
	protected Map<Integer, Integer> userIdMapT;
	protected Map<Integer, Integer> videoIdMapT;

	private static DecimalFormat df = new DecimalFormat("###.00");

	public void start() {
		
		List<Integer> userIdList = userMapper.selectAllUserId();
		List<Integer> videoIdList = videoMapper.getAllVideoId();
		this.N = userIdList.size();
		this.M = videoIdList.size();
		System.out.println("N =" + N + " ;M = " + M);

		userIdMap = new HashMap<>();
		videoIdMap = new HashMap<>();
		// 键值对调
		userIdMapT = new HashMap<>();
		videoIdMapT = new HashMap<>();
		// R为评分矩阵
		double[][] R = new double[N][M];
		double[] P = new double[N * K];
		double[] Q = new double[K * M];
		int userId;
		int videoId;
		float star;

		for (int i = 0; i < userIdList.size(); i++) {
			userIdMapT.put(i, userIdList.get(i));
		}
		for (int j = 0; j < videoIdList.size(); j++) {
			videoIdMapT.put(j, videoIdList.get(j));
		}

		List<UAIModel> modelList = commentedStarMapper.getUIModelList();

		int t = 0;
		for (int i = 0; i < N; i++) {			
			for (int j = 0; j < M; j++) {
				if (t<modelList.size() && userIdMapT.get(i) == modelList.get(t).getUserId()) {
					if (videoIdMapT.get(j) == modelList.get(t).getVideoId()) {
						R[i][j] = Float.valueOf(modelList.get(t).getStarNum());
						t++;
						//break;
					}
				}	
			}
		}

		// 初始化P，Q矩阵，这里简化了，通常也可以对服从正态分布的数据进行随机数生成
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < K; ++j) {
				P[i * K + j] = Math.random();
			}
		}
		for (int i = 0; i < K; ++i) {
			for (int j = 0; j < M; ++j) {
				Q[i * M + j] = Math.random();
			}
		}
		System.out.println("矩阵分解开始");
		matrix_factorization(R, P, Q, N, M, K);

		System.out.println("重构出来的R矩阵");
		
		commentedStarMapper.deletePredict();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				double predict = 0;
				for (int k = 0; k < K; ++k) {
					predict += P[i * K + k] * Q[k * M + j];
				}
				R[i][j] = predict;
				userId = userIdMapT.get(i);
				videoId = videoIdMapT.get(j);
				commentedStarMapper.addPredictStar(userId, videoId, R[i][j]);
				//System.out.print(df.format(predict) + "="+R[i][j]+",");
			}
			//System.out.println();
		}
	}

	public static void matrix_factorization(double[][] R, double[] P, double[] Q, int N, int M, int K) {
		int steps = 5000;
		double alpha = 0.01;
		double beta = 0.001;

		// https://www.jianshu.com/p/f840a5ad1655
		// 计算5000次
		for (int step = 0; step < steps; ++step) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {

					// 用户（i）对视频（j）有评分
					if (R[i][j] > 0) {

						// 记录当前评分
						double eij = R[i][j];

						for (int k = 0; k < K; ++k) {
							eij -= P[i * K + k] * Q[k * M + j];
						}
						for (int k = 0; k < K; ++k) {
							P[i * K + k] += alpha * (2 * eij * Q[k * M + j] - beta * P[i * K + k]);
							Q[k * M + j] += alpha * (2 * eij * P[i * K + k] - beta * Q[k * M + j]);
						}
					}
				}
			}
			double loss = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (R[i][j] > 0) {
						double eij = 0;

						// 求PQ
						for (int k = 0; k < K; ++k) {
							eij += P[i * K + k] * Q[k * M + j];
						}

						// (rij-PQ)^2
						loss += Math.pow(R[i][j] - eij, 2);

						double p = 0, q = 0;

						// 求pik,qkj的模的平方
						for (int k = 0; k < K; ++k) {
							// loss += beta * (Math.pow(P[i * K + k], 2) + Math.pow(Q[k * M + j], 2));
							p = Math.pow(P[i * K + k], 2);
							q = Math.pow(Q[k * M + j], 2);
						}
						loss += beta * (p + q);
					}
				}
			}
			if (loss < 0.01) {
				break;
			}
			if (step % 5000 == 0) {
				//System.out.println("loss:" + loss);
			}
		}
	}
}
