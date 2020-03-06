package com.liucm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.VideoType;
import com.liucm.service.VideoTypeService;

@Controller
@RequestMapping(value="/VideoTypeController")
public class VideoTypeController {

	@Autowired
	private VideoTypeService videoTypeService;
	
	@PostMapping("getAllVideoTypeByAjax")
	@ResponseBody
	public List<VideoType> getAllVideoTypeByAjax(){
		List<VideoType> videoTypeAll = videoTypeService.getVideoTypeAll();
		return videoTypeAll;
	}
}
