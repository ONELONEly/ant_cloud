package com.gree.bean;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

@Configuration("localRedisConfigFactory")
@ConditionalOnProperty(name = "host", prefix = "spring.local-redis")
public class LocalRedisConfig {

    @Value("${spring.local-redis.host}")
    private String host;
    @Value("${spring.local-redis.port}")
    private Integer port;
    @Value("${spring.local-redis.password}")
    private String password;
    @Value("${spring.local-redis.database}")
    private Integer database;
    @Value("${spring.local-redis.lettuce.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.local-redis.lettuce.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.local-redis.lettuce.pool.max-wait}")
    private Long maxWait;
    @Value("${spring.local-redis.lettuce.pool.min-idle}")
    private Integer minIdle;

    @Bean
    public GenericObjectPoolConfig localPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);
        return config;
    }

    @Bean
    public RedisStandaloneConfiguration localRedisConfig() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPassword(RedisPassword.of(password));
        config.setPort(port);
        config.setDatabase(database);
        return config;
    }
}

