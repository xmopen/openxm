package cn.openxm.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO: 其实这里应该将Exception转换为目标Response。
 *
 * @author Xiao Ma
 * @date 2024/6/23
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Data
@AllArgsConstructor
public class Response<T> implements Serializable {

    public Integer code;

    public String msg;

    public T data;

    /**
     * success 返回成功状态码通过要返回的数据
     * */
    public static <T> Response<T> success(T data) {
        return new Response<T>(ResponseEnum.RESPONSE_SUCCESS.getCode(), ResponseEnum.RESPONSE_SUCCESS.getMessage(),
                data);
    }

    /**
     * fail 根据给定的ResponseEnum将其构造成Fail的结构进行传输给上层服务。
     * @param responseEnum 本次返回的枚举，如果为空，则使用默认的系统异常进行返回。
     * */
    public static <T> Response<T> fail(ResponseEnum responseEnum) {
        if (responseEnum == null) {
            responseEnum = ResponseEnum.RESPONSE_FAIL_SYSTEM_ERROR;
        }
        return new Response<T>(responseEnum.getCode(), responseEnum.getMessage(), null);
    }

}
