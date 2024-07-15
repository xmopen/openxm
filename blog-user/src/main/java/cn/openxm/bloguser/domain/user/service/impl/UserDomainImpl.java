package cn.openxm.bloguser.domain.user.service.impl;

import cn.openxm.bloguser.domain.user.event.UserAuthEvent;
import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.bloguser.domain.user.repository.UserRepository;
import cn.openxm.bloguser.domain.user.service.UserDomain;
import cn.openxm.bloguser.infrastructure.icon.RandomIcon;
import cn.openxm.bloguser.infrastructure.util.jwt.JwtUtil;
import cn.openxm.common.randome.UniqueRandom;
import cn.openxm.common.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Ma
 * @date 2024/7/13
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Service
public class UserDomainImpl implements UserDomain {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDomainImpl.class);

    private final UserAuthEvent userAuthEvent;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public UserDomainImpl(UserAuthEvent userAuthEvent,UserRepository userRepository,JwtUtil jwtUtil) {
        this.userAuthEvent = userAuthEvent;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(UserEntity userEntity) {
        // perfect the userEntity before registration.
        userEntity.setToken(UniqueRandom.nextUuid());
        userEntity.setJwtToken(this.jwtUtil.generateTokenWithUserData(userEntity));
        userEntity.setIcon(RandomIcon.getRandomIcon());
        LOGGER.info("fill user info token:{}, jwt token:{}",userEntity.getToken(),userEntity.getJwtToken());

        // to register.
        this.userRepository.save(userEntity);
        this.userAuthEvent.onRegister(userEntity);
    }

    @Override
    public Response<Object> login(UserEntity userEntity) {
        this.userAuthEvent.onLogin(userEntity);
        return null;
    }
}
