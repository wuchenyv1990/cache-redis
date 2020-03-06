package com.wcyv90.sp.cache.redis.domain.utils.redis;


import com.wcyv90.sp.cache.redis.infra.Counter;

public interface UserCounter extends Counter {

    Long nextUserId();

}
