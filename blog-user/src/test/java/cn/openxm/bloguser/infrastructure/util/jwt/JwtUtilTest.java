package cn.openxm.bloguser.infrastructure.util.jwt;

import cn.openxm.bloguser.BlogUserApplication;
import cn.openxm.bloguser.domain.user.model.UserEntity;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xiao Ma
 * @date 2024/7/15
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@SpringBootTest(classes = {BlogUserApplication.class})
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void test() {
        String token =  jwtUtil.generateTokenWithUserData(
                UserEntity.builder()
                        .id(999).
                        mail("2280480546@qq.com")
                        .build());
        System.out.println(token);
        Claims result =  jwtUtil.getClaimsFromJwtToken(token);
        System.out.println(result);
    }

}
