// sbpokems/src/main/java/com/gabriel/pokems/config/KafkaConfig.java
package com.gabriel.pokems.config;

import java.util.Map;
import java.util.HashMap;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {
    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(
            ProducerFactory<String,Object> pf) {
        return new KafkaTemplate<>(pf);
    }

    @Bean
    public ProducerFactory<String,Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                  "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                  StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                  JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }
}