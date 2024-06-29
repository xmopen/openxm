package cn.openxm.common.redis;

import cn.openxm.common.redis.options.LuaScriptOption;


/**
 * author Xiao Ma
 * date 2024/6/30
 */
public interface RedisLuaScriptOptions<K> {

    /**
     * exec 执行Lua脚本命令。
     * */
    boolean exec(LuaScriptOption<K> option);

}
