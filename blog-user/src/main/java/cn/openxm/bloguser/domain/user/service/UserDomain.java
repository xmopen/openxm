package cn.openxm.bloguser.domain.user.service;

import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.common.response.Response;

/**
 * @author Xiao Ma
 * @date 2024/7/13
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public interface UserDomain {

    /**
     * register 用户注册信息。
     * @param  userEntity 用户实体。
     * @return 注册响应结果。
     * */
    Response<Object> register(UserEntity userEntity);

    /**
     * login 用户登录。
     * @param userEntity 用户登录实体。
     * @return 登录结果。
     * */
    Response<Object> login(UserEntity userEntity);

}
