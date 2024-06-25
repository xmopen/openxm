package cn.openxm.bloguser.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig 增加针对WebMvc的一些特定配置。
 * <p>
 * author Xiao Ma
 * date 2024/6/26
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Cors 配置：针对所有路径、所有来源、所有请求方法、所有请求头都允许进行访问，同时支持Cookie以及最大缓存时间为一个小时。
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的 HTTP 方法
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
