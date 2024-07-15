package cn.openxm.bloguser.controller;

import cn.openxm.bloguser.application.manager.UserAuthManager;
import cn.openxm.bloguser.constant.TransformConstant;
import cn.openxm.bloguser.controller.dto.UserAuthDTO;
import cn.openxm.common.response.Response;
import cn.openxm.common.service.UserAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * UserAuthController 用户鉴权控制器。
 * 提供登录、注册、校验登录态三种接口。
 * 注意：所有路径前缀为：/openxm/api/v1/xxx。
 *
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@RestController
@RequestMapping(value = "/user/auth")
public class UserAuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthController.class);

    private final UserAuthManager userAuthManager;

    public UserAuthController(UserAuthManager userAuthManager) {
        this.userAuthManager = userAuthManager;
    }

    @PostMapping(value = "/register")
    public Response<String> userRegister(@RequestBody UserAuthDTO userAuthDTO, HttpServletResponse response){
        Response<String> result =  this.userAuthManager.register(userAuthDTO.toDomainEntity());
        this.addTokenToCookie(response,result.getData());
        LOGGER.info("register token:{}",result.getData());
        return result;
    }

    private void addTokenToCookie(HttpServletResponse response, String token){
        response.addCookie(new Cookie(TransformConstant.TRANSFORM_COOKIE_NAME_TOKEN_KEY, token));
    }

    @PostMapping(value = "/login")
    public Response<Object> userLogin(){
        return null;
    }

    @GetMapping(value = "/check/session")
    public Response<Object> checkSession(){
        return null;
    }
}
