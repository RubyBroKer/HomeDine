package com.foodfarm.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodfarm.order.entity.Order;
import java.util.List;


public interface OrderRepository extends CrudRepository<Order,Integer>{

    public List<Order> findByCustomer_id(Object customer_id);
    
}