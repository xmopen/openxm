package cn.openxm.bloguser;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * author Xiao Ma
 * date 2024/6/23
 */
@EnableDubbo
@MapperScan(value = "cn.openxm.bloguser.dao")
@SpringBootApplication
public class BlogUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogUserApplication.class, args);
    }

}
