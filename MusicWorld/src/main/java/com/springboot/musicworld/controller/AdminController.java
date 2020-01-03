package com.springboot.musicworld.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.musicworld.entity.Admin;
import com.springboot.musicworld.entity.Messages;
import com.springboot.musicworld.service.AdminService;
import com.springboot.musicworld.utils.MD5Util;
import com.springboot.musicworld.utils.PublicUtil;
import com.springboot.musicworld.utils.ServiceResultErum;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Resource
    public  AdminService adminService ;



    @RequestMapping("/index")
    public String index(){
        return "/admin/index";
    }


    @GetMapping("/login")
    public String goLogin( HttpSession session){
        session.setAttribute("msg","欢迎进入");
        return "/admin/login";//两个login*.html选择其一
    }


    @PostMapping("/post/login")
    public String loginA(@RequestParam String loginName , @RequestParam String password  , HttpSession session ){
        if(StringUtils.isEmpty(loginName)&&StringUtils.isEmpty(password)){
            session.setAttribute("msg",ServiceResultErum.NotEmpty.getResult());
        }
        Admin admin =  adminService.login(loginName,password);
        if(PublicUtil.judgeObject(admin)){
            session.setAttribute("admin",admin);
            session.setAttribute("id", admin.getId());
            session.setAttribute("name", admin.getName());
            return "/admin/index";//redirect:重定向
        }else{
            session.setAttribute("msg",ServiceResultErum.failure.getResult());
            return "/admin/login";
        }
    }


    /**
     * 登录
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/loginOperation")
    public String login(@RequestParam String loginName , @RequestParam String password , HttpSession session){
        JSONObject json = new JSONObject();
        if(StringUtils.isEmpty("loginName")||StringUtils.isEmpty("password")){
            session.setAttribute("msg","登录名或者密码不能为空");
            return "/admin/login";
        }else{
            Messages messages = adminService.loginCheck(loginName,password);
            if(messages.isResult()){
                Admin admin = adminService.login(loginName,password);
                if(PublicUtil.judgeObject(admin)){
                    session.setAttribute("admin",admin);
                    session.setAttribute("id", admin.getId());
                    session.setAttribute("name", admin.getName());
                    return "/admin/index";//redirect:重定向
                }else{
                    session.setAttribute("msg",messages.getMessage());
                    //return "redirect:/admin/login";
                    return "/admin/login";
                }
            }else{
                session.setAttribute("msg",messages.getMessage());
                return "/admin/login";
            }
        }
    }







    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public  String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }


    /**
     * 跳转修改密码
     * @param request
     * @return
     */
    @RequestMapping("/goPasswrod")
    public  String goPasswrod(HttpServletRequest request){
        String  adminId = (String) request.getSession().getAttribute("id");
        Admin admin = adminService.getAdminById(adminId) ;
        if (PublicUtil.judgeObject(admin)){
            request.setAttribute("path", "profile");
            request.setAttribute("loginName",admin.getLoginName());
            request.setAttribute("name",admin.getName());
            request.setAttribute("superAccount",admin.getSuperAccount());
            request.setAttribute("id",adminId);
            return "/admin/profile";
        }else{
            return "/admin/login";
        }
    }


    /**
     * 音乐信息
     * @param request
     * @return
     */
    @GetMapping("/music/edit")
    public String musicEdit(HttpServletRequest request){
        request.setAttribute("path", "edit");
        return "";
    }


    @RequestMapping("/updateName")
    @ResponseBody
    public  boolean updateName(@RequestParam("name") String name,HttpSession session,HttpServletRequest request){
        String id = (String) session.getAttribute("id");
        Admin admin = new Admin();
        System.out.println("name----------------->"+name);
        System.out.println("id----------------->"+id);
        admin.setId(id);
        admin.setName(name);
       boolean result =  adminService.updateName(admin);
        if(result){
            return true;
        }else{
            return false;
        }
    }


    @RequestMapping("/updatePassword")
    @ResponseBody
    public  boolean updatePassword(@RequestParam("newPassword") String newPassword,@RequestParam("oldPassword") String oldPassword,HttpSession session,HttpServletRequest request){
        String id = (String) session.getAttribute("id");

        System.out.println("oldPassword----------------->"+oldPassword);
        System.out.println("newPassword----------------->"+newPassword);
        System.out.println("id----------------->"+id);
        boolean result =  adminService.updatePassword(oldPassword,id,newPassword);
        if(result){
            session.removeAttribute("admin");
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/changePassword")
    @ResponseBody
    public  boolean changePassword(@RequestParam("newPassword") String newPassword,@RequestParam("oldPassword") String oldPassword,HttpSession session,HttpServletRequest request){
        String id = (String) session.getAttribute("id");

        System.out.println("oldPassword----------------->"+oldPassword);
        System.out.println("newPassword----------------->"+newPassword);
        System.out.println("id----------------->"+id);
        String oldPasswordMD5 =  MD5Util.MD5Encode(oldPassword,"UTF-8");
        String newPasswordMD5 =  MD5Util.MD5Encode(newPassword,"UTF-8");
        boolean result =  adminService.changePassword(oldPasswordMD5,id,newPasswordMD5);
        if(result){
            session.removeAttribute("admin");
            return true;
        }else{

            return false;
        }
    }




    @RequestMapping("/addAdmin")
    @ResponseBody
    public String  addAdmin(Admin admin,@RequestParam("loginName") String loginName ,@RequestParam("password") String password){
        admin.setId(PublicUtil.getUUID());
        admin.setLoginName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password,"UTF-8");
        admin.setPassword(passwordMD5);
       boolean result =  adminService.addAdmin(admin);
       if(result){
           return ServiceResultErum.SUCCESS.getResult();
       }else{
           return ServiceResultErum.failure.getResult();
       }
    }





}



