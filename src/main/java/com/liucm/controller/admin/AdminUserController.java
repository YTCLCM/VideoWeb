package com.liucm.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.State;
import com.liucm.bean.User;
import com.liucm.bean.UserRole;
import com.liucm.service.StateService;
import com.liucm.service.UserRoleService;
import com.liucm.service.UserService;
import com.liucm.util.MsgResponse;
import com.liucm.util.Page;
import com.liucm.util.PageSize;

@Controller
@RequestMapping("/AdminUserController")
public class AdminUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private PageSize pageSize;
	
	@Autowired
	private StateService stateService;
	
	@GetMapping("/getUserByAjax")
	@ResponseBody
	public Page<User> getUserByAjax(@RequestParam int curPage) {
		//,@RequestParam String action
		Page<User> page = new Page<>(curPage,pageSize.getPageSize());
		page.setMessage(userService.getUserByPage(page));		
		return page;
	}
	
	@GetMapping("/getUserByUserId")
	@ResponseBody
	public MsgResponse getUserByUserId(@RequestParam int userId) {
		User user = userService.getUserByUserId(userId);
		if(user != null) {
			return MsgResponse.success("查询成功", user);
		}else {
			return MsgResponse.error("查询失败");
		}
	}
	
	@PostMapping("/addUser")
	@ResponseBody
	public MsgResponse addUserByAjax(@RequestParam String userMail,@RequestParam String password,@RequestParam String userName,@RequestParam String userRole,
			@RequestParam String stateName) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserMail(userMail);
		user.setUserAddress(null);
		user.setUserPhone(null);
		user.setIconUrl("\\img\\defaulticon.jpg");
		UserRole role = userRoleService.getOneByRoleName(userRole);
		if(role != null && role.getUserRole().equals(userRole)) {
			user.setUserRole(role);
		}else {
			user.setUserRole(userRoleService.getOneByRoleName("public"));
		}
		List<State> states = stateService.getAllState();
		for(State state:states) {
			if(state.getStateName().equals(stateName)) {
				user.setState(state);
			}
		}
		boolean sign = userService.addUser(user);
		if(sign == true)
			return MsgResponse.success("用户添加成功", null);
		else
			return MsgResponse.error("用户添加失败");
	}

	@PostMapping("/updateUserByAjax")
	@ResponseBody
	public MsgResponse updateUserByAjax(HttpServletRequest request,@RequestParam int userId,@RequestParam int userAge,@RequestParam String userSex,
			@RequestParam String userMail,@RequestParam String userPhone,@RequestParam String userAddress,@RequestParam String userRole,
			@RequestParam String stateName) {
		User user = userService.getUserByUserId(userId);
		user.setUserAge(userAge);
		user.setUserSex(userSex);
		user.setUserMail(userMail);
		user.setUserPhone(userPhone);
		user.setUserAddress(userAddress);
		List<UserRole> roles = userRoleService.getAllRole();
		for(UserRole role:roles) {
			if(role.getUserRole().equals(userRole)) {
				user.setUserRole(role);
			}
		}
		List<State> states = stateService.getAllState();
		for(State state:states) {
			if(state.getStateName().equals(stateName)) {
				user.setState(state);
			}
		}
		boolean bool = userService.updateUser(user);
		if(bool == true) {
			HttpSession session = request.getSession();
			User currentUser = (User)session.getAttribute("user");
			if(currentUser.getUserName().equals(user.getUserName()) && currentUser.getUserRole().getUserRole().equals(userRole)) {
				session.removeAttribute("user");
			}
			return MsgResponse.success("用户更新成功", null);
		}
		else
			return MsgResponse.error("用户更新失败");
	}
	
	@PostMapping("/deleteUserByAjax")
	@ResponseBody
	public MsgResponse deleteUserByAjax(@RequestParam int userId) {
		return MsgResponse.success(userService.delUser(userId),null);
	}
}
