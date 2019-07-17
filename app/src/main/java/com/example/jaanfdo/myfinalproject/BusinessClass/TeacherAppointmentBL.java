package com.example.jaanfdo.myfinalproject.BusinessClass;

/**
 * Created by jaanfdo on 6/18/2017.
 */

public class TeacherAppointmentBL {
    private String id;
    private  String course;
    private  String date;
    private String time;
    private String lecturer;
    private String reason;
    private String user;

    public TeacherAppointmentBL(String course, String date, String time, String lecturer, String reason, String user) {
        this.course = course;
        this.date = date;
        this.time = time;
        this.lecturer = lecturer;
        this.reason = reason;
        this.user = user;
    }

    public TeacherAppointmentBL() {
    }

    public TeacherAppointmentBL(String id, String course, String date, String time, String lecturer, String reason, String user) {
        this.id = id;
        this.course = course;
        this.date = date;
        this.time = time;
        this.lecturer = lecturer;
        this.reason = reason;
        this.user = user;
    }

    public TeacherAppointmentBL(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
