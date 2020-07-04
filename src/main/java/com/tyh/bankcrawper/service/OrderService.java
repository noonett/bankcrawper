package com.tyh.bankcrawper.service;

import com.tyh.bankcrawper.dao.OrderMapper;
import com.tyh.bankcrawper.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public int addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        int rows = orderMapper.insertOrder(order);
        return rows;
    }

    public Order findOrderById(int id) {
        return orderMapper.selectOrderById(id);
    }

    public Order findNewestOrder(String bankName, String goodName) {
        return orderMapper.selectNewestOrder(bankName, goodName);
    }
}
