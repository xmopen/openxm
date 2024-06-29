package cn.openxm.common.redis.options;

import lombok.*;

import java.util.List;

/**
 * 对Redis执行Lua脚本进行封装，使用该类进行Redis脚本操作，默认将TTL放入到参数最后一位。
 *
 * author Xiao Ma
 * date 2024/6/30
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LuaScriptOption<K> {

    /**
     * script 本次具体要执行的Lua脚本命令。
     * */
    private String script;

    private List<K> keys;

    private List<Object> args;

    private int ttl;

}
