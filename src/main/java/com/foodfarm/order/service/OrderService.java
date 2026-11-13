package com.foodfarm.order.service;
import java.util.List;
import com.foodfarm.order.dto.OrderDTO;
import com.foodfarm.order.exception.OrderException;

public interface OrderService {
    
    public OrderDTO getOrderById(Integer order_id) throws OrderException;
    public List<OrderDTO> getAllOrdersByCustomer(Integer customer_id) throws OrderException;
    public OrderDTO createOrder(OrderDTO orderDTO) throws OrderException;
    public OrderDTO updateOrder(OrderDTO orderDTO) throws OrderException;
}
