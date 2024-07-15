package cn.openxm.bloguser.domain.user.repository;

import cn.openxm.bloguser.domain.user.model.UserEntity;

/**
 * UserRepository 用户存储。
 *
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public interface UserRepository {

    /**
     * save persist user information and finally synchronized it to redis.
     *
     * @param userEntity user information that is synchronized.
     * */
    void save(UserEntity userEntity);

    /**
     * refreshUserInfoTInRedis refreshes user information in redis.
     *
     * @param  userEntity user information that is refreshed.
     * */
    void refreshUserInfoTInRedis(UserEntity userEntity);

}
