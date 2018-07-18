package id.co.indocyber.android.starbridges.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.ProgressDialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import id.co.indocyber.android.starbridges.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import id.co.indocyber.android.starbridges.adapter.HistoryAdapter;
import id.co.indocyber.android.starbridges.model.Attendence;
import id.co.indocyber.android.starbridges.model.OLocation.OLocation;
import id.co.indocyber.android.starbridges.model.history.History;
import id.co.indocyber.android.starbridges.model.history.ReturnValue;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.AlertDialogManager;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;
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
    private ReturnValue latestReturnValue;
    List<id.co.indocyber.android.starbridges.model.OLocation.ReturnValue> listReturnValueLocation = new ArrayList<>();;
    String sLocationID, sLocationName, sLocationAddress, sLatitude, sLongitude;
    private FusedLocationProviderClient client;

    static final int REQUEST_ACCESS_LOCATION = 101;

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
                else if(mShowDetail.getText().toString().matches("Check Out"))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CheckInOutActivity.this);
                    alert.setTitle("Are you sure want to check out?");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getLocation();
                        }
                    });

                    alert.show();


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

        Log.d("cekMock", isMockLocationOn(location, CheckInOutActivity.this)+"");
        Log.d("cekMockList", getListOfFakeLocationApps(CheckInOutActivity.this)+"");
    }

    public void getLocation() {

        progressDialog = new ProgressDialog(CheckInOutActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        id.co.indocyber.android.starbridges.model.OLocation.ReturnValue returnValue=new id.co.indocyber.android.starbridges.model.OLocation.ReturnValue();
        returnValue.setID("");
        returnValue.setAddress("");
        returnValue.setCode("");
        returnValue.setDescription("");
        returnValue.setName("");
        listReturnValueLocation.add(returnValue);

        apiInterface = APIClient.getLocationValue(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.postLocation().enqueue(new Callback<OLocation>() {
            @Override
            public void onResponse(Call<OLocation> call, Response<OLocation> response) {

                if (response.isSuccessful()) {

                    listReturnValueLocation.addAll(response.body().getReturnValue());

                } else {

                    Toast.makeText(CheckInOutActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }


                progressDialog.dismiss();

                checkLatestLocation();
            }

            @Override
            public void onFailure(Call<OLocation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CheckInOutActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void checkPermissionLocation()
    {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_LOCATION);
        }
    }

    public void getCoordinate()
    {
        checkPermissionLocation();

        client = LocationServices.getFusedLocationProviderClient(this);
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    sLatitude = String.valueOf(location.getLatitude());
                    sLongitude = String.valueOf(location.getLongitude());



                }

                callInputAbsence();

            }
        });


    }

    public void callInputAbsence()
    {

        String sUsername = GlobalVar.loginName();
        String sEmployeeID = null;
        String sBussinessGroupID = null;
        String sBeaconID = null;

        long date = System.currentTimeMillis();

        String sEvent = null;
        String sNotes = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = sdf.format(date);
        String sTime = mTimeView.getText().toString();

        TimeZone timezone = TimeZone.getDefault();
        int timeZoneOffset = timezone.getRawOffset()/(60 * 60 * 1000);


        if(sLocationID==null)
        {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try{
                addresses = geocoder.getFromLocation(Double.parseDouble(sLatitude),Double.parseDouble(sLongitude) , 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                sLocationAddress = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            }catch (Exception e)
            {

            }
        }


        Call<Attendence> call3 = apiInterface.inputAbsence(sUsername, sEmployeeID, sBussinessGroupID, dateString, sTime, sBeaconID, sLocationID, sLocationName, sLocationAddress, sLongitude, sLatitude, "Check Out", null, sNotes, sEvent, timeZoneOffset);
        call3.enqueue(new Callback<Attendence>() {
            @Override
            public void onResponse(Call<Attendence> call, Response<Attendence> response) {
                Attendence data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(CheckInOutActivity.this, "Data Submitted", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                } else if(data != null && data.getMessage() =="Please Check Your Time And Date Settings"){
                    Toast.makeText(CheckInOutActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                }else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CheckInOutActivity.this, "Failed to Submit", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(CheckInOutActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Attendence> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void checkLatestLocation()
    {
        for(id.co.indocyber.android.starbridges.model.OLocation.ReturnValue location:listReturnValueLocation)
        {
            if(location.getName().equals(latestReturnValue.getLocationName()+""))
            {
                sLocationID=location.getID();
            }
        }
        getCoordinate();
    }

    public static boolean isMockLocationOn(Location location, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return location.isFromMockProvider();
        } else {
            String mockLocation = "0";
            try {
                mockLocation = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return !mockLocation.equals("0");
        }
    }

    public static List<String> getListOfFakeLocationApps(Context context) {
        List<String> runningApps = getRunningApps(context, false);
        for (int i = runningApps.size() - 1; i >= 0; i--) {
            String app = runningApps.get(i);
            if(!hasAppPermission(context, app, "android.permission.ACCESS_MOCK_LOCATION")){
                runningApps.remove(i);
            }
        }
        return runningApps;
    }

    public static List<String> getRunningApps(Context context, boolean includeSystem) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<String> runningApps = new ArrayList<>();

        List<ActivityManager.RunningAppProcessInfo> runAppsList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runAppsList) {
            for (String pkg : processInfo.pkgList) {
                runningApps.add(pkg);
            }
        }

        try {
            //can throw securityException at api<18 (maybe need "android.permission.GET_TASKS")
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1000);
            for (ActivityManager.RunningTaskInfo taskInfo : runningTasks) {
                runningApps.add(taskInfo.topActivity.getPackageName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(1000);
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServices) {
            runningApps.add(serviceInfo.service.getPackageName());
        }

        runningApps = new ArrayList<>(new HashSet<>(runningApps));

        if(!includeSystem){
            for (int i = runningApps.size() - 1; i >= 0; i--) {
                String app = runningApps.get(i);
                if(isSystemPackage(context, app)){
                    runningApps.remove(i);
                }
            }
        }
        return runningApps;
    }

    public static boolean isSystemPackage(Context context, String app){
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo pkgInfo = packageManager.getPackageInfo(app, 0);
            return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean hasAppPermission(Context context, String app, String permission){
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(app, PackageManager.GET_PERMISSIONS);
            if(packageInfo.requestedPermissions!= null){
                for (String requestedPermission : packageInfo.requestedPermissions) {
                    if (requestedPermission.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        if (Build.VERSION.SDK_INT >= 18) {
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
                        latestReturnValue=data.getReturnValue().get(0);
                        sLocationName=latestReturnValue.getLocationName().toString();
                        sLocationAddress=latestReturnValue.getLocationAddress().toString();
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
