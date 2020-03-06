package com.liucm.bean;

import java.io.Serializable;

public class UserRole implements Serializable{

	private static final long serialVersionUID = 950434112505684175L;
	private int roleId;
	private String userRole;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", userRole=" + userRole + "]";
	}
	
}
