package cn.openxm.bloguser.domain.user.service.impl;

import cn.openxm.bloguser.domain.user.event.UserAuthEvent;
import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.bloguser.domain.user.repository.UserRepository;
import cn.openxm.bloguser.domain.user.service.UserDomain;
import cn.openxm.bloguser.infrastructure.icon.RandomIcon;
import cn.openxm.common.response.Response;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Ma
 * @date 2024/7/13
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Service
public class UserDomainImpl implements UserDomain {

    private final UserAuthEvent userAuthEvent;

    private final UserRepository userRepository;

    public UserDomainImpl(UserAuthEvent userAuthEvent,UserRepository userRepository) {
        this.userAuthEvent = userAuthEvent;
        this.userRepository = userRepository;
    }

    @Override
    public Response<Object> register(UserEntity userEntity) {
        // 注册。
        userEntity.setIcon(RandomIcon.getRandomIcon());
        this.userRepository.save(userEntity);
        this.userAuthEvent.register(userEntity);
        return null;
    }

    @Override
    public Response<Object> login(UserEntity userEntity) {


        this.userAuthEvent.login(userEntity);
        return null;
    }
}
