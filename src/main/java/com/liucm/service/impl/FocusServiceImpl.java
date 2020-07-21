package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.Focus;
import com.liucm.dao.FocusMapper;
import com.liucm.service.FocusService;

@Service
public class FocusServiceImpl implements FocusService {

	@Autowired
	private FocusMapper focusMapper;
	
	@Autowired
	private Focus focus;

	@Override
	public String addFocused(int userId, int focusedId) {

		// 不可以对自己关注
		if (userId == focusedId) {
			return "不可以对自己进行关注";
		}
		focus.setUserId(userId);
		focus.setFocusedId(focusedId);
		int signal = focusMapper.selectOneVerify(focus);
		if (signal == 0) {
			try {
				focusMapper.insertOne(focus);
				return "关注成功";
			} catch (Exception e) {
				return "关注失败";
			}
		} else {
			return "已关注";
		}
	}

	@Override
	public String focusVerify(int userId, int focusedId) {
		focus.setUserId(userId);
		focus.setFocusedId(focusedId);
		int signal = focusMapper.selectOneVerify(focus);
		if (signal == 0) {
			return "未关注";
		} else {
			return "已关注";
		}
	}

	@Override
	public List<Integer> getUserFocusList(int userId) {
		List<Integer> focusedsIdList = focusMapper.selectFocusedsId(userId);
		return focusedsIdList; 
	}

}
