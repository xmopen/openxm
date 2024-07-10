package cn.openxm.bloguser.constant;

/**
 * RedisLuaScriptConstant Redis Lua 脚本命令常量。
 *
 * @author Xiao Ma
 * @date 2024/6/28
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public class RedisLuaScriptConstant {

    /**
     * REDIS_LUA_SCRIPT_HASH_WITH_EXPIRED Redis 执行Redis哈希Set操作同时设定添加过期时间。
     * 说明：
     * KEYS[1] hset ARGV[1] ARGV[2]
     * KEYS[1] expire ARGV[3]
     */
    public static final String REDIS_LUA_SCRIPT_HASH_SET_WITH_EXPIRED = """
            local result=redis.call('hset', KEYS[1], ARGV[1],ARGV[2]);
            if (result==1)
            then
                -- 执行成功，如果执行失败，需要删除掉hset的值
                local expireResult=redis.call('expire', KEYS[1],ARGV[3]);
                if (expireResult==0)
                then
                    -- 如果添加过期时间失败，则删除掉hset时设置的值。
                    return redis.call('del', KEYS[1]);
                end
                return expireResult
            end
            return result
            """;

    /**
     * REDIS_LUA_SCRIPT_SLIDING_WINDOW_RATE_LIMIT_ZSET 原子操作通过ZSET实现滑动窗口限流。
     *  TODO: Lua脚本实现，具体逻辑如下：
     *  1、判断当前Key的次数是否达到限制。
     *       1.1 达到限制，返回false。
     *       1.2 未达到限制，则累加。
     *  TODO: 过期时间的score如何清除：
     *       1、懒删除
     *       2、随着过期时间自动删除，每次新的请求都刷新过期时间。
     *  TODO: 命令参数：key只有一个
     *  1、windowLeft
     *  2、windowRight(也就是当前时间)
     *  3、limitCount
     *  4、value
     *  5、ttl
     */
    public static final String REDIS_LUA_SCRIPT_SLIDING_WINDOW_RATE_LIMIT_ZSET = """
            -- 1、删除不在窗口范围的请求标记
            local removeResult = redis.call('ZREMRANGEBYSCORE',KEYS[1],0,ARGV[1]);
            if (removeResult==0)
            then
                return 0;
            end
                       
            -- 2、对当前Key进行续期
            local expireResult=redis.call('expire', KEYS[1],ARGV[5]);
            if (expireResult==0)
            then
                return 0;
            end
                      
            -- 3、如果请求次数大于指定次数，则限流
            local hitCount = redis.call('ZRANGEBYSCORE',KEYS[1],ARGV[1],ARGV[2],ARGV[3]);
            if (hitCount>=ARGV[3])
            then
                return 0;
            end
                      
            -- 4、增加请求次数到当前Key中
            return redis.call('ZADD',KEYS[1],ARGV[2],ARGV[4]);
           """;
}
