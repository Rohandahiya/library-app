package com.example.gfg.libraryapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class UserCacheRepository {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private static final String KEY_PREFIX = "usr::";

    public void setUserInRedisCache(User user){
        redisTemplate.opsForValue().set(KEY_PREFIX + user.getUsername(),user, Duration.ofMinutes(20));
    }

    public User getUserFromCache(String username){
       return (User)redisTemplate.opsForValue().get(KEY_PREFIX+username);
    }

}
