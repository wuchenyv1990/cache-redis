package com.wcyv90.sp.cache.redis.infra;

import com.wcyv90.sp.cache.redis.domain.model.User;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static java.util.Collections.singletonMap;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;
import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Jackson2JsonRedisSerializer反序列化需要指定user类型，否则会转换成linkedHashMap
     */
    @Bean
    public RedisCacheManager userCacheManager(
            RedisConnectionFactory redisConnectionFactory,
            RedisCacheConfiguration userRedisCacheConfiguration
    ) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(userRedisCacheConfiguration)
                .withInitialCacheConfigurations(
                        singletonMap("predefined", defaultCacheConfig().disableCachingNullValues())
                )
                .transactionAware()
                .build();
    }

    @Bean
    @Primary
    public RedisCacheManager genericCacheManager(
            RedisConnectionFactory redisConnectionFactory,
            RedisCacheConfiguration genericRedisCacheConfiguration
    ) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(genericRedisCacheConfiguration)
                .withInitialCacheConfigurations(
                        singletonMap("predefined", defaultCacheConfig().disableCachingNullValues())
                )
                .transactionAware()
                .build();
    }

    /**
     * 只针对user序列化
     */
    @Bean
    public RedisCacheConfiguration userRedisCacheConfiguration(RedisSerializer<User> jsonRedisSerializer) {
        return defaultCacheConfig()
                .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(fromSerializer(jsonRedisSerializer))
                .prefixKeysWith("cache:user:name:");
    }

    /**
     * 通用配置，支持泛型
     */
    @Bean
    public RedisCacheConfiguration genericRedisCacheConfiguration(RedisSerializer<?> genericRedisSerializer) {
        return defaultCacheConfig()
                .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(fromSerializer(genericRedisSerializer))
                .disableKeyPrefix(); // 默认生成cache.value::cache.key
    }

}
