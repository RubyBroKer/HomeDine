package com.foodfarm.street.dto;

import lombok.Data;

@Data 
public class Items {
    private Integer itemId;
    private String itemName;
    private String category;
    private Integer price;
    private String status;
    private Boolean favourites;
}
