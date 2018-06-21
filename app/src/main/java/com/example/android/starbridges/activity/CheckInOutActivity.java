package com.example.android.starbridges.activity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.ProgressDialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.HistoryAdapter;
import com.example.android.starbridges.model.history.History;
import com.example.android.starbridges.model.history.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.AlertDialogManager;
import com.example.android.starbridges.utility.GlobalVar;
import com.google.android.gms.location.FusedLocationProviderApi;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.time.TimeTCPClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInOutActivity extends AppCompatActivity {

    private TextView mDateView;
    private TextView mTimeView;
    private Button mShowDetail;
    private RecyclerView recyclerView;
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog;
    private List<ReturnValue> value;
    private HistoryAdapter viewAdapter;
    private String dateString;
    private String dateString2;
    private boolean checkStartDay=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out);

        this.setTitle("Attendance");

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);

        Location location = new Location(LocationManager.NETWORK_PROVIDER);
        if(onLocationChanged(location)==true)
        {
            Toast.makeText(CheckInOutActivity.this, "You are using fake gps", Toast.LENGTH_LONG).show();
        }
        else
        {
            //Toast.makeText(CheckInOutActivity.this, "GPS true", Toast.LENGTH_LONG).show();
        }



        long date = System.currentTimeMillis();
        mDateView = (TextView) findViewById(R.id.txt_date);
        mTimeView = (TextClock) findViewById(R.id.txt_time);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
        dateString = sdf.format(date);
        dateString2 = sdf2.format(date);
        mDateView.setText(dateString);

        recyclerView = (RecyclerView) findViewById(R.id.StartDayList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mShowDetail = (Button) findViewById(R.id.btn_show_detail);
        mShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);

                if (isAppInstalled("com.lexa.fakegps")
                        || isAppInstalled("com.theappninjas.gpsjosystick")
                        ||isAppInstalled("com.incorporateapps.fakegps.fre")
                        ||isAppInstalled("com.divi.fakeGPS")
                        ||isAppInstalled("com.fakegps.mock")
                        ||isAppInstalled("com.frastan.fakegps")
                        ||isAppInstalled("com.gsmartstudio.fakegps")
                        ||isAppInstalled("com.lkr.fakelocation")
                        ||isAppInstalled("com.ltp.pro.fakelocation")
                        ||isAppInstalled("com.pe.fakegpsrun")
                        ||isAppInstalled("com.perfect.apps.fakegps.flygps.fake.location.changer.fake.gps")
                        ||isAppInstalled("com.usefullapps.fakegpslocationpro")
                        ||isAppInstalled("com.fake.gps.location")
                        ||isAppInstalled("org.hola.gpslocation")
                        ){
                    //Toast.makeText(CheckInOutActivity.this,"Terdeteksi",Toast.LENGTH_SHORT).show();
                    AlertDialogManager alertDialogManager = new AlertDialogManager();
                    alertDialogManager.showAlertDialog(CheckInOutActivity.this, "Warning","Please Uninstall your Fake GPS Apps",false);
                }
                else
                    showDetail();
            }
        });

        ActivityManager actvityManager = (ActivityManager)
                this.getSystemService( ACTIVITY_SERVICE );
        List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();


        /*Toast.makeText(CheckInOutActivity.this, printForegroundTask(), Toast.LENGTH_LONG).show();

        Toast.makeText(CheckInOutActivity.this, needPermissionForBlocking(CheckInOutActivity.this)+"", Toast.LENGTH_LONG).show();
        */
    }

    public boolean isAppInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static boolean needPermissionForBlocking(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid, applicationInfo.packageName);
            return  (mode != AppOpsManager.MODE_ALLOWED);
        } catch (PackageManager.NameNotFoundException e) {
            return true;
        }
    }

    private String printForegroundTask() {
        String currentApp = "NULL";
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usm = (UsageStatsManager)this.getSystemService("usagestats");
            long time = System.currentTimeMillis();
            List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  time - 1000*1000, time);
            if (appList != null && appList.size() > 0) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : appList) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                }
            }
        } else {
            ActivityManager am = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
            currentApp = tasks.get(0).processName;
        }

        Log.e("adapter", "Current App in foreground is: " + currentApp);
        return currentApp;
    }

    public boolean detectFakeGPS(Context context)
    {
//        if (Settings.Secure.getString(getContentResolver(),
//                Settings.Secure.ALLOW_MOCK_LOCATION).equals("0"))
//            return false;
//        else return true;
        Location location = new Location(LocationManager.NETWORK_PROVIDER);

//        String debug =LocationManager.NETWORK_PROVIDER;
//        if(location.isFromMockProvider()) return true;
//        else return false;
        boolean isMock = false;
        if (android.os.Build.VERSION.SDK_INT >= 18) {
            isMock = location.isFromMockProvider();
        } else {
            isMock = !Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION).equals("0");
        }

        return isMock;

    }

    public boolean onLocationChanged (Location location){
        Bundle extras = location.getExtras();
        boolean isMockLocation = extras != null && extras.getBoolean(FusedLocationProviderApi.KEY_MOCK_LOCATION, false);
        return isMockLocation;

    }



    @Override
    protected void onResume() {
        super.onResume();
        getAttendaceLog(dateString2, dateString2);
    }

    public void showDetail(){
        String dateValue;
        String timeValue;
        String logType;

        timeValue = mTimeView.getText().toString();
        dateValue = mDateView.getText().toString();
        logType = mShowDetail.getText().toString();

        Intent intent = new Intent(this, CheckInOutDetailActivity.class);
        intent.putExtra("time",timeValue);
        intent.putExtra("date",dateValue);
        intent.putExtra("logType", logType);
        intent.putExtra("checkStartDay", checkStartDay);
        startActivity(intent);
    }

    public void getAttendaceLog(String DateFrom, String DateTo) {
        apiInterface = APIClient.getHistory(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(CheckInOutActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        // khusus logType di hardcode -> LogType -> Start Day
        Call<History> call3 = apiInterface.getHistory(DateFrom, DateTo);
        call3.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                progressDialog.dismiss();
                History data = response.body();
                if (data != null && data.getIsSucceed()) {

                    for(ReturnValue x: data.getReturnValue())
                    {
                        if(x.getLogType().equals("Start Day"))
                        {
                            checkStartDay=true;
                        }
                    }

                    String lastLogType="";
                    if(data.getReturnValue().size()>0)
                    {
//                        int dataSize=data.getReturnValue().size()-1;
                        lastLogType=data.getReturnValue().get(0).getLogType();
                    }
                    if (lastLogType.equals("Check In") ) {
                        mShowDetail.setText("Check Out");
                    } else if (lastLogType.equals("End Day")) {
                        mShowDetail.setText("Check Out");
                        mShowDetail.setEnabled(false);
                    } else {
                        mShowDetail.setText("Check In");
                    }
                    viewAdapter = new HistoryAdapter(CheckInOutActivity.this, data.getReturnValue());
                    recyclerView.setAdapter(viewAdapter);
                } else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CheckInOutActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(CheckInOutActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
