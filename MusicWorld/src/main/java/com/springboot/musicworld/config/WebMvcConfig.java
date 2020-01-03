package com.springboot.musicworld.config;


import com.springboot.musicworld.common.Constants;
import com.springboot.musicworld.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    public LoginInterceptor loginInterceptor ;

//    public void addInterceptors(InterceptorRegistry registry) {
//        // 添加一个拦截器，拦截以/admin为前缀的url路径（后台登陆拦截）
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/admin/**")
//               .excludePathPatterns("/admin/login") ; //排除
////                .excludePathPatterns("/admin/")
////                .excludePathPatterns("/admin/plugins/**");
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/music/**");
////                .excludePathPatterns("/admin/login")
////                .excludePathPatterns("/admin/")
////                .excludePathPatterns("/admin/plugins/**");
//    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/musicWorld/imgFile/**")
                .addResourceLocations("file:" + Constants.MUSIC_IMG_FILE_PATH);
    }

}
