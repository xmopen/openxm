package cn.openxm.response;

import lombok.Getter;

/**
 * author zhenxinma
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
    RESPONSE_FAIL_SYSTEM_ERROR(-1,"system error"),;

    private final Integer code;

    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
