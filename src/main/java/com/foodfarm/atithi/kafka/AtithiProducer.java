package com.foodfarm.atithi.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service 
public class AtithiProducer {

    private final KafkaTemplate<String, AtithiEvent> kafkaTemplate;

    AtithiProducer(KafkaTemplate<String, AtithiEvent> atithiTemplate){
        this.kafkaTemplate = atithiTemplate;
    }
    
    public void publishEvent(AtithiEvent atithiEvent){
        kafkaTemplate.send(
            "atithi-events",
            atithiEvent.customer_id().toString(),
            atithiEvent
        );
    }
}
