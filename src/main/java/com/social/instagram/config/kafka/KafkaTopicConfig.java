package com.social.instagram.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.bootstrap.address}")
    private String address;

    @Value("${kafka.topic.type.nice}")
    private String niceTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> kafkaAdminConfigMap = new HashMap<>();

        kafkaAdminConfigMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, address);

        return new KafkaAdmin(kafkaAdminConfigMap);
    }

    @Bean
    public NewTopic niceTopic() {
        return TopicBuilder.name(niceTopic)
                .build();
    }

}