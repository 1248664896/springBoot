package com.springboot.musicworld.dao;

import com.springboot.musicworld.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {


    public Admin login(@Param("loginName") String loginName , @Param("password")  String password);

    public Admin getAdminById(String id);

    public String   returnId(String loginName);

    public String   returnPassword(String id );

    public int updateName(Admin admin);

    public int updatePassword( @Param("oldPassword") String oldPassword,@Param("id")  String id,@Param("newPassword") String newPassword );

    public int addAdmin(Admin admin);

    public int changePassword(String passwrod , String id );

    public Admin verifyTwoPassword(@Param("oldPassword") String oldPassword ,String id);



}
