package com.zty.onlineedu.service.base.redis;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Author zty
 * @Date 2023/3/16 22:15
 * redis的抽象类
 */
public abstract class AbstarctBaseRedis<K,V> {
    @Resource(name="redisTemplate")
    protected RedisTemplate<K,V> redisTemplate;

}
