package com.foodfarm.street.kafka;

import java.time.Instant;

import lombok.Data;

public record RestaurantEvent(

    Integer restaurantId,
    String eventType,
    String email,
    Instant timestamp

) {}
