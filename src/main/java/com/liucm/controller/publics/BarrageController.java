package com.liucm.controller.publics;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucm.bean.User;
import com.liucm.socketserver.BarrageWebSocket;

@Controller
@RequestMapping("/BarrageController")
public class BarrageController {
	
    
    @ResponseBody
    @PostMapping("/sendGroupMessage/{videoId}")
    public Map<String, Object> groupMessage(HttpServletRequest request,@PathVariable Integer videoId,@RequestParam String message) {
        Map<String, Object> result = new HashMap<>();        
        User sendUser = (User)request.getSession().getAttribute("user");
        if(sendUser != null && !message.equals("")) {
        	//接收消息message，进行组发
        	BarrageWebSocket.sendGroupMessage(sendUser.getUserName(),videoId,message);
     		result.put("code", 200);
     		result.put("msg", "success");
        }   
		result.put("code", 500);
		result.put("msg", "error");
        return result;
    }
}
