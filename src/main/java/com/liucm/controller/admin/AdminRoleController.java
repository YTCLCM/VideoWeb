package com.liucm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.service.UserRoleService;
import com.liucm.util.MsgResponse;

@Controller
@RequestMapping("/AdminRoleController")
public class AdminRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping("/getRoleByAjax")
	@ResponseBody
	public MsgResponse getRoleByAjax() {
		return MsgResponse.success("查询成功", userRoleService.getAllRole());
	}
	
	@PostMapping("/addRoleByAjax")
	@ResponseBody
	public MsgResponse addRoleByAjax(@RequestParam String userRoleName) {
		boolean exist = userRoleService.verifyexist(userRoleName);
		if(exist == true) {
			return MsgResponse.success("角色类型已存在", null);
		}else {
			return MsgResponse.success(userRoleService.addRole(userRoleName), null);
		}
	}
	
	@PostMapping("/deleteRoleByAjax")
	@ResponseBody
	public MsgResponse deleteRoleByAjax(@RequestParam int roleId) {
		
		return MsgResponse.success(userRoleService.deleteRoleByRoleId(roleId), null);
	}
}
