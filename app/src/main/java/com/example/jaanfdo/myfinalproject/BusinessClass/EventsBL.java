package com.example.jaanfdo.myfinalproject.BusinessClass;

/**
 * Created by jaanfdo on 6/18/2017.
 */

public class EventsBL {
    private String id;
    private  String eventname;
    private String Course;
    private  String date;
    private String time;
    private  String place;
    private  String description;



    public EventsBL() {
    }

    public EventsBL(String eventname, String Course, String date, String time, String place, String description) {
        this.eventname = eventname;
        this.Course = Course;
        this.date = date;
        this.time = time;
        this.place = place;
        this.description = description;
    }

    public EventsBL(String id, String eventname,String Course,String date,  String time, String place, String description) {
        this.id = id;

        this.eventname = eventname;
        this.Course = Course;
        this.date = date;
        this.time = time;
        this.place = place;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
