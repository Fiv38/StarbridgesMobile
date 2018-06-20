package com.example.android.starbridges.reminder.alarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;

import com.example.android.starbridges.utility.GlobalVar;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static java.util.Calendar.PM;

public class AlarmManagerPulang {
    private static final int NOTIFICATION_ID2 = 1;
    private static final String ACTION_NOTIFY2 = "com.example.android.starbridges.ACTION_NOTIFY_PULANG";

    public static void start(Context ctx) {
        final AlarmManager alarmManager2 = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);
        // setting waktu
//        final long time = 2 * 60 * 1000; // 15 menit
//        Date a = Calendar.getInstance().getTime();
//        long time2 = a.getTime();
//        long triggerTime = SystemClock.elapsedRealtime() + time;
//        long repeatInterval = time;
////                AlarmManager.INTERVAL_FIFTEEN_MINUTES /3 ;
        //setting specific time

//        Calendar checkOutTime = Calendar.getInstance();
//        if (DateFormat.is24HourFormat(ctx)) {
//            checkOutTime.set(Calendar.HOUR_OF_DAY, 17);
//        } else {
//            checkOutTime.set(Calendar.HOUR, 5);
//            checkOutTime.set(Calendar.AM_PM, PM);
//        }
//        checkOutTime.set(Calendar.MINUTE, 20);
//        checkOutTime.set(Calendar.SECOND, 10);
//        checkOutTime.set(Calendar.MILLISECOND, 0);

        Intent notifyIntent2 = new Intent(ACTION_NOTIFY2);


        final PendingIntent notifyPendingIntent2 = PendingIntent.getBroadcast
                (ctx.getApplicationContext(), NOTIFICATION_ID2, notifyIntent2, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager2.setInexactRepeating(AlarmManager.RTC_WAKEUP, GlobalVar.jamPulang(ctx).getTimeInMillis(), AlarmManager.INTERVAL_DAY, notifyPendingIntent2);

    }

}
