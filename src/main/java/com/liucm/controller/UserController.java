package com.liucm.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liucm.bean.User;
import com.liucm.config.FilePathConfig;
import com.liucm.service.UserService;

@Controller
@RequestMapping("/UserController")
public class UserController {

	@Autowired
	private User user;

	@Autowired
	private UserService userService;

	@Autowired
	private FilePathConfig filePathConfig;

	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = userService.verifyUser(userName, password);
		if (null != user) {
			request.getSession().setAttribute("user", user);
			// return "redirect:Role";
			if(user.getUserRole().getUserRole().equals("admin")) {
				return "index";
			}
			else if(user.getUserRole().getUserRole().equals("public")){
				return "index";
			}
			else if(user.getUserRole().getUserRole().equals("restrict")){
				return "index";
			}
			
		}
		return "LAR";
	}

	@PostMapping("/register")
	@ResponseBody
	public boolean register(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		return userService.addUser(userName, password);
	}

	@PostMapping("/forget")
	@ResponseBody
	public boolean forget(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		Object attribute = request.getSession().getAttribute("user");
		if (attribute != null) {
			userName = ((User) attribute).getUserName();
		}
		return userService.forget(userName);
	}

	/**
	 * 修改密码
	 * @param request
	 * @param oldpswd
	 * @param newpswd
	 * @return
	 */
	@PostMapping("/passwordUpdate")
	@ResponseBody
	public String passwordUpdate(HttpServletRequest request, @RequestParam String oldpswd,
			@RequestParam String newpswd) {
		User user = request.getSession().getAttribute("user") != null ? (User) request.getSession().getAttribute("user")
				: null;
		return userService.updatePswd(user, oldpswd, newpswd);
		
	}

	/**
	 * 用于更改用户的信息
	 * 
	 * @param request
	 * @param userName
	 * @param userAge
	 * @param userSex
	 * @param userMail
	 * @param userPhone
	 * @param userAddress
	 * @param iconFile
	 * @return
	 */
	@RequestMapping(value = "/userInfoUpdate", method = RequestMethod.POST)
	public String userInfoUpdate(HttpServletRequest request,
			@RequestParam String userAge, @RequestParam String userSex, @RequestParam String userMail,
			@RequestParam String userPhone, @RequestParam String userAddress,
			@RequestParam("iconFile") MultipartFile iconFile) {

		String saveFileName = "";
		String FileRealPath = "";
		boolean signal = false;
		if (iconFile != null && !iconFile.isEmpty()) {
			if (!iconFile.getOriginalFilename().contains(".")) {
				return "redirect:/RedirectController/UserInformationRedirect";
			}
			// 图片的保存路劲
			// 数据库中保存的:文件名=时间戳.后缀,路径=path+文件名（）
			saveFileName = filePathConfig.getImagePath() + System.currentTimeMillis()
					+ iconFile.getOriginalFilename().substring(iconFile.getOriginalFilename().lastIndexOf('.'));
			FileRealPath = filePathConfig.getFileUrl() + saveFileName;// 保存的到本地的URL
			File dest = new File(FileRealPath);
			if (!dest.getParentFile().exists()) {
				// 判断文件父目录是否存在
				dest.getParentFile().mkdirs();
			}
			try {
				iconFile.transferTo(dest);
				signal = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			Object obj = request.getSession().getAttribute("user");

			/**
			 * 判断上传回来的数据是否还是之前为改变的数据，如果是，不更新，不是，就更新
			 */
			if (obj != null) {
				this.user = (User) obj;
			}
//			if (!userName.equals(user.getUserName()))
//				user.setUserName(userName);
			if (userAge != null) {
				if (Integer.parseInt(userAge) != user.getUserAge())
					user.setUserAge(Integer.parseInt(userAge));
			}
			if (!userSex.equals(user.getUserSex()))
				user.setUserSex(userSex);
			if (!userMail.equals(user.getUserMail()))
				user.setUserMail(userMail);
			if (!userPhone.equals(user.getUserPhone()))
				user.setUserPhone(userPhone);
			if (!userAddress.equals(user.getUserAddress()))
				user.setUserAddress(userAddress);
			if (signal)
				user.setIconUrl(saveFileName);
			userService.updateUser(user);
			request.getSession().setAttribute("user", user);
			return "redirect:/RedirectController/UserInformationRedirect";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/RedirectController/UserInformationRedirect";
	}

}
