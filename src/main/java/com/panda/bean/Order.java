package com.panda.bean;

import java.util.Date;

/**
 * @author: 青铜
 * @create: 2018-07-10
 **/
public class Order {

    private String order;

    private Date createTime;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
