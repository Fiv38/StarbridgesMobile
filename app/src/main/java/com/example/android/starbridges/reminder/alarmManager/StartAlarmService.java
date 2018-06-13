package com.example.android.starbridges.reminder.alarmManager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.android.starbridges.activity.LoginActivity;

public class StartAlarmService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        boolean isAlarmMasuk = (PendingIntent.getBroadcast(context, 0,
//                new Intent("com.example.android.starbridges.ACTION_NOTIFY_MASUK"),
//                PendingIntent.FLAG_NO_CREATE) != null);
//
//        if (isAlarmMasuk)
//        {
//            Log.d("myTag", "startService Alarm Masuk is already active");
//            Toast.makeText(context, "Alarm Masuk is already active", Toast.LENGTH_SHORT).show();
//        }else {
//            AlarmManagerMasuk.start(context);
//            Log.d("myTag", "startService AlarmMasuk is Created");
//            Toast.makeText(context, "AlarmMasuk is Created", Toast.LENGTH_SHORT).show();
//        }
//
//        boolean isAlarmKeluar = (PendingIntent.getBroadcast(context, 1,
//                new Intent("com.example.android.starbridges.ACTION_NOTIFY_PULANG"),
//                PendingIntent.FLAG_NO_CREATE) != null);
//
//        if (isAlarmKeluar)
//        {
//            Log.d("myTag", "startService AlarmPulang is already active");
//            Toast.makeText(context, "Alarm Pulang is already active", Toast.LENGTH_SHORT).show();
//        }else {
//            AlarmManagerPulang.start(context);
//            Log.d("myTag", "startService AlarmPulang is Created");
//            Toast.makeText(context, "AlarmPulang is Created", Toast.LENGTH_SHORT).show();
//        }
        AlarmManagerMasuk.start(context);
        AlarmManagerPulang.start(context);
    }
}
