package com.liucm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.VideoType;
import com.liucm.service.VideoTypeService;
import com.liucm.util.MsgResponse;

@Controller
@RequestMapping("/AdminVideoTypeController")
public class AdminVideoTypeController {
	
	@Autowired
	private VideoTypeService videoTypeService;
	
	@GetMapping("/getVideoTypeByAjax")
	@ResponseBody
	public MsgResponse getVideoTypeByAjax() {
		return MsgResponse.success("查询成功", videoTypeService.getVideoTypeAll());
	}
	
	@PostMapping("/addVideoTypByAjax")
	@ResponseBody
	public MsgResponse addVideoTypByAjax(@RequestParam String videoTypeName) {
		VideoType type = videoTypeService.getVideoTypeByTypeName(videoTypeName.trim());
		if(type == null) {
			return MsgResponse.success(videoTypeService.addVideoType(videoTypeName), null);
		}else {
			return MsgResponse.success("类型数据已存在", null);
		}
	}
	
	@PostMapping("/deleteVideoTypeByAjax")
	@ResponseBody
	public MsgResponse deleteVideoTypeByAjax(@RequestParam int videoTypeId) {
		return MsgResponse.success(videoTypeService.deleteVideoType(videoTypeId), null);
	}
}
