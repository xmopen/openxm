package cn.openxm.bloguser;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Xiao Ma
 * @date 2024/6/23
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@EnableDubbo
@EnableWebMvc
@MapperScan(value = "cn.openxm.bloguser.infrastructure.dao")
@ComponentScan(value = "cn.openxm")
@SpringBootApplication
public class BlogUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogUserApplication.class, args);
    }

}
