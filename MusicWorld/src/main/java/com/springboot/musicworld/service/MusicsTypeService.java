package com.springboot.musicworld.service;

import com.springboot.musicworld.entity.MusicsType;

import java.util.List;


public interface MusicsTypeService {


        public boolean addMusicsType(MusicsType musicsType);

        public List<MusicsType> musicsTypeList();

}
