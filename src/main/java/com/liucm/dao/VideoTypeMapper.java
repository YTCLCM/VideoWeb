package com.liucm.dao;

import java.util.List;

import com.liucm.bean.VideoType;

public interface VideoTypeMapper {
	
	public List<VideoType> selectAll();
	
	public VideoType selectVideoTypeByTypeName(String typeName);
	
	public VideoType selectVideoTypeByVideoTypeId(int videoId);
	
	public int insertOne(VideoType videoType);
	
	public int update(VideoType videoType);
	
	public int delete(int videoTypeId);
	
}
