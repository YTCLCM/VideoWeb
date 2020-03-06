package com.liucm.mailtest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.User;
import com.liucm.dao.UserMapper;
import com.liucm.service.MailService;

public class MailTest extends ApplicationTest{
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserMapper userMapper;

	@Test
	public void TestMail() {
		User user = userMapper.selectOne(9);
		boolean verify = mailService.sendSimpleMail(user.getUserMail(),"找回密码", "尊敬的用户"+user.getUserName()+"你的密码为"+user.getPassword()+"，请及时修改你的密码。");
		if(verify == true) {
			System.out.println("发送成功");
		}else {
			System.out.println("发送失败");
		}
	}

}
