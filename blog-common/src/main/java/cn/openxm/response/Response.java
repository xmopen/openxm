package cn.openxm.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * author zhenxinma
 * date 2024/6/23
 */

@Data
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final Integer RESPONSE_SUCCESS_CODE = 0;

    private static final String RESPONSE_SUCCESS_MSG = "success";

    public Integer code;

    public String msg;

    public T data;

}
