package com.panda.service.kafka.bean;

import java.io.Serializable;

public class StockInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5244495968759652403L;

    private String stockCode;
    private float price;
    private long tradeTime;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return stockCode + "|" + price + "|" + tradeTime;
    }

    public long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }

}
