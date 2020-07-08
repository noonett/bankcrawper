package com.tyh.bankcrawper.client;

import com.tyh.bankcrawper.util.JinshiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Date;

@EnableScheduling
@Configuration
public class ScheduleClient implements JinshiConstants {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ScheduleClient.class);

    @Autowired
    private OrderClient orderClient;

    @PostConstruct
    public void ClientInit() throws Exception {
        orderClient.clientInit();
    }

    @Scheduled(fixedDelay = 3600000)
    public void dataInspectTask() throws Exception{
        System.out.println("投行订单监测任务启动: " + new Date());
        try {
            orderClient.dataInspect();
        } catch (Exception e) {
            logger.error("发生异常，开始爬虫重试！");
            dataInspectRetry(RETRY_TIMES);
            throw e;
        }
        System.out.println("投行订单监测任务结束: " + new Date());
    }

    public void dataInspectRetry(int retryTimes) throws Exception {
        while (retryTimes > 0) {
            orderClient.clientInit();
            Thread.sleep(1000);
            orderClient.dataInspect();
            --retryTimes;
        }
    }

}
