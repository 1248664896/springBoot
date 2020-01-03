package com.springboot.musicworld.utils;

import java.util.UUID;

public class PublicUtil {


    /**
     * 判断对象是否存在
     * @param object
     * @return  存在返回true 不存再返回 false
     */
    public  static  boolean judgeObject(Object object){
        if(object!=null){
            return true;
        }else{
            return false;
        }
    }


    /**
     *获取uUID
     * @return
     */
    public static  String getUUID(){
        return  UUID.randomUUID().toString();
    }






}
