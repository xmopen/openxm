package cn.openxm.blogcontent.controller;

import cn.openxm.response.Response;
import cn.openxm.service.UserAuthService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author zhenxinma
 * date 2024/6/23
 */

@Component
@RestController()
public class ProbeController {

    @DubboReference
    private UserAuthService userAuthService;

    @RequestMapping("/test")
    public Response<String> healthProbe(){
        System.out.println("Hello Word");
        return Response.success(userAuthService.hello());
    }
}
