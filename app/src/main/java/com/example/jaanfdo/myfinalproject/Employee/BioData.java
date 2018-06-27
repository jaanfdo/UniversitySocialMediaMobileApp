package com.example.jaanfdo.myfinalproject.Employee;

import com.example.jaanfdo.myfinalproject.WebRequest.Configuration;

/**
 * Created by jaanfdo on 7/29/2017.
 */

public class BioData extends Configuration {
    String URL = "http://127.0.0.1:1234/AndroidPHP/server2.php";

    String url = "";
    String response = "";

    public String viewBiodata() {
        try {
            url = URL + "?operation=view";
            System.out.println("URL view Biodata: " + url);
            response = call(url);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    public String inserBiodata(String name, String surname) {
        try {
            url = URL + "?operation=insert&name=" + name + "&surname=" + surname;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    public String getBiodataById(int id) {
        try {
            url = URL + "?operation=get_biodata_by_id&id=" + id;
            System.out.println("URL Biodata ID : " + url);
            response = call(url);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    public String updateBiodata(String id, String name, String surname) {
        try {
            url = URL + "?operation=update&id=" + id + "&name=" + name + "&surname=" + surname;
            System.out.println("URL Update Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    public String deleteBiodata(int id) {
        try {
            url = URL + "?operation=delete&id=" + id;
            System.out.println("URL Delete Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }
}
