package com.social.instagram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FeedsRedisCacheManagerConfig {

    @Value("${spring.redis.cache.host}")
    private String redisHost;

    @Value("${spring.redis.cache.port}")
    private int redisPort;

    @Value("${feeds.per.user.name}")
    private String feedsPerUserName;

    @Value("${feeds.per.user.expire.minute}")
    private int feedsPerUserExpireMinute;

    @Bean
    public RedisConnectionFactory redisCacheConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }

    @Bean
    public CacheManager redisCacheManager() {
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisCacheConnectionFactory())
                .withInitialCacheConfigurations(redisConfigurationMap())
                .build();
    }

    private Map<String, RedisCacheConfiguration> redisConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheMap = new HashMap<>();

        redisCacheMap.put(feedsPerUserName,
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        .entryTtl(Duration.ofMinutes(feedsPerUserExpireMinute)));

        return redisCacheMap;
    }

}