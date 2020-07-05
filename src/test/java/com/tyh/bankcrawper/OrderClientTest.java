package com.tyh.bankcrawper;

import com.tyh.bankcrawper.client.OrderClient;
import com.tyh.bankcrawper.entity.Order;
import com.tyh.bankcrawper.util.JinshiConstants;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = BankcrawperApplication.class)
public class OrderClientTest implements JinshiConstants {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private OrderClient orderClient;

    @Test
    public void dataInspectTest() throws InterruptedException {
        // orderClient.dataInspect();
    }
}
