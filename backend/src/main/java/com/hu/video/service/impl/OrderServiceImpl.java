package com.hu.video.service.impl;

import com.hu.video.entity.Order;
import com.hu.video.mapper.OrderMapper;
import com.hu.video.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int updateById(Order order) {
        return orderMapper.updateById(order);
    }

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public Order querybyid(Long orderId) {
        return orderMapper.querybyid(orderId);
    }

    @Override
    public Order getNewOrderId() {
        return orderMapper.getNewOrderId();
    }
}
