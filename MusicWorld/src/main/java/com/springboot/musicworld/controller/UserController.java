package com.springboot.musicworld.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.musicworld.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    public UsersService usersService ;

    @RequestMapping("/login")
    public String login(){
        return "/users/login";
    }


    @RequestMapping("/logins")
    public String logins(){
        return "/index/login";
    }


    @RequestMapping("/goRegister")
    public String goRegister(){
        return "/users/register";
    }


    @RequestMapping("/register")
    public JSONObject register(@RequestParam("userName") String userName,
                               @RequestParam("mobilePhone") String mobilePhone,
                               @RequestParam("password")String password ){
        //JSONObject  = new JSONObject();
        String  UUid = UUID.randomUUID().toString();
        JSONObject json =  usersService.register(userName,mobilePhone,password,UUid);
        return json;
    }


    @GetMapping("/usersPage")
    public String usersPage(HttpServletRequest request) {
        request.setAttribute("path", "users");
        return "/admin/user_list";
    }




}
