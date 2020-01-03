package com.springboot.musicworld.dao;

import com.springboot.musicworld.entity.Users;
import com.springboot.musicworld.utils.PageQueryUtil;

import java.util.List;

public interface UsersMapper {

    public int register(String mobilePhone , String userName,String password,String id);

    public String  mobilePhoneExist(String  mobilePhone);

    public String   userNameExist(String  userName);

    public List<Users> findUserList(PageQueryUtil pageQueryUtil );

    public int getTolUsers(PageQueryUtil pageUtil);


}
