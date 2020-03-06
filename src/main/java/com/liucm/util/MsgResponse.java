package com.liucm.util;

public class MsgResponse {
	private Integer status;	//状态码	200 成功 500代码异常
	private String message;	//错误、成功信息
	private Object data;	//数据	500 null
	
	public static MsgResponse success(String message, Object data){
		MsgResponse response = new MsgResponse();
		response.setStatus(200);
		response.setMessage(message);
		response.setData(data);
		return response;
	}
	
	public static MsgResponse error(String message){
		MsgResponse response = new MsgResponse();
		response.setStatus(500);
		response.setMessage(message);
		response.setData(null);
		return response;
	}
	
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
