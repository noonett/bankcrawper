package com.tyh.bankcrawper.client;

import com.tyh.bankcrawper.config.WebDriverConfig;
import com.tyh.bankcrawper.entity.Order;
import com.tyh.bankcrawper.service.OrderService;
import com.tyh.bankcrawper.util.JinshiConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class OrderClient implements JinshiConstants {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private OrderService orderService;


    public void clientInit(){
        webDriver.get("https://datacenter.jin10.com/banks_orders");
        System.out.println("已打开页面！");
        while(!webDriver.findElement(By.xpath("//div[@id='dcVideoClose']")).isDisplayed()){
        }
        webDriver.findElement(By.xpath("//div[@id='dcVideoClose']")).click();
        System.out.println("已关闭弹窗！");
    }

    /**
     * 订单爬虫客户端的初始化
     * 访问JinShi数据的投行订单页面，将现有的数据存入数据库。
     */
    public void instoreFirstTimeData() {
        try {
            webDriver.get("https://datacenter.jin10.com/banks_orders");
            Thread.sleep(20);
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
                    orderService.addOrder(order);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            webDriver.close();
        }
    }

    /**
     * 数据变化监控，看是否有新的交易历史出现
     */
    public void dataInspect() throws InterruptedException {
        System.out.println("开始爬取数据！");
        WebElement path = webDriver.findElement(By.xpath(".//ul[@class='banks-list clearfixed']"));
        int size = path.findElements(By.xpath(".//div[@class='name pt']")).size() * 2;
        String bankName = "";
        String goodName = "";
        for (int i = 2; i < size + 2; ++i) {
            String parrentPath = "./li[" + i + "]";
            if (i % 2 == 0) {
                bankName = path.findElement(By.xpath(parrentPath + "/div[1]/span[2]")).getText();
                goodName = path.findElement(By.xpath(parrentPath + "/div[2]/a")).getText();
                path.findElement(By.xpath(parrentPath + "/div[1]/span[1]/i")).click();
            } else {
                String latestHistory = path.findElement(By.xpath(parrentPath + "/div[5]/p")).getText();
                if(latestHistory == null || latestHistory.length() < 12){
                    continue;
                }
                String preHistory = orderService.findNewestOrder(bankName, goodName).getTradeHistory();
                String time1 = latestHistory.substring(0, 12);; String time2 = "";
                if(preHistory != null && preHistory.length() > 12){
                    time2 = preHistory.substring(0, 12);
                }
                if (!time1.equals(time2)) {
                    System.out.println("有新的订单产生： " + latestHistory);
                    // 保存对应的订单信息
                    orderService.addOrder(getSpecificOrder(i));
                    // 发送消息通知
                    NoticeClient.get
                            ("SCU103655Tcf47996aea9cabd3041e7802d6aa50c45efc16c341d21", "新投行订单",bankName + ":  "+ latestHistory );
                    System.out.println("已发送消息提醒！");
                }
            }
        }
    }

    /**
     * 获取特定银行订单
     */
    public Order getSpecificOrder(int i) {
        // webDriver.navigate().refresh();
        WebElement path = webDriver.findElement(By.xpath(".//ul[@class='banks-list clearfixed']"));
        String tradePath = "./li[" + i + "]";
        --i;
        String parrentPath = "./li[" + i + "]";
        Order order = new Order();

        path.findElement(By.xpath(parrentPath + "/div[1]/span[1]/i")).click();
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
        order.setTradeLogic(path.findElement(By.xpath(tradePath + "/div[3]/p")).getText());
        order.setTradeHistory(path.findElement(By.xpath(tradePath + "/div[5]/p")).getText());
        webDriver.close();
        return order;
    }
}
