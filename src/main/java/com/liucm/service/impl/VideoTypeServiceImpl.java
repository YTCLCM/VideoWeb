package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.VideoType;
import com.liucm.dao.VideoTypeMapper;
import com.liucm.service.VideoTypeService;

@Service
public class VideoTypeServiceImpl implements VideoTypeService {

	@Autowired
	private VideoTypeMapper videoTypeMapper;

	@Autowired
	private VideoType videoType;

	@Override
	public VideoType getVideoTypeByTypeName(String typeName) {
		return videoTypeMapper.selectVideoTypeByTypeName(typeName);
	}

	@Override
	public List<VideoType> getVideoTypeAll() {
		return videoTypeMapper.selectAll();
	}

	@Override
	public VideoType getVideoTypeByVideoTypeId(int videoId) {
		return videoTypeMapper.selectVideoTypeByVideoTypeId(videoId);
	}

	@Override
	public String addVideoType(String typeName) {
		try {
			videoType.setTypeName(typeName);
			videoTypeMapper.insertOne(videoType);
		} catch (Exception e) {
			return "添加类型失败";
		}
		return "添加类型成功";
	}

	@Override
	public String deleteVideoType(int videoTypeId) {
		try {
			videoTypeMapper.delete(videoTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			return "删除类型失败";
		}
		return "删除类型成功";
	}

}
