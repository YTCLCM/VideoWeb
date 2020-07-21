package com.liucm.socketserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@ServerEndpoint("/BarrageWebSocket")
@Component
@Slf4j
public class BarrageWebSocket {

	private Session session;
	private Integer videoId = null;
	//系统连接数
	private static int connectCount = 0;
	// 记录观看同一视频的人数
	private static Map<Integer, Integer> videoCount = new ConcurrentHashMap<>();
	//实时弹幕注册中心
	private static Map<Integer, CopyOnWriteArraySet<BarrageWebSocket>> barrageRegister = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		//localhost/BarrageWebSocket?videoId=123&userName=abc
		Map<String, Object> param = new HashMap<String, Object>();
		for (String str : this.session.getQueryString().split("&")) {
			String[] split = str.split("=");
			param.put(split[0], split[1]);
		}
		try {
			this.videoId = (Integer) Integer.parseInt((String) param.get("videoId"));
			// 不为空直接放入,为空新建CopyOnWriteArraySet<BarrageWebSocket> svu，再放入
			if (barrageRegister.get(videoId) != null) {
				barrageRegister.get(videoId).add(this);
			} else {
				CopyOnWriteArraySet<BarrageWebSocket> svu = new CopyOnWriteArraySet<>();
				svu.add(this);
				barrageRegister.put(this.videoId, svu);
			}
			// 记录链接数
			BarrageWebSocket.addConnectCount();
			BarrageWebSocket.addVideoCount(videoId);
			// 反馈用户
			this.sendMessage("你可以发送弹幕了，一大波弹幕因你而来！");
			this.sendMessage("当前有" + videoCount.get(videoId) + "个人正在观看此视频");
			log.info("有用户正在观看视频{" + videoId + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		Integer videoId = this.videoId;
		BarrageWebSocket.subConnectCount();
		BarrageWebSocket.subVideoCount(videoId);
		if (barrageRegister.get(videoId) != null) {
			barrageRegister.get(videoId).remove(this);
			int size = barrageRegister.get(videoId).size();
			log.info("有用户退出观看视频{" + videoId + "}");
			if (size == 0) {
				barrageRegister.remove(videoId);
				log.info("视频{" + videoId + "}Socket已经移除");
			}
		}
	}

	/**
	 * socket收到客户端消息，给予反馈
	 * @param message 客户端发来的信息
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			this.sendMessage("接受到你的消息，你现在观看的视频videoId = " + this.videoId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	/**
	 * sendMessage向特定用户发送内容
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * sendGroupMessage完成的是组发内容
	 * 
	 * @param userName 正在观看视频的用户名
	 * @param videoId  map的key，标记着一个视频
	 * @param message  要发送的信息
	 */
	public static void sendGroupMessage(String userName, Integer videoId, String message) {
		CopyOnWriteArraySet<BarrageWebSocket> userGroup = barrageRegister.get(videoId);
		if (userGroup != null) {
			log.info("用户{" + userName + "}在观看视频{" + videoId + "}时，组发了message{" + message + "}");
			for (BarrageWebSocket bws : userGroup) {
				try {
					bws.sendMessage(userName + ":" + message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			log.info("不可发送消息，因为已经没有用户观看该视频{" + videoId + "}");
		}
	}

	/**
	 * 增加观看同一视频的用户数
	 * @param videoId
	 */
	public static void addVideoCount(Integer videoId) {
		if (videoCount.get(videoId) != null) {
			Integer countTemp = videoCount.get(videoId) + 1;
			videoCount.replace(videoId, countTemp);
		} else {
			videoCount.put(videoId, 1);
		}
		log.info("视频{" + videoId + "}观看人数增加了，现在为" + videoCount.get(videoId));
	}

	/**
	 * 减少观看同一视频的用户数
	 * @param videoId
	 */
	public static void subVideoCount(Integer videoId) {
		if (videoCount.get(videoId) != null) {
			Integer countTemp = videoCount.get(videoId) - 1;
			if (countTemp > 0) {
				videoCount.replace(videoId, countTemp);
				log.info("视频{" + videoId + "}观看人数减少了，现在为" + videoCount.get(videoId));
			} else {
				videoCount.remove(videoId);
				log.info("视频{" + videoId + "}已无人观看了");
			}
		}
	}

	/**
	 * 增加实时弹幕总的连接数
	 */
	public static synchronized void addConnectCount() {
		BarrageWebSocket.connectCount++;
		log.info("当前连接数增加了，现在为" + BarrageWebSocket.getConnectCount());
	}

	/**
	 * 减少视频弹幕总的连接数
	 */
	public static synchronized void subConnectCount() {
		if (BarrageWebSocket.getConnectCount() > 0) {
			BarrageWebSocket.connectCount--;
			log.info("当前连接数减少了，现在为" + BarrageWebSocket.getConnectCount());
		}
	}

	/**
	 * 获得实时弹幕系统此时总的连接数
	 * @return connectCount
	 */
	public static synchronized int getConnectCount() {
		return BarrageWebSocket.connectCount;
	}
}
