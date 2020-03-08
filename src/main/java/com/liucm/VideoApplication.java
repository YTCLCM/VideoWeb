package com.liucm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
@MapperScan(basePackages="com.liucm.dao")
public class VideoApplication {
	public static void main(String[] args) {
		SpringApplication.run(VideoApplication.class, args);
	}
}
