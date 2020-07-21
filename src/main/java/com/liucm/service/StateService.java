package com.liucm.service;

import java.util.List;

import com.liucm.bean.State;

public interface StateService {
	public State getStateByStateId(int stateId);
	public List<State> getAllState();
}
