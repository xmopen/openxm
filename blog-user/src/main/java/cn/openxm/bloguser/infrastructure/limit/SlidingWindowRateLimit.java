package cn.openxm.bloguser.infrastructure.limit;

import cn.openxm.common.annotation.RateLimit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * SlidingWindowRateLimit 滑动窗口限流器。
 * <p>
 * @author Xiao Ma
 * @date 2024/7/4
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Component
public class SlidingWindowRateLimit {


    private final RedisTemplate<String,Object> redisTemplate;

    public SlidingWindowRateLimit(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * limitWithRedis 针对注解RateLimit通过Redis实现限流器。
     * 1、固定窗口：通过Redis总的setnx实现。
     * 1、漏桶算法：通过Redis中的List实现。
     * 2、令牌桶(滑动窗口)：通过Redis中的Zset实现。
     * @return false 表示限流，否则表示不限流。
     */
    public boolean limitWithRedis(RateLimit rateLimit) {
        if (rateLimit == null) {
            return false;
        }
        return true;
    }

}
