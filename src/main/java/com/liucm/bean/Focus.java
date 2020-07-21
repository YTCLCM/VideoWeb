package com.liucm.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Focus implements Serializable{

	private static final long serialVersionUID = 2333832277086203824L;
	private int focusId;
	private int userId;
	private int focusedId;
	public int getFocusId() {
		return focusId;
	}
	public void setFocusId(int focusId) {
		this.focusId = focusId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFocusedId() {
		return focusedId;
	}
	public void setFocusedId(int focusedId) {
		this.focusedId = focusedId;
	}
	@Override
	public String toString() {
		return "Focus [focusId=" + focusId + ", userId=" + userId + ", focusedId=" + focusedId + "]";
	}

}
