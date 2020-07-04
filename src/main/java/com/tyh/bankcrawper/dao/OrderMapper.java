package com.tyh.bankcrawper.dao;

import com.tyh.bankcrawper.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    Order selectOrderById(int id);
    int insertOrder(Order order);
    Order selectNewestOrder(String bankName, String goodName);
}
