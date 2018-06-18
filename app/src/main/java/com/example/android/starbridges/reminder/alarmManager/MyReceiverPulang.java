package com.example.android.starbridges.reminder.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class MyReceiverPulang extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Calendar today2 = Calendar.getInstance();
        int a = today2.get(Calendar.DAY_OF_WEEK);
        if (a == 2 || a == 3 || a == 4 || a == 5 || a == 6) {
            NotificationPulang.deliverNotification(context, "Star Brigdes", "Jam kerja akan berakhir, Pastikan melakukan absensi pulang !!!");
            Log.d("myTag", "AlarmPulang di jalankan");
        } else {
            Log.d("myTag", "AlarmPulang tidak di jalankan");
        }

    }
}
