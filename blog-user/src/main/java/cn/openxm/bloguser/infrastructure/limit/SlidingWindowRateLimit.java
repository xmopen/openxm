package cn.openxm.bloguser.infrastructure.limit;

import cn.openxm.bloguser.constant.RedisLuaScriptConstant;
import cn.openxm.common.annotation.RateLimit;
import cn.openxm.common.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * SlidingWindowRateLimit 滑动窗口限流器。
 * <p>
 * @author Xiao Ma
 * @date 2024/7/4
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Component
public class SlidingWindowRateLimit {

    /**
     * DEFAULT_RATE_LIMIT_VALUE 默认的限流内容(为了更加的节省内存，使用一个字节来占位)。
     * */
    private static final byte DEFAULT_RATE_LIMIT_VALUE = 0x00;

    /**
     * LOGGER 日志。
     * */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlidingWindowRateLimit.class);

    private final RedisTemplate<String,Object> redisTemplate;

    public SlidingWindowRateLimit(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * limitWithRedis 针对注解RateLimit通过Redis实现限流器。
     * 1、固定窗口：通过Redis总的setnx实现。
     * 1、漏桶算法：通过Redis中的List实现。
     * 2、令牌桶(滑动窗口)：通过Redis中的Zset实现。
     * @return true 表示限流，否则表示不限流。
     */
    public boolean limitWithRedis(RateLimit rateLimit,String clientLimitKey) {
        if (rateLimit == null) {
            return true;
        }
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
        script.setScriptText(RedisLuaScriptConstant.REDIS_LUA_SCRIPT_SLIDING_WINDOW_RATE_LIMIT_ZSET);
        script.setResultType(Boolean.class);
        long windowRight = LocalTime.getCurrenTimeWithMillis();
        long windowLeft = windowRight - rateLimit.window();
        LOGGER.info("limit key:{},windowLeft:{},windowRight:{},window:{}", this.getRateLimitKey(rateLimit, clientLimitKey),
                windowLeft, windowRight, (windowRight-windowLeft)/1e3);
        return !Boolean.TRUE.equals(this.redisTemplate.execute(script,
                Collections.singletonList(this.getRateLimitKey(rateLimit,clientLimitKey)),
                windowLeft,
                windowRight,
                rateLimit.limit(),
                rateLimit.window()/1000));
    }

    /**
     * getRateLimitKey 获取限流Key。
     * */
    private String getRateLimitKey(RateLimit rateLimit,String clientLimitKey) {
        return String.format("openxm.limit.%s:%s",rateLimit.limitKey(), clientLimitKey);
    }

}
