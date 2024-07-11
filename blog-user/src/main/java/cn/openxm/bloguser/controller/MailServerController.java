package cn.openxm.bloguser.controller;

import cn.openxm.bloguser.application.manager.UserMailManager;
import cn.openxm.bloguser.controller.dto.UserMailDTO;
import cn.openxm.common.annotation.RateLimit;
import cn.openxm.common.response.Response;
import org.springframework.web.bind.annotation.*;

/**
 * 路径前缀：/openxm/api/v1/xxx。
 * /openxm/api/v1/mail/generate/code
 *
 * @author Xiao Ma
 * @date 2024/6/27
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@RestController
@RequestMapping(value = "/mail")
public class MailServerController {

    private final UserMailManager userMailManager;

    public MailServerController(UserMailManager userMailManager) {
        this.userMailManager = userMailManager;
    }

    /**
     * generateMailCode 针对用户的邮件生成一个随机的验证码，并保留15分钟。
     * @RequestBody UserMailDTO userMailDTO
     * */
    @PostMapping(value = "/generate/code")
    @RateLimit(limit = 1,limitKey = "user.mail",window = 1000*60)
    public Response<String> generateMailCode(@RequestBody UserMailDTO userMailDTO){
        return this.userMailManager.generateMailCode(userMailDTO.toDomainEntity());
    }
}
