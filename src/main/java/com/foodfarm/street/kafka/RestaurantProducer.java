package com.foodfarm.street.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
public class RestaurantProducer {
    private final KafkaTemplate<String, RestaurantEvent> kafkaTemplate;

    RestaurantProducer(KafkaTemplate<String, RestaurantEvent> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void publishEvent(RestaurantEvent restaurantEvent){
        kafkaTemplate.send(
            "restaurant-events",
            restaurantEvent.restaurantId().toString(),
            restaurantEvent
        );
    }
}
