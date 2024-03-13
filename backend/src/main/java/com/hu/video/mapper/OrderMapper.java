package com.hu.video.mapper;


import com.hu.video.entity.Order;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper {
     //没有支付的话改用户状态
     int updateById(Order order);
     //插入
     int insertOrder(Order order);
     //根据orderId查询所有
     Order querybyid(Long orderId);

     //得到最新的order_id
     Order getNewOrderId();
}
