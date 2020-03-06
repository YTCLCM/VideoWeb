package com.liucm.bean;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Collection implements Serializable{

	private static final long serialVersionUID = 3086120303453993908L;
	private int collectionId;
	private User user;
	private Video video;
	
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
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
		return "Collection [collectionId=" + collectionId + ", user=" + user + ", video=" + video + "]";
	}
	
	
}
