package com.liucm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.User;
import com.liucm.service.FocusService;
import com.liucm.util.MsgResponse;

@Controller
@RequestMapping("/FocusController")
public class FocusController {
	
	@Autowired
	private FocusService focusServiceImpl;

	@PostMapping("FocusOnByAjax")
	@ResponseBody
	public MsgResponse FocusOnByAjax(HttpServletRequest request,@RequestParam String focusedId) {
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null && focusedId!=null && !focusedId.equals("")) {
			int userId = user.getUserId();
			int focusedIdInt = Integer.parseInt(focusedId);		
			return MsgResponse.success(focusServiceImpl.addFocused(userId, focusedIdInt), null);
		}else {
			return MsgResponse.error("参数错误");
		}		
	}
}
