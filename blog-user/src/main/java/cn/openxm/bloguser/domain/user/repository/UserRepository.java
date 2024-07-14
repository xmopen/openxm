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
     * save 持久化用户信息，并同步信息到Redis中。
     * */
    void save(UserEntity userEntity);
}
