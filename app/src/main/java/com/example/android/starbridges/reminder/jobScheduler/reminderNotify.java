package com.example.android.starbridges.reminder.jobScheduler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.android.starbridges.R;
import com.example.android.starbridges.activity.LoginActivity;

import static android.app.Notification.DEFAULT_SOUND;
import static android.content.Context.NOTIFICATION_SERVICE;

public class reminderNotify extends Service{
    static NotificationManager mNotificationManager;



    public static void deliverNotification(int NOTIFICATION_ID,Context context,String title, String message){

        mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent contentIntent = new Intent (context,LoginActivity.class);
        final PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.cast_ic_expanded_controller_pause)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
//                .setLights(Color.WHITE,3000,3000)
                .setPriority(NotificationCompat.PRIORITY_MAX);


        mNotificationManager.notify(NOTIFICATION_ID,builder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
