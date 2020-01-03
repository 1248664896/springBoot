package com.springboot.musicworld.service.impl;

import com.springboot.musicworld.common.Constants;
import com.springboot.musicworld.dao.MusicsMapper;
import com.springboot.musicworld.entity.Musics;
import com.springboot.musicworld.service.MusicsService;
import com.springboot.musicworld.utils.PageQueryUtil;
import com.springboot.musicworld.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicsServiceImpl implements MusicsService {


    @Autowired
    private MusicsMapper musicsMapper;

    @Override
    public List<Musics> musicsList() {
        List<Musics>  list =  musicsMapper.musicsList();
        return list;
    }

    @Override
    public boolean addMusicA(Musics musics) {
       int i =  musicsMapper.addMusicA(musics);
       if(i>0){
           return true;
       }
        return false;
    }

    @Override
    public PageResult findMusicsList(PageQueryUtil pageQueryUtil) {

        List<Musics>  list =  musicsMapper.findMusicsList(pageQueryUtil);
        int total =  musicsMapper.getToMusics(pageQueryUtil);
        PageResult pageResult = new PageResult(list,total,pageQueryUtil.getLimit(),pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public boolean musicPutaway(String id) {
        int i = musicsMapper.musicPutaway(id, Constants.UnShelve);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean musicUnShelve(String[] id) {
        int i = musicsMapper.musicUnShelve(id,Constants.UnShelve);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean musicDelete(String id) {
        int i = musicsMapper.musicDelete(id);
        if(i>0){
            return false;
        }
        return true;
    }

    @Override
    public List<Musics> list() {

        return musicsMapper.list();
    }


}
