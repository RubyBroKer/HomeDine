package com.foodfarm.atithi.repository;
import org.springframework.data.repository.CrudRepository;

import com.foodfarm.atithi.entity.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    
}
