package com.foodfarm.order.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class OrderProducer {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void publishEvent(OrderEvent orderEvent){
        kafkaTemplate.send(
            "order-events",
            orderEvent.order_id().toString(),
            orderEvent
        );
    }
}
