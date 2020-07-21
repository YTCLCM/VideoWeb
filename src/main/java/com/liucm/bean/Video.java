package com.liucm.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Video implements Comparable<Video>, Serializable{

	private static final long serialVersionUID = 2771694935756573328L;
	private int videoId;
	private String videoTitle;
	private String videoInfo;
	private Date editDate;
	private String videoUrl;
	private String thumbnailUrl;
	private int viewNum;
	private int ppNum;
	
	private State videoState;	
	private User user;
	private VideoType videoType;
	private List<Barrage> barrages;
	
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoInfo() {
		return videoInfo;
	}
	public void setVideoInfo(String videoInfo) {
		this.videoInfo = videoInfo;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public int getPpNum() {
		return ppNum;
	}
	public void setPpNum(int ppNum) {
		this.ppNum = ppNum;
	}
	public State getVideoState() {
		return videoState;
	}
	public void setVideoState(State videoState) {
		this.videoState = videoState;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public VideoType getVideoType() {
		return videoType;
	}
	public void setVideoType(VideoType videoType) {
		this.videoType = videoType;
	}
	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoTitle=" + videoTitle + ", videoInfo=" + videoInfo + ", editDate="
				+ editDate + ", videoUrl=" + videoUrl + ", thumbnailUrl=" + thumbnailUrl + ", viewNum=" + viewNum
				+ ", ppNum=" + ppNum + ", videoState=" + videoState + ", user=" + user + ", videoType=" + videoType
				+ "]";
	}
	public List<Barrage> getBarrages() {
		return barrages;
	}
	public void setBarrages(List<Barrage> barrages) {
		this.barrages = barrages;
	}
	@Override
	public int compareTo(Video video) {
		return -this.editDate.compareTo(video.editDate);
	}
	
}
