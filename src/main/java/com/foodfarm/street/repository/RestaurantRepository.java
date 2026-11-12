package com.foodfarm.street.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.foodfarm.street.dto.RestaurantDTO;
import com.foodfarm.street.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

    @Query ("select r from Restaurant r where r.name ilike ?1")
    public List<Restaurant> findByName(String name);

}
