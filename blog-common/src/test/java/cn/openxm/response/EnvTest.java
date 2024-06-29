package cn.openxm.response;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.Map;

/**
 * author Xiao Ma
 * date 2024/6/25
 */
public class EnvTest {

    /**
     *  测试系统环境变量。
     *
     *     username: ${DATA_SOURCE_USER_NAME}
     *     password: ${DATA_SOURCE_USER_PASSWORD}
     *
     * */
    @Test
    public void testSystemEnvironment() {
        Map<String, String> env = System.getenv();
        for (Map.Entry<String, String> entry : env.entrySet()) {
//            if (entry.getKey().equals("DATA_SOURCE_USER_NAME")) {
//                System.out.println(entry.getValue());
//            }
//            if (entry.getKey().equals("DATA_SOURCE_USER_PASSWORD")) {
//                System.out.println(entry.getValue());
//            }
//            if (entry.getKey().equals("JAVA_HOME")) {
//                System.out.println(entry.getValue());
//            }
            if (entry.getKey().equals("DATA_REDIS_PASSWORD")) {
                System.out.println(entry.getValue());
            }
        }
    }

}
