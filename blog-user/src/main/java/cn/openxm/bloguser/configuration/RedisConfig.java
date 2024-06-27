package cn.openxm.bloguser.configuration;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * author Xiao Ma
 * date 2024/6/27
 */
@Configuration
public class RedisConfig {

    /**
     * 将当前RedisTemplate加入到Spring IOC环境中。
     * */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // 设置序列化策略。
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new FastJsonRedisSerializer<Object>(Object.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new FastJsonRedisSerializer<Object>(Object.class));
        return template;
    }

}
