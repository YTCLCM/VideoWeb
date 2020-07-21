package com.liucm.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.Message;
import com.liucm.bean.MsgType;
import com.liucm.bean.State;
import com.liucm.bean.User;
import com.liucm.bean.Video;
import com.liucm.service.MsgService;
import com.liucm.service.MsgTypeService;
import com.liucm.service.StateService;
import com.liucm.service.VideoService;
import com.liucm.util.MsgResponse;
import com.liucm.util.Page;
import com.liucm.util.PageSize;

@Controller
@RequestMapping("/AdminVideoController")
public class AdminVideoController {
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private PageSize pageSize;
	
	@Autowired
	private StateService ststeService;
	
	@Autowired
	private MsgTypeService msgTypeService;
	
	@GetMapping("/getVideoByAjax")
	@ResponseBody
	public Page<Video> getVideoByAjax(@RequestParam int curPage) {
		Page<Video> page = new Page<>(curPage,pageSize.getPageSize());
		page.setMessage(videoService.getVideoByPage(page));
		return page;
	}
	
	@PostMapping("/verifyVideoByAjax")
	@ResponseBody
	public MsgResponse verifyVideoByAjax(@RequestParam int videoId,@RequestParam int stateId,@RequestParam String verifyText) {
		Video video = videoService.getVideoByVideoId(videoId);
		User user = video.getUser();
		videoService.verifyVideo(videoId, stateId);
		
		Message msg = new Message();
		if(stateId == 2) {
			msg.setMsgContext("你的视频《"+video.getVideoTitle()+"》已经通过审核");
		}else if(stateId == 3) {
			msg.setMsgContext("你的视频《"+video.getVideoTitle()+"》未通过审核,"+verifyText+"。请重新对内容进行调整");
		}else {
			msg.setMsgContext("你的视频《"+video.getVideoTitle()+"》等待进一步审核，"+verifyText);
		}
		
		State msgState = ststeService.getStateByStateId(6);
		msg.setMsgState(msgState);
		msg.setMsgTitle("系统通知");
		
		MsgType msgType = msgTypeService.findOneBymsgTypeId(2);
		msg.setMsgType(msgType);
		msg.setReceiveUser(user);
		msgService.addMsg(msg);
		return MsgResponse.success("操作成功", null);
	}
	
	@GetMapping("/getVideoByVideoId")
	@ResponseBody
	public MsgResponse getVideoByVideoId(@RequestParam int videoId) {
		return MsgResponse.success("success", videoService.getVideoByVideoId(videoId));
	}
	
	@PostMapping("/updateVideoByAjax")
	@ResponseBody
	public MsgResponse updateVideoByAjax() {
		return null;
	}
	
	@PostMapping("/deleteVideoByAjax")
	@ResponseBody
	public MsgResponse deleteVideoByAjax(@RequestParam int videoId) {
		return MsgResponse.success(videoService.deleteVideo(videoId),null);
	}
}
