package com.example.android.btp.other;

/**
 * Created by Pallav on 10/12/2017.
 */

public class Categories {
    private String name;
    private int thumbnail;
    public Categories(String name,int thumbnail){
        this.name=name;
        this.thumbnail=thumbnail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
