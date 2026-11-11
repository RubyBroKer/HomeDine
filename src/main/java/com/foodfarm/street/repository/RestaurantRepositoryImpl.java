package com.foodfarm.street.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.foodfarm.street.dto.RestaurantDTO;
import com.foodfarm.street.entity.Restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository 
public class RestaurantRepositoryImpl implements RestaurantRepository{

    @PersistenceContext 
    private EntityManager entityManager;


    @Override 
    public Page<RestaurantDTO> getAllRestaurants(Integer page, Integer size){

        List<RestaurantDTO> restaurantDTOs = null;
        String queryString = "select r from Restaurant r";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        List<Restaurant> restraurants = query.setFirstResult(page*size).setMaxResults(size).getResultList();
        Integer totalRecords = entityManager.createQuery("SELECT COUNT(R) FROM RESTAURANT R").getFirstResult();
        
        for(Restaurant restraurant : restraurants){

            RestaurantDTO restraurantDTO = new RestaurantDTO();
            restraurantDTO.setAddress(restraurant.getAddress());
            restraurantDTO.setPhone_number(restraurant.getPhone_number());
            restraurantDTO.setRestraurant_id(restraurantDTO.getRestraurant_id());
            restraurantDTO.setRestraurant_name(restraurant.getRestraurant_name());
            restaurantDTOs.add(restraurantDTO);
        }

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(restaurantDTOs, pageable, totalRecords);
    }

    @Override 
    public Page<RestaurantDTO> getRestaurantByName(String name, Integer page, Integer size){

        List<RestaurantDTO> restaurantDTOs = null;

        String queryString = "select r from Restaurant r where r.name ilike %" + name + "%";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        List<Restaurant> restaurants = query.setFirstResult(page*size).setMaxResults(size).getResultList();
        
        Integer totalCount = entityManager.createQuery("SELECT COUNT(R) FROM RESTAURANT R").getFirstResult();

        for(Restaurant restaurant : restaurants){

            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setAddress(restaurant.getAddress());
            restaurantDTO.setPhone_number(restaurant.getPhone_number());
            restaurantDTO.setRestraurant_id(restaurant.getRestraurant_id());
            restaurantDTO.setRestraurant_name(restaurant.getRestraurant_name());
            restaurantDTOs.add(restaurantDTO);
        }

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(restaurantDTOs, pageable, totalCount);
    }

    @Override 
    public RestaurantDTO getRestaurantById(Integer restaurant_id){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        String queryString = "select r from Restaurant r where r.id :=restaurant_id";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        query.setParameter("restaurant_id", restaurant_id);
        List<Restaurant> restaurants = query.getResultList();

        for(Restaurant restaurant : restaurants){
            restaurantDTO.setAddress(restaurant.getAddress());
            restaurantDTO.setPhone_number(restaurant.getPhone_number());
            restaurantDTO.setRestraurant_id(restaurant.getRestraurant_id());
            restaurantDTO.setRestraurant_name(restaurant.getRestraurant_name());
        }

        return restaurantDTO;

    }

    @Override 
    public RestaurantDTO getRestaurantMenu(Integer restaurant_id){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        String queryString = "select r from Restaurant r where r.id :=restaurant_id";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        query.setParameter("restaurant_id", restaurant_id);
        List<Restaurant> restaurants = query.getResultList();

        for(Restaurant restaurant : restaurants){
            restaurantDTO.setAddress(restaurant.getAddress());
            restaurantDTO.setPhone_number(restaurant.getPhone_number());
            restaurantDTO.setRestraurant_id(restaurant.getRestraurant_id());
            restaurantDTO.setRestraurant_name(restaurant.getRestraurant_name());
            restaurantDTO.setItems(restaurant.getItems());
        }

        return restaurantDTO;
    }
    
}
