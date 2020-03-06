package com.wcyv90.sp.cache.redis.infra;

public interface Counter {

    Long getAndIncrement(String counterName);

}
