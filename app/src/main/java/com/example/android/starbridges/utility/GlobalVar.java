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

    public static String getLoginName() {
        return loginname;
    }

    public static void setLoginName(String loginname) {
        GlobalVar.loginname = loginname;
    }

    public static String loginname;

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        GlobalVar.fullname = fullname;
    }

    public static String fullname;

    public static String getNik() {
        return nik;
    }

    public static void setNik(String nik) {
        GlobalVar.nik = nik;
    }

    public static String nik;

    public static String getEmployeeID() {
        return employee_id;
    }

    public static void setEmployee_id(String employee_id) {
        GlobalVar.employee_id = employee_id;
    }

    public static String employee_id;


}
