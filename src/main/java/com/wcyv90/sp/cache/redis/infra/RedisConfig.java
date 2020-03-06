package com.wcyv90.sp.cache.redis.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcyv90.sp.cache.redis.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;


@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Long> counterRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.string());
        return template;
    }

    @Bean
    public RedisTemplate<String, User> userRedisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            RedisSerializer<User> userRedisSerializer
    ) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(userRedisSerializer);
        template.setDefaultSerializer(userRedisSerializer);
        template.setHashKeySerializer(RedisSerializer.string());
//        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    public RedisTemplate<String, ?> genericRedisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            RedisSerializer<?> genericRedisSerializer
    ) {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(genericRedisSerializer);
        return template;
    }

    @Bean
    public RedisSerializer<User> userRedisSerializer(ObjectMapper objectMapper) {
        Jackson2JsonRedisSerializer<User> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(User.class);
        jsonRedisSerializer.setObjectMapper(objectMapper);
        return jsonRedisSerializer;
    }

    @Bean
    public RedisSerializer<?> genericRedisSerializer(ObjectMapper objectMapper) {
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

}
