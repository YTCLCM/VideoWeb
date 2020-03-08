package com.liucm.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.liucm.bean.State;
import com.liucm.bean.User;
import com.liucm.bean.Video;
import com.liucm.bean.VideoType;
import com.liucm.config.FilePathConfig;
import com.liucm.service.RecordService;
import com.liucm.service.StateService;
import com.liucm.service.UserService;
import com.liucm.service.VideoService;
import com.liucm.service.VideoTypeService;
import com.liucm.util.MsgResponse;
import com.liucm.util.ThumbnailThread;

@Controller
@RequestMapping("/VideoController")
public class VideoController {

	@Autowired
	private Video video;
	
	@Autowired
	private User user;

	@Autowired
	private VideoService videoService;

	@Autowired
	private VideoTypeService videoTypeService;

	@Autowired
	private UserService userService;

	@Autowired
	private FilePathConfig filePathConfig;

	@Autowired
	private StateService stateService;
	
	@Autowired
	private RecordService recordService;
	
	@RequestMapping("commented")
	@ResponseBody
	public MsgResponse commented(@RequestParam int starNum,@RequestParam int userId,@RequestParam int videoId) {
		return MsgResponse.success(videoService.addComment(starNum, userId, videoId),null);
	}
	

	@RequestMapping("VideoListByAjax")
	@ResponseBody
	public List<Video> getVideoListByAjax(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int curPage = 0;
		if (session.getAttribute("curPage") != null) {
			curPage = Integer.parseInt(session.getAttribute("curPage").toString());
		}
		if (request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		curPage = curPage + 1;
		session.setAttribute("curPage", curPage);
		return videoService.getVideoListByAjax(curPage, 10);
	}

	@RequestMapping("SimpleVideoListByAjax")
	@ResponseBody
	public List<Video> SimpleVideoListByAjax(HttpServletRequest request) {
/*		HttpSession session = request.getSession();
		int SimpleCurPage = 0;
		if (session.getAttribute("SimpleCurPage") != null) {
			SimpleCurPage = Integer.parseInt(session.getAttribute("SimpleCurPage").toString());
		}
		if (request.getParameter("SimpleCurPage") != null) {
			SimpleCurPage = Integer.parseInt(request.getParameter("SimpleCurPage"));
		}
		SimpleCurPage = SimpleCurPage + 1;
		session.setAttribute("SimpleCurPage", SimpleCurPage);*/
		return videoService.getVideoListByAjax(1, 6);
	}

	@RequestMapping("VideoTypeListByAjax")
	@ResponseBody
	public List<VideoType> getVideoTypeListByAjax(HttpServletRequest request) {
		return videoTypeService.getVideoTypeAll();
	}

	@RequestMapping("videoPlay")
	public ModelAndView videoPlay(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String videoId = request.getParameter("videoId");
		Object obj = request.getSession().getAttribute("user");
		if (obj != null && videoId != null && !videoId.equals("")) {
			int videoId2 = Integer.parseInt(videoId);
			//因为要观看要记录，故要增加观看次数
			String result = videoService.addViewSum(videoId2);
			
			Video video = videoService.getVideoByVideoId(videoId2);

			//添加观看记录
			recordService.addRecord(((User)obj).getUserId(), videoId2);
			
	
			mv.addObject(result);
			if (video != null) {
				User user = userService.getUserByUserId(video.getUser().getUserId());
				video.setUser(user);
				VideoType videoType = videoTypeService.getVideoTypeByVideoTypeId(video.getVideoType().getVideoTypeId());
				video.setVideoType(videoType);
				request.getSession().setAttribute("curVideo", video);
			}
		}
		mv.setViewName("videoplay");
		return mv;
	}

	@PostMapping("getVideoByAjax")
	@ResponseBody
	public Video getVideoByAjax(@RequestParam String videoId) {

		return videoService.getVideoByVideoId(Integer.parseInt(videoId));
	}

	@GetMapping("enableVideoListByAjax")
	@ResponseBody
	public MsgResponse enableVideoListByAjax(HttpServletRequest request) {
		User user = request.getSession().getAttribute("user") != null ? (User) request.getSession().getAttribute("user")
				: null;
		if (user != null) {
			return MsgResponse.success("获取上架视频成功",videoService.getEnableVideoByUserId(user.getUserId()));
		} else {
			return MsgResponse.error("当前用户不存在");
		}
	}

	@GetMapping("disableVideoListByAjax")
	@ResponseBody
	public MsgResponse disableVideoListByAjax(HttpServletRequest request) {
		User user = request.getSession().getAttribute("user") != null ? (User) request.getSession().getAttribute("user")
				: null;
		if (user != null) {
			return MsgResponse.success("获取下架视频成功",videoService.getDisableVideoByUserId(user.getUserId()));
		} else {
			return MsgResponse.error("当前用户不存在");
		}
	}

	@GetMapping("verifyingVideoListByAjax")
	@ResponseBody
	public MsgResponse verifyingVideoListByAjax(HttpServletRequest request) {
		User user = request.getSession().getAttribute("user") != null ? (User) request.getSession().getAttribute("user")
				: null;
		if (user != null) {
			return MsgResponse.success("获取正在视频成功",videoService.getVerifyingVideoByUserId(user.getUserId()));
		} else {
			return MsgResponse.error("当前用户不存在");
		}
	}

	@GetMapping("verifiedVideoListByAjax")
	@ResponseBody
	public MsgResponse verifiedVideoListByAjax(HttpServletRequest request) {
		User user = request.getSession().getAttribute("user") != null ? (User) request.getSession().getAttribute("user")
				: null;
		if (user != null) {
			return MsgResponse.success("获取已认证视频成功",videoService.getVerifiedVideoByUserId(user.getUserId()));
		} else {
			return MsgResponse.error("当前用户不存在");
		}
	}

	@GetMapping("verifyFalseVideoListByAjax")
	@ResponseBody
	public MsgResponse verifyFalseVideoListByAjax(HttpServletRequest request) {
		User user = request.getSession().getAttribute("user") != null ? (User) request.getSession().getAttribute("user")
				: null;
		if (user != null) {
			return MsgResponse.success("获取认证失败视频成功",videoService.getVerifyFalseVideoByUserId(user.getUserId()));
		} else {
			return MsgResponse.error("当前用户不存在");
		}
	}

	@RequestMapping("videoRecommend")
	@ResponseBody
	public List<Video> videoRecommend() {
		 List<Video> recommend = videoService.getVideoRecommend(10);
		 return recommend;
	}

	@RequestMapping("upStoreByAjax")
	@ResponseBody
	public String upStoreByAjax(@RequestParam String videoId) {
		videoService.upStore(Integer.parseInt(videoId));
		return "true";
	}

	@RequestMapping("downStoreByAjax")
	@ResponseBody
	public String downStoreByAjax(@RequestParam String videoId) {

		if (videoService.downStore(Integer.parseInt(videoId)) == 1) {
			return "true";
		} else {
			return "true";
		}
	}

	@RequestMapping("deleteVideoByAjax")
	@ResponseBody
	public String deleteVideoByAjax(@RequestParam String videoId) {
		if (videoService.deleteVideo(Integer.parseInt(videoId)) == 1) {
			return "true";
		} else {
			return "true";
		}
	}
	
	
	@RequestMapping("DianZanByAjax")
	@ResponseBody
	public MsgResponse DianZanByAjax(HttpServletRequest request,@RequestParam String videoId) {
		user = (User)request.getSession().getAttribute("user");
		String msg = videoService.addVideoDianZanSum(user,Integer.parseInt(videoId));
		if(msg.equals("点赞成功")) {
			return MsgResponse.success(msg, null);
		}else {
			return MsgResponse.error(msg);
		}
	}
	
	

	@RequestMapping("updateVideoByAjax")
	@ResponseBody
	public String updateVideoByAjax(@RequestParam String videoId, @RequestParam String videoTitle,
			@RequestParam String videoInfo, @RequestParam String videoTypeId) {
		Video video = videoService.getVideoByVideoId(Integer.parseInt(videoId));
		video.setVideoTitle(videoTitle);
		video.setVideoInfo(videoInfo);
		VideoType videoType = new VideoType();
		videoType.setVideoTypeId(Integer.parseInt(videoTypeId));
		video.setVideoType(videoType);
		System.out.println(video.toString());
		try {
			videoService.updateVideo(video);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}

	}

	@PostMapping("videoUpload")
	@ResponseBody
	public MsgResponse videoUpload(@RequestParam("videoFile") MultipartFile videoFile, @RequestParam String videoTitle,
			@RequestParam String videoInfo, @RequestParam String typeName, @RequestParam String userId) {

		if (videoFile != null && !videoFile.isEmpty()) {
			if (!videoFile.getOriginalFilename().contains(".")) {
				return MsgResponse.error("文件格式错误！");
			}

			// 视频的保存路劲
			// 数据库中保存的:文件名=时间戳.后缀,路径=path+文件名（）
			String saveFileName = filePathConfig.getVideoPath() + System.currentTimeMillis()
					+ videoFile.getOriginalFilename().substring(videoFile.getOriginalFilename().lastIndexOf('.'));
			String FileRealPath = filePathConfig.getFileUrl() + saveFileName;// 保存的到本地的URL
			// System.out.println("saveFileName ="+FileRealPath);

			File dest = new File(FileRealPath);
			if (!dest.getParentFile().exists()) {
				// 判断文件父目录是否存在
				dest.getParentFile().mkdirs();
			}

			// 保存文件
			try {
				videoFile.transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			int userIdInt = 0;

			if (userId != null && !userId.equals("")) {

				// 将上传视频的用户的用户ID:userId设置为int类型
				userIdInt = Integer.parseInt(userId);

				// 截图的保存路劲

				String thumbnailUrl = filePathConfig.getImagePath() + System.currentTimeMillis() + ".jpg";
				String thumbnailRealPath = filePathConfig.getFileUrl() + thumbnailUrl;
				// System.out.println("thumbnailRealPath = "+thumbnailRealPath);

				ThumbnailThread thumbnailThread = new ThumbnailThread(FileRealPath, thumbnailRealPath);
				thumbnailThread.start();

				video.setUser(userService.getUserByUserId(userIdInt));
				video.setVideoTitle(videoTitle);
				video.setVideoInfo(videoInfo);
				video.setVideoUrl(saveFileName);
				video.setThumbnailUrl(thumbnailUrl);
				video.setVideoType(videoTypeService.getVideoTypeByTypeName(typeName));
				State state = stateService.getStateByStateId(1);
				video.setVideoState(state);
				videoService.addVideo(video);
			}

		} else {
			return MsgResponse.error("上传失败:文件不存在");
		}
		return MsgResponse.success("上传成功",null);
	}
}
