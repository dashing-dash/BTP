package com.example.android.btp.other;

/**
 * Created by Pallav on 11/24/2017.
 */

public class Article {
    private String content;
    private String link;
    private String title;

    public Article(){}

    public Article(String content,String link, String title){
        this.content=content;
        this.link=link;
        this.title=title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
