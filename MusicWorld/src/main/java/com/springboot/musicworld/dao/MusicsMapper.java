package com.springboot.musicworld.dao;

import com.springboot.musicworld.entity.Musics;
import com.springboot.musicworld.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicsMapper {

    public List<Musics> musicsList();

    public int addMusicA(Musics musics);

    /**
     * 音乐分页
     * @param PageQueryUtil
     * @return
     */
    public List<Musics> findMusicsList(PageQueryUtil PageQueryUtil);

    public int getToMusics(PageQueryUtil pageQueryUtil);

    /**
     * 音乐上架
     * @param id
     * @return
     */
    public int musicPutaway(String id , @Param("status") String status);


    /**
     * 音乐下架
     * @param id
     * @return
     */
    public int musicUnShelve(@Param("id") String[] id, @Param("status") String status);


    /**
     * 音乐删除
     * @param id
     * @return
     */
    public int musicDelete(String id );


    public List<Musics> list();

}
