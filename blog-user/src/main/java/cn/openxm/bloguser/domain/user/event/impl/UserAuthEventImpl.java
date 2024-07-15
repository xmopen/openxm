package cn.openxm.bloguser.domain.user.event.impl;

import cn.openxm.bloguser.domain.user.event.UserAuthEvent;
import cn.openxm.bloguser.domain.user.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Xiao Ma
 * @date 2024/7/14
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Component
public class UserAuthEventImpl implements UserAuthEvent {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthEventImpl.class);

    @Override
    public void onLogin(UserEntity userEntity) {
        this.onRefresh(userEntity);
    }

    @Override
    public void onLogout(UserEntity userEntity) {

    }

    @Override
    public void onRegister(UserEntity userEntity) {
        LOGGER.info("trigger register event userinfo:{}",userEntity);
        this.onRefresh(userEntity);
    }

    @Override
    public void onRefresh(UserEntity userEntity) {
        LOGGER.info("trigger refresh event userinfo:{}",userEntity);
    }
}
