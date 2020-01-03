package com.springboot.musicworld.entity;

import java.util.Date;

/**
 * 歌曲
 */
public class Musics {



    private String id        ;  //id
    private String name      ;  //歌名
    private String size      ;  //大小
    private String duration  ;  //歌曲时间长度
    private String info 	 ;  //歌曲信息
    private Date addDateTime; //添加时间
    private String fileName ;   //文件名
    private  String newFileName;//新文件名
    private  String type;
    private  String coverImgId ;
    private  String status ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getAddDateTime() {
        return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
        this.addDateTime = addDateTime;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoverImgId() {
        return coverImgId;
    }

    public void setCoverImgId(String coverImgId) {
        this.coverImgId = coverImgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
