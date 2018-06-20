package com.example.android.starbridges.reminder.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.android.starbridges.utility.GlobalVar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar today = Calendar.getInstance();
        int a = today.get(Calendar.DAY_OF_WEEK);
        if (a == 2 || a == 3 || a == 4 || a == 5 || a == 6) {
            today.add(Calendar.MINUTE,-3);
            Calendar alarm =  GlobalVar.jamMasuk(context);
            Boolean hasil = today.before(alarm);
            if(hasil==true){
                String message = "Jam kerja akan di mulai Pastikan sudah melakukan absensi masuk  !!!";
                String tittle = "Star Brigdes";
                Notification.deliverNotification(context, tittle, message);
                Log.d("myTag", "notif AlarmMasuk di jalankan karena jam masih akan datang");
            }else{
                Log.d("myTag", "notif AlarmMasuk tidak di jalankan karena jam sudah terlewat");
            }
        } else {
            Log.d("myTag", "notif AlarmMasuk tidak di jalankan karena bukan hari kerja");
        }
    }
}
