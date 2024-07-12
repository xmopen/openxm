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
        try {
            int result = 1/0;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
