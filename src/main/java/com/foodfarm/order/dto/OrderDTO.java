package com.foodfarm.order.dto;

import java.util.List;

import lombok.Data;

@Data 
public class OrderDTO {
    private Integer order_id;
    private Integer customer_id;
    private Integer restaurant_id;
    private Integer delivery_id;
    private List<Item> items;

}
