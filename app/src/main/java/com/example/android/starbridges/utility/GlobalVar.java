package com.example.android.starbridges.utility;

/**
 * Created by user on 5/14/2018.
 */

public class  GlobalVar {
    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        GlobalVar.token = token;
    }

    public static String token;

    public static String loginName() {
        return username;
    }

    public static void setLoginName(String username) {
        GlobalVar.username = username;
    }

    public static String username;

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        GlobalVar.fullname = fullname;
    }

    public static String fullname;

    public static String getNIK() {
        return nIK;
    }

    public static void setNIK(String nIK) {
        GlobalVar.nIK = nIK;
    }

    public static String nIK;

    public static String getEmployeeId() {
        return employeeId;
    }

    public static void setEmployeeId(String employeeId) {
        GlobalVar.employeeId = employeeId;
    }

    public static String employeeId;

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        GlobalVar.location = location;
    }

    public static String location;

    public static String getLocationId() {
        return locationId;
    }

    public static void setLocationId(String locationId) {
        GlobalVar.locationId = locationId;
    }

    public static String locationId;


}
