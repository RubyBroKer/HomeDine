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
    public Page<Restaurant> getAllRestaurants(Integer page, Integer size){

        String queryString = "select r from Restaurant r";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        List<Restaurant> restraurants = query.setFirstResult(page*size).setMaxResults(size).getResultList();
        Integer totalRecords = entityManager.createQuery("SELECT COUNT(R) FROM RESTAURANT R").getFirstResult();
        
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(restraurants, pageable, totalRecords);
    }

    @Override 
    public Page<Restaurant> getRestaurantByName(String name, Integer page, Integer size){

        List<RestaurantDTO> restaurantDTOs = null;

        String queryString = "select r from Restaurant r where r.name ilike %" + name + "%";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        List<Restaurant> restaurants = query.setFirstResult(page*size).setMaxResults(size).getResultList();
        
        Integer totalCount = entityManager.createQuery("SELECT COUNT(R) FROM RESTAURANT R").getFirstResult();

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(restaurants, pageable, totalCount);
    }

    @Override 
    public Restaurant getRestaurantById(Integer restaurant_id){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        String queryString = "select r from Restaurant r where r.id :=restaurant_id";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        query.setParameter("restaurant_id", restaurant_id);
        List<Restaurant> restaurants = query.getResultList();

        return restaurants.getFirst();

    }

    @Override 
    public Restaurant getRestaurantMenu(Integer restaurant_id){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        String queryString = "select r from Restaurant r where r.id :=restaurant_id";
        Query query = entityManager.createQuery(queryString, Restaurant.class);
        query.setParameter("restaurant_id", restaurant_id);
        List<Restaurant> restaurants = query.getResultList();



        return restaurants.getFirst();
    }
    
}
