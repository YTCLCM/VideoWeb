package com.liucm.service;

import java.util.List;

import com.liucm.bean.Video;

public interface CollectionService {
	public List<Video> getAllCollection(int userId);
	public String deleteCollection(int userId,int videoId);
	public String addCollection(int userId,int videoId);
	public String vertifyIfCollection(int userId,int videoId);
}
