package com.foodfarm.order.dto;

import lombok.Data;

@Data 
public class Item {
    private String item_id;
    private String price;
    private String name;
    private String quantity;
}
