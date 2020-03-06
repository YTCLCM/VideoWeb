package com.liucm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liucm.bean.Record;
import com.liucm.bean.Video;

public interface RecordMapper {
	public List<Video> getRecordAll(int userId);
	public int deleteRecordByVideoId(@Param(value="userId")int userId,@Param(value="videoId")int videoId);
	public int insertOne(@Param(value="userId")int userId,@Param(value="videoId")int videoId);
	public Record selectOne(@Param(value="userId")int userId,@Param(value="videoId")int videoId);
}
