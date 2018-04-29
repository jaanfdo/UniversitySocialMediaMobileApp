package com.example.jaanfdo.myfinalproject.BusinessClass;

/**
 * Created by jaanfdo on 6/19/2017.
 */

public class SignUpBL {
    private String id;
    private  String username;
    private  String password;
    private String email;
    private  String universitycode;

    public SignUpBL(String id, String username, String password, String email, String universitycode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.universitycode = universitycode;
    }

    public SignUpBL(String username, String password, String email, String universitycode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.universitycode = universitycode;
    }

    public SignUpBL() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversitycode() {
        return universitycode;
    }

    public void setUniversitycode(String universitycode) {
        this.universitycode = universitycode;
    }
}
