package com.social.instagram.config.kafka;

import com.social.instagram.dto.request.FeedNiceRequestDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap.address}")
    private String address;

    @Bean
    public ProducerFactory<String, FeedNiceRequestDto> feedNiceProducerFactory() {
        Map<String, Object> producerConfigMap = new HashMap<>();

        producerConfigMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        producerConfigMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfigMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(producerConfigMap);
    }

    @Bean
    public KafkaTemplate<String, FeedNiceRequestDto> feedNiceKafkaTemplate() {
        return new KafkaTemplate<>(feedNiceProducerFactory());
    }

}