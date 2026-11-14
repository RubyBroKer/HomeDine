package com.foodfarm.street.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class RestaurantDTO {
    private Integer restaurantId;

    @NotNull (message = "Please provide a name")
    private String restaurantName;

    @NotNull (message = "Please provide an address")
    private String address;

    @NotNull (message = "Please provide a phone numer")
    private String phoneNumber;

    @Valid 
    private List<ItemDTO> items;
}
