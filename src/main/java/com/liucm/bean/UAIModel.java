package com.liucm.bean;

import java.io.Serializable;

public class UAIModel implements Serializable{

	@Override
	public String toString() {
		return "UAIModel [userId=" + userId + ", videoId=" + videoId + ", starNum=" + starNum + "]";
	}
	private static final long serialVersionUID = -2858298391932566075L;
	private int userId;
	private int videoId;
	private int starNum;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public int getStarNum() {
		return starNum;
	}
	public void setStarNum(int starNum) {
		this.starNum = starNum;
	}

}
