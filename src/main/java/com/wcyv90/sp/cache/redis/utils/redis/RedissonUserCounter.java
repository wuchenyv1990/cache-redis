package com.wcyv90.sp.cache.redis.utils.redis;

import com.wcyv90.sp.cache.redis.domain.utils.redis.AbstractUserCounter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedissonUserCounter extends AbstractUserCounter {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public Long getAndIncrement(String counterName) {
        return redissonClient.getAtomicLong(counterName).getAndIncrement();
    }

}
