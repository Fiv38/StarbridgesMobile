package id.co.indocyber.android.starbridges.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import id.co.indocyber.android.starbridges.utility.AlertDialogManager;

public class TimeChangedReceiver extends BroadcastReceiver {
    AlertDialogManager alertDialogManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(Intent.ACTION_TIME_CHANGED) || action.equals(Intent.ACTION_TIMEZONE_CHANGED))
        {
            alertDialogManager.showAlertDialog(context,"ERROR","Please Set Time Or TimeZone On Your Phone", false);
        }
    }
}
