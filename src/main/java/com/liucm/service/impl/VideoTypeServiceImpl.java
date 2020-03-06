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

}
