package com.liucm.dao;

import java.util.List;

import com.liucm.bean.State;

public interface StateMapper {
	
	public List<State> selectAll(); 
	
	public State selectStateByStateId(int stateId);
}
