package com.liucm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomeController {
	
	@RequestMapping("/welcome")
	public String Welcome() {
		return "LAR";
	}
}
