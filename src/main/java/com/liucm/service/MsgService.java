package com.liucm.service;

import java.util.List;

import com.liucm.bean.Message;
import com.liucm.bean.User;

public interface MsgService {
	public List<Message> findMsgByMsgType(User receive,String msgTypeName);
	public String addMsg(Message msg);
	public String deleteMsg(int msgId);
	public String updateReadMsg(int msgId,int stateId);
	
	public int getMsgSum(int userId);
}
