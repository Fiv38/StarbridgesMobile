package com.example.android.starbridges.utility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 5/14/2018.
 */

public class GlobalVar {
    /*
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
    */

    public static String accessToken;
    public static String tokenType;
    public static int expiresIn;
    public static String asClientId;
    public static String loginName;
    public static String fullName;
    public static String nik;
    public static String issued;
    public static String expires;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        GlobalVar.accessToken = accessToken;
    }

    public static String getTokenType() {
        return tokenType;
    }

    public static void setTokenType(String tokenType) {
        GlobalVar.tokenType = tokenType;
    }

    public static int getExpiresIn() {
        return expiresIn;
    }

    public static void setExpiresIn(int expiresIn) {
        GlobalVar.expiresIn = expiresIn;
    }

    public static String getAsClientId() {
        return asClientId;
    }

    public static void setAsClientId(String asClientId) {
        GlobalVar.asClientId = asClientId;
    }

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        GlobalVar.loginName = loginName;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        GlobalVar.fullName = fullName;
    }

    public static String getNik() {
        return nik;
    }

    public static void setNik(String nik) {
        GlobalVar.nik = nik;
    }

    public static String getIssued() {
        return issued;
    }

    public static void setIssued(String issued) {
        GlobalVar.issued = issued;
    }

    public static String getExpires() {
        return expires;
    }

    public static void setExpires(String expires) {
        GlobalVar.expires = expires;
    }
}
