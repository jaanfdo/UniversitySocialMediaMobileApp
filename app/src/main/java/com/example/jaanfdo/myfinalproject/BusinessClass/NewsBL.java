package com.example.jaanfdo.myfinalproject.BusinessClass;

/**
 * Created by jaanfdo on 6/28/2017.
 */

public class NewsBL {
    private String id;
    private  String news;
    private String date;
    private String time;
    private String owner;

    public NewsBL(String id, String news, String date, String time, String owner) {
        this.id = id;
        this.news = news;
        this.date = date;
        this.time = time;
        this.owner = owner;
    }

    public NewsBL(String news, String date, String owner, String time) {


        this.news = news;
        this.date = date;
        this.owner = owner;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
