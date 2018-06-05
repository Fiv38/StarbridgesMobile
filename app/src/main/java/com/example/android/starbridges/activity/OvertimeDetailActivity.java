package com.example.android.starbridges.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.EditOvertime.EditOvertime;
import com.example.android.starbridges.model.MessageReturn.MessageReturn;
import com.example.android.starbridges.model.PersonalOvertime.PersonalOvertime;
import com.example.android.starbridges.model.PersonalOvertime.PersonalOvertime;
import com.example.android.starbridges.model.PersonalOvertime.ReturnValue;
import com.example.android.starbridges.model.SubmitOvertime.SubmitOvertime;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OvertimeDetailActivity extends AppCompatActivity {


    EditText reqDate,ovStart,ovEnd,notes;
    Button upload, save,submit,cancel;
    ImageView image_view;
    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;
    Calendar myCalendar = Calendar.getInstance();
    String photo;
    TextView txtShiftOvertimeDetail, txtOvertimeStartOvertimeDetail, txtOvertimeEndOvertimeDetail;
    private static final int MY_GALLERY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE = 999;
    ReturnValue personalOvertime;

    com.example.android.starbridges.model.EditOvertime.ReturnValue editOvertime;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabelDate();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtime_detail);
        reqDate=(EditText)findViewById(R.id.txt_req_date);
        ovStart = (EditText)findViewById(R.id.txt_req_ov_start);
        ovEnd = (EditText)findViewById(R.id.txt_req_ov_end);
        image_view = (ImageView)findViewById(R.id.image_upload);
        notes=(EditText)findViewById(R.id.txtNotesOvertimeDetail);

        txtShiftOvertimeDetail=(TextView)findViewById(R.id.txtShiftOvertimeDetail);
        txtOvertimeStartOvertimeDetail=(TextView)findViewById(R.id.txtOvertimeStartOvertimeDetail);
        txtOvertimeEndOvertimeDetail=(TextView)findViewById(R.id.txtOvertimeEndOvertimeDetail);

        upload = (Button)findViewById(R.id.btn_upload);
        save = (Button)findViewById(R.id.btn_save);
        submit = (Button)findViewById(R.id.btn_submit);
        cancel = (Button)findViewById(R.id.btn_cancel);
        setTitle("Overtime Request");


        reqDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OvertimeDetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ovStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(OvertimeDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            ovStart.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(ovStart.getText().toString().substring(0,2)) , Integer.parseInt(ovStart.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(OvertimeDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            ovStart.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        ovEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(OvertimeDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            ovEnd.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(ovEnd.getText().toString().substring(0,2)) , Integer.parseInt(ovEnd.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(OvertimeDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            ovEnd.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_GALLERY_REQUEST_CODE);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
                String reqDateFormatted = "";

                Date convertDate;
                try{
                    convertDate =  sdf.parse(reqDate.getText().toString());
                    reqDateFormatted=df.format(convertDate);
                }catch (Exception e)
                {

                }

                AlertDialog.Builder alert = new AlertDialog.Builder(OvertimeDetailActivity.this);
                alert.setTitle("Request Confirmation");
                alert.setMessage("Date\n" +
                        "\t"+reqDateFormatted+"" +
                        "\nStart\n" +
                        "\t"+ ovStart.getText().toString()+"" +
                        "\nEnd\n" +
                        "\t"+ovStart.getText().toString()+"\n\n"+
                        "This information will be saved in draft"
                );
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveDetailOvertime();
                    }
                });

                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
                String reqDateFormatted = "";

                Date convertDate;
                try{
                    convertDate =  sdf.parse(reqDate.getText().toString());
                    reqDateFormatted=df.format(convertDate);
                }catch (Exception e)
                {

                }

                AlertDialog.Builder alert = new AlertDialog.Builder(OvertimeDetailActivity.this);
                alert.setTitle("Request Confirmation");
                alert.setMessage("Date\n" +
                        "\t"+reqDateFormatted+"" +
                        "\nStart\n" +
                        "\t"+ ovStart.getText().toString()+"" +
                        "\nEnd\n" +
                        "\t"+ovStart.getText().toString()+"\n\n"
                        );
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        submitDetailOvertime();
                    }
                });

                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();



            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
                String reqDateFormatted = "";

                Date convertDate;
                try{
                    convertDate =  sdf.parse(reqDate.getText().toString());
                    reqDateFormatted=df.format(convertDate);
                }catch (Exception e)
                {

                }

                AlertDialog.Builder alert = new AlertDialog.Builder(OvertimeDetailActivity.this);
                alert.setTitle("Information will not be save");

                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });

        final Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if(id!=null)
        {
            getData(id);
        }

        getPersonalOverTime();

    }

    public void getData(String id)
    {
        progressDialog = new ProgressDialog(OvertimeDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.editOvertime(id).enqueue(new Callback<EditOvertime>() {
            @Override
            public void onResponse(Call<EditOvertime> call, Response<EditOvertime> response) {

                if (response.body().isIsSucceed()) {
                    editOvertime= response.body().getReturnValue();

                    ovStart.setText(editOvertime.getFrom().substring(11,16) );
                    ovEnd.setText(editOvertime.getTo().substring(11,16) );
                    reqDate.setText(dateFormat(editOvertime.getRequestDate()));
                    notes.setText(editOvertime.getNotes());
                    photo=editOvertime.getAttachmentFile();

                    if(photo != null) {
                        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        image_view.setImageBitmap(bitmap);
                    }


                } else {

                    Toast.makeText(OvertimeDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<EditOvertime> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(OvertimeDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String dateFormat(String dateString)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1;
        String result;
        try{
            date1=df.parse(dateString);
            result=sdf.format(date1);
        }catch (Exception e)
        {
            result="";
        }
        return result;
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                image_view.setImageBitmap(selectedImage);
                photo = encodeImage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(OvertimeDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(OvertimeDetailActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    private void updateLabelDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        reqDate.setText(sdf.format(myCalendar.getTime()));
    }


    public void getPersonalOverTime()
    {
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getPersonalOvertime().enqueue(new Callback<PersonalOvertime>() {
            @Override
            public void onResponse(Call<PersonalOvertime> call, Response<PersonalOvertime> response) {

                if (response.body().isIsSucceed()) {
                    personalOvertime = response.body().getReturnValue();

                    txtShiftOvertimeDetail.setText(personalOvertime.getShiftName());
                    txtOvertimeStartOvertimeDetail.setText(personalOvertime.getOvertimeStart());
                    txtOvertimeEndOvertimeDetail.setText(personalOvertime.getOvertimeEnd());

                } else {

                    Toast.makeText(OvertimeDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PersonalOvertime> call, Throwable t) {
                Toast.makeText(OvertimeDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveDetailOvertime() {

        progressDialog = new ProgressDialog(OvertimeDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        JSONObject paramObject= new JSONObject();
        try {
            paramObject.put("ShiftName",txtShiftOvertimeDetail.getText().toString());
            paramObject.put("OvertimeStart",txtOvertimeStartOvertimeDetail.getText().toString());
            paramObject.put("OvertimeEnd",txtOvertimeEndOvertimeDetail.getText().toString());
            paramObject.put("ID",null);
            paramObject.put("EmployeeID",GlobalVar.getEmployeeId());

            Date date=new Date();
            String patternSQLServer = "yyyy-MM-dd'T'HH:mm:ss.sssssZ";
            SimpleDateFormat formatTimeSQLServer = new SimpleDateFormat(patternSQLServer);

            paramObject.put("RequestDate",formatTimeSQLServer.format(date).toString());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String overtimeDate = "";
            Date convertOvertimeDate;
            try{
                convertOvertimeDate =  sdf.parse(reqDate.getText().toString());
                overtimeDate=df.format(convertOvertimeDate);
            }catch (Exception e)
            {

            }

            paramObject.put("OvertimeDate",overtimeDate);
            paramObject.put("From",ovStart.getText().toString());
            paramObject.put("To",ovEnd.getText().toString());
            paramObject.put("Notes", notes.getText().toString());
            paramObject.put("AttachmentID", "11");
            paramObject.put("AttachmentFile", photo);

            paramObject.put("TransactionStatusID", personalOvertime.getTransactionStatusID()+"");
            paramObject.put("SubmitType", personalOvertime.getSubmitType()+"");
            paramObject.put("Message", personalOvertime.getMessage()+"");
            paramObject.put("MaxOvertimePerDay", personalOvertime.getMaxOvertimePerDay()+"");
            paramObject.put("MaxOvertimePerWeek", personalOvertime.getMaxOvertimePerWeek()+"");
            paramObject.put("MaxOvertimePerMonth", personalOvertime.getMaxOvertimePerMonth()+"");
            paramObject.put("TotalRequestCurrentWeek", personalOvertime.getTotalRequestCurrentWeek()+"");
            paramObject.put("TotalRequestCurrentMonth", personalOvertime.getTotalRequestCurrentMonth()+"");
            paramObject.put("TransactionStatusSaveOrSubmit", "Save");
            paramObject.put("FullAccess", true);
            paramObject.put("ExclusiveFields", personalOvertime.getExclusionFields()+"");
            paramObject.put("AccessibilityAttribute", personalOvertime.getAccessibilityAttribute()+"");

        }catch (Exception e)
        {

        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),paramObject.toString());
        final APIInterfaceRest apiInterface = APIClient.saveLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<MessageReturn> call3 = apiInterface.saveDetailOvertime(body);

        call3.enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                progressDialog.dismiss();
                MessageReturn data = response.body();
                if (data != null) {
                    Toast.makeText(getApplicationContext(), data.getMessage(), Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OvertimeDetailActivity.this, OvertimeActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void submitDetailOvertime() {

        progressDialog = new ProgressDialog(OvertimeDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        JSONObject paramObject= new JSONObject();
        try {
            paramObject.put("ShiftName",txtShiftOvertimeDetail.getText().toString());
            paramObject.put("OvertimeStart",txtOvertimeStartOvertimeDetail.getText().toString());
            paramObject.put("OvertimeEnd",txtOvertimeEndOvertimeDetail.getText().toString());
            paramObject.put("ID",null);
            paramObject.put("EmployeeID",GlobalVar.getEmployeeId());

            Date date=new Date();
            String patternSQLServer = "yyyy-MM-dd'T'HH:mm:ss.sssssZ";
            SimpleDateFormat formatTimeSQLServer = new SimpleDateFormat(patternSQLServer);

            paramObject.put("RequestDate",formatTimeSQLServer.format(date).toString());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String overtimeDate = "";
            Date convertOvertimeDate;
            try{
                convertOvertimeDate =  sdf.parse(reqDate.getText().toString());
                overtimeDate=df.format(convertOvertimeDate);
            }catch (Exception e)
            {

            }

            paramObject.put("OvertimeDate",overtimeDate);
            paramObject.put("From",ovStart.getText().toString());
            paramObject.put("To",ovEnd.getText().toString());
            paramObject.put("Notes", notes.getText().toString());
            paramObject.put("AttachmentID", "11");
            paramObject.put("AttachmentFile", photo);

            paramObject.put("TransactionStatusID", personalOvertime.getTransactionStatusID()+"");
            paramObject.put("SubmitType", personalOvertime.getSubmitType()+"");
            paramObject.put("Message", personalOvertime.getMessage()+"");
            paramObject.put("MaxOvertimePerDay", personalOvertime.getMaxOvertimePerDay()+"");
            paramObject.put("MaxOvertimePerWeek", personalOvertime.getMaxOvertimePerWeek()+"");
            paramObject.put("MaxOvertimePerMonth", personalOvertime.getMaxOvertimePerMonth()+"");
            paramObject.put("TotalRequestCurrentWeek", personalOvertime.getTotalRequestCurrentWeek()+"");
            paramObject.put("TotalRequestCurrentMonth", personalOvertime.getTotalRequestCurrentMonth()+"");
            paramObject.put("TransactionStatusSaveOrSubmit", "Submit");
            paramObject.put("FullAccess", true);
            paramObject.put("ExclusiveFields", personalOvertime.getExclusionFields()+"");
            paramObject.put("AccessibilityAttribute", personalOvertime.getAccessibilityAttribute()+"");

        }catch (Exception e)
        {

        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),paramObject.toString());
        final APIInterfaceRest apiInterface = APIClient.saveLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<SubmitOvertime> call3 = apiInterface.submitOvertime(body, "Submit");

        call3.enqueue(new Callback<SubmitOvertime>() {
            @Override
            public void onResponse(Call<SubmitOvertime> call, Response<SubmitOvertime> response) {
                progressDialog.dismiss();
                SubmitOvertime data = response.body();
                if (data != null) {
                    Toast.makeText(getApplicationContext(), data.getMessage(), Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(OvertimeDetailActivity.this, OvertimeActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<SubmitOvertime> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

}
