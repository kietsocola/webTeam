package com.myProject.webteam.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myProject.webteam.services.PointService;

@Component
public class OverdueTaskScheduler {
	@Autowired
	private PointService pointService; 
	// Chạy mỗi ngày lúc 00:00
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkAndDeductPoints() {
        pointService.deductPointsForOverdueTasks();
    }
}
