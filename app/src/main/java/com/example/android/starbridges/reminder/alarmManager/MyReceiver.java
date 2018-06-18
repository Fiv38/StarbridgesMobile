package com.example.android.starbridges.reminder.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Calendar today = Calendar.getInstance();
        int a = today.get(Calendar.DAY_OF_WEEK);
        if (a == 2 || a == 3 || a == 4 || a == 5 || a == 6) {
            Notification.deliverNotification(context, "Star Brigdes", "Jam kerja akan di mulai Pastikan sudah melakukan absensi masuk  !!!");
            Log.d("myTag", "AlarmMasuk di jalankan");
        } else {
            Log.d("myTag", "AlarmMasuk tidak di jalankan");
        }
    }
}
