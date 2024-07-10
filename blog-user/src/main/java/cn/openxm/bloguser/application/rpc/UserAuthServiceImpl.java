package cn.openxm.bloguser.application.rpc;

import cn.openxm.common.pojo.po.OpenXmUser;
import cn.openxm.common.service.UserAuthService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 实现 UserAuthService 接口，并添加 @DubboService 对外提供RPC服务。
 *
 * @author Xiao Ma
 * @date 2024/6/23
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@DubboService
public class UserAuthServiceImpl implements UserAuthService {
    @Override
    public String hello() {
        OpenXmUser user = new  OpenXmUser();
        System.out.println("RPC run..");
        return "UserAuthServiceImpl Run...";
    }
}
