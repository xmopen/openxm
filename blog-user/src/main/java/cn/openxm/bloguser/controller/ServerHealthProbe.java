package cn.openxm.bloguser.controller;

import cn.openxm.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意：所有路径前缀为：/openxm/api/v1/xxx。
 *
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@RestController
@RequestMapping(value = "/user")
public class ServerHealthProbe {

    /**
     * 接口：/openxm/api/v1/user/probe
     * 对外暴露HTTP GET接口，用于Kubernetes进行健康检查。
     * 具体会在当前服务的Pod.yaml文件中指定该接口。
     *
     * */
    @GetMapping(value = "/probe")
    public Response<String> probe(){
        return Response.success("health");
    }
}
