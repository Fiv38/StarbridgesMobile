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
        AlarmManagerMasuk.start(context);
        AlarmManagerPulang.start(context);
    }
}
