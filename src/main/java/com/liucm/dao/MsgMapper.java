package com.liucm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liucm.bean.Message;
import com.liucm.bean.MsgType;
import com.liucm.bean.User;

public interface MsgMapper {
	public List<Message> selectMsgByMsgType(@Param(value="receive")User receive,@Param(value="msgType")MsgType msgType);
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int insertMsg(Message msg);
	
	public void updateMsgState(@Param(value="msgId")int msgId,@Param(value="stateId")int stateId);
	
	public int deleteMessage(@Param(value="msgId")int msgId);
	
	public Message selectMessage(@Param(value="msgId")int msgId);
	
	public int selectMsgSum(int userId);

}
