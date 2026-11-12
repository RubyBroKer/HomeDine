package com.foodfarm.street.repository;

import org.springframework.data.domain.Page;
import com.foodfarm.street.entity.Restaurant;

public interface RestaurantRepository {
    
    public Page<Restaurant> getAllRestaurants(Integer page, Integer size);
    public Page<Restaurant> getRestaurantByName(String name, Integer page, Integer size);
    public Restaurant getRestaurantById(Integer restaurant_id);
    public Restaurant getRestaurantMenu(Integer restaurant_id);
}
