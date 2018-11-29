package com.wenba.service.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service("cacheUsage")
public class CacheUsage {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public void setCache() {
        redisTemplate.opsForValue().set("wenba", "dominate it");
    }

    public String getCache() {
        return redisTemplate.opsForValue().get("wenba").toString();
    }

}