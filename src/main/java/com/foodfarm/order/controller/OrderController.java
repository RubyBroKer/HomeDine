package com.foodfarm.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodfarm.order.dto.OrderDTO;
import com.foodfarm.order.exception.OrderException;
import com.foodfarm.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController 
@RequestMapping (value = "/order")
public class OrderController {
    
    @Autowired 
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(value = "orderId") Integer orderId) throws OrderException{
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) throws OrderException{
        OrderDTO orderDTO2 = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(orderDTO2, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> putMethodName(@PathVariable Integer id, @RequestBody OrderDTO orderDTO) throws OrderException{
        OrderDTO orderDTO2 = orderService.updateOrder(orderDTO);        
        return new ResponseEntity<>(orderDTO2, HttpStatus.OK);
    }

}
