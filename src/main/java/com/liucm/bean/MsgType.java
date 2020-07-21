package com.liucm.bean;

import org.springframework.stereotype.Component;

@Component
public class MsgType {
	private int msgTypeId;
	private String msgTypeName;
	public int getMsgTypeId() {
		return msgTypeId;
	}
	public void setMsgTypeId(int msgTypeId) {
		this.msgTypeId = msgTypeId;
	}
	public String getMsgTypeName() {
		return msgTypeName;
	}
	public void setMsgTypeName(String msgTypeName) {
		this.msgTypeName = msgTypeName;
	}
	@Override
	public String toString() {
		return "MsgType [msgTypeId=" + msgTypeId + ", msgTypeName=" + msgTypeName + "]";
	}
	
}
