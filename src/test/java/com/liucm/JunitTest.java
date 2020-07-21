package com.liucm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JunitTest {
	@Before
	public void init() {
		System.out.println("开始测试-----------------");
	}

	@After
	public void after() {
		System.out.println("测试结束-----------------");
	}
	
	@Test
	public void test() {
		System.out.println("测试中-----------------");
	}
}
