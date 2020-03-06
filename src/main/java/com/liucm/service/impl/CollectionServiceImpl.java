package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.Video;
import com.liucm.dao.CollectionMapper;
import com.liucm.dao.UserMapper;
import com.liucm.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService{

	@Autowired
	private CollectionMapper collectionMapper;
	
	@Autowired
	private UserMapper userMapper;
	
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
		if(userMapper.selectOne(userId)!=null) {
			try {
				if(collectionMapper.selectOne(userId, videoId) == null) {
					collectionMapper.insertOne(userId, videoId);
					return "收藏成功";
				}else {
					return "已收藏";
				}
			}catch (Exception e) {
				e.printStackTrace();
				return "收藏失败";
			}
		}
		return "用户未登录";
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
