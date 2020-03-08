package com.liucm.service;

public interface FocusService {
	public String addFocused(int userId,int focusedId);
	public String focusVerify(int userId,int focusedId);
}
