package com.liucm.daotest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.Message;
import com.liucm.bean.MsgType;
import com.liucm.bean.User;
import com.liucm.dao.MsgMapper;
import com.liucm.dao.MsgTypeMapper;
import com.liucm.service.MsgTypeService;

public class MsgTest extends ApplicationTest {
	@Autowired
	private MsgMapper msgMapper;
	@Autowired
	private MsgTypeMapper msgTypeMapper;
	@Autowired
	private Message message;

	@Autowired
	private MsgTypeService msgTypeService;
	@Test
	public void TestSelectMsgByMsgType() {
//		MsgType msgType = msgTypeMapper.selectByTypeName("PrivateMsg");
//		User user = new User();
//		user.setUserId(1);
//		List<Message> list = msgMapper.selectMsgByMsgType(user,msgType);
//		System.out.println(list);
		
		MsgType msgType2 = msgTypeService.findByTypeName("CollectionNotice");
		System.out.println(msgType2);
	}
	
	@Test
	public void TestInsertMsg() {
//		User user = new User();
//		user.setUserId(1);
//		message.setMsgContext("abc");
//		message.setSendUser(user);
//		message.setReceiveUser(user);
//		msgMapper.insertMsg(message);
	}

}
