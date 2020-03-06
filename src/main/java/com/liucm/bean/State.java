package com.liucm.bean;

import java.io.Serializable;

public class State implements Serializable{

	private static final long serialVersionUID = 8597450108037290810L;
	private int stateId;
	private String stateName;
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", stateName=" + stateName + "]";
	}
	
}
