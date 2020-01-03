package com.springboot.musicworld.service.impl;

import com.springboot.musicworld.dao.AdminMapper;
import com.springboot.musicworld.entity.Admin;
import com.springboot.musicworld.entity.Messages;
import com.springboot.musicworld.service.AdminService;
import com.springboot.musicworld.utils.MD5Util;
import com.springboot.musicworld.utils.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin login(String loginName, String password) {
        String  passwordMD5 = MD5Util.MD5Encode(password,"UTF-8");
        Admin admin =  adminMapper.login(loginName,passwordMD5);
        return admin;
    }


    @Override
    public Admin getAdminById(String id) {
        Admin admin =  adminMapper.getAdminById(id);
        return admin;
    }

    @Override
    public Messages loginCheck(String loginName, String password) {
        Messages messages = new Messages();
        String id = adminMapper.returnId(loginName);
        if(id!=null){
            String sqlPassword = adminMapper.returnPassword(id);
            if(sqlPassword==null){
                messages.setResult(false);
                messages.setMessage("密码错误");
            }
            if(sqlPassword.equals(password)){
                messages.setResult(true);
                messages.setMessage("登录成功");
            }else{
                messages.setResult(false);
                messages.setMessage("密码错误");
            }
        }else{
            messages.setResult(false);
            messages.setMessage("账号不存在");
        }
        return messages;
    }

    @Override
    public boolean updateName(Admin admin) {
        int i = adminMapper.updateName(admin);
        if(i>0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean updatePassword(String oldPassword,String id,String newPassword) {
        String oldPasswordMD5 = MD5Util.MD5Encode(oldPassword,"UTF-8");
        String newPasswordMD5 = MD5Util.MD5Encode(newPassword,"UTF-8");
        int i = adminMapper.updatePassword(oldPasswordMD5,id,newPasswordMD5);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(String oldPassword,String id,String newPassword) {
        Admin admin = adminMapper.verifyTwoPassword(oldPassword,id);
        if(PublicUtil.judgeObject(admin)){
            String newPasswordMD5 = MD5Util.MD5Encode(newPassword,"UTF-8");
            int i = adminMapper.changePassword(newPasswordMD5,id);
            if(i>0){
                return true;
            }
            return false;
        }else{
            System.out.println("原密码不一致");
            return false;
        }
    }

    //TUDO
    @Override
    public boolean verifyTwoPassword(String passwordA, String passowrdB) {
        return false;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        int i = adminMapper.addAdmin(admin);
        if(i>0)
            return true;
        return false;
    }


}
