package com.foodfarm.street.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import com.foodfarm.street.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

    @Query ("select r from Restaurant r where r.restaurantName ilike ?1")
    public List<Restaurant> findByName(String name);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.items WHERE r.restaurantId = ?1 ")
    Optional<Restaurant> findRestaurantWithItems(Integer restaurantId);

}
