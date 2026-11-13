package com.foodfarm.order.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import com.foodfarm.order.entity.*;
import com.foodfarm.order.dto.OrderDTO;
import com.foodfarm.order.exception.OrderException;
import com.foodfarm.order.kafka.OrderEvent;
import com.foodfarm.order.kafka.OrderProducer;
import com.foodfarm.order.repository.OrderRepository;
import com.google.gson.reflect.TypeToken;
import jakarta.transaction.Transactional;

@Service 
@Transactional 
@CacheConfig (cacheNames = "order_cache")
public class OrderServiceImpl implements OrderService{
    
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired 
    private OrderRepository orderRepository;

    @Autowired 
    private OrderProducer orderProducer;

    @Override 
    public OrderDTO getOrderById(Integer order_id) throws OrderException
    {
        Optional<Order> optional = orderRepository.findById(order_id);
        Order order = optional.orElseThrow(() -> new OrderException("Service.Customer not found"));
        return modelMapper.map(order, OrderDTO.class);
    }

    @Override 
    public List<OrderDTO> getAllOrdersByCustomer(Integer customer_id) throws OrderException{
        List<Order> iterable = orderRepository.findByCustomer_id(customer_id);
        return modelMapper.map(iterable, new TypeToken<List<OrderDTO>>(){}.getType());
    }

    @Override 
    public OrderDTO createOrder(OrderDTO orderDTO) throws OrderException{
        Order order = modelMapper.map(orderDTO, Order.class);
        order = orderRepository.save(order);
        orderDTO.setOrder_id(order.getOrder_id());
        OrderEvent orderEvent = new OrderEvent(
            orderDTO.getOrder_id(),
            "Order created",
            Instant.now(),
            orderDTO.getCustomer_id(),
            orderDTO.getRestaurant_id()
        );
    
        orderProducer.publishEvent(orderEvent);
        return orderDTO;
    }

    @Override 
    public OrderDTO updateOrder(OrderDTO orderDTO) throws OrderException{
        Optional<Order> optional = orderRepository.findById(orderDTO.getOrder_id());
        Order order = optional.orElseThrow(() -> new OrderException("Order doesn't exists"));
        order.setItems(orderDTO.getItems());
        return orderDTO;
    } 

}
