package com.springboot.musicworld.service.impl;

import com.springboot.musicworld.dao.MusicsTypeMapper;
import com.springboot.musicworld.entity.MusicsType;
import com.springboot.musicworld.service.MusicsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicsTypeServiceImpl implements MusicsTypeService {

    @Autowired
    private MusicsTypeMapper musicsTypeMapper;

    @Override
    public boolean addMusicsType(MusicsType musicsType) {
       int i =  musicsTypeMapper.addMusicsType(musicsType);
       if(i>0){
           return true;
       }else{
           return false;
       }


    }

    @Override
    public List<MusicsType> musicsTypeList() {
        return  musicsTypeMapper.getMusicsTypeList();
    }


}
