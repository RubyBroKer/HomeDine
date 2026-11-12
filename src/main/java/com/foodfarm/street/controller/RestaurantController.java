package com.foodfarm.street.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.foodfarm.street.dto.RestaurantDTO;
import com.foodfarm.street.exception.RestaurantException;
import com.foodfarm.street.service.RestaurantService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/restaurants")
public class RestaurantController {

    @Autowired 
    private RestaurantService restaurantService;

    @GetMapping ("/")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants(@RequestParam Integer page,@RequestParam Integer size) throws RestaurantException{

        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants(page, size);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Integer restaurantId) throws RestaurantException{
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
    
}
