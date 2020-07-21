package com.liucm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="进入欢迎页面",tags = "WelcomeController")
@Controller
@RequestMapping("/")
public class WelcomeController {
	
	@ApiOperation(value = "进入登录页面")
	@GetMapping("/")
	public String Welcome() {
		return "LAR";
	}
}
