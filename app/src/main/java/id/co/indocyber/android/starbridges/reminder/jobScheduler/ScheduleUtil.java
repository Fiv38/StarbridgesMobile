package id.co.indocyber.android.starbridges.reminder.jobScheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class ScheduleUtil {
    //schedule untuk menjalanakan service setiap 10-30 detik
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleJob(Context context){

            ComponentName serviceComponent = new ComponentName(context,ReminderJobService.class);

            JobInfo.Builder builder = new JobInfo.Builder(0,serviceComponent);
            builder.setMinimumLatency(10*1000); //wait at least
            builder.setOverrideDeadline(10*1000);//maximum delay


            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(builder.build());
    }
}
