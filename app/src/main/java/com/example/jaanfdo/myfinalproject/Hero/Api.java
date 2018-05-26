package com.example.jaanfdo.myfinalproject.Hero;

/**
 * Created by jaanfdo on 11/11/2017.
 */

public class Api {
    private static final String ROOT_URL = "http://127.0.0.1/AndroidPHP/Api.php?apicall=";

    public static final String URL_CREATE_HERO = ROOT_URL + "createhero";
    public static final String URL_READ_HEROES = ROOT_URL + "getheroes";
    public static final String URL_UPDATE_HERO = ROOT_URL + "updatehero";
    public static final String URL_DELETE_HERO = ROOT_URL + "deletehero&id=";
}
