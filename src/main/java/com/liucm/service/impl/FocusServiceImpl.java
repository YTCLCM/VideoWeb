package com.liucm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.Focus;
import com.liucm.dao.FocusMapper;
import com.liucm.service.FocusService;

@Service
public class FocusServiceImpl implements FocusService{

	@Autowired
	private FocusMapper focusMapper;
	@Override
	public String addFocused(int userId, int focusedId) {
		
		//不可以对自己关注
		if(userId==focusedId) {
			return "不可以对自己进行关注";
		}
		
		Focus focus = new Focus();
		focus.setUserId(userId);
		focus.setFocusedId(focusedId);
		int signal = focusMapper.selectOneVerify(focus);
		if(signal == 0) {
			focusMapper.insertOne(focus);
			return "关注成功";
		}else {
			return "已关注";
		}
		
	}

}
