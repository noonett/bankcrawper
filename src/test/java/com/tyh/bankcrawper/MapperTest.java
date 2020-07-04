package com.tyh.bankcrawper;

import com.tyh.bankcrawper.dao.OrderMapper;
import com.tyh.bankcrawper.entity.Order;
import com.tyh.bankcrawper.util.JinshiConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = BankcrawperApplication.class)
public class MapperTest implements JinshiConstants {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrderTest() {
        Order order = new Order();
        order.setGoodName("原谅基金");
        order.setMarketPrice(6.66);
        order.setBankName("测试银行");
        order.setOpenPrice(5.55555);
        order.setTargetPrice(11.111);
        order.setProtectPrice(3.3);
        order.setTransactionType(TRANSACTION_BUY);
        order.setCreateTime(new Date());
        order.setTradeLogic("就是富");
        order.setTradeHistory("今天买了1个亿");
        orderMapper.insertOrder(order);
        System.out.println(order);
    }

    @Test
    public void selectNewestOrderTest() {
        System.out.println(orderMapper.selectNewestOrder("道明银行","纽元/美元"));;
    }
}
