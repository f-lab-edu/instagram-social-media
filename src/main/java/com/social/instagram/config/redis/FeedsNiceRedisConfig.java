package com.social.instagram.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class FeedsNiceRedisConfig {

    @Value("${spring.redis.feeds-nice.host}")
    private String redisHost;

    @Value("${spring.redis.feeds-nice.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory feedsNiceRedisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }

    @Bean
    public RedisTemplate<Long, Long> feedsNiceRedisTemplate() {
        RedisTemplate<Long, Long> feedsNiceRedisTemplate = new RedisTemplate<>();
        feedsNiceRedisTemplate.setConnectionFactory(feedsNiceRedisConnectionFactory());
        feedsNiceRedisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        feedsNiceRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return feedsNiceRedisTemplate;
    }

}