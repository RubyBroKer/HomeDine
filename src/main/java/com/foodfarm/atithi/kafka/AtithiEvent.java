package com.foodfarm.atithi.kafka;

import java.time.Instant;

public record AtithiEvent(
    Integer customer_id,
    String eventType,
    Instant timestamp
) {}
