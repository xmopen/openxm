package cn.openxm.bloguser.controller;

import cn.openxm.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserAuthController 用户鉴权控制器。
 * 提供登录、注册、校验登录态三种接口。
 * 注意：所有路径前缀为：/openxm/api/v1/xxx。
 *
 * author Xiao Ma
 * date 2024/6/26
 */
@RestController
@RequestMapping(value = "/auth")
public class UserAuthController {

    @PostMapping(value = "/register")
    public Response<Object> userRegister(){
        return null;
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
