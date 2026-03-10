package com.gabriel.pokems.controller;

import com.gabriel.pokems.model.CatchEvent;
import com.gabriel.pokems.model.CatchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class CatchController {
    private final KafkaTemplate<String,Object> kafka;
    @Value("${pokems.topic.catch}") private String topic;

    public CatchController(KafkaTemplate<String,Object> kafka) {
        this.kafka = kafka;
    }

    @PostMapping("/catch")
    public ResponseEntity<CatchResult> post(@RequestBody CatchEvent evt) {
        boolean kept = new Random().nextBoolean();
        CatchResult result = new CatchResult(kept, kept ? "You caught it!" : "It ran away…");
        kafka.send(topic, evt);
        return ResponseEntity.ok(result);
    }
}