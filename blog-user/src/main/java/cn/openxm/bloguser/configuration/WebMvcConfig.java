package cn.openxm.bloguser.configuration;

import cn.openxm.bloguser.interceptor.EmailRateLimiterInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * WebMvcConfig 增加针对WebMvc的一些特定配置。
 *
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final EmailRateLimiterInterceptor emailRateLimiterInterceptor;

    public WebMvcConfig(EmailRateLimiterInterceptor emailRateLimiterInterceptor) {
        this.emailRateLimiterInterceptor = emailRateLimiterInterceptor;
    }

    /**
     * Cors 配置：针对所有路径、所有来源、所有请求方法、所有请求头都允许进行访问，同时支持Cookie以及最大缓存时间为一个小时。
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://openxm.cn","http://127.0.0.1")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的 HTTP 方法
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Bean
    public HttpMessageConverter<Object> fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullStringAsEmpty);

        // 设置字符编码
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 中文乱码
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        return fastConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(fastJsonHttpMessageConverter());
    }

    /**
     * addInterceptors 针对指定路径添加拦截器。注意这里的路径是SpringMVC的路径，而不是Servlet的路径。
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.emailRateLimiterInterceptor).addPathPatterns("/mail/generate/code");
    }
}
