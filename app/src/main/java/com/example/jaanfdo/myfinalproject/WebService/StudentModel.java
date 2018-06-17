package com.example.jaanfdo.myfinalproject.WebService;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class StudentModel {
    private String Base_URL = "http://192.168.1.104/server/student/";
    private RestTemplate restTemplate = new RestTemplate();


    public List<Student> findAll(){
        try{
            return  restTemplate.exchange(
                    Base_URL + "findall",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Student>>() {
                    }
            ).getBody();

        }catch (Exception e)
        {
            return null;
        }
    }

}
