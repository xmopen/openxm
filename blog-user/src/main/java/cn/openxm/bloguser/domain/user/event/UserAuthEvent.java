package cn.openxm.bloguser.domain.user.event;

import cn.openxm.bloguser.domain.user.model.UserEntity;

/**
 * @author Xiao Ma
 * @date 2024/7/14
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public interface UserAuthEvent {

    /**
     * onLogin event。
     * */
    void onLogin(UserEntity userEntity);

    /**
     * onRegister event。
     * */
    void onRegister(UserEntity userEntity);

    /**
     * onLogout event。
     * */
    void onLogout(UserEntity userEntity);

    /**
     * onRefresh 用户身份状态刷新。
     * */
    void onRefresh(UserEntity userEntity);

}
