package com.springboot.musicworld.config;

import com.springboot.musicworld.interceptor.ContextPathInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration  extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new ContextPathInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
