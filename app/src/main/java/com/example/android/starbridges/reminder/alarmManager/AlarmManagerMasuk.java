package com.example.android.starbridges.reminder.alarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;

import com.example.android.starbridges.utility.GlobalVar;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static java.util.Calendar.AM;


public class AlarmManagerMasuk {
    private static final int NOTIFICATION_ID = 0;
    private static final String ACTION_NOTIFY = "com.example.android.starbridges.ACTION_NOTIFY_MASUK";

    public static void start(Context ctx) {
        final AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);

//        Calendar checkOutTime2 = Calendar.getInstance();
//
//        if (DateFormat.is24HourFormat(ctx)) {
//            checkOutTime2.set(Calendar.HOUR_OF_DAY, 8);
//        } else {
//            checkOutTime2.set(Calendar.HOUR, 8);
//            checkOutTime2.set(Calendar.AM_PM, AM);
//        }
//        checkOutTime2.set(Calendar.MINUTE, 20);
//        checkOutTime2.set(Calendar.SECOND, 10);
//        checkOutTime2.set(Calendar.MILLISECOND, 0);

        Intent notifyIntent = new Intent(ACTION_NOTIFY);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (ctx.getApplicationContext(), NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, GlobalVar.jamMasuk(ctx).getTimeInMillis(), AlarmManager.INTERVAL_DAY, notifyPendingIntent);
    }

}
