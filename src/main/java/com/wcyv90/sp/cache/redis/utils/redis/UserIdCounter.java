package com.wcyv90.sp.cache.redis.utils.redis;

import com.wcyv90.sp.cache.redis.domain.utils.redis.AbstractUserCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserIdCounter extends AbstractUserCounter {

    @Autowired
    private RedisTemplate<String, Long> counter;

    @Override
    public Long getAndIncrement(String counterName) {
        return counter.opsForValue().increment(counterName);
    }

}
