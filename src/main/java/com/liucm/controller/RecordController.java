package com.liucm.controller;

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
import com.liucm.bean.Video;
import com.liucm.service.RecordService;
import com.liucm.util.MsgResponse;

@RequestMapping("RecordController")
@Controller
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	
	@GetMapping("getAllRecordByAjax")
	@ResponseBody
	public MsgResponse getAllRecordByAjax(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("user");
		if(obj != null) {
			User user = (User) obj;
			List<Video> records = recordService.getAllRecord(user.getUserId());
			if(records != null) {
				return MsgResponse.success("获取记录列表成功", records);
			}
			else {
				return MsgResponse.error("获取记录列表失败");
			}
		}else {
			return MsgResponse.error("当前用户不存在，请登录！");
		}
	}
	
	
	
	@PostMapping("removeRecordByAjax")
	@ResponseBody
	public MsgResponse removeRecordByAjax(HttpServletRequest request,@RequestParam String videoId) {
		Object obj = request.getSession().getAttribute("user");		    
		if(obj != null && videoId.matches("[0-9]*")) {
			return MsgResponse.success(recordService.deleteRecord(((User)obj).getUserId(), Integer.parseInt(videoId)),null);
		}else {
			return MsgResponse.error("用户未登录或视频ID错误");
		}
	}
}
