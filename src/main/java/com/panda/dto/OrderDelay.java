package com.panda.dto;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author: 青铜
 * @create: 2018-06-02
 **/
public class OrderDelay implements Delayed {

    private String orderId;

    private long timeout;

    public OrderDelay(String orderId,long timeout){
        this.orderId = orderId;
        this.timeout = timeout + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((timeout - System.nanoTime()),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public static void main(String[] args) {

    }
}
