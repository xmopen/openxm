package cn.openxm.common.redis.impl;

import cn.openxm.common.redis.RedisLuaScriptOptions;
import cn.openxm.common.redis.options.LuaScriptOption;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

/**
 * author Xiao Ma
 * date 2024/6/30
 */
@Component
public class RedisOptionsImpl implements RedisLuaScriptOptions {

    private final Class<Boolean> redisLuaResultType = Boolean.class;

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisOptionsImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean exec(LuaScriptOption option) {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(this.redisLuaResultType);
        redisScript.setScriptText(option.getScript());
        option.getArgs().add(option.getTtl());
        Boolean execResult = this.redisTemplate.execute(redisScript,  option.getKeys(),option.getArgs().toArray());
        if (execResult == null) {
            return false;
        }
        return execResult;
    }
}
