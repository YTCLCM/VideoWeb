package com.liucm.daotest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.State;
import com.liucm.dao.StateMapper;

public class StateTest extends ApplicationTest{

	@Autowired
	private StateMapper stateMapper;
	
	@Test
	public void TestSelectStateByStateId() {
		State state = stateMapper.selectStateByStateId(1);
		System.out.println(state);
	}
	
	@Test
	public void TestSelectAll() {
		List<State> states = stateMapper.selectAll();
		for(State state:states) {
			System.out.println(state);
		}
	}

}
