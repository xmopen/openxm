package cn.openxm.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * author Xiao Ma
 * date 2024/6/23
 */
@Data
@AllArgsConstructor
public class Response<T> implements Serializable {

    public Integer code;

    public String msg;

    public T data;

    /**
     * success 返回成功状态码通过要返回的数据
     * TODO: data 如果为空，返回JSON.data则为null，必须进行处理。
     * */
    public static <T> Response<T> success(T data) {
        return new Response<T>(ResponseEnum.RESPONSE_SUCCESS.getCode(), ResponseEnum.RESPONSE_SUCCESS.getMessage(),
                data);
    }

    /**
     * fail 根据给定的ResponseEnum将其构造成Fail的结构进行传输给上层服务。
     * */
    public static <T> Response<T> fail(ResponseEnum responseEnum) {
        return new Response<T>(responseEnum.getCode(), responseEnum.getMessage(), null);
    }

    // TODO: 其实这里应该将Exception转换为目标Response。
}
