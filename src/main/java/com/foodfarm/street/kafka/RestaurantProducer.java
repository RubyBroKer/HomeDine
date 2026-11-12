package com.foodfarm.street.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.foodfarm.street.entity.Restaurant;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RestaurantProducer {
    private final KafkaTemplate<String, RestaurantEvent> kafkaTemplate;

    public void publishEvent(RestaurantEvent restaurantEvent){
        kafkaTemplate.send(
            "customer-events",
            restaurantEvent.customerId().toString(),
            restaurantEvent
        );
    }
}
