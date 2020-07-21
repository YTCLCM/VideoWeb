package com.liucm.service;

import java.util.List;

public interface FocusService {
	public String addFocused(int userId,int focusedId);
	
	public String focusVerify(int userId,int focusedId);
	
	public List<Integer> getUserFocusList(int userId);
}
