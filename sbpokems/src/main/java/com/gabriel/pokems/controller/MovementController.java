package com.gabriel.pokems.controller;

import com.gabriel.pokems.model.MovementEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovementController {
    private final KafkaTemplate<String,Object> kafka;
    @Value("${pokems.topic.movement}") private String topic;

    public MovementController(KafkaTemplate<String,Object> kafka) {
        this.kafka = kafka;
    }

    @PostMapping("/movement")
    public ResponseEntity<Void> post(@RequestBody MovementEvent evt) {
        kafka.send(topic, evt);
        return ResponseEntity.ok().build();
    }
    
}