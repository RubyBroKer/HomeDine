package com.foodfarm.atithi.service;

import java.time.Instant;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.foodfarm.atithi.dto.CustomerDTO;
import com.foodfarm.atithi.entity.Customer;
import com.foodfarm.atithi.exception.CustomerException;
import com.foodfarm.atithi.kafka.AtithiEvent;
import com.foodfarm.atithi.kafka.AtithiProducer;
import com.foodfarm.atithi.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service 
@Transactional 
@CacheConfig (cacheNames = "customer")
public class CustomerServiceImpl implements CustomerService{

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired 
    private CustomerRepository customerRepository;

    @Autowired 
    private AtithiProducer atithiProducer;

    @Override 
    public CustomerDTO addCustomer(CustomerDTO customerDTO) throws CustomerException{
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer = customerRepository.save(customer);
        customerDTO.setCustomerId(customer.getCustomerId());
        AtithiEvent atithiEvent = new AtithiEvent(customer.getCustomerId(), 
                                        "customer added",
                                        Instant.now()
                                        );

        atithiProducer.publishEvent(atithiEvent);

        return customerDTO;
    }
    
    @Override 
    public CustomerDTO updateCustomerDTO(CustomerDTO customerDTO) throws CustomerException{
        Optional<Customer> customer = customerRepository.findById(customerDTO.getCustomerId());
        Customer customer2 = customer.orElseThrow(()-> new CustomerException("Customer doesn't exists"));
        customer2.setAddress(customerDTO.getAddress());
        customer2.setEmailAddress(customerDTO.getEmailAddress());
        customer2.setFirstName(customerDTO.getFirstName());
        customer2.setLastName(customerDTO.getLastName());
        customer2.setPhoneNumber(customerDTO.getPhoneNumber());

        AtithiEvent atithiEvent = new AtithiEvent(customer2.getCustomerId(), 
                                        "customer updated",
                                        Instant.now()
                                        );

        atithiProducer.publishEvent(atithiEvent);

        return customerDTO;
    }
    
    @Override 
    public Integer deleteCustomer(Integer customer_id) throws CustomerException{
        customerRepository.deleteById(customer_id);

        AtithiEvent atithiEvent = new AtithiEvent(customer_id, 
                                        "customer deleted",
                                        Instant.now()
                                        );

        atithiProducer.publishEvent(atithiEvent);
        return customer_id;

    }
    
    @Override 
    public CustomerDTO getCustomer(Integer customer_id) throws CustomerException{
        Optional<Customer> optional = customerRepository.findById(customer_id);
        Customer customer = optional.orElseThrow(() -> new CustomerException("Customer doesn't exits"));
        return modelMapper.map(customer, CustomerDTO.class);
    }
    
}
