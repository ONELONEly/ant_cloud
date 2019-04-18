package com.gree.config;

import com.gree.store.AuthJwtTokenStore;
import com.gree.store.RedisTokenStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * token存储配置
 *
 * @author yhm
 */
public class TokenStoreConfiguration {

    @Configuration
    @ConditionalOnProperty(prefix = "yhm.oauth2.token.store", name = "type", havingValue = "authJwt")
    @Import(AuthJwtTokenStore.class)
    public class AuthJwtTokenConfig {
    }

    @Configuration
    @ConditionalOnProperty(prefix = "yhm.oauth2.token.store", name = "type", havingValue = "redis")
    @Import(RedisTokenStore.class)
    public class RedisTokenConfig {
    }
}
