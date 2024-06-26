package cn.openxm.response;

import lombok.Getter;

/**
 * 错误码分类。
 * 1、0-10 服务内部基础异常。
 * 2、1000-2000: 用户服务异常。
 * 3、2000-3000: 博客服务异常。
 * 4、。。。。。 每次新添加一个服务这里必须进行注释。
 * author Xiao Ma
 * date 2024/6/23
 */
@Getter
public enum ResponseEnum {

    /**
     * RESPONSE_SUCCESS 成功响应请求
     * */
    RESPONSE_SUCCESS(0,"success"),

    /**
     * RESPONSE_FAIL_SYSTEM_ERROR 系统内部异常错误码，用来表示内部通用异常
     * */
    RESPONSE_FAIL_SYSTEM_ERROR(-1,"system error"),

    /**
     * RESPONSE_FAIL_USER_MAIL_IS_EMPTY 用户服务中用户的邮箱为空。
     * */
    RESPONSE_FAIL_USER_MAIL_IS_EMPTY(1000,"用户邮箱为空，请重新检查后进行重试。"),;

    private final Integer code;

    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
