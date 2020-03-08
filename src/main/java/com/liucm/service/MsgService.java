package com.liucm.service;

import java.util.List;

import com.liucm.bean.Message;
import com.liucm.bean.User;

public interface MsgService {
	public List<Message> findMsgByMsgType(User receive,String msgTypeName);
	public int addMsg(Message msg);
}
