package com.example.android.starbridges.reminder.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification.deliverNotification(context, "Star Brigdes", "Jam kerja akan di mulai Pastikan sudah melakukan absensi masuk  !!!");
        Log.d("myTag", "AlarmMasuk di jalankan");
    }
}
