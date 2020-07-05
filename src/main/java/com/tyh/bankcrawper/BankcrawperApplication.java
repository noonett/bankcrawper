package com.tyh.bankcrawper;

import com.tyh.bankcrawper.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BankcrawperApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankcrawperApplication.class, args);
    }

}
