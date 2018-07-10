package id.co.indocyber.android.starbridges.reminder.jobScheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * reminderJobService to be scheduled by the JobScheduler.
 * start another service
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ReminderJobService extends JobService{

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("PercobaanJobScheduler","success");

//        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String cTime = sdf.format(currentTime);
        String message ;
        if(cTime.equalsIgnoreCase("08:20")){
            message="jam masuk";
            reminderNotify.deliverNotification(0,getApplicationContext(),"StartBrige",message);

        }else if(cTime.equalsIgnoreCase("16:45")){
            message="jam pulang";
            reminderNotify.deliverNotification(0,getApplicationContext(),"StartBrige",message);
        }
        message="Test Berhasil";
        reminderNotify.deliverNotification(0,getApplicationContext(),"StartBrige",message);
        ScheduleUtil.scheduleJob(getApplicationContext());//reschedule job
//        Intent service = new Intent(getApplicationContext(), LoginActivity.class);
//        getApplicationContext().startService( reminderNotify.deliverNotification(getApplicationContext()));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
