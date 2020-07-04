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
    public void addOrderTest() {
        List<Order> orders = new ArrayList<>();
        webDriver.get("https://datacenter.jin10.com/banks_orders");
        webDriver.findElement(By.xpath("//div[@id='dcVideoClose']")).click();
        WebElement path = webDriver.findElement(By.xpath(".//ul[@class='banks-list clearfixed']"));
        int size = path.findElements(By.xpath(".//div[@class='name pt']")).size() * 2;
        Order order = new Order();
        for (int i = 2; i < size + 2; ++i) {
            String parrentPath = "./li[" + i + "]";
            if (i % 2 == 0) {
                order = new Order();

                order.setGoodName(path.findElement(By.xpath(parrentPath + "/div[2]/a")).getText());
                order.setMarketPrice(Double.valueOf(path.findElement(By.xpath(parrentPath + "/div[7]/span/span")).getText()));
                order.setBankName(path.findElement(By.xpath(parrentPath + "/div[1]/span[2]")).getText());
                order.setOpenPrice(Double.valueOf(path.findElement(By.xpath(parrentPath + "/div[4]")).getText()));

                // 判断是否有目标价格
                String targetPrice = path.findElement(By.xpath(parrentPath + "/div[5]")).getText();
                if (!targetPrice.equals("-")) {
                    order.setTargetPrice(Double.valueOf(targetPrice));
                }
                order.setProtectPrice(Double.valueOf(path.findElement(By.xpath(parrentPath + "/div[6]")).getText()));

                // 判断交易类型
                String transaction = path.findElement(By.xpath(parrentPath + "/div[3]/span[1]")).getText();
                if (transaction.equals("买")) {
                    order.setTransactionType(TRANSACTION_BUY);
                } else if (transaction.equals("卖")) {
                    order.setTransactionType(TRANSACTION_SELL);
                }
                order.setCreateTime(new Date());
                path.findElement(By.xpath(parrentPath + "/div[1]/span[1]/i")).click();
            } else {
                order.setTradeLogic(path.findElement(By.xpath(parrentPath + "/div[3]/p")).getText());
                order.setTradeHistory(path.findElement(By.xpath(parrentPath + "/div[5]/p")).getText());
                orders.add(order);
            }
        }
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    @Test
    public void clientInitTest() {
        orderClient.clientInit();
    }

    @Test
    public void dataInspectTest() throws InterruptedException {
        orderClient.dataInspect();
    }
}
