package com.example.android.starbridges.reminder.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiverPulang extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationPulang.deliverNotification(context, "Star Brigdes", "Jam kerja akan berakhir, Pastikan melakukan absensi pulang !!!");
        Log.d("myTag", "AlarmPulang di jalankan");
    }
}
