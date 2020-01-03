package com.springboot.musicworld.service.impl;

import com.springboot.musicworld.dao.MusicPictureMapper;
import com.springboot.musicworld.entity.MusicPicture;
import com.springboot.musicworld.service.MusicPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicPictureServiceImpl implements MusicPictureService {

    @Autowired
    private MusicPictureMapper musicPictureMapper;

    @Override
    public boolean addMusicPicture(MusicPicture musicPicture) {
        int i = musicPictureMapper.addMusicPicture(musicPicture);
        if(i>0){
            return true;
        }
        return false;
    }
}
