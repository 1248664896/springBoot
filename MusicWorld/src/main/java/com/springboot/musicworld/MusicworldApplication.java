package com.springboot.musicworld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@MapperScan("com.springboot.musicworld.dao")
@SpringBootApplication
public class MusicworldApplication extends SpringBootServletInitializer {


    /**
     * 在tomcat上部署使用
     * @param args
     */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(MusicworldApplication.class);
//    }


    public static void main(String[] args) {
        SpringApplication.run(MusicworldApplication.class, args);

    }



}
