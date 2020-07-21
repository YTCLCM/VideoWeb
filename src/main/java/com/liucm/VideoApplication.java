package com.liucm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JOptionPane;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
//扫描过滤器
@ServletComponentScan(basePackages = "com.liucm.filter")
@MapperScan(basePackages = "com.liucm.dao")
public class VideoApplication {

	public static void main(String[] args) {

		// 判断是否要去运行无网的程序
		boolean run = isInterntAble();

		if (run == false) {
			int runAble = JOptionPane.showConfirmDialog(null, "[Error] 连接网络失败，无法使用涉及到网络的功能\n[Info] 未连接到网络，是否继续？", "提示",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (runAble == 0) {
				run = true;
			} else if (runAble == 1 || runAble == 2) {
				run = false;
			}
		}
		if (run == true) {
			SpringApplication.run(VideoApplication.class, args);
		} else {
			JOptionPane.showMessageDialog(null, "[Info] 退出运行程序", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static boolean isInterntAble() {
		URL url = null;
		InputStream in = null;
		try {
			url = new URL("http://www.baidu.com");
			in = url.openStream();
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				System.out.println("[Error] 网络验证关闭失败");
			}
		}
	}

}
