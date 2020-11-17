package com.social.instagram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*
    @Value
    자바 코드 외부의 리소스나 환경정보에 담긴 값을 사용하도록 지정해준다. @Value는 소스코드 안에 포함되는 애노테이션이어서 값을 수정하면 매번 새로 컴파일해야한다.
    하지만 @Value에서 프로퍼티 파일의 내용을 참조하게 해주면 소스코드의 수정 없이 @Value를 통해 프로퍼티에 주입되는 값을 변경할 수 있다.
 */
@Configuration
public class RedisSessionConfig {

    @Value("${spring.redis.session.host}")
    private String redisHost;

    @Value("${spring.redis.session.port}")
    private int redisPort;

    /*
        lettuce
        netty 기반으로 멀티스레드 환경에서 thread-safe 합니다. 또한 sentinel, cluster, pipeline등 이러한 기능들을 지원합니다.
        TPS/CPU/Connection 개수/응답속도가 전부 Jedis보다 뛰어나다.
        하지만 비동기방식을 사용한다면 설계와 실행흐름을 파악하기 쉽지 않아 신경써야 한다.

        jedis
        멀티스레드 환경에서 thread-unsafe하며 다중 스레드 환경에서는 connection pool이 필요하다.
        하지만 이러한 방법은 Connection할 인스턴스를 미리 만들어놓고 연결하는 비용이 증가하고 idle 상태일 때 커넥션을
        끊었다가 다시 맺을 때의 부하가 매우 심하고 connect time out이 매우 많이 발생 한다.
    */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

}