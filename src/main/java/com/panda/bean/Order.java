package com.panda.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: 青铜
 * @create: 2018-07-10
 **/
public class Order {

    private String orderId;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
