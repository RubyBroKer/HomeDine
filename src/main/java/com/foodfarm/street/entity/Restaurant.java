package com.foodfarm.street.entity;
import jakarta.persistence.GenerationType;

import java.util.List;

import com.foodfarm.street.dto.Items;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data  
public class Restaurant {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer restraurant_id;

    private String restraurant_name;

    private String address;

    private String phone_number;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "restraurant_id")
    private List<Items> items;

}
