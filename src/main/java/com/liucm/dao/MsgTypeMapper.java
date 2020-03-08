package com.liucm.dao;

import com.liucm.bean.MsgType;

public interface MsgTypeMapper {
	public void insertOne(MsgType msgType);
	public MsgType selectByTypeName(String msgTypeName);
	public MsgType selectOne(int msgTypeId);
}
