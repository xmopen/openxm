package cn.openxm.response;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * author zhenxinma
 * date 2024/6/23
 */
public class ResponseTest {

    /**
     * testResponseObject 测试Response对象序列化是否正常。
     * */
    @Test
    void testResponseObject(){
        Response<String> resp = new Response<>(0,"","Hello Word");
        System.out.println(JSON.toJSONString(resp));
    }

    @Test
    void testResponseSuccess(){
        System.out.println(Response.success(null));
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        System.out.println(Response.success(list));
    }

}
