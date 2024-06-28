package cn.openxm.bloguser.redis;

import cn.openxm.bloguser.BlogUserApplication;
import cn.openxm.bloguser.constant.RedisKeysConstant;
import cn.openxm.bloguser.constant.RedisLuaScriptConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;

/**
 * author Xiao Ma
 * date 2024/6/28
 */
@SpringBootTest(classes = {BlogUserApplication.class})
public class RedisTest {

//    @Value("${spring.data.redis.password}")
//    private String password;

    @Value("${spring.datasource.username}")
    private String mysqlUserName;

    @Value("${spring.data.redis.password}")
    private String password;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testPass(){
        System.out.println(this.mysqlUserName);
        System.out.println(this.password);
    }

    @Test
    public void test() {
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
        script.setScriptText(RedisLuaScriptConstant.REDIS_LUA_SCRIPT_HASH_SET_WITH_EXPIRED);
        script.setResultType(Boolean.class);
        boolean result = Boolean.TRUE.equals(redisTemplate.execute(script,
                Collections.singletonList(String.format(RedisKeysConstant.REDIS_KEY_USER_MAIL_CODE_KEY, "openxm.test")),
                "subkey","object value", RedisKeysConstant.REDIS_TTL_MAIL_CODE_REDIS_MINUTE_TTL));
        System.out.println(result);
    }
}
