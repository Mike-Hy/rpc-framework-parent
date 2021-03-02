package com.michael.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author Michael.Ng
 * @Date 2021/2/21 11:38
 **/
//@Configuration
//@EnableCaching
public class RedisConfig {

        @Bean(name = "redisTemplate")
        public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
                RedisTemplate<String,String> redisTemplate=new RedisTemplate<>();
                RedisSerializer<String> redisSerializer = new StringRedisSerializer();
                redisTemplate.setConnectionFactory(factory);
                //key序列化
                redisTemplate.setKeySerializer(redisSerializer);
                //value序列化
                redisTemplate.setValueSerializer(redisSerializer);
                //value hashmap序列化
                redisTemplate.setHashKeySerializer(redisSerializer);
                //key hashmap序列化
                redisTemplate.setHashValueSerializer(redisSerializer);
                return redisTemplate;
        }


}