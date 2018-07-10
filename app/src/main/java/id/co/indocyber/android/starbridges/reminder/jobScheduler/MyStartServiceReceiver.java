package id.co.indocyber.android.starbridges.reminder.jobScheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyStartServiceReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                ScheduleUtil.scheduleJob(context);
//            }else{
//                reminderAlarmManager.start(context);
                    reminderNotify.deliverNotification(0,context,"Star Bridges","Bangun");
//            }

        }
    }
}
