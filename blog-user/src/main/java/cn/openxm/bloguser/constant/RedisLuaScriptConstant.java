package cn.openxm.bloguser.constant;

/**
 * RedisLuaScriptConstant Redis Lua 脚本命令常量。
 *
 * author Xiao Ma
 * date 2024/6/28
 */
public class RedisLuaScriptConstant {

    /**
     * REDIS_LUA_SCRIPT_HASH_WITH_EXPIRED Redis 执行Redis哈希Set操作同时设定添加过期时间。
     * 说明：
     * KEYS[1] hset ARGV[1] ARGV[2]
     * KEYS[1] expire ARGV[3]
     * */
    public static final String REDIS_LUA_SCRIPT_HASH_SET_WITH_EXPIRED = """
            result=redis.call('hset', KEYS[1], ARGV[1],ARGV[2]);
            if (result==1)
            then
                -- 执行成功，如果执行失败，需要删除掉hset的值
                result=redis.call('expire', KEYS[1],ARGV[3]);
                if (result==0)
                then
                    -- 如果添加过期时间失败，则删除掉hset时设置的值。
                    redis.call('del', KEYS[1]);
                end
            end
            return result
            """;
}
