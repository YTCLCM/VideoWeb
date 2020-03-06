package com.liucm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan(basePackages="com.liucm.dao")
public class VideoApplication {
	public static void main(String[] args) {
		SpringApplication.run(VideoApplication.class, args);
	}
}
