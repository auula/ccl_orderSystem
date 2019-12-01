package me.ponyo.order.models;

import me.ponyo.order.utils.DateUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderInfo {
    private Long orderId;

    private String orderNumber;

    private String orderAccount;

    private Timestamp createTime;

    private Byte orderStatus;

    private BigDecimal totalPrice;

    public OrderInfo(String orderNumber, String orderAccount, BigDecimal totalPrice) {
        this.orderNumber = orderNumber;
        this.orderAccount = orderAccount;
        this.totalPrice = totalPrice;
        this.createTime = DateUtil.asDateToTimestamp();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount == null ? null : orderAccount.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}