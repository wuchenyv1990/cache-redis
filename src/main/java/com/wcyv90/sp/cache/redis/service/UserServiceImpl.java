package com.wcyv90.sp.cache.redis.service;

import com.wcyv90.sp.cache.redis.domain.model.User;
import com.wcyv90.sp.cache.redis.domain.service.UserService;
import com.wcyv90.sp.cache.redis.domain.service.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 默认Primary的genericCacheManager也可，需要指定前缀
     */
    @CachePut(value = "users", key = "'cache:user:name:'.concat(#name)")
    @Override
    public User createUser(String name) {
        return userDao.save(name);
    }

    @Cacheable(
            value = "users",
            key = "#name",
            cacheManager = "userCacheManager",
            unless = "#result == null" //配置不缓存空值
    )
    @Override
    public Optional<User> getUser(String name) {
        return Optional.ofNullable(userDao.get(name));
    }

    @CacheEvict(value = "users", cacheManager = "userCacheManager", key = "#name")
    @Override
    public Optional<User> deleteUser(String name) {
        return Optional.ofNullable(userDao.delete(name));
    }
}
