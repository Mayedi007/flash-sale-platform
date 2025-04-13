package com.flashsaleproject.service.model;

import java.math.BigDecimal;

public class OrderModel {
    //Order number, e.g. 2019052100001212, using string type

    private String id;

    private Integer userId;

    private Integer itemId;

    //If it is not empty, it means that the order is placed as a second order.
    private Integer promoId;

    //The unit price of the product at the time of purchase, if promoId is not empty, it means that the order is placed in the form of seconds.
    private BigDecimal itemPrice;

    private  Integer amount;

    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}
