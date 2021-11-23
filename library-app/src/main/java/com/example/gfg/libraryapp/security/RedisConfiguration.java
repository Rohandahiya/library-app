package com.example.gfg.libraryapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    LettuceConnectionFactory getRedisFactory(){

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis-13330.c264.ap-south-1-1.ec2.cloud.redislabs.com",13330);

        //Since no constructor having password so password is entered seperately
        redisStandaloneConfiguration.setPassword("M4GZzWHMRrj5LrOe3BhvQ1F1IarN90dz");

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return  lettuceConnectionFactory;
    }

    @Bean
    RedisTemplate<String,Object> getTemplate(){

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setConnectionFactory(getRedisFactory());


        return redisTemplate;
    }

}
