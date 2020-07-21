package com.liucm.controller.publics;

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
import com.liucm.service.CollectionService;
import com.liucm.util.MsgResponse;

@RequestMapping("/CollectionController")
@Controller
public class CollectionController {
	@Autowired
	private CollectionService collectionService;
	
	@GetMapping("getAllCollectionByAjax")
	@ResponseBody
	public MsgResponse getAllCollectionByAjax(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("user");
		if(obj != null) {
			User user = (User) obj;
			List<Video> collection = collectionService.getAllCollection(user.getUserId());
			if(collection != null) {
				return MsgResponse.success("获取收藏列表成功", collection);
			}
			else {
				return MsgResponse.error("获取收藏列表失败");
			}
		}else {
			return MsgResponse.error("当前用户不存在，请登录！");
		}
		
	}
	
	@PostMapping("removeCollectionByAjax")
	@ResponseBody
	public MsgResponse removeCollectionByAjax(HttpServletRequest request,@RequestParam String videoId) {
		Object obj = request.getSession().getAttribute("user");		    
		if(obj != null && videoId.matches("[0-9]*")) {
			return MsgResponse.success(collectionService.deleteCollection(((User)obj).getUserId(), Integer.parseInt(videoId)),null);
		}else {
			return MsgResponse.error("用户未登录或视频ID错误");
		}
	}
	
	@PostMapping("addCollectionByAjax")
	@ResponseBody
	public MsgResponse addCollectionByAjax(HttpServletRequest request,@RequestParam String videoId) {
		Object obj = request.getSession().getAttribute("user");		    
		if(obj != null && videoId.matches("[0-9]*")) {
			return MsgResponse.success(collectionService.addCollection(((User)obj).getUserId(), Integer.parseInt(videoId)),null);
		}else {
			return MsgResponse.error("用户未登录或视频ID错误");
		}
	}
	
	@PostMapping("vertifyIfCollectionByAjax")
	@ResponseBody
	public MsgResponse vertifyIfCollectionByAjax(HttpServletRequest request,@RequestParam String videoId) {
		Object obj = request.getSession().getAttribute("user");		    
		if(obj != null && videoId.matches("[0-9]*")) {
			return MsgResponse.success(collectionService.vertifyIfCollection(((User)obj).getUserId(), Integer.parseInt(videoId)),null);
		}else {
			return MsgResponse.error("用户未登录或视频ID错误");
		}
	}
	
}
