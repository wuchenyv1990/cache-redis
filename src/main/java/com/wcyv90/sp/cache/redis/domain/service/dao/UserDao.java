package com.wcyv90.sp.cache.redis.domain.service.dao;

import com.wcyv90.sp.cache.redis.domain.model.User;

public interface UserDao {

    User save(String name);

    User get(String name);

    User delete(String name);

}
