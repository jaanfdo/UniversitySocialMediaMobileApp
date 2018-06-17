package com.example.jaanfdo.myfinalproject.WebService;

import java.io.Serializable;

public class Student implements Serializable {

    public int sid;
    public String sName;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }


    public Student(int sid, String sName) {
        this.sid = sid;
        this.sName = sName;
    }

    public Student() {
    }
}
