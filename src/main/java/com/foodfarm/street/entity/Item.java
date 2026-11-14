package com.foodfarm.street.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@Table (name = "item")
public class Item {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer itemId;

    private String itemName;
    
    private String category;
    
    private Integer price;
    
    private String status;
    
    private Boolean favourites;
    
}
