package cn.openxm.common.annotation;

import java.lang.annotation.*;

/**
 * 限流注解，配合SpringMVC的Interceptor使用。
 * 既然是限流，那肯定是要有限流Key进行限流的。
 * <p>
 * author Xiao Ma
 * date 2024/7/3
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * limit 限流次数。
     */
    int limit();

    /**
     * limitKey 限流Key。
     */
    String limitKey() default "";

    /**
     * seconds 限流窗口。
     */
    int window();

    /**
     * needLogin 限流是否针对用户登录。
     */
    boolean needLogin() default false;
}
