package com.liucm.service;

import com.liucm.bean.MsgType;

public interface MsgTypeService {
	public void addOne(String msgTypeName);
	public MsgType findByTypeName(String msgTypeName);
	public MsgType findOneBymsgTypeId(int msgTypeId);
}
