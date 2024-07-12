package cn.openxm.bloguser.domain.user.service.impl;

import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.bloguser.domain.user.service.UserDomain;
import cn.openxm.common.response.Response;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Ma
 * @date 2024/7/13
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Service
public class UserDomainImpl implements UserDomain {

    @Override
    public Response<Object> register(UserEntity userEntity) {
        return null;
    }

    @Override
    public Response<Object> login(UserEntity userEntity) {
        return null;
    }
}
