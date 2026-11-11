package com.foodfarm.street.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.foodfarm.street.dto.RestaurantDTO;
import com.foodfarm.street.exception.RestaurantException;
import com.foodfarm.street.repository.RestaurantRepository;
import com.foodfarm.street.service.RestaurantService;

import jakarta.transaction.Transactional;


@Service (value = "restaurant_service")
@Transactional 
public class RestaurantServiceImpl implements RestaurantService {
    
    @Autowired 
    private RestaurantRepository repository;

    @Autowired 
    private Environment environment;

    @Override 
    public List<RestaurantDTO> getAllRestaurants(Integer page, Integer size) throws RestaurantException{

        List<RestaurantDTO> restaurants = repository.getAllRestaurants(page, size).getContent();

        return restaurants;

    }


    @Override 
    public List<RestaurantDTO>getRestaurantByName(String name, Integer page, Integer size) throws RestaurantException{
        List<RestaurantDTO> restaurants = repository.getRestaurantByName(name, page,size).getContent();
        return restaurants;

    }

    @Override 
    public RestaurantDTO getRestaurantById(Integer restaurant_id) throws RestaurantException{
        RestaurantDTO restaurantDTO = repository.getRestaurantById(restaurant_id);
        return restaurantDTO;
    }

    @Override 
    public RestaurantDTO getRestaurantMenu(Integer restaurant_id) throws RestaurantException{
        RestaurantDTO restaurantDTO = repository.getRestaurantMenu(restaurant_id);
        return restaurantDTO;
    }

 
}
