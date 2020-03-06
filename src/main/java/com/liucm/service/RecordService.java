package com.liucm.service;

import java.util.List;

import com.liucm.bean.Video;

public interface RecordService {
	public List<Video> getAllRecord(int userId);
	public String deleteRecord(int userId,int videoId);
	public String addRecord(int userId,int videoId);
}
