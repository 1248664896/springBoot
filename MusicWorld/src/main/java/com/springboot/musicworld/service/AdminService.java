package com.springboot.musicworld.service;

import com.springboot.musicworld.entity.Admin;
import com.springboot.musicworld.entity.Messages;

/**
 * 管理员
 */
public interface AdminService {


    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    public Admin login(String loginName ,String password);


    /**
     * 根据id查找admin对象
     * @param id
     * @return
     */
    public  Admin getAdminById(String id) ;


    /*
     *登录
     * @param loginName
     * @param password
     * @return
     */
    public Messages  loginCheck(String loginName , String password );


    /**
     * 修改昵称
     * @param admin
     * @return
     */
    public boolean updateName(Admin admin);


    /**
     * 更改密码
     * @param
     * @return
     */
    public boolean updatePassword(String oldPassword,String id,String newPassword);


    /**
     * 修改密码
     * @param
     * @param id
     * @return
     */
    public  boolean changePassword(String oldPassword,String id,String newPassword );


    /**
     * 验证两次密码
     * @param passwordA
     * @param passowrdB
     * @return
     */
    public boolean verifyTwoPassword(String passwordA ,String passowrdB);


    /**
     * 添加admin
     * @param admin
     * @return
     */
    public boolean addAdmin(Admin admin);

}
