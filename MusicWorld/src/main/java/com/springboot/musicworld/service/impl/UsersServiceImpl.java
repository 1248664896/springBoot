package com.springboot.musicworld.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.musicworld.dao.UsersMapper;
import com.springboot.musicworld.entity.Messages;
import com.springboot.musicworld.entity.Users;
import com.springboot.musicworld.service.UsersService;
import com.springboot.musicworld.utils.PageQueryUtil;
import com.springboot.musicworld.utils.PageResult;
import com.springboot.musicworld.utils.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    public UsersMapper usersMapper;

    @Override
    public JSONObject register(String mobilePhone , String userName,String password , String id) {
        Messages messages = new Messages();
        JSONObject json = new JSONObject();
        String  usersA  =  usersMapper.userNameExist(userName);
        String usersB =  usersMapper.mobilePhoneExist(mobilePhone);
        if(usersA==null){
            if(usersB==null){
                int result  = usersMapper.register(userName,mobilePhone,password,id);
                if(result>0){
                    json.put("msg","成功");
                    json.put("result",true);

                }else{
                    json.put("msg","失败");
                    json.put("result",false);
                }
            }else{
                json.put("msg","手机号已存在");
                json.put("result",false);
            }
        }else{
            json.put("msg","用户名存在");
            json.put("result",false);
        }
//        if(usersA.equals(userName)){
//            json.put("msg","用户名存在");
//            json.put("result",false);
//        }else{
//            if(usersB.equals(mobilePhone)){
//                json.put("msg","手机号已存在");
//                json.put("result",false);
//            }else{
//                int result  = usersMapper.register(userName,mobilePhone,password,id);
//                if(result>0){
//                    json.put("msg","成功");
//                    json.put("result",true);
//
//                }else{
//                    json.put("msg","失败");
//                    json.put("result",false);
//                }
//            }
//        }
        return json;
    }

    @Override
    public PageResult getUserListPage(PageQueryUtil pageQueryUtil) {
        usersMapper.findUserList(pageQueryUtil);
        return null;
    }


}
