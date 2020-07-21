package com.liucm.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.liucm.util.DateUtil;
import com.liucm.util.SVDAlgorithm;
@Component
public class SVDSchedule {
	
	@Autowired
	private SVDAlgorithm algorithm;
	
	@Scheduled(cron = "0 0/10 * * * ?")
	public void recommendAlgorithmSchedule() {
		DateUtil dateUtil = new DateUtil();
		String formatDate = dateUtil.getFormatDate(null);
		System.out.println(formatDate);
		algorithm.start();		
	}
}
