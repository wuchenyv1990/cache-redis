package com.wcyv90.sp.cache.redis.domain.service;


import com.wcyv90.sp.cache.redis.domain.model.User;

import java.util.Optional;

public interface UserService {

    User createUser(String name);

    Optional<User> getUser(String name);

    Optional<User> deleteUser(String name);

}
