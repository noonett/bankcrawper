package com.tyh.bankcrawper.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Date;

@EnableScheduling
@Configuration
public class ScheduleClient {

    @Autowired
    private OrderClient orderClient;

    @PostConstruct
    public void ClientInit(){
        orderClient.clientInit();
    }

    @Scheduled(fixedDelay=3600000)
    public void dataInspectTask() {
        System.out.println("投行订单监测任务启动: " + new Date());
        try {
            orderClient.dataInspect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("投行订单监测任务结束: " + new Date());
    }

}
