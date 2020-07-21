package com.liucm.controller.publics;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.User;
import com.liucm.service.FocusService;
import com.liucm.service.UserService;
import com.liucm.util.MsgResponse;

@Controller
@RequestMapping("/FocusController")
public class FocusController {
	
	@Autowired
	private FocusService focusService;
	
	@Autowired
	private UserService userService;

	@PostMapping("FocusOnByAjax")
	@ResponseBody
	public MsgResponse FocusOnByAjax(HttpServletRequest request,@RequestParam String focusedId) {
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null && focusedId!=null && !focusedId.equals("")) {
			int userId = user.getUserId();
			int focusedIdInt = Integer.parseInt(focusedId);		
			return MsgResponse.success(focusService.addFocused(userId, focusedIdInt), null);
		}else {
			return MsgResponse.error("参数错误");
		}		
	}
	
	@PostMapping("FocusVerifyByAjax")
	@ResponseBody
	public MsgResponse FocusVerifyByAjax(HttpServletRequest request,@RequestParam String focusedId) {
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null && focusedId!=null && !focusedId.equals("")) {
			int userId = user.getUserId();
			int focusedIdInt = Integer.parseInt(focusedId);		
			return MsgResponse.success(focusService.focusVerify(userId, focusedIdInt), null);
		}else {
			return MsgResponse.error("参数错误");
		}		
	}
	
	@GetMapping("focusedListByAjax")
	@ResponseBody
	public MsgResponse focusedListByAjax(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null) {
			int userId = user.getUserId();
			List<Integer> userFocusList = focusService.getUserFocusList(userId);
			List<User> focusList= new ArrayList<User>();
			for(Integer focusId:userFocusList) {
				focusList.add(userService.getUserByUserId(focusId));
			}
			
			return MsgResponse.success("success", focusList);
		}else {
			return MsgResponse.error("参数错误");
		}		
	}
}
