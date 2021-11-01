package com.zwj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zwj
 * @date 2021/10/30 - 23:16
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**").addResourceLocations("file:/data/upload/");
        registry.addResourceHandler("css/**").addResourceLocations("classpath:static/css/");
        registry.addResourceHandler("js/**").addResourceLocations("classpath:static/js/");
    }
}
