package cn.openxm.bloguser.service.impl;

import cn.openxm.service.UserAuthService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * author zhenxinma
 * date 2024/6/23
 */
@DubboService
public class UserAuthServiceImpl implements UserAuthService {
    @Override
    public String hello() {
        System.out.println("RPC run..");
        return "UserAuthServiceImpl Run...";
    }
}
