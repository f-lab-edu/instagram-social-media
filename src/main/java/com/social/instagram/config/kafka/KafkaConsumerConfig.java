package com.social.instagram.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap.address}")
    private String address;

    @Value("${kafka.batch.allow}")
    private boolean batchAllow;

    @Value("${kafka.batch.size}")
    private int batchMessageMax;

    @Value("${kafka.fetch-min-bytes-size}")
    private int fetchMinByteSize;

    @Value("${kafka.fetch-max-wait-time}")
    private int fetchMaxWaitTime;

    @Value("${kafka.trust-package}")
    private String trustPackage;

    @Bean
    public ConsumerFactory<String, Long> consumerFactory() {
        Map<String, Object> consumerConfigMap = new HashMap<>();

        consumerConfigMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        consumerConfigMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerConfigMap.put(JsonDeserializer.TRUSTED_PACKAGES, trustPackage);
        consumerConfigMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, batchMessageMax);
        consumerConfigMap.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, fetchMinByteSize);
        consumerConfigMap.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, fetchMaxWaitTime);

        return new DefaultKafkaConsumerFactory<>(consumerConfigMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Long>
                                    ListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Long> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(batchAllow);

        return factory;
    }

}