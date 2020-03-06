package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.Video;
import com.liucm.config.FilePathConfig;
import com.liucm.dao.VideoMapper;
import com.liucm.service.VideoService;
import com.liucm.util.FileUtil;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private FilePathConfig filePathConfig;

	@Override
	public List<Video> getVideoListByAjax(int curPage, int pageSize) {
		int videoCount = videoMapper.selectVideoCount();
		int curIndex = (curPage - 1) * pageSize;
		if (curPage > 0 && pageSize > 0 && curIndex <= videoCount) {
			List<Video> videoList = videoMapper.selectVideoListByAjax(curIndex, pageSize);
			if (videoList != null) {
				return videoList;
			}
		}
		return null;
	}

	@Override
	public List<Video> getVideoRecommend(int pageSize) {
		if (pageSize > 0) {
			List<Video> videoList = videoMapper.selectVideoByPPNum(pageSize);
			if (videoList != null) {
				return videoList;
			}
		}
		return null;
	}

	@Override
	public Video getVideoByVideoId(int videoId) {
		if (videoId != 0) {
			Video video = videoMapper.selectOneByVideoId(videoId);
			if(video != null) {
				return video;
			}
		}
		return null;
	}

	@Override
	public boolean addVideo(Video video) {
		try {
			videoMapper.insertVideo(video);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<Video> getEnableVideoByUserId(int userId) {
		return videoMapper.selectEnableVideoByUserId(userId);
	}

	@Override
	public List<Video> getDisableVideoByUserId(int userId) {
		return videoMapper.selectDisableVideoByUserId(userId);
	}

	@Override
	public List<Video> getVerifyingVideoByUserId(int userId) {
		return videoMapper.selectVerifyingVideoByUserId(userId);
	}

	@Override
	public List<Video> getVerifiedVideoByUserId(int userId) {
		return videoMapper.selectVerifiedVideoByUserId(userId);
	}

	@Override
	public List<Video> getVerifyFalseVideoByUserId(int userId) {
		return videoMapper.selectVerifyFalseVideoByUserId(userId);
	}

	@Override
	public int upStore(int videoId) {	
		return videoMapper.updateVideoStateByVideoId(videoId,4);
	}

	@Override
	public int downStore(int videoId) {
		return videoMapper.updateVideoStateByVideoId(videoId,5);
	}

	@Override
	public int deleteVideo(int videoId) {
		Video video = videoMapper.selectOneByVideoId(videoId);
		//获得视频存储的实际路径
		String videoRealPath = filePathConfig.getFileUrl()+filePathConfig.getVideoPath()+video.getVideoUrl().substring(video.getVideoUrl().lastIndexOf("\\")+1);
		String imageRealPath = filePathConfig.getFileUrl()+filePathConfig.getImagePath()+video.getThumbnailUrl().substring(video.getThumbnailUrl().lastIndexOf("\\")+1);
		

		//删除文件
		fileUtil.deleteAllFilesOfDir(fileUtil.getFile(videoRealPath));
		fileUtil.deleteAllFilesOfDir(fileUtil.getFile(imageRealPath));
		return videoMapper.deleteVideoByVideoId(videoId);
	}

	@Override
	public int updateVideo(Video video) {
		return videoMapper.updateVideoByVideoId(video);
	}

	@Override
	public String addViewSum(int videoId) {
		int signal = videoMapper.updateVideoViewSumByVideoId(videoId);
		if(signal == 1) {
			return "更新成功";
		}else {
			return "观看次数更新出错";
		}

	}

	@Override
	public String addVideoDianZanSum(int videoId) {
		int signal = videoMapper.updateVideoPpSumByVideoId(videoId);
		if(signal == 1) {
			return "点赞成功";
		}else {
			return "点赞失败";
		}
	}

}
