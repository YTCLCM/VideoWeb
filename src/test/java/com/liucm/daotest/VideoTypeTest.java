package com.liucm.daotest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.VideoType;
import com.liucm.dao.VideoTypeMapper;

public class VideoTypeTest extends ApplicationTest {

	@Autowired
	private VideoTypeMapper videoTypeMapper;
	
	
	@Test
	public void TestSelectAll() {
		for(VideoType videoType:videoTypeMapper.selectAll()) {
			System.out.println(videoType); 
		} 

	}
	
	
}
