package com.example.jaanfdo.myfinalproject.BusinessClass;

/**
 * Created by jaanfdo on 6/4/2017.
 */

public class ScheduleBL {

    private String id;
    private  String course;
    private  String subject;
    private  String date;
    private String time;
    private String classfloor;
    private String classno;
    private  String lecname;

    public ScheduleBL() {
    }

    public ScheduleBL(String id, String course, String subject, String date, String time, String classfloor, String classno, String lecname) {
        this.id = id;
        this.course = course;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.classfloor = classfloor;
        this.classno = classno;

        this.lecname = lecname;
    }

    public ScheduleBL(String date, String course,String subject, String time, String classfloor, String classno, String lecname) {
        this.course = course;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.classfloor = classfloor;
        this.classno = classno;
        this.lecname = lecname;
    }

    public ScheduleBL(String id) {
        this.id = id;
    }

    public String getLecname() {
        return lecname;
    }

    public void setLecname(String lecname) {
        this.lecname = lecname;
    }

    public String getClassno() {
        return classno;
    }

    public void setClassno(String classno) {
        this.classno = classno;
    }

    public String getClassfloor() {
        return classfloor;
    }

    public void setClassfloor(String classfloor) {
        this.classfloor = classfloor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
