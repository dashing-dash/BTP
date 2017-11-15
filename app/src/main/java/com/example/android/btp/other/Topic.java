package com.example.android.btp.other;

/**
 * Created by Pallav on 11/15/2017.
 */

public class Topic {
    private int ID;
    private String Name;
    private String link;

    public Topic() {

    }

    public Topic(int id, String name,String link) {
        this.ID = id;
        this.Name = name;
        this.link=link;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setLink(String link){
        this.link=link;
    }

    public String getLink(){
        return link;
    }
}
