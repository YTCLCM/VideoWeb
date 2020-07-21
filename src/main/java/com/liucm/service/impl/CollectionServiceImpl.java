package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.Message;
import com.liucm.bean.User;
import com.liucm.bean.Video;
import com.liucm.dao.CollectionMapper;
import com.liucm.dao.MsgMapper;
import com.liucm.dao.UserMapper;
import com.liucm.service.CollectionService;
import com.liucm.service.MsgTypeService;
import com.liucm.service.StateService;
import com.liucm.service.VideoService;

@Service
public class CollectionServiceImpl implements CollectionService{

	@Autowired
	private CollectionMapper collectionMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MsgMapper msgMapper;
	
	@Autowired
	private Message msg;
	
	@Autowired
	private MsgTypeService msgTypeService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private Video video;
	
	@Autowired
	private User user;
	
	@Autowired
	private VideoService videoServic;
	
	@Override
	public List<Video> getAllCollection(int userId) {
		if(userMapper.selectOne(userId)!=null) {
			List<Video> collectionList = collectionMapper.getCollettionAll(userId);
			return collectionList;
		}
		return null;
	}

	@Override
	public String deleteCollection(int userId, int videoId) {
		if(userMapper.selectOne(userId)!=null) {
			try {
				if(collectionMapper.selectOne(userId, videoId) != null) {
					collectionMapper.deleteCollectionByVideoId(userId, videoId);
					return "收藏删除成功";
				}else {
					return "收藏记录不存在";
				}
			}catch (Exception e) {
				e.printStackTrace();
				return "收藏删除失败";
			}
		}
		return "用户未登录";
	}

	@Override
	public String addCollection(int userId, int videoId) {		
		video =videoServic.getVideoByVideoId(videoId);
		user = userMapper.selectOne(userId);
		if(user != null && user.getUserId() != video.getUser().getUserId()) {
			try {		
				if(collectionMapper.selectOne(userId, videoId) == null) {
					collectionMapper.insertOne(userId, videoId);
					msg.setMsgTitle("收藏提醒");
					msg.setMsgContext("你好！你的主题为《"+video.getVideoTitle()+"》的视频,被用户【"+user.getUserName()+"】的收藏");
					msg.setMsgType(msgTypeService.findByTypeName("CollectionNotice"));
					msg.setMsgState(stateService.getStateByStateId(6));			
					msg.setReceiveUser(video.getUser());
					msgMapper.insertMsg(msg);	
					return "收藏成功";
				}else {
					return "已收藏";
				}
			}catch (Exception e) {
				e.printStackTrace();
				return "收藏失败";
			}
		}else {
			return "不可对自身视频收藏";
		}

	}

	@Override
	public String vertifyIfCollection(int userId, int videoId) {
		if(userMapper.selectOne(userId)!=null) {
			try {
				if(collectionMapper.selectOne(userId, videoId) == null) {
					return "未收藏";
				}else {
					return "已收藏";
				}
			}catch (Exception e) {
				return "收藏失败";
			}
		}
		return "用户未登录";
	}

}
