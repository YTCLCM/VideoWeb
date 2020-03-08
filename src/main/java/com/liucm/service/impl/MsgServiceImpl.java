package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.Message;
import com.liucm.bean.MsgType;
import com.liucm.bean.User;
import com.liucm.dao.MsgMapper;
import com.liucm.dao.MsgTypeMapper;
import com.liucm.service.MsgService;

@Service
public class MsgServiceImpl implements MsgService{
	
	@Autowired
	private MsgMapper msgMapper;
	
	@Autowired
	private MsgTypeMapper msgTypeMapper;
	
	@Autowired
	private MsgType msgType;

	@Override
	public List<Message> findMsgByMsgType(User receive, String msgTypeName) {
		if(receive != null && msgTypeName != null && !msgTypeName.equals("")) {
			if(msgTypeName.equals("All")) {
				return msgMapper.selectMsgByMsgType(receive, null);
			}else {
				msgType = msgTypeMapper.selectByTypeName(msgTypeName);
				return msgMapper.selectMsgByMsgType(receive, msgType);
			}
			
		}
		return null;
	}

	@Override
	public int addMsg(Message msg) {
		return msgMapper.insertMsg(msg);
	}

}
