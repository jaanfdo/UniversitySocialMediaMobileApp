package com.example.jaanfdo.myfinalproject.WebService;

import java.util.List;

import com.example.jaanfdo.myfinalproject.WebRequest.Configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class StudentModel extends Configuration {
    private String Base_URL = "http://192.168.1.104/webservice/api/student/";
    private RestTemplate restTemplate = new RestTemplate();

    String url = "";
    String response = "";
    //List<Student> response = null;
    //JSONArray result;
    List<Student> students = null;

    /*public List<Student> findAll(){
        try{
            url = Base_URL + "findall";
            System.out.println("URL : " + url);

            //response = restTemplate.exchange( url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {}).getBody();

            //return response;
        }catch (Exception e)
        {
            return null;
        }
    }*/

    public String findAll() {
        try {
            url = Base_URL + "findall";
            System.out.println("URL : " + url);

            response = call(url);

        } catch (Exception e) {
        }
        return response;
    }

}
