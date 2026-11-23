package com.foodfarm.atithi.service;

import com.foodfarm.atithi.dto.CustomerDTO;
import com.foodfarm.atithi.exception.CustomerException;

public interface CustomerService {
    public CustomerDTO addCustomer(CustomerDTO customerDTO) throws CustomerException;
    public CustomerDTO updateCustomerDTO(CustomerDTO customerDTO) throws CustomerException;
    public Integer deleteCustomer(Integer customer_id) throws CustomerException;
    public CustomerDTO getCustomer(Integer customer_id) throws CustomerException;
}
