package com.wcyv90.sp.cache.redis.service.daoimpl;


import com.wcyv90.sp.cache.redis.domain.model.User;
import com.wcyv90.sp.cache.redis.domain.service.dao.UserDao;
import com.wcyv90.sp.cache.redis.domain.utils.redis.UserCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Slf4j
public class UserDaoRedisImpl implements UserDao {


    private final BoundHashOperations<String, String, User> userRedisDao;
    private final UserCounter userCounter;

    public UserDaoRedisImpl(
            RedisTemplate<String, User> userRedisDao,
            @Qualifier("redissonUserCounter") UserCounter userCounter
    ) {
        this.userRedisDao = userRedisDao.boundHashOps("db:users");
        this.userCounter = userCounter;
    }

    @Override
    public User save(String name) {
        User user = get(name);
        if (user != null) {
            throw new DuplicateKeyException("User [" + name + "] already saved.");
        }
        user = User.builder()
                .id(userCounter.nextUserId())
                .name(name)
                .createTime(LocalDateTime.now())
                .build();
        log.info("Save user:{}", user);
        userRedisDao.put(user.getName(), user);
        return user;
    }

    @Override
    public User get(String name) {
        log.info("Query user name={} from db.", name);
        return userRedisDao.get(name);
    }

    @Override
    public User delete(String name) {
        log.info("Delete user name={} from db.", name);
        User user = get(name);
        if (user == null) {
            return null;
        }
        userRedisDao.delete(name);
        return user;
    }


}
