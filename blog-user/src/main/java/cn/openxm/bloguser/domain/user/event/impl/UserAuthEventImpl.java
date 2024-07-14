package cn.openxm.bloguser.domain.user.event.impl;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
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
    public void login(UserEntity userEntity) {
        this.refresh(userEntity);
    }

    @Override
    public void logout(UserEntity userEntity) {

    }

    @Override
    public void register(UserEntity userEntity) {
        this.refresh(userEntity);
    }

    @Override
    public void refresh(UserEntity userEntity) {

    }
}
