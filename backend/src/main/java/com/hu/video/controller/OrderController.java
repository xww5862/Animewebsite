package com.hu.video.controller;

import com.hu.video.entity.Order;
import com.hu.video.entity.TUser;
import com.hu.video.service.IOrderService;


import com.hu.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

    @RequestMapping("getNewOrderId")
    public Order getNewOrderId(){
        Order orders = orderService.getNewOrderId();
        return orders;
    }

    @RequestMapping("getNowStateId")
    public TUser getNowStateId(Long userId){
        return userService.getNowStateId(userId);
    }
}
