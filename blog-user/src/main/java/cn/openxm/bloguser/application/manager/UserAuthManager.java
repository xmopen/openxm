package cn.openxm.bloguser.application.manager;

import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.bloguser.domain.user.service.UserDomain;
import cn.openxm.bloguser.infrastructure.icon.RandomIcon;
import cn.openxm.common.response.Response;
import cn.openxm.common.response.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Service
public class UserAuthManager {

    private final UserDomain userDomain;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthManager.class);

    public UserAuthManager(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    public Response<Object> register(UserEntity userEntity){
        if (!userEntity.checkRegisterInfoIsValid()){
            return Response.fail(ResponseEnum.RESPONSE_FAIL_USER_REGISTER_INFO_NOT_MATCH);
        }
        userEntity.setIcon(RandomIcon.getRandomIcon());
        LOGGER.info("user:[{}] icon:[{}]",userEntity.getMail(),userEntity.getIcon());
        try {
            return this.userDomain.register(userEntity);
        } catch (Exception e){
            LOGGER.error("user{} register error:{}",userEntity,e.getMessage());
            return Response.fail(ResponseEnum.RESPONSE_FAIL_USER_REGISTER_ERROR);
        }
    }

}
