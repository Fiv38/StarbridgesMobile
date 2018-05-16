package com.example.android.starbridges.utility;

/**
 * Created by user on 5/14/2018.
 */

public class GlobalVar {
    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GlobalVar.token = token;
    }

    public static String token;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        GlobalVar.username = username;
    }

    public static String username;


}
