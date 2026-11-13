package com.foodfarm.order.entity;

import java.util.List;
import com.foodfarm.order.dto.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity 
@Data 
@Table (name = "order")
public class Order {
    
    @Id 
    private Integer order_id;

    @NotNull 
    private Integer customer_id;

    @NotNull 
    private Integer restaurant_id;

    @NotNull 
    private Integer delivery_id;

    @NotNull 
    private List<Item> items;
}
