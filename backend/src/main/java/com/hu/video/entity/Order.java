package com.hu.video.entity;

import java.util.Date;

public class Order {
    private Long orderId;
    private Long userId;
    private double price;
    private Integer userStateId;
    private Date createTime;
    private String alipayId;
    private Date payTime;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", price=" + price +
                ", userStateId=" + userStateId +
                ", createTime=" + createTime +
                ", alipayId='" + alipayId + '\'' +
                ", payTime=" + payTime +
                '}';
    }

    public Order(Long orderId, Long userId, double price, Integer userStateId, Date createTime, String alipayId, Date payTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.price = price;
        this.userStateId = userStateId;
        this.createTime = createTime;
        this.alipayId = alipayId;
        this.payTime = payTime;
    }

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getUserStateId() {
        return userStateId;
    }

    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
