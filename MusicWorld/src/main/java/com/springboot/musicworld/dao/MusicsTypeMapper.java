package com.springboot.musicworld.dao;

import com.springboot.musicworld.entity.MusicsType;

import java.util.List;

public interface MusicsTypeMapper {


    public int addMusicsType(MusicsType musicsType);

    public List<MusicsType> getMusicsTypeList();

}
