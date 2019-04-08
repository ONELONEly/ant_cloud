package com.gree.redis.bean;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
public class RedisAutoConfig {

    @Bean
    @Primary
    public LettuceConnectionFactory defaultLettuceConnectionFactory(RedisStandaloneConfiguration defaultRedisConfig, GenericObjectPoolConfig defaultPoolConfig) {
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(100)).poolConfig(defaultPoolConfig).build();
        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    }

    @Bean
    public RedisTemplate<String, String> defaultRedisTemplate(@Qualifier("defaultLettuceConnectionFactory") LettuceConnectionFactory defaultLettuceConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultLettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Object> defaultObjectRedisTemplate(@Qualifier("defaultLettuceConnectionFactory")LettuceConnectionFactory defaultLettuceConnectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultLettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }



    @Bean
    @ConditionalOnBean(name = "localRedisConfig")
    public LettuceConnectionFactory localLettuceConnectionFactory(RedisStandaloneConfiguration localRedisConfig, GenericObjectPoolConfig localPoolConfig) {
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(100)).poolConfig(localPoolConfig).build();
        return new LettuceConnectionFactory(localRedisConfig, clientConfig);
    }

    @Bean
    @ConditionalOnBean(name = "localLettuceConnectionFactory")
    public RedisTemplate<String, String> localRedisTemplate(@Qualifier("localLettuceConnectionFactory")LettuceConnectionFactory localLettuceConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(localLettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @ConditionalOnBean(name = "localLettuceConnectionFactory")
    public RedisTemplate<String, Object> localObjectRedisTemplate(@Qualifier("localLettuceConnectionFactory")LettuceConnectionFactory localLettuceConnectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(localLettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
