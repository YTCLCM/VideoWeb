package com.liucm.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Message implements Serializable{

	private static final long serialVersionUID = -6366336765301601479L;
	private int msgId;
	private String msgTitle;
	private String msgContext;
	private String msgSendDate;
	private User sendUser;
	private User receiveUser;
	private State msgState;
	private MsgType msgType;
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getMsgContext() {
		return msgContext;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public void setMsgContext(String msgContext) {
		this.msgContext = msgContext;
	}
	public String getMsgSendDate() {
		return msgSendDate;
	}
	public void setMsgSendDate(String msgSendDate) {
		this.msgSendDate = msgSendDate;
	}
	public User getSendUser() {
		return sendUser;
	}
	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}
	public User getReceiveUser() {
		return receiveUser;
	}
	public void setReceiveUser(User receiveUser) {
		this.receiveUser = receiveUser;
	}
	public State getMsgState() {
		return msgState;
	}
	public void setMsgState(State msgState) {
		this.msgState = msgState;
	}
	public MsgType getMsgType() {
		return msgType;
	}
	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", msgTitle=" + msgTitle + ", msgContext=" + msgContext + ", msgSendDate="
				+ msgSendDate + ", sendUser=" + sendUser + ", receiveUser=" + receiveUser + ", msgState=" + msgState
				+ ", msgType=" + msgType + "]";
	}

	
}
