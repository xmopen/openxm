package cn.openxm.bloguser.application.manager;

import cn.openxm.bloguser.domain.mail.model.MailEntity;
import cn.openxm.bloguser.domain.mail.service.MailDomain;
import cn.openxm.bloguser.domain.user.model.UserEntity;
import cn.openxm.bloguser.domain.user.service.UserDomain;
import cn.openxm.bloguser.infrastructure.icon.RandomIcon;
import cn.openxm.bloguser.infrastructure.util.jwt.JwtUtil;
import cn.openxm.common.randome.UniqueRandom;
import cn.openxm.common.response.Response;
import cn.openxm.common.response.ResponseEnum;
import jakarta.servlet.http.HttpServletResponse;
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

    private final MailDomain mailDomain;

    private final JwtUtil jwtUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthManager.class);

    public UserAuthManager(UserDomain userDomain,MailDomain mailDomain,JwtUtil jwtUtil) {
        this.userDomain = userDomain;
        this.mailDomain = mailDomain;
        this.jwtUtil = jwtUtil;
    }

    public Response<String> register(UserEntity userEntity){
        if (!userEntity.checkRegisterInfoIsValid()){
            return Response.fail(ResponseEnum.RESPONSE_FAIL_USER_REGISTER_INFO_NOT_MATCH);
        }
        LOGGER.info("register user data:{}",userEntity);
        // verify that the mail code matches successfully.
        if (userEntity.mailCodeIsNotMatch(this.mailDomain
                .getMailCodeInfo(MailEntity.
                        builder().
                        to(userEntity.getMail())
                        .build())
                .getCode())) {
            return Response.fail(ResponseEnum.RESPONSE_FAIL_USER_MAIL_CODE_NOT_MATCH);
        }

        LOGGER.info("user:[{}] icon:[{}]",userEntity.getMail(),userEntity.getIcon());
        try {
            this.userDomain.register(userEntity);
            return Response.success(userEntity.getJwtToken());
        } catch (Exception e){
            LOGGER.error("user{} register error:{}",userEntity,e.getMessage());
            return Response.fail(ResponseEnum.RESPONSE_FAIL_USER_REGISTER_ERROR);
        }
    }

}
