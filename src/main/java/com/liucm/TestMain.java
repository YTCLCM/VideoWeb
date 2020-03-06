package com.liucm;

import java.text.DecimalFormat;

public class TestMain {
	private static int N = 5;// 用户的数目
	private static int M = 4;// 产品的数目
	private static int K = 5;// 特征的数目
	private static DecimalFormat df = new DecimalFormat("###.000");

	public static void main(String[] args) {
		double[] R = new double[N * M];
		double[] P = new double[N * K];
		double[] Q = new double[K * M];
		R[0] = 5;
		R[1] = 3;
		R[2] = 0;
		R[3] = 1;
		R[4] = 4;
		R[5] = 0;
		R[6] = 0;
		R[7] = 1;
		R[8] = 1;
		R[9] = 1;
		R[10] = 0;
		R[11] = 5;
		R[12] = 1;
		R[13] = 0;
		R[14] = 0;
		R[15] = 4;
		R[16] = 0;
		R[17] = 0;
		R[18] = 0;
		R[19] = 0;
		System.out.println("R矩阵");
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				System.out.print(R[i * M + j] + ",");
			}
			System.out.println();
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
		
		System.out.println("P矩阵分解");
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < K; ++j) {
				System.out.print(P[i * K + j] + ",");
			}
			System.out.println();
		}
		
		System.out.println("Q矩阵分解");
		for (int i = 0; i < K; ++i) {
			for (int j = 0; j < M; ++j) {
				System.out.print(Q[i * M + j] + ",");
			}
			System.out.println();
		}
		
		
		System.out.println("重构出来的R矩阵");
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				double temp = 0;
				for (int k = 0; k < K; ++k) {
					temp += P[i * K + k] * Q[k * M + j];
				}
				System.out.print(df.format(temp) + ",");
			}
			System.out.println();
		}
	}

	public static void matrix_factorization(double[] R, double[] P, double[] Q, int N, int M, int K) {
		int steps = 5000;
		double alpha = 0.001;
		double beta = 0.02;
		for (int step = 0; step < steps; ++step) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (R[i * M + j] > 0) {
						double eij = R[i * M + j];
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
					if (R[i * M + j] > 0) {
						double eij = 0;
						for (int k = 0; k < K; ++k) {
							eij += P[i * K + k] * Q[k * M + j];
						}
						loss += Math.pow(R[i * M + j] - eij, 2);
						for (int k = 0; k < K; ++k) {
							loss += (beta / 2) * (Math.pow(P[i * K + k], 2) + Math.pow(Q[k * M + j], 2));
						}
					}
				}
			}
			if (loss < 0.001) {
				break;
			}
			if (step % 1000 == 0) {
				System.out.println("loss:" + loss);
			}
		}
	}
}
