package com.example.android.starbridges.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

        Intent intent = getIntent();
        sDate = intent.getStringExtra("date");
        sTime = intent.getStringExtra("time");
        sUsername = GlobalVar.getLoginName();
        sLogType=intent.getStringExtra("logType");


        mDateView.setText(sDate);
        mTimeView.setText(sTime);
        initSpinnerLoc();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_LOCATION);
        }

        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                    final ReturnValue returnValue1=(ReturnValue)mLocationSpinner.getItemAtPosition(i);
                    //Log.d("LocationIdnya", returnValue1.getID());
                    sLocationID=returnValue1.getID();
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



    public void SubmitData() {
        callInputAbsence();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case REQUEST_ACCESS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager mgr = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                    List<String> providers = mgr.getAllProviders();
                    if (providers != null && providers.contains(LocationManager.NETWORK_PROVIDER)) {
                        Location loc = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null) {
                            sLatitude=String.valueOf(loc.getLatitude());
                            sLongitude=String.valueOf(loc.getLongitude());
                        }
                    }
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void getLocation() {
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    sLatitude=String.valueOf(location.getLatitude());
                    sLongitude=String.valueOf(location.getLongitude());

                }
            }
        });
    }


    public void initSpinnerLoc() {

        final List<ReturnValue> listReturnValue= new ArrayList<>();
        ReturnValue returnValue=new ReturnValue();
        returnValue.setID("");
        returnValue.setAddress("");
        returnValue.setCode("");
        returnValue.setDescription("");
        returnValue.setName("");
        listReturnValue.add(returnValue);

        apiInterface = APIClient.getLocationValue(GlobalVar.getAccessToken()).create(APIInterfaceRest.class);
        apiInterface.postLocation(" ").enqueue(new Callback<OLocation>() {
            @Override
            public void onResponse(Call<OLocation> call, Response<OLocation> response) {

                if (response.isSuccessful()) {

                    List<ReturnValue> LocItems = response.body().getReturnValue();
                    listReturnValue.addAll(LocItems);

                    ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(StartEndDayDetailActivity.this,
                            android.R.layout.simple_spinner_item, listReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mLocationSpinner.setAdapter(adapter);
                } else {

                    Toast.makeText(StartEndDayDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<OLocation> call, Throwable t) {
                Toast.makeText(StartEndDayDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void callInputAbsence() {
        // get token
        apiInterface = APIClient.inputAbsence(GlobalVar.getAccessToken()).create(APIInterfaceRest.class);
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
        String sLocationName = mLocationNameView.getText().toString();
        String sLocationAddress = null;
        String sPhoto = null;

        // khusus logType di hardcode -> LogType -> Start Day
        Call<Attendence> call3 = apiInterface.inputAbsence(sUsername, sEmployeeID, sBussinessGroupID, dateString, sTime, sBeaconID, sLocationID, sLocationName, sLocationAddress, sLongitude, sLatitude, sLogType, sPhoto, sNotes, sEvent);
        call3.enqueue(new Callback<Attendence>() {
            @Override
            public void onResponse(Call<Attendence> call, Response<Attendence> response) {
                progressDialog.dismiss();
                Attendence data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(StartEndDayDetailActivity.this, "Data Submitted", Toast.LENGTH_LONG).show();
                    finish();
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
