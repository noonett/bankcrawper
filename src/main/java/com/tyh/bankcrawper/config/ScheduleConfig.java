package com.tyh.bankcrawper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Date;

// @EnableScheduling
@Configuration
public class ScheduleConfig {

    // @Scheduled(cron = "0/5 * * * * ?")
    public void ScheduleTask1() {
        System.out.println("执行静态定时任务时间: " + new Date());
    }
}
