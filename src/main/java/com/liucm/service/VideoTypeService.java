package com.liucm.service;

import java.util.List;

import com.liucm.bean.VideoType;

public interface VideoTypeService {
	public List<VideoType> getVideoTypeAll();
	public VideoType  getVideoTypeByTypeName(String typeName);
	public VideoType  getVideoTypeByVideoTypeId(int videoId);
}
