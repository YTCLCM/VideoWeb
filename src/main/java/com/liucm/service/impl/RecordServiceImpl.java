package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.Video;
import com.liucm.dao.RecordMapper;
import com.liucm.dao.UserMapper;
import com.liucm.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{

	@Autowired
	private RecordMapper recordMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<Video> getAllRecord(int userId) {
		if(userMapper.selectOne(userId)!=null) {
			List<Video> List = recordMapper.getRecordAll(userId);
			return List;
		}
		return null;
	}

	@Override
	public String deleteRecord(int userId, int videoId) {
		if(userMapper.selectOne(userId)!=null) {
			try {
				if(recordMapper.selectOne(userId, videoId) != null) {
					recordMapper.deleteRecordByVideoId(userId, videoId);
					return "记录删除成功";
				}else {
					return "观看记录不存在";
				}
			}catch (Exception e) {
				return "记录删除失败";
			}
		}
		return "用户未登录";
	}

	@Override
	public String addRecord(int userId, int videoId) {
		if(userMapper.selectOne(userId)!=null) {
			try {
				if(recordMapper.selectOne(userId, videoId) == null) {
					recordMapper.insertOne(userId, videoId);
					return "观看成功";
				}else {
					return "记录已存在";
				}
				
			}catch (Exception e) {
				return "观看失败";
			}
		}
		return "用户未登录";
	}

}
