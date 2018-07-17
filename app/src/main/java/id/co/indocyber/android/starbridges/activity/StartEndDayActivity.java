package id.co.indocyber.android.starbridges.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import id.co.indocyber.android.starbridges.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import id.co.indocyber.android.starbridges.adapter.HistoryAdapter;
import id.co.indocyber.android.starbridges.model.Attendence;
import id.co.indocyber.android.starbridges.model.OLocation.OLocation;
import id.co.indocyber.android.starbridges.model.history.History;
import id.co.indocyber.android.starbridges.model.history.ReturnValue;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.AlertDialogManager;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartEndDayActivity extends AppCompatActivity {

    private TextView mDateView, mTimeView;
    private Button mShowDetail;
    private RecyclerView recyclerView;
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog;
    private List<ReturnValue> value;
    private HistoryAdapter viewAdapter;
    private String dateString, dateString2;

    private ReturnValue latestReturnValue;
    List<id.co.indocyber.android.starbridges.model.OLocation.ReturnValue> listReturnValueLocation = new ArrayList<>();;
    String sLocationID, sLocationName, sLocationAddress, sLatitude, sLongitude;
    private FusedLocationProviderClient client;

    static final int REQUEST_ACCESS_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_end_day);
        this.setTitle("Attendance");
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

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);

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
                    //Toast.makeText(StartEndDayActivity.this,"Terdeteksi",Toast.LENGTH_SHORT).show();
                    AlertDialogManager alertDialogManager = new AlertDialogManager();
                    alertDialogManager.showAlertDialog(StartEndDayActivity.this, "Warning","Please Uninstall your Fake GPS Apps",false);
                }
                else if(mShowDetail.getText().toString().matches("End Day"))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(StartEndDayActivity.this);
                    alert.setTitle("Are you sure want to end day?");
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

        //getAttendaceLog(dateString2, dateString2);
    }

    public void getLocation() {

        progressDialog = new ProgressDialog(StartEndDayActivity.this);
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

                    Toast.makeText(StartEndDayActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }


                progressDialog.dismiss();

                checkLatestLocation();
            }

            @Override
            public void onFailure(Call<OLocation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(StartEndDayActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

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



        Call<Attendence> call3 = apiInterface.inputAbsence(sUsername, sEmployeeID, sBussinessGroupID, dateString, sTime, sBeaconID, sLocationID, sLocationName, sLocationAddress, sLongitude, sLatitude, "End Day", null, sNotes, sEvent, timeZoneOffset);
        call3.enqueue(new Callback<Attendence>() {
            @Override
            public void onResponse(Call<Attendence> call, Response<Attendence> response) {
                Attendence data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(StartEndDayActivity.this, "Data Submitted", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                } else if(data != null && data.getMessage() =="Please Check Your Time And Date Settings"){
                    Toast.makeText(StartEndDayActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                }else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(StartEndDayActivity.this, "Failed to Submit", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(StartEndDayActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
            if(location.getName().matches(latestReturnValue.getLocationName().toString()))
            {
                sLocationID=location.getID();
            }
        }
        getCoordinate();
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

    @Override
    protected void onResume() {
        super.onResume();
        getAttendaceLog(dateString2, dateString2);
    }

    public void showDetail() {
        String dateValue;
        String timeValue;
        String logType;

        timeValue = mTimeView.getText().toString();
        dateValue = mDateView.getText().toString();
        logType = mShowDetail.getText().toString();

        Intent intent = new Intent(this, StartEndDayDetailActivity.class);

        intent.putExtra("time", timeValue);
        intent.putExtra("date", dateValue);
        intent.putExtra("logType", logType);
        startActivity(intent);
    }

    public void getAttendaceLog(String DateFrom, String DateTo) {
        apiInterface = APIClient.getHistory(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(StartEndDayActivity.this);
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
                    if(data.getReturnValue().size()>0)
                    {
                        latestReturnValue=data.getReturnValue().get(0);
                        sLocationName=latestReturnValue.getLocationName().toString();
                        sLocationAddress=latestReturnValue.getLocationAddress().toString();
                    }

                    for(ReturnValue x: data.getReturnValue())
                    {
                        if(x.getLogType().equals("End Day"))
                        {
                            mShowDetail.setEnabled(false);
                        }else if (x.getLogType().equals("Start Day")){
                            mShowDetail.setText("End Day");

                            //mShowDetail.setEnabled(false);
                        }
                    }

//                    String lastLogType="";
//                    if(data.getReturnValue().size()>0)
//                    {
////                        int dataSize=data.getReturnValue().size()-1;
//                        lastLogType=data.getReturnValue().get(0).getLogType();
//                    }
//                    if (lastLogType.equals("Start Day") ) {
//                        mShowDetail.setText("End Day");
//                    } else if (lastLogType.equals("End Day")) {
//                        mShowDetail.setEnabled(false);
//                    } else {
//                        mShowDetail.setText("Start Day");
//                    }
                    viewAdapter = new HistoryAdapter(StartEndDayActivity.this, data.getReturnValue());
                    recyclerView.setAdapter(viewAdapter);
                } else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(StartEndDayActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(StartEndDayActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
