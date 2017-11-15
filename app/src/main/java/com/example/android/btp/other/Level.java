package com.example.android.btp.other;

/**
 * Created by Pallav on 11/16/2017.
 */

public class Level {
    private int ID;
    private String Name;

    public Level(){

    }

    public Level(int ID, String name){
        this.ID = ID;
        this.Name =name;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return Name;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public void setName(String name){
        this.Name =name;
    }
}