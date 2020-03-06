package com.wcyv90.sp.cache.redis.domain.utils.redis;

public abstract class AbstractUserCounter implements UserCounter {

    @Override
    public Long nextUserId() {
        return getAndIncrement("ct:userId");
    }

}
