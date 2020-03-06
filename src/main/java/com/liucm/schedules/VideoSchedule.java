package com.liucm.schedules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.liucm.bean.Video;
import com.liucm.config.FilePathConfig;
import com.liucm.dao.VideoMapper;
import com.liucm.util.FileUtil;

@Component
public class VideoSchedule {

	@Autowired
	private VideoMapper videoMapper;

	@Autowired
	private FilePathConfig filePathConfig;

	@Autowired
	private FileUtil fileUtil;

	/**
	 * cron属性：定时任务的触发时间
	 */
	@Scheduled(cron = "0 0 0/8 * * ?")
	public void clearVideoSchedule() {
		int videoNum = videoMapper.selectVideoSum();
		int pageSize = 2;
		int curIndex = 0;
		String videoUrl = null;
		List<Video> videoList = null;
		String videoRealPath = null;
		while (videoNum > 0) {
			videoList = videoMapper.selectExistVideoListByAjax(curIndex, pageSize);
			for (Video video : videoList) {
				if (video.getVideoUrl().contains("\\")) {
					videoUrl = video.getVideoUrl().substring(video.getVideoUrl().lastIndexOf('\\') + 1);
					videoRealPath = filePathConfig.getFileUrl() + filePathConfig.getVideoPath() + videoUrl;
				} else {
					videoRealPath = video.getVideoUrl();
				}
				if (fileUtil.isExist(videoRealPath) == false) {
					// fileUtil.deleteAllFilesOfDir(fileUtil.getFile("D:\\aaa\\bbb\\ddd"));
					if(!video.getVideoUrl().equals("\\video\\defaultvideo.mp4")) {
						video.setVideoUrl("\\video\\defaultvideo.mp4");
					}
					if (!video.getThumbnailUrl().equals("\\img\\defaultvideoicon.jpg")) {
						video.setThumbnailUrl("\\img\\defaultvideoicon.jpg");
					}
				}

				videoMapper.updateVideoByVideoId(video);
			}
			videoNum -= pageSize;
			curIndex += pageSize;
		}

	}
}
