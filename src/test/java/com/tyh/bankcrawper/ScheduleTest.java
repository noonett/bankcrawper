package com.tyh.bankcrawper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

@Configuration
// @EnableScheduling
public class ScheduleTest {

    // @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleTest(){
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
