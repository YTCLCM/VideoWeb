package com.liucm.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class VideoType implements Serializable{

	private static final long serialVersionUID = 7142113169220822173L;
	private int videoTypeId;
	private String typeName;
	public int getVideoTypeId() {
		return videoTypeId;
	}
	public void setVideoTypeId(int videoTypeId) {
		this.videoTypeId = videoTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "VideoType [videoTypeId=" + videoTypeId + ", typeName=" + typeName + "]";
	}
}
