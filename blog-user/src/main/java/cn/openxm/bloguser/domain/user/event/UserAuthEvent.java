package cn.openxm.bloguser.domain.user.event;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.user.model.UserEntity;

/**
 * @author Xiao Ma
 * @date 2024/7/14
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public interface UserAuthEvent {

    /**
     * login event。
     * */
    void login(UserEntity userEntity);

    /**
     * register event。
     * */
    void register(UserEntity userEntity);

    /**
     * logout event。
     * */
    void logout(UserEntity userEntity);

    /**
     * refresh 用户身份状态刷新。
     * */
    void refresh(UserEntity userEntity);

}
