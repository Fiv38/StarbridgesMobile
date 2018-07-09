package com.example.android.starbridges.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.time.TimeTCPClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInOutDetailActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_ACCESS_LOCATION = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private FusedLocationProviderClient client;
    private LocationManager locationManager;

    private EditText mEventView, mDateView, mTimeView, mLocationNameView, mNotesView;
    private Spinner mLocationSpinner;
    private Button mSubmit;
    private String sLocationID, sUsername, sLongitude, sLatitude, sDate, sTime, sLogType, sPhoto;
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog;
    private boolean checkStartDay;
    SessionManagement session;
    int timeZoneOffset;
    String sLocationName;
    String sLocationAddress;
    List<ReturnValue> LocItems;
    final List<ReturnValue> listReturnValue= new ArrayList<>();




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out_detail);

        setTitle("ATTENDANCE");
        mEventView = (EditText) findViewById(R.id.txt_event);
        mDateView = (EditText) findViewById(R.id.txt_date);
        mTimeView = (EditText) findViewById(R.id.txt_time);
        mLocationSpinner = (Spinner) findViewById(R.id.sp_location);
        mLocationNameView = (EditText) findViewById(R.id.txt_location_name);
        mNotesView = (EditText) findViewById(R.id.txt_notes);

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String token_sp = user.get(SessionManagement.KEY_TOKEN);
        String loginName = user.get(SessionManagement.KEY_LOGINNAME);
        String employeeId = user.get(SessionManagement.KEY_EMPLOYEE_ID);



        GlobalVar.setToken(token_sp);
        GlobalVar.setLoginName(loginName);
        GlobalVar.setEmployeeId(employeeId);


        mSubmit = (Button) findViewById(R.id.btn_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitData();
            }
        });



        Intent intent = getIntent();
        sDate = intent.getStringExtra("date");
        sTime = intent.getStringExtra("time");
        sUsername = GlobalVar.loginName();
        sLogType = intent.getStringExtra("logType");
        checkStartDay = intent.getBooleanExtra("checkStartDay", false);
        TimeZone timezone = TimeZone.getDefault();
        timeZoneOffset = timezone.getRawOffset()/(60 * 60 * 1000);


        mDateView.setText(sDate);
        mTimeView.setText(sTime);
        initSpinnerLoc();

        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    final ReturnValue returnValue1 = (ReturnValue) mLocationSpinner.getItemAtPosition(i);
                    //Log.d("LocationIdnya", returnValue1.getID());
                    sLocationID = returnValue1.getID();
                    sLocationName=returnValue1.getName();
                    sLocationAddress=returnValue1.getAddress();
                }

                setEnableSpinnerAndEditTextLocation();

//                try {
//                    getInternetTime();
//                } catch (Exception e) {
//
//                }

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
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
        getLocation();

//        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
//        }

    }

//    public void getInternetTime() throws IOException {
//        String timeServer = "time-a.nist.gov";
//
//        NTPUDPClient timeClient = new NTPUDPClient();
//        timeClient.open();
//        timeClient.setSoTimeout(5000);
//        InetAddress inetAddress = InetAddress.getByName(timeServer);
//        TimeInfo timeInfo = timeClient.getTime(inetAddress);
//        long returnTime = timeInfo.getReturnTime();
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(returnTime);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_ACCESS_LOCATION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //getLocation();
//                } else {
//                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
//                }
//
//
//            case MY_CAMERA_REQUEST_CODE:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//
//                } else {
//
//                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//                }
//                break;
//        }

        if (requestCode == REQUEST_ACCESS_LOCATION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "GPS granted", Toast.LENGTH_SHORT).show();
                //getLocation();
            } else {
                Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == MY_CAMERA_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                //dispatchTakePictureIntent();

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }

    }


    public void getLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_LOCATION);
        }
