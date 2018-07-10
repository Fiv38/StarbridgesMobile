package id.co.indocyber.android.starbridges.reminder.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.indocyber.android.starbridges.R;

import java.util.Calendar;

import id.co.indocyber.android.starbridges.utility.GlobalVar;

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
                String message = context.getString(R.string.reminder_pesan_pulang);
                String tittle = context.getString(R.string.reminder_title);
                NotificationPulang.deliverNotification(context, tittle, message);
                Log.d("myTag", "notif AlarmPulang di jalankan");
            }else{
                Log.d("myTag", "notif AlarmPulang tidak di jalankan karena jam sudah terlewat");
            }
        } else {
            Log.d("myTag", "notif AlarmPulang tidak di jalankan karena beda hari");
        }

    }
}
