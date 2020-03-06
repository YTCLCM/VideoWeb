package com.liucm.daotest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.Barrage;
import com.liucm.dao.BarrageMapper;
import com.liucm.dao.UserMapper;
import com.liucm.dao.VideoMapper;

public class BarrageTest extends ApplicationTest {

	@Autowired
	private BarrageMapper barrageMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private VideoMapper videoMapper;
	
	
	@Test
	public void TestInsertOne() {
		Barrage barrage = new Barrage();
		barrage.setContext("这是一条合格的评论");
		barrage.setUser(userMapper.selectOne(9));
		barrage.setVideo(videoMapper.selectOneByVideoId(6));
		barrageMapper.insertOne(barrage);
	}

	@Test
	public void TestDeleteOneByBarrageId() {
		barrageMapper.deleteOneByBarrageId(1);
	}
	
}
