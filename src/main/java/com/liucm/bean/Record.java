package com.liucm.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Record implements Serializable{

	private static final long serialVersionUID = -1368695689066422711L;
	private int recordId;
	private User user;
	private Video video;
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", user=" + user + ", video=" + video + "]";
	}

	
}
