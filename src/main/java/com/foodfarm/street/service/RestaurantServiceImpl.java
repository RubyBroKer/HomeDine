package com.foodfarm.street.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.foodfarm.street.entity.Restaurant;
import com.foodfarm.street.dto.RestaurantDTO;
import com.foodfarm.street.exception.RestaurantException;
import com.foodfarm.street.repository.RestaurantRepository;
import com.foodfarm.street.service.RestaurantService;
import com.google.gson.reflect.TypeToken;
import jakarta.transaction.Transactional;


@Service (value = "restaurant_service")
@Transactional 
public class RestaurantServiceImpl implements RestaurantService {
    
    @Autowired 
    private RestaurantRepository repository;

    @Autowired 
    private Environment environment;

    private ModelMapper modelMapper = new ModelMapper();

    @Override 
    public List<RestaurantDTO> getAllRestaurants(Integer page, Integer size) throws RestaurantException{

        List<Restaurant> restaurants = repository.getAllRestaurants(page, size).getContent();

        return modelMapper.map(restaurants, new TypeToken<List<RestaurantDTO>>(){}.getType());

    }


    @Override 
    public List<RestaurantDTO>getRestaurantByName(String name, Integer page, Integer size) throws RestaurantException{
        List<Restaurant> restaurants = repository.getRestaurantByName(name, page,size).getContent();
        return modelMapper.map(restaurants, new TypeToken<List<RestaurantDTO>>(){}.getType());

    }

    @Override 
    public RestaurantDTO getRestaurantById(Integer restaurant_id) throws RestaurantException{
        Restaurant restaurant = repository.getRestaurantById(restaurant_id);
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    @Override 
    public RestaurantDTO getRestaurantMenu(Integer restaurant_id) throws RestaurantException{
        Restaurant restaurant = repository.getRestaurantMenu(restaurant_id);
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

 
}
