package cn.openxm.bloguser.configuration;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisAutoConfig Redis自动装配配置类。
 *
 * @author Xiao Ma
 * @date 2024/6/27
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Configuration
public class RedisAutoConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.database}")
    private int database;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        LettuceConnectionFactory factory =  new LettuceConnectionFactory(this.host,this.port);
        factory.setDatabase(this.database);
        factory.setPassword(this.password);
        return factory;
    }

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
