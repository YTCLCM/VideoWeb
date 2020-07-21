package com.liucm.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.util.SVDAlgorithm;

@RequestMapping("/a")
@Controller
public class CommentStarController {
	
	@Autowired
	private SVDAlgorithm algorithm;
	
	@GetMapping("b")
	@ResponseBody
	public boolean getRecommentedVideo() {
		algorithm.start();
		return true;
	}
}
