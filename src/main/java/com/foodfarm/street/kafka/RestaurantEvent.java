package com.foodfarm.street.kafka;

import java.time.Instant;

public record RestaurantEvent(

    Integer customerId,
    String eventType,
    String email,
    Instant timestamp

) {}
