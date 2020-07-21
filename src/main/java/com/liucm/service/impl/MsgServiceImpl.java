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
	private Message message;
	
	
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
	public String addMsg(Message msg) {
		try {
			int signal = msgMapper.insertMsg(msg);
			 if(signal == 1) {
				 return "信息数据插入成功";
			 } else {
				 return "信息数据插入失败";
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return "信息数据插入异常";
		}	 
	}

	@Override
	public String deleteMsg(int msgId) {
		try {
			message = msgMapper.selectMessage(msgId);
			if(message == null) {
				return "删除数据不存在";
			}else {
				msgMapper.deleteMessage(msgId);
				return "信息删除成功";
			}
		} catch (Exception e) {
			return "系统异常";
		}
	}

	@Override
	public String updateReadMsg(int msgId, int stateId) {
		try {
			message = msgMapper.selectMessage(msgId);
			if(message == null) {
				return "更新数据不存在";
			}else {
				msgMapper.updateMsgState(msgId, stateId);
				return "信息已读";
			}		
		} catch (Exception e) {
			return "系统异常";
		}
	}

	@Override
	public int getMsgSum(int userId) {
		return msgMapper.selectMsgSum(userId);
	}

}