//        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//                    sLatitude=String.valueOf(location.getLatitude());
//                    sLongitude=String.valueOf(location.getLongitude());
//
////                    if (sLogType.equals("Check In")) {
////                        dispatchTakePictureIntent();
////                    } else {
////                        sPhoto=null;
////                        callInputAbsence();
////                    }
//                }
//            }
//        });

    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);
            //final Uri imageUri = data.getData();
            //final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            //final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            sPhoto = encodeImage(imageBitmap);
            callInputAbsence();
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void SubmitData() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_LOCATION);
        }

        if(mLocationNameView.isEnabled())
        {
            if(mLocationNameView.getText().toString().matches(""))
            {
                mLocationNameView.setError("Please fill the location");
            }
            else
            {
                capturePhoto();
            }
            sLocationID=null;
            sLocationName = mLocationNameView.getText().toString();
            sLocationAddress=null;
        }
        else
        {
            capturePhoto();
        }
    }

    public void capturePhoto()
    {
        client = LocationServices.getFusedLocationProviderClient(this);
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    sLatitude = String.valueOf(location.getLatitude());
                    sLongitude = String.valueOf(location.getLongitude());

                    if (sLogType.equals("Check In")) {
                        dispatchTakePictureIntent();
                    } else {
                        sPhoto=null;
                        callInputAbsence();
                    }

                }
                else
                {
                    if (sLogType.equals("Check In")) {
                        dispatchTakePictureIntent();
                    } else {
                        sPhoto=null;
                        callInputAbsence();
                    }
                }
            }
        });
    }

    public void initSpinnerLoc() {

        progressDialog = new ProgressDialog(CheckInOutDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

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

                    LocItems = response.body().getReturnValue();
                    if (LocItems!= null){
                        listReturnValue.addAll(LocItems);
                    } else{
                        Toast.makeText(CheckInOutDetailActivity.this, "spinner Tidak dapat data",Toast.LENGTH_LONG).show();
                    }

                    ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(CheckInOutDetailActivity.this,
                            android.R.layout.simple_spinner_item, listReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mLocationSpinner.setAdapter(adapter);
                } else {

                    Toast.makeText(CheckInOutDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }

                HashMap<String, String> user = session.getUserDetails();
                String location=user.get(SessionManagement.KEY_LOCATION);
                String locationId=user.get(SessionManagement.KEY_LOCATION_ID);

                if(locationId!="" || locationId != null)
                {
                    setupSpinner(locationId);
                }
                else mLocationNameView.setText(location);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<OLocation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CheckInOutDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();

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
        if(counter >= LocItems.size())
            counter=0;

        mLocationSpinner.setSelection(counter);
        progressDialog.dismiss();
    }

    public void callInputAbsence() {

        apiInterface = APIClient.inputAbsence(GlobalVar.getToken()).create(APIInterfaceRest.class);


        long date = System.currentTimeMillis();

        String sEvent = mEventView.getText().toString();
        String sNotes = mNotesView.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = sdf.format(date);
        String sTime = mTimeView.getText().toString();
        String sEmployeeID = null;
        String sBussinessGroupID = null;
        String sBeaconID = null;

        if(mLocationNameView.isEnabled())
        {
            if(mLocationNameView.getText().toString().matches(""))
            {
                mLocationNameView.setError("Please fill the location");
            }
            sLocationID=null;
            sLocationName = mLocationNameView.getText().toString();
            sLocationAddress=null;
        }

        if((mLocationNameView.isEnabled()&&!mLocationNameView.getText().toString().matches(""))||!mLocationSpinner.getSelectedItem().toString().matches(""))
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Loading");
            progressDialog.show();

            SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm:ss");
            Calendar todaysTime = new GregorianCalendar();
            if(checkStartDay==false)
            {

                //adding time 1 second for checkin
                try{
                    todaysTime.setTime(timeFmt.parse(sTime));
                    todaysTime.add(Calendar.SECOND,1);

                }catch (Exception e)
                {

                }

                Call<Attendence> call3 = apiInterface.inputAbsence(sUsername, sEmployeeID, sBussinessGroupID, dateString, sTime, sBeaconID, sLocationID, sLocationName, sLocationAddress, sLongitude, sLatitude, "Start Day", null, sNotes, sEvent,timeZoneOffset);
                call3.enqueue(new Callback<Attendence>() {
                    @Override
                    public void onResponse(Call<Attendence> call, Response<Attendence> response) {
                        Attendence data = response.body();

                        if (data != null && data.getIsSucceed()) {
                            Toast.makeText(CheckInOutDetailActivity.this, "Start Day", Toast.LENGTH_LONG).show();
                        }else if(data != null && data.getMessage() =="Please Check Your Time And Date Settings"){
                            Toast.makeText(CheckInOutDetailActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                        } else {
                            try {
                                //JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(CheckInOutDetailActivity.this, "Failed to Start Day", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(CheckInOutDetailActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Attendence> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                        call.cancel();
                    }
                });
            }



            sTime=new SimpleDateFormat("HH:mm:ss").format(todaysTime.getTime());


            // khusus logType di hardcode -> LogType -> Start Day
            Call<Attendence> call3 = apiInterface.inputAbsence(sUsername, sEmployeeID, sBussinessGroupID, dateString, sTime, sBeaconID, sLocationID, sLocationName, sLocationAddress, sLongitude, sLatitude, sLogType, sPhoto, sNotes, sEvent, timeZoneOffset);
            call3.enqueue(new Callback<Attendence>() {
                @Override
                public void onResponse(Call<Attendence> call, Response<Attendence> response) {
                    progressDialog.dismiss();
                    Attendence data = response.body();

                    if (data != null && data.getIsSucceed()) {
                        Toast.makeText(CheckInOutDetailActivity.this, "Data Submitted", Toast.LENGTH_LONG).show();
                        finish();
                    } else if(data != null && data.getMessage() =="Please Check Your Time And Date Settings"){
                        Toast.makeText(CheckInOutDetailActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                    }else {
                        try {
                            //JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(CheckInOutDetailActivity.this, "Failed to Submit", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(CheckInOutDetailActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
