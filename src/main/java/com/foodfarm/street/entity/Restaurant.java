package com.foodfarm.street.entity;
import jakarta.persistence.GenerationType;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data 
@Table (name = "restaurants")
public class Restaurant {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    private String restaurantName;

    private String address;

    private String phoneNumber;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "restaurant_id")
    private List<Item> items;

}
