package com.liucm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liucm.bean.Collection;
import com.liucm.bean.Video;

public interface CollectionMapper {
	public List<Video> getCollettionAll(int userId);
	public int deleteCollectionByVideoId(@Param(value="userId")int userId,@Param(value="videoId")int videoId);
	public int insertOne(@Param(value="userId")int userId,@Param(value="videoId")int videoId);
	public Collection selectOne(@Param(value="userId")int userId,@Param(value="videoId")int videoId);
}
