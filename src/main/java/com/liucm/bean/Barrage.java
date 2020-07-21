package com.liucm.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Barrage implements Serializable{

	private static final long serialVersionUID = -1803997340414937823L;
	
	private int barrageId;
	private String context;
	private Date releaseDate;
	private int ppNum;
	
	private User user;
	private Video video;
	public int getBarrageId() {
		return barrageId;
	}
	public void setBarrageId(int barrageId) {
		this.barrageId = barrageId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getPpNum() {
		return ppNum;
	}
	public void setPpNum(int ppNum) {
		this.ppNum = ppNum;
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
		return "Barrage [barrageId=" + barrageId + ", context=" + context + ", releaseDate=" + releaseDate + ", ppNum="
				+ ppNum + ", user=" + user + ", video=" + video + "]";
	}
	
}
