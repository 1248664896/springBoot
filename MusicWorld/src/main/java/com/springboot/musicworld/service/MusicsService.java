package com.springboot.musicworld.service;

import com.springboot.musicworld.entity.Musics;
import com.springboot.musicworld.utils.PageQueryUtil;
import com.springboot.musicworld.utils.PageResult;

import java.util.List;

public interface MusicsService {

    /**
     * 音乐列表
     * @return
     */
    public List<Musics> musicsList();


    /**
     * 单个音乐文件添加
     * @param musics
     * @return
     */
    public boolean addMusicA(Musics musics);

    /**
     * 音乐分页
     * @param pageQueryUtil
     * @return
     */
    public PageResult findMusicsList(PageQueryUtil pageQueryUtil);


    /**
     * 音乐上架
     * @param id
     * @return
     */
    public boolean musicPutaway(String id );


    /**
     * 音乐下架
     * @param id
     * @return
     */
    public boolean musicUnShelve(String[] id );


    /**
     * 音乐删除
     * @param id
     * @return
     */
    public boolean musicDelete(String id );


    public List<Musics> list();

}
