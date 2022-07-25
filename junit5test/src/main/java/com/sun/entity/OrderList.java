package com.sun.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderList {

    @JsonProperty("item")
    private String oderItem;
    @JsonProperty("quantity")
    private int qua;
    @JsonProperty("unitPrice")
    private BigDecimal price;
    @JsonProperty("orderDate")
    private LocalDate date;

    public OrderList() {
    }

    public OrderList(String oderItem, int qua, BigDecimal price, LocalDate date) {
        this.oderItem = oderItem;
        this.qua = qua;
        this.price = price;
        this.date = date;
    }

    public String getOderItem() {
        return oderItem;
    }

    public void setOderItem(String oderItem) {
        this.oderItem = oderItem;
    }

    public int getQua() {
        return qua;
    }

    public void setQua(int qua) {
        this.qua = qua;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "oderItem='" + oderItem + '\'' +
                ", qua=" + qua +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
