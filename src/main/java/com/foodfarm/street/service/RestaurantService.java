package com.foodfarm.street.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodfarm.street.dto.RestaurantDTO;
import com.foodfarm.street.exception.RestaurantException;

import jakarta.transaction.Transactional;

public interface RestaurantService {

    public List<RestaurantDTO> getAllRestaurants(Integer page, Integer size) throws RestaurantException;
    public List<RestaurantDTO> getRestaurantByName(String name, Integer page, Integer size) throws RestaurantException;
    public RestaurantDTO getRestaurantById(Integer restaurant_id) throws RestaurantException;
    public RestaurantDTO getRestaurantMenu(Integer restaurant_id) throws RestaurantException;
    
}
