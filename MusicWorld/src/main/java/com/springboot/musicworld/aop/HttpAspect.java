package com.springboot.musicworld.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class HttpAspect {


  /*  private static final Logger logger  = (Logger) LoggerFactory.getLogger(HttpAspect.class);

    *//**
     * 这样写是将重复的代码提取出来方便处理
     *//*
    @Pointcut("execution(public * com.kafei.HelloController.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore() {
        logger.info("1");
        logger.error("11");
    }

    @After("log()")
    public void doAfter() {
        logger.info("2");
    }*/


}
