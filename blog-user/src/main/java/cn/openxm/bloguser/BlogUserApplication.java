package cn.openxm.bloguser;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class BlogUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogUserApplication.class, args);
    }

}
