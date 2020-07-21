package com.liucm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.CommentedStar;
import com.liucm.bean.Message;
import com.liucm.bean.User;
import com.liucm.bean.Video;
import com.liucm.config.FilePathConfig;
import com.liucm.dao.CommentedStarMapper;
import com.liucm.dao.MsgMapper;
import com.liucm.dao.UserMapper;
import com.liucm.dao.VideoMapper;
import com.liucm.service.MsgTypeService;
import com.liucm.service.StateService;
import com.liucm.service.UserService;
import com.liucm.service.VideoService;
import com.liucm.util.FileUtil;
import com.liucm.util.Page;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private User user;

	@Autowired
	private Message msg;
	
	@Autowired
	private CommentedStar commentedStar;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private VideoService videoService;

	@Autowired
	private UserService userService;

	@Autowired
	private FilePathConfig filePathConfig;

	@Autowired
	private StateService stateService;

	@Autowired
	private CommentedStarMapper commentedStarMapper;

	@Autowired
	private VideoMapper videoMapper;

	@Autowired
	private MsgMapper msgMapper;

	@Autowired
	private Video video;

	@Autowired
	private MsgTypeService msgTypeService;

	@Override
	public String addComment(int starNum, int userId, int videoId) {
		video = videoService.getVideoByVideoId(videoId);
		//发送视频的用户
		user = userService.getUserByUserId(video.getUser().getUserId());
		commentedStar = commentedStarMapper.getCommentedStar(userId, videoId);
		if(commentedStar != null) {
			return "你已经参与点评";
		}
		if (user != null && video != null ) {
			commentedStarMapper.insertOne(userId, videoId, starNum);
			msg.setReceiveUser(user);
			//点评该视频的用户
			user = userMapper.selectOne(userId);
			msg.setMsgTitle("点评提醒");
			msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频获得用户【" + user.getUserName() + "】的" + starNum + "星点评");
			msg.setMsgType(msgTypeService.findByTypeName("EvaluateNotice"));
			msg.setMsgState(stateService.getStateByStateId(6));
			msgMapper.insertMsg(msg);
			return "点评成功";
		} else {
			return "不可对自身视频点评";
		}
	}

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
	public List<Video> getRecommendVideo(int curPage, int pageSize,int userId) {
		int videoCount = videoMapper.selectVideoCount();
		int curIndex = (curPage - 1) * pageSize;
		if (curPage > 0 && pageSize > 0 && curIndex <= videoCount) {
			List<Video> videoList = videoMapper.selectRecommendVideo(curIndex, pageSize,userId);
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
			if (video != null) {
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
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Video> getEnableVideoByUserId(int userId) {
		//return videoMapper.selectEnableVideoByUserId(userId);
		return videoMapper.selectVideoByUserIdAndVideoStateId(userId, 4);
	}

	@Override
	public List<Video> getDisableVideoByUserId(int userId) {
		//return videoMapper.selectDisableVideoByUserId(userId);
		List<Video> list = videoMapper.selectVideoByUserIdAndVideoStateId(userId, 2);
		Iterator<Video> iterator = list.iterator();
		list = videoMapper.selectVideoByUserIdAndVideoStateId(userId, 5);
		while(iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	@Override
	public List<Video> getVerifyingVideoByUserId(int userId) {
		//return videoMapper.selectVerifyingVideoByUserId(userId);
		return videoMapper.selectVideoByUserIdAndVideoStateId(userId, 1);
	}

	@Override
	public List<Video> getVerifiedVideoByUserId(int userId) {
		//return videoMapper.selectVerifiedVideoByUserId(userId);
		return videoMapper.selectVideoByUserIdAndVideoStateId(userId, 2);
	}

	@Override
	public List<Video> getVerifyFalseVideoByUserId(int userId) {
		//return videoMapper.selectVerifyFalseVideoByUserId(userId);
		return videoMapper.selectVideoByUserIdAndVideoStateId(userId, 3);
	}

	@Override
	public int upStore(int videoId) {
		return videoMapper.updateVideoStateByVideoId(videoId, 4);
	}

	@Override
	public int downStore(int videoId) {
		return videoMapper.updateVideoStateByVideoId(videoId, 5);
	}

	@Override
	public String deleteVideo(int videoId) {
		Video video = videoMapper.selectOneByVideoId(videoId);
		if(video != null) {
			// 获得视频存储的实际路径
			String videoRealPath = filePathConfig.getFileUrl() + filePathConfig.getVideoPath()
					+ video.getVideoUrl().substring(video.getVideoUrl().lastIndexOf("\\") + 1);
			String imageRealPath = filePathConfig.getFileUrl() + filePathConfig.getImagePath()
					+ video.getThumbnailUrl().substring(video.getThumbnailUrl().lastIndexOf("\\") + 1);

			// 删除文件
			FileUtil fileUtil = new FileUtil();
			fileUtil.deleteAllFilesOfDir(fileUtil.getFile(videoRealPath));
			fileUtil.deleteAllFilesOfDir(fileUtil.getFile(imageRealPath));
			videoMapper.deleteVideoByVideoId(videoId);
			return "视频删除成功";
		}else {
			return "该视频不存在";
		}
	}

	@Override
	public int updateVideo(Video video) {
		return videoMapper.updateVideoByVideoId(video);
	}

	@Override
	public String addViewSum(int videoId) {
		int signal = videoMapper.updateVideoViewSumByVideoId(videoId);
		if (signal == 1) {
			return "更新成功";
		} else {
			return "观看次数更新出错";
		}

	}

	@Override
	public String addVideoDianZanSum(User user, int videoId) {
		video = this.getVideoByVideoId(videoId);
		if (user != null && user.getUserId() != video.getUser().getUserId()) {
			try {
				videoMapper.updateVideoPpSumByVideoId(videoId);
				msg.setMsgTitle("点赞提醒");
				msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频,获得用户【" + user.getUserName() + "】的一枚点赞");
				msg.setMsgType(msgTypeService.findByTypeName("SuportNotice"));
				msg.setMsgState(stateService.getStateByStateId(6));
				msg.setReceiveUser(video.getUser());
				msgMapper.insertMsg(msg);
				return "点赞成功";
			} catch (Exception e) {
				return "点赞失败";
			}
		} else {
			return "不可对自身视频点赞";
		}
	}

	@Override
	public List<Video> getVideoListByAjax(int curPage, int pageSize, int videoTypeId) {
		return videoMapper.selectVideoByTypeAjax(curPage, pageSize,videoTypeId);
	}

	@Override
	public String getVideoByPage(Page<Video> page) {
		page.setTotalSize(videoMapper.selectVerifyVideoCount());
		List<Video> data = videoMapper.selectVideoByPage(page,1);
		if(data != null) {
			page.setData(data);
			return "查询成功";
		}else {
			return "查询失败";
		}
	}

	@Override
	public boolean verifyVideo(int videoId , int stateId) {
		
		videoMapper.updateVideoStateByVideoId(videoId, stateId);
		
		return true;
	}




}
