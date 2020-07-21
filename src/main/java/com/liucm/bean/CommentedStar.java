package com.liucm.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CommentedStar implements Serializable{

	private static final long serialVersionUID = -7208043708543774541L;
	private int commentedstarId;
	private int userId;
	private int videoId;
	private int starNum;
	private String commentDate;
	public int getCommentedstarId() {
		return commentedstarId;
	}
	public void setCommentedstarId(int commentedstarId) {
		this.commentedstarId = commentedstarId;
	}
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
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	@Override
	public String toString() {
		return "CommentedStar [commentedstarId=" + commentedstarId + ", userId=" + userId + ", videoId=" + videoId
				+ ", starNum=" + starNum + ", commentDate=" + commentDate + "]";
	}
	
}
