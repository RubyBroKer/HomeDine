package com.foodfarm.order.kafka;

import java.time.Instant;

import lombok.Data;

public record OrderEvent(
    Integer order_id,
    String eventType,
    Instant timestamp,
    Integer customer_id,
    Integer restaurant_id
) {}
