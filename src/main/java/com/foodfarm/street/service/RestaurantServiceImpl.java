package com.foodfarm.street.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.foodfarm.street.entity.Restaurant;
import com.foodfarm.street.dto.RestaurantDTO;
import com.foodfarm.street.exception.RestaurantException;
import com.foodfarm.street.kafka.RestaurantEvent;
import com.foodfarm.street.kafka.RestaurantProducer;
import com.foodfarm.street.repository.RestaurantRepository;
import com.google.gson.reflect.TypeToken;
import jakarta.transaction.Transactional;


@Service (value = "restaurant_service")
@Transactional 
@CacheConfig (cacheNames = "customer")
public class RestaurantServiceImpl implements RestaurantService {
    
    @Autowired 
    private RestaurantRepository repository;

    @Autowired 
    private RestaurantProducer restaurantProducer;

    @Autowired 
    private Environment environment;

    private ModelMapper modelMapper = new ModelMapper();

    @Override 
    @Cacheable ()
    public List<RestaurantDTO> getAllRestaurants(Integer page, Integer size) throws RestaurantException{

        Iterable<Restaurant> restaurants = repository.findAll();

        return modelMapper.map(restaurants, new TypeToken<List<RestaurantDTO>>(){}.getType());

    }


    @Override 
    public List<RestaurantDTO>getRestaurantByName(String name, Integer page, Integer size) throws RestaurantException{
        List<Restaurant> restaurants = repository.findByName(name);
        return modelMapper.map(restaurants, new TypeToken<List<RestaurantDTO>>(){}.getType());

    }

    @Override 
    public RestaurantDTO getRestaurantById(Integer restaurant_id) throws RestaurantException{
        Optional<Restaurant> restaurant = repository.findById(restaurant_id);
        if(restaurant != null)
            throw new RestaurantException("Restaurant doesn't exists");
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    @Override 
    public RestaurantDTO getRestaurantMenu(Integer restaurant_id) throws RestaurantException{
        Optional<Restaurant> restaurant = repository.findById(restaurant_id);
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    @Override 
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) throws RestaurantException{
        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        restaurant = repository.save(restaurant);
        restaurantDTO.setRestraurant_id(restaurant.getRestraurant_id());

        RestaurantEvent restaurantEvent = new RestaurantEvent(
            restaurant.getRestraurant_id(),
            "Restaurant Added",
            restaurant.getPhone_number(),
            Instant.now()
        );

        restaurantProducer.publishEvent(restaurantEvent);
        return restaurantDTO;
    }

 
}
