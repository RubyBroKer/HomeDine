package com.foodfarm.street.repository;

import org.springframework.data.domain.Page;

import com.foodfarm.street.dto.RestaurantDTO;

public interface RestaurantRepository {
    
    public Page<RestaurantDTO> getAllRestaurants(Integer page, Integer size);
    public Page<RestaurantDTO> getRestaurantByName(String name, Integer page, Integer size);
    public RestaurantDTO getRestaurantById(Integer restaurant_id);
    public RestaurantDTO getRestaurantMenu(Integer restaurant_id);
}
