package com.liucm.controller.publics;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.Message;
import com.liucm.bean.MsgType;
import com.liucm.bean.State;
import com.liucm.bean.User;
import com.liucm.service.MsgService;
import com.liucm.service.MsgTypeService;
import com.liucm.service.StateService;
import com.liucm.service.UserService;
import com.liucm.util.MsgResponse;

@Controller
@RequestMapping("/MsgController")
public class MsgController {
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MsgTypeService msgTypeService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private Message msg;
	
	
	@GetMapping("getMsgListByAjax")
	@ResponseBody
	public List<Message> getMsgListByAjax(HttpServletRequest req,@RequestParam String msgTypeName) {
		return msgService.findMsgByMsgType((User)req.getSession().getAttribute("user"),msgTypeName);
	}
	
	@GetMapping("delMsgByAjax")
	@ResponseBody
	public MsgResponse delMsgByAjax(@RequestParam int msgId) {
		return MsgResponse.success(msgService.deleteMsg(msgId), null);
	}
	
	@GetMapping("getMsgSum")
	@ResponseBody
	public MsgResponse getMsgSum(HttpServletRequest req) {
		User user = (User)req.getSession().getAttribute("user");
		return MsgResponse.success("success", msgService.getMsgSum(user.getUserId()));
	}
	
	@GetMapping("updateMsgState")
	@ResponseBody
	public MsgResponse updateMsgState(@RequestParam int msgId) {
		return MsgResponse.success(msgService.updateReadMsg(msgId, 7),null);
	}
	
	@PostMapping("addLetter")
	@ResponseBody
	public MsgResponse addLetter(HttpServletRequest request ,@RequestParam String msgTitle,@RequestParam String msgContext,@RequestParam int receiveUserId,@RequestParam int msgTypeId) {
		User sendUser = (User)request.getSession().getAttribute("user");
		String addMsg = null;
		if(sendUser != null) {
			msg.setMsgTitle(msgTitle);
			msg.setMsgContext(msgContext);
			msg.setSendUser(sendUser);
			User receiveUser = userService.getUserByUserId(receiveUserId);
			if(receiveUser == null) {
				addMsg ="接收用户为空";
				return MsgResponse.error(addMsg);	
			}
			msg.setReceiveUser(receiveUser);
			MsgType msgType = msgTypeService.findOneBymsgTypeId(msgTypeId);
			msg.setMsgType(msgType);
			State msgState = stateService.getStateByStateId(6);
			msg.setMsgState(msgState);
			addMsg = msgService.addMsg(msg);
			return MsgResponse.success(addMsg,null);
		}
		addMsg ="发送用户为空";
		return MsgResponse.error(addMsg);	
	}
}
