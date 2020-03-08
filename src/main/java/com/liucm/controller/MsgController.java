package com.liucm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.Message;
import com.liucm.bean.User;
import com.liucm.service.MsgService;
import com.liucm.service.MsgTypeService;

@Controller
@RequestMapping("/MsgController")
public class MsgController {
	
	@Autowired
	private MsgService msgService;
	
	
	@GetMapping("getMsgListByAjax")
	@ResponseBody
	public List<Message> getMsgListByAjax(HttpServletRequest req,@RequestParam String msgTypeName) {
		return msgService.findMsgByMsgType((User)req.getSession().getAttribute("user"),msgTypeName);
	}
}
