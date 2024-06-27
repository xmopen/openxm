package cn.openxm.response;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * author Xiao Ma
 * date 2024/6/23
 */
public class CustomTest {
    @Test
    public void test() {
        SecureRandom random = new   SecureRandom();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000,9999));
        }
    }
}
