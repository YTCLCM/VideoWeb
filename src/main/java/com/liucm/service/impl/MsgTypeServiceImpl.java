package com.liucm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.MsgType;
import com.liucm.dao.MsgTypeMapper;
import com.liucm.service.MsgTypeService;

@Service
public class MsgTypeServiceImpl implements MsgTypeService{
	
	@Autowired
	private MsgTypeMapper msgTypeMapper;
	
	@Autowired
	private MsgType msgType;

	@Override
	public void addOne(String msgTypeName) {
		if(msgTypeName != null && msgTypeName.equals("")) {
			msgType = this.findByTypeName(msgTypeName);
			if(msgType == null) {
				msgType.setMsgTypeName(msgTypeName);
				msgTypeMapper.insertOne(msgType);
			}else {
				System.out.println("信息类型已存在");
			}
		}else {
			System.out.println("插入的信息类型为空");
		}	
	}

	@Override
	public MsgType findByTypeName(String msgTypeName) {
		if(msgTypeName != null && !msgTypeName.equals("")) {
			msgType = msgTypeMapper.selectByTypeName(msgTypeName);
		}
		return msgType;
	}

	@Override
	public MsgType findOneBymsgTypeId(int msgTypeId) {
		return null;
	}

}
