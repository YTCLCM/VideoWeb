package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.State;
import com.liucm.dao.StateMapper;
import com.liucm.service.StateService;

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateMapper stateMapper;
	
	@Override
	public State getStateByStateId(int stateId) {
		return stateMapper.selectStateByStateId(stateId);
	}

	@Override
	public List<State> getAllState() {
		return stateMapper.selectAll();
	}

}
