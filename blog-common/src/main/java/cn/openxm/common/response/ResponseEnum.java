package cn.openxm.common.response;

import lombok.Getter;

/**
 * 错误码分类。
 * 1、0-10 服务内部基础异常。
 * 2、1000-2000: 用户服务异常。
 * 3、2000-3000: 博客服务异常。
 * 4、。。。。。 每次新添加一个服务这里必须进行注释。
 *
 * @author Xiao Ma
 * @date 2024/6/23
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Getter
public enum ResponseEnum {

    //***************************************************基础异常分类*****************************************************
    /**
     * RESPONSE_SUCCESS 成功响应请求
     * */
    RESPONSE_SUCCESS(0,"success"),

    /**
     * RESPONSE_FAIL_SYSTEM_ERROR 系统内部异常错误码，用来表示内部通用异常
     * */
    RESPONSE_FAIL_SYSTEM_ERROR(1,"系统异常，请稍后重试！"),

    /**
     * RESPONSE_FAIL_INTERFACE_LIMIT 接口限流。
     * */
    RESPONSE_FAIL_INTERFACE_LIMIT(2,"接口达到访问次数限制，请稍后重试！"),


    //*************************************************用户服务异常分类***************************************************

    /**
     * RESPONSE_FAIL_USER_MAIL_IS_EMPTY 用户服务中用户的邮箱为空。
     * */
    RESPONSE_FAIL_USER_MAIL_IS_EMPTY(1000,"用户邮箱为空，请重新检查后进行重试。"),

    /**
     * RESPONSE_FAIL_USER_REGISTER_INFO_NOT_MATCH 输入的用户信息有误。
     * */
    RESPONSE_FAIL_USER_REGISTER_INFO_NOT_MATCH(10001,"输入的注册用户信息有误，请校验之后重试！"),

    /**
     * RESPONSE_FAIL_USER_REGISTER_ERROR 用户注册失败。
     * */
    RESPONSE_FAIL_USER_REGISTER_ERROR(10002,"用户注册失败，请稍后在进行重试。"),

    ;

    private final Integer code;

    private final String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
