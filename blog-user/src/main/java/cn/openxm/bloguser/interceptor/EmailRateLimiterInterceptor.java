package cn.openxm.bloguser.interceptor;

import cn.openxm.bloguser.infrastructure.limit.SlidingWindowRateLimit;
import cn.openxm.common.annotation.RateLimit;
import cn.openxm.common.response.Response;
import cn.openxm.common.response.ResponseEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * @author Xiao Ma
 * @date 2024/6/30
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Component
public class EmailRateLimiterInterceptor implements HandlerInterceptor {

    private final SlidingWindowRateLimit slidingWindowRateLimit;

    public EmailRateLimiterInterceptor(SlidingWindowRateLimit slidingWindowRateLimit) {
        this.slidingWindowRateLimit = slidingWindowRateLimit;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod method) {
            if (this.slidingWindowRateLimit.limitWithRedis(method.getMethodAnnotation(RateLimit.class),
                    this.getRemoteClientIp(request))) {
                return true;
            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(Response.fail(ResponseEnum.RESPONSE_FAIL_INTERFACE_LIMIT));
            return false;
        }
        return true;
    }

    /**
     * getRemoteClientIp 获取远端客户端IP。
     * 如果没有经过代理则取remoteAddr，如果经过代理，应该确认代理哪里有转发客户端真实的IP。
     * 我的Nginx转发配置：
     *         location /openxm/api/v1 {
     *             proxy_pass http://192.168.49.2:30387; # 后端服务器地址
     *             proxy_set_header Host $host;
     *             proxy_set_header X-Real-IP $remote_addr; # 这里会将客户端的真实地址赋值给Header中的X-Real-IP
     *             proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
     *             proxy_set_header X-Forwarded-Proto $scheme;
     *         }
     * */
    private String getRemoteClientIp(HttpServletRequest request) {
        String realIp =  request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isEmpty()) {
            return realIp;
        }
        return  request.getRemoteAddr();
    }
}
