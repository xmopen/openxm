package cn.openxm.bloguser.domain.user.repository.impl;

import cn.openxm.bloguser.constant.RedisKeysConstant;
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

import java.util.IllegalFormatCodePointException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
            // throw an exception directly,and the upper layer cached it.
            throw new RuntimeException("user repository insert fail");
        }

        this.refreshUserInfoTInRedis(userEntity);
    }

    /**
     * insertIsSuccess insert并判断是否成功增加。
     * */
    private boolean insertIsSuccess(UserDO userData){
        // the return value of the insert function represents the number of rows affected.
        return this.userDao.insert(userData) > 0;
    }


    @Override
    public void refreshUserInfoTInRedis(UserEntity userEntity) {
        if (userEntity == null || Objects.equals(userEntity.getToken(), "")
                || Objects.equals(userEntity.getJwtToken(),"")) {
            throw new RuntimeException("Description failed to refresh the user information because the token is empty");
        }
        ValueOperations<String, Object> ops = this.redisTemplate.opsForValue();
        LOGGER.info("refresh user information in redis:{}",userEntity);
        ops.set(this.getUserSaveRedisKey(userEntity),JSON.toJSONString(userEntity),3, TimeUnit.DAYS);
    }

    /**
     * getUserSaveRedisKey obtain the user information save key.
     * */
    private String getUserSaveRedisKey(UserEntity userEntity){
        // openxm.key.unique.user.info:${token}
        return String.format(RedisKeysConstant.REDIS_KEY_UNIQUE_USER_INFO_TOKEN,userEntity.getToken());
    }
}
