package cn.openxm.bloguser.domain.user.repository.impl;

import cn.openxm.bloguser.domain.user.model.UserDO;
import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.bloguser.domain.user.repository.UserRepository;
import cn.openxm.bloguser.infrastructure.dao.UserDao;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author Xiao Ma
 * @date 2024/7/15
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Component
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private final UserDao userDao;

    private final RedisTemplate<String, Object> redisTemplate;

    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate, UserDao userDao) {
        this.redisTemplate = redisTemplate;
        this.userDao = userDao;
    }

    @Override
    public void save(UserEntity userEntity) {
        UserDO userData =  UserDO.builder()
                .userName(userEntity.getNickName())
                .password(userEntity.getPassword())
                .email(userEntity.getMail())
                .userIcon(userEntity.getIcon())
                .build();
        LOGGER.info("user repository save userdata:[{}]",userData);
        if (!this.insertIsSuccess(userData)){
            // 直接抛出异常即可，上层捕捉。
            throw new RuntimeException("user repository insert fail");
        }
        ValueOperations<String, Object> ops =  this.redisTemplate.opsForValue();
        ops.set(this.getUserSaveRedisKey(userEntity),JSON.toJSONString(userEntity));
    }

    /**
     * insertIsSuccess insert并判断是否成功增加。
     * */
    private boolean insertIsSuccess(UserDO userData){
        return this.userDao.insert(userData) > 0;
    }

    private String getUserSaveRedisKey(UserEntity userEntity){
        return userEntity.getNickName() + userEntity.getPassword();
    }
}
