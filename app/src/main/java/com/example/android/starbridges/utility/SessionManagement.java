package com.example.android.starbridges.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android.starbridges.activity.HomeActivity;
import com.example.android.starbridges.activity.LoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SessionManagement {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_LOGINNAME = "loginNname";
    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_EXPIRES = "expires";
    public static final String KEY_NIK = "nik";
    public static final String KEY_EMPLOYEE_ID = "employee_id";

    // Constructor
    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String loginName, String fullName,String token, String expires, String nik, String employee_id){

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_LOGINNAME,loginName);
        editor.putString(KEY_FULLNAME, fullName);
        editor.putString(KEY_TOKEN, token);
        editor.putString(KEY_EXPIRES, expires);
        editor.putString(KEY_NIK, nik);
        editor.putString(KEY_EMPLOYEE_ID, employee_id);
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
//    public void checkLogin() {
//        // Check login status
//        if(this.isLoggedIn() == false){
//            // user is not logged in redirect him to Login Activity
//            Intent i = new Intent(_context, LoginActivity.class);
//            // Closing all the Activities
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            // Add new Flag to start new Activity
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            // Staring Login Activity
//            _context.startActivity(i);
//        }else{
//
//        }
//
//    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_LOGINNAME, pref.getString(KEY_LOGINNAME, null));
        user.put(KEY_FULLNAME, pref.getString(KEY_FULLNAME, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));
        user.put(KEY_EXPIRES, pref.getString(KEY_EXPIRES, null));
        user.put(KEY_NIK, pref.getString(KEY_NIK, null));
        user.put(KEY_EMPLOYEE_ID, pref.getString(KEY_EMPLOYEE_ID, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn() {

        return pref.getBoolean(IS_LOGIN, false);
    }


}
