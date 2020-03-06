package com.liucm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 在application.properties中配置
 * filepathconfig.fileUrl=D:\\videoweb
 * filepathconfig.imagePath=\\images\\
 * filepathconfig.videopath=\\videos\\
 * @author 13576
 *
 */
@Component
@ConfigurationProperties(prefix="filepathconfig")
public class FilePathConfig {
	private String fileUrl;
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	private String imagePath;
	private String videoPath;
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	@Override
	public String toString() {
		return "FilePathConfig [fileUrl=" + fileUrl + ", imagePath=" + imagePath + ", videoPath=" + videoPath + "]";
	}
	
}
