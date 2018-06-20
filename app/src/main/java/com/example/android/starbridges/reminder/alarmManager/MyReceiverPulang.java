package com.example.android.starbridges.reminder.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.android.starbridges.utility.GlobalVar;

import java.util.Calendar;

public class MyReceiverPulang extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
         Calendar today2 = Calendar.getInstance();
        int a = today2.get(Calendar.DAY_OF_WEEK);
        if (a == 2 || a == 3 || a == 4 || a == 5 || a == 6) {
            today2.add(Calendar.MINUTE,-3);
            Calendar alarm2 = GlobalVar.jamPulang(context);
            Boolean hasil2 = today2.before(alarm2);
            if(hasil2==true) {
                NotificationPulang.deliverNotification(context, "Star Brigdes", "Jam kerja akan berakhir, Pastikan melakukan absensi pulang !!!");
                Log.d("myTag", "notif AlarmPulang di jalankan");
            }else{
                Log.d("myTag", "notif AlarmPulang tidak di jalankan karena jam sudah terlewat");
            }
        } else {
            Log.d("myTag", "notif AlarmPulang tidak di jalankan karena beda hari");
        }

    }
}
