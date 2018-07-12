package id.co.indocyber.android.starbridges.utility;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;

import static java.util.Calendar.AM;
import static java.util.Calendar.PM;

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

    public static String getAttendancePrivilege() {
        return attendancePrivilege;
    }

    public static void setAttendancePrivilege(String attendancePrivilege) {
        GlobalVar.attendancePrivilege = attendancePrivilege;
    }

    public static String attendancePrivilege;

    public static Calendar jamMasuk(Context ctx){
        Calendar checkOutTime2 = Calendar.getInstance();
        if (DateFormat.is24HourFormat(ctx)) {
            checkOutTime2.set(Calendar.HOUR_OF_DAY, 8);
        } else {
            checkOutTime2.set(Calendar.HOUR, 8);
            checkOutTime2.set(Calendar.AM_PM, AM);
        }
        checkOutTime2.set(Calendar.MINUTE, 20);
        checkOutTime2.set(Calendar.SECOND, 10);
        checkOutTime2.set(Calendar.MILLISECOND, 0);
        return checkOutTime2;
    }

    public static Calendar jamPulang(Context ctx){
        Calendar checkOutTime = Calendar.getInstance();
        if (DateFormat.is24HourFormat(ctx)) {
            checkOutTime.set(Calendar.HOUR_OF_DAY, 17);
        } else {
            checkOutTime.set(Calendar.HOUR, 5);
            checkOutTime.set(Calendar.AM_PM, PM);
        }
        checkOutTime.set(Calendar.MINUTE, 20);
        checkOutTime.set(Calendar.SECOND, 10);
        checkOutTime.set(Calendar.MILLISECOND, 0);
        return checkOutTime;
    }

}
