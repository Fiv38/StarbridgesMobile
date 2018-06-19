package com.example.android.starbridges.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.Attendence;
import com.example.android.starbridges.model.OLocation.OLocation;
import com.example.android.starbridges.model.OLocation.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;
import com.example.android.starbridges.utility.SessionManagement;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartEndDayDetailActivity extends AppCompatActivity {
    static final int REQUEST_ACCESS_LOCATION = 101;

    private FusedLocationProviderClient client;

    private EditText mEventView, mDateView, mTimeView, mLocationNameView, mNotesView;
    private Spinner mLocationSpinner;
    private Button mSubmit;
    private String sLocationID, sUsername, sLongitude, sLatitude, sDate, sTime,sLogType;
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog;
    String sLocationName;
    String sLocationAddress;
    int timeZoneOffset;
    final List<ReturnValue> listReturnValue= new ArrayList<>();
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_end_day_detail);
        setTitle("ATTENDANCE");
        mEventView = (EditText) findViewById(R.id.txt_event_sd);
        mDateView = (EditText) findViewById(R.id.txt_date_sd);
        mTimeView = (EditText) findViewById(R.id.txt_time_sd);
        mLocationSpinner = (Spinner) findViewById(R.id.sp_location_sd);
        mLocationNameView = (EditText) findViewById(R.id.txt_location_name_sd);
        mNotesView = (EditText) findViewById(R.id.txt_notes_sd);

        mSubmit = (Button) findViewById(R.id.btn_submit_sd);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitData();
            }
        });

        TimeZone timezone = TimeZone.getDefault();
        //String TimeZoneName = timezone.getDisplayName();
        timeZoneOffset = timezone.getRawOffset()/(60 * 60 * 1000);

        Intent intent = getIntent();
        sDate = intent.getStringExtra("date");
        sTime = intent.getStringExtra("time");
        sUsername = GlobalVar.loginName();
        sLogType=intent.getStringExtra("logType");
        client = LocationServices.getFusedLocationProviderClient(this);
        getLocation();

        mDateView.setText(sDate);
        mTimeView.setText(sTime);
        initSpinnerLoc();



        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                    final ReturnValue returnValue1=(ReturnValue)mLocationSpinner.getItemAtPosition(i);
                    //Log.d("LocationIdnya", returnValue1.getID());
                    sLocationID=returnValue1.getID();
                    sLocationName=returnValue1.getName();
                    sLocationAddress=returnValue1.getAddress();
                }

                setEnableSpinnerAndEditTextLocation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setEnableSpinnerAndEditTextLocation();
            }
        });

        mLocationNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setEnableSpinnerAndEditTextLocation();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setEnableSpinnerAndEditTextLocation();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setEnableSpinnerAndEditTextLocation();
            }
        });

    }


    void getLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_LOCATION);
        }

        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    sLatitude = String.valueOf(location.getLatitude());
                    sLongitude = String.valueOf(location.getLongitude());

                }
            }
        });


    }

    public void SubmitData() {
        callInputAbsence();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case REQUEST_ACCESS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

//    public void getLocation() {
//        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//                    sLatitude=String.valueOf(location.getLatitude());
//                    sLongitude=String.valueOf(location.getLongitude());
//
//                }
//            }
//        });
//    }


    public void initSpinnerLoc() {

        ReturnValue returnValue=new ReturnValue();
        returnValue.setID("");
        returnValue.setAddress("");
        returnValue.setCode("");
        returnValue.setDescription("");
        returnValue.setName("");
        listReturnValue.add(returnValue);

        apiInterface = APIClient.getLocationValue(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.postLocation().enqueue(new Callback<OLocation>() {
            @Override
            public void onResponse(Call<OLocation> call, Response<OLocation> response) {

                if (response.isSuccessful()) {

                    List<ReturnValue> LocItems = response.body().getReturnValue();
                    if (LocItems!= null){
                        listReturnValue.addAll(LocItems);
                    } else{
                        Toast.makeText(StartEndDayDetailActivity.this, "spinner Tidak dapat data",Toast.LENGTH_LONG).show();
                    }

                    ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(StartEndDayDetailActivity.this,
                            android.R.layout.simple_spinner_item, listReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mLocationSpinner.setAdapter(adapter);
                } else {

                    Toast.makeText(StartEndDayDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }

                session = new SessionManagement(getApplicationContext());
                HashMap<String, String> user = session.getUserDetails();
                String location=user.get(SessionManagement.KEY_LOCATION);
                String locationId=user.get(SessionManagement.KEY_LOCATION_ID);

                if(locationId!="" || locationId != null)
                {
                    setupSpinner(locationId);
                }
                else mLocationNameView.setText(location);
            }



            @Override
            public void onFailure(Call<OLocation> call, Throwable t) {
                Toast.makeText(StartEndDayDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setupSpinner(String locationId)
    {
        int counter=0;
        for(com.example.android.starbridges.model.OLocation.ReturnValue decisionNumber:listReturnValue)
        {
            if(locationId.equals(decisionNumber.getID())) break;
            counter++;
        }
        if(counter >= listReturnValue.size())
            counter=0;

        mLocationSpinner.setSelection(counter);
    }

    public void callInputAbsence() {
        // get token
        apiInterface = APIClient.inputAbsence(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(StartEndDayDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        long date = System.currentTimeMillis();

        String sEvent = mEventView.getText().toString();
        String sNotes = mNotesView.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = sdf.format(date);
        String sTime = mTimeView.getText().toString();
        String sEmployeeID = null;
        String sBussinessGroupID = null;
        String sBeaconID = null;
        String sPhoto = null;

        if(mLocationNameView.isEnabled())
        {
            sLocationName = mLocationNameView.getText().toString();
            sLocationAddress = null;
            sLocationID=null;
        }


        // khusus logType di hardcode -> LogType -> Start Day
        Call<Attendence> call3 = apiInterface.inputAbsence(sUsername, sEmployeeID, sBussinessGroupID, dateString, sTime, sBeaconID, sLocationID, sLocationName, sLocationAddress, sLongitude, sLatitude, sLogType, sPhoto, sNotes, sEvent, timeZoneOffset);
        call3.enqueue(new Callback<Attendence>() {
            @Override
            public void onResponse(Call<Attendence> call, Response<Attendence> response) {
                progressDialog.dismiss();
                Attendence data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(StartEndDayDetailActivity.this, "Data Submitted", Toast.LENGTH_LONG).show();
                    finish();
                }else if(data != null && data.getMessage() =="Please Check Your Time And Date Settings"){
                    Toast.makeText(StartEndDayDetailActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(StartEndDayDetailActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(StartEndDayDetailActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Attendence> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    public void setEnableSpinnerAndEditTextLocation()
    {

        if(mLocationSpinner.getSelectedItem().toString().matches(""))
        {
            mLocationNameView.setEnabled(true);
        }
        else if(!mLocationSpinner.getSelectedItem().toString().matches(""))
        {
            mLocationNameView.setEnabled(false);
        }

        if(mLocationNameView.getText().toString().matches(""))
        {
            mLocationSpinner.setEnabled(true);
        }
        else
            mLocationSpinner.setEnabled(false);

    }
}
