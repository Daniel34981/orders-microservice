package com.ecommerce.orders.domain.model;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Long userid;
    private List<OrderProduct> productList;
    private Double totalPrice;
    private Long status;
    private String address;
    private String paidMethod;
    private Date orderDate;
    private Date finalDate;

    public Order(Long id, Long userid, List<OrderProduct> productList, Double totalPrice, Long status, String address, String paidMethod, Date orderDate, Date finalDate) {
        this.id = id;
        this.userid = userid;
        this.productList = productList;
        this.totalPrice = totalPrice;
        this.status = status;
        this.address = address;
        this.paidMethod = paidMethod;
        this.orderDate = orderDate;
        this.finalDate = finalDate;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userid=" + userid +
                ", productList=" + productList +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", paidMethod='" + paidMethod + '\'' +
                ", orderDate=" + orderDate +
                ", finalDate=" + finalDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public List<OrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProduct> productList) {
        this.productList = productList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaidMethod() {
        return paidMethod;
    }

    public void setPaidMethod(String paidMethod) {
        this.paidMethod = paidMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
