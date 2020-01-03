package com.springboot.musicworld.entity;

public class MusicPicture {

    private String  id       ;
    private String  musicsId ;
    private String   name    ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusicsId() {
        return musicsId;
    }

    public void setMusicsId(String musicsId) {
        this.musicsId = musicsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
