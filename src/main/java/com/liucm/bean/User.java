package com.liucm.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Alias("User")
@Component
@ConfigurationProperties
public class User implements Serializable{

	private static final long serialVersionUID = -1266252492937582358L;
	private int userId;
	private String userName;
	private int userAge;
	private String userSex;
	private String userMail;
	private String userPhone;
	private String userAddress;
	private String password;
	private Date registerDate;
	private int fanNum;
	private String iconUrl;
	
	private UserRole userRole;
	private State state;
	
	private List<Video> videos;
	private List<User> focuses;//用户关注的人
	private List<User> focuseds;//关注该用户的人
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public int getFanNum() {
		return fanNum;
	}
	public void setFanNum(int fanNum) {
		this.fanNum = fanNum;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	public List<User> getFocuses() {
		return focuses;
	}
	public void setFocuses(List<User> focuses) {
		this.focuses = focuses;
	}
	public List<User> getFocuseds() {
		return focuseds;
	}
	public void setFocuseds(List<User> focuseds) {
		this.focuseds = focuseds;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userAge=" + userAge + ", userSex=" + userSex
				+ ", userMail=" + userMail + ", userPhone=" + userPhone + ", userAddress=" + userAddress + ", password="
				+ password + ", registerDate=" + registerDate + ", fanNum=" + fanNum + ", iconUrl=" + iconUrl
				+ ", userRole=" + userRole + ", state=" + state + ", videos=" + videos + ", focuses=" + focuses
				+ ", focuseds=" + focuseds + "]";
	}
	
	
}
