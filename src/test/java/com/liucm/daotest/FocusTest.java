package com.liucm.daotest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.User;
import com.liucm.dao.FocusMapper;

public class FocusTest extends ApplicationTest {

	@Autowired
	private FocusMapper focusMapper;

	@Test
	public void TestSelectFocuseds() {
		List<User> focuseds = focusMapper.selectFocuseds(1);
		for(User user:focuseds) {
			System.out.println(user);
		}
	}
	
	@Test
	public void TestSelectFocuses() {
		List<User> focuseds = focusMapper.selectFocuses(11);
		for(User user:focuseds) {
			System.out.println(user);
		}
	}

}
