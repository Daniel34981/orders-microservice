package com.ecommerce.orders.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long userid;
    private List<OderProductRequest> productRequestList;
    private String address;
    private String paidMethod;
    private Date orderDate;
    private Date finalDate;
}
