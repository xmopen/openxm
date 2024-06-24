package cn.openxm.blogcontent;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableDubbo
@SpringBootApplication
public class BlogContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogContentApplication.class, args);
    }


}
