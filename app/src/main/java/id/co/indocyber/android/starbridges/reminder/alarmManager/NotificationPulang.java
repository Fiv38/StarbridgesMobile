package id.co.indocyber.android.starbridges.reminder.alarmManager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.indocyber.android.starbridges.R;

import id.co.indocyber.android.starbridges.activity.LoginActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public final class NotificationPulang {
    static NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 3;

    public static void deliverNotification(Context context, String title, String message) {
            mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            Intent contentIntent = new Intent(context, LoginActivity.class);
            PendingIntent contentPendingIntent = PendingIntent.getActivity
                    (context, NOTIFICATION_ID, contentIntent, 0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher2_round)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setContentIntent(contentPendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(message));
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
