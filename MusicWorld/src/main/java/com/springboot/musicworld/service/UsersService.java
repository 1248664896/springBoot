package com.springboot.musicworld.service;

import com.alibaba.fastjson.JSONObject;
import com.springboot.musicworld.entity.Messages;
import com.springboot.musicworld.entity.Users;
import com.springboot.musicworld.utils.PageQueryUtil;
import com.springboot.musicworld.utils.PageResult;

public interface UsersService {

    public JSONObject register(String mobilePhone , String userName,String password,String id);


    /**
     * 用户分页
     * @param pageQueryUtil
     * @return
     */
    public PageResult getUserListPage(PageQueryUtil pageQueryUtil );

 }
