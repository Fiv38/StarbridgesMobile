package id.co.indocyber.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import id.co.indocyber.android.starbridges.model.DecisionNumber.DecisionNumber;
import id.co.indocyber.android.starbridges.model.DecisionNumber.ReturnValue;
import id.co.indocyber.android.starbridges.model.EditLeaveCancelation.EditLeaveCancelation;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.DatePickerFragment;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveCancelationDetailActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 999;

    Spinner spnDecisionNumberCancelDetail;
    EditText txtLeaveFromCancelDetail, txtLeaveToCancelDetail, txtCancelFromCancelDetail, txtCancelToCancelDetail, txtNotesCancelDetail;
    Button btnUploadCancelDetail, btnSubmitCancelDetail, btnSaveCancelDetail, btnCancelCancelDetail;
    List<ReturnValue> listDecisionNumber;
    APIInterfaceRest apiInterface;
    SessionManagement session;
    ImageView imgCancelDetail, imgCancelFromDateCancelDetail, imgCancelFromTimeCancelDetail, imgCancelToDateCancelDetail, imgCancelToTimeCancelDetail;
    ProgressDialog progressDialog;

    String requestType, leaveRequestTransactionID, accessibilityAttribute, photo, id="";
    String cancelFrom="", cancelTo="";
    List<Object> exclusiveFields;
    boolean fullAccess;
    int leaveRequestRuleID;
    final Calendar myCalendar = Calendar.getInstance();

    Date dateLeaveFrom, dateLeaveTo;

    id.co.indocyber.android.starbridges.model.EditLeaveCancelation.ReturnValue editLeaveCancelation;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_cancelation_detail);
        setTitle("Leave Cancelation");

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String token_sp = user.get(SessionManagement.KEY_TOKEN);
        String fullName_sp = user.get(SessionManagement.KEY_FULLNAME);
        String nik_sp = user.get(SessionManagement.KEY_NIK);
        String employee_sp=user.get(SessionManagement.KEY_EMPLOYEE_ID);
        GlobalVar.setToken(token_sp);
        GlobalVar.setFullname(fullName_sp);
        GlobalVar.setNIK(nik_sp);
        GlobalVar.setEmployeeId(employee_sp);


        final Intent intent = getIntent();
        id = intent.getStringExtra("id");
        if(id!=null)
        {
            getData(id);
        }
        else
            initSpinner();

        spnDecisionNumberCancelDetail=(Spinner)findViewById(R.id.spnDecisionNumberCancelDetail);
        txtLeaveFromCancelDetail=(EditText)findViewById(R.id.txtLeaveFromCancelDetail);
        txtLeaveToCancelDetail=(EditText)findViewById(R.id.txtLeaveToCancelDetail);
        txtCancelFromCancelDetail=(EditText)findViewById(R.id.txtCancelFromCancelDetail);
        txtCancelToCancelDetail=(EditText)findViewById(R.id.txtCancelToCancelDetail);
        txtNotesCancelDetail=(EditText)findViewById(R.id.txtNotesCancelDetail);

        btnUploadCancelDetail=(Button)findViewById(R.id.btnUploadCancelDetail);
        btnSubmitCancelDetail=(Button)findViewById(R.id.btnSubmitCancelDetail);
        btnSaveCancelDetail=(Button)findViewById(R.id.btnSaveCancelDetail);
        btnCancelCancelDetail=(Button)findViewById(R.id.btnCancelCancelDetail);

        imgCancelDetail=(ImageView)findViewById(R.id.imgCancelDetail);
        imgCancelFromDateCancelDetail=(ImageView)findViewById(R.id.imgCancelFromDateCancelDetail);
        imgCancelToDateCancelDetail=(ImageView)findViewById(R.id.imgCancelToDateCancelDetail);


        imgCancelFromDateCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int daysBetween=getTimeRemaining(dateLeaveFrom, dateLeaveTo);

                DialogFragment newFragment = new DatePickerFragment(txtCancelFromCancelDetail, dateLeaveFrom, daysBetween);
                newFragment.show(getFragmentManager(),getString(R.string.date_picker));
            }
        });

        btnCancelCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LeaveCancelationDetailActivity.this);
                alert.setTitle("This information will not be saved");
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

        imgCancelToDateCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int daysBetween=getTimeRemaining(dateLeaveFrom, dateLeaveTo);

                DialogFragment newFragment = new DatePickerFragment(txtCancelToCancelDetail, dateLeaveFrom, daysBetween);
                newFragment.show(getFragmentManager(),getString(R.string.date_picker));
            }
        });

        btnUploadCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                //dispatchTakePictureIntent();
            }
        });

        btnSaveCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
                String leaveFrom = "";
                String leaveTo = "";
                String cancelFrom="";
                String cancelTo="";

                Date convertDateLeaveFrom, convertDateLeaveTo, convertDateCancelFrom=new Date(), convertDateCancelTo=new Date();
                try{
                    convertDateLeaveFrom =  sdf.parse(txtLeaveFromCancelDetail.getText().toString());
                    leaveFrom=df.format(convertDateLeaveFrom);
                    convertDateLeaveTo =  sdf.parse(txtLeaveToCancelDetail.getText().toString());
                    leaveTo=df.format(convertDateLeaveTo);
                    convertDateCancelFrom=sdf.parse(txtCancelFromCancelDetail.getText().toString());
                    cancelFrom=df.format(convertDateCancelFrom);
                    convertDateCancelTo=sdf.parse(txtCancelToCancelDetail.getText().toString());
                    cancelTo=df.format(convertDateCancelTo);
                }catch (Exception e)
                {

                }

                if(txtCancelFromCancelDetail.getText().toString().matches(""))
                {
                    txtCancelFromCancelDetail.setError("Please fill cancel from");
                }
                else if(txtCancelToCancelDetail.getText().toString().matches(""))
                {
                    txtCancelToCancelDetail.setError("Please fill cancel to");
                }
                else if(convertDateCancelFrom.after(convertDateCancelTo))
                {
                    Toast.makeText(LeaveCancelationDetailActivity.this, "Cancel To must bigger than Cancel From", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(LeaveCancelationDetailActivity.this);
                    alert.setTitle("Request Confirmation");
                    alert.setMessage("Request Type\n" +
                            "\t"+requestType+"" +
                            "\nLeave\n" +
                            "\t"+ leaveFrom +" - "+leaveTo+"" +
                            "\nCancelation\n" +
                            "\t"+cancelFrom+" - "+cancelTo+"" +
                            "\nNotes\n" +
                            "\t"+txtNotesCancelDetail.getText().toString()+"\n\n" +
                            "This information will be saved in draft");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            saveLeaveCollection();
                        }
                    });

                    alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
            }
        });

        btnSubmitCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
                String leaveFrom = "";
                String leaveTo = "";
                String cancelFrom="";
                String cancelTo="";

                Date convertDateLeaveFrom, convertDateLeaveTo, convertDateCancelFrom=new Date(), convertDateCancelTo=new Date();
                try{
                    convertDateLeaveFrom =  sdf.parse(txtLeaveFromCancelDetail.getText().toString());
                    leaveFrom=df.format(convertDateLeaveFrom);
                    convertDateLeaveTo =  sdf.parse(txtLeaveToCancelDetail.getText().toString());
                    leaveTo=df.format(convertDateLeaveTo);
                    convertDateCancelFrom=sdf.parse(txtCancelFromCancelDetail.getText().toString());
                    cancelFrom=df.format(convertDateCancelFrom);
                    convertDateCancelTo=sdf.parse(txtCancelToCancelDetail.getText().toString());
                    cancelTo=df.format(convertDateCancelTo);
                }catch (Exception e)
                {

                }


                if(txtCancelFromCancelDetail.getText().toString().matches(""))
                {
                    txtCancelFromCancelDetail.setError("Please fill cancel from");
                }
                else if(txtCancelToCancelDetail.getText().toString().matches(""))
                {
                    txtCancelToCancelDetail.setError("Please fill cancel to");
                }
                else if(convertDateCancelFrom.after(convertDateCancelTo))
                {
                    Toast.makeText(LeaveCancelationDetailActivity.this, "Cancel To must bigger than Cancel From", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(LeaveCancelationDetailActivity.this);
                    alert.setTitle("Request Confirmation");
                    alert.setMessage("Request Type\n" +
                            "\t"+requestType+"" +
                            "\nLeave\n" +
                            "\t"+ leaveFrom +" - "+leaveTo+"" +
                            "\nCancelation\n" +
                            "\t"+cancelFrom+" - "+cancelTo+"" +
                            "\nNotes\n" +
                            "\t"+txtNotesCancelDetail.getText().toString());
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            submitLeaveCollection();
                        }
                    });

                    alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();

                }
            }
        });

        spnDecisionNumberCancelDetail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //final com.example.android.starbridges.model.OLocation.ReturnValue returnValue1=(com.example.android.starbridges.model.OLocation.ReturnValue)spnLocationCDraftDetails.getItemAtPosition(i);
                ReturnValue decisionNumber=(ReturnValue)spnDecisionNumberCancelDetail.getItemAtPosition(i);

                requestType=decisionNumber.getRequestType();
                leaveRequestRuleID=decisionNumber.getLeaveRequestRuleID();
                leaveRequestTransactionID=decisionNumber.getID();
                fullAccess=decisionNumber.isFullAccess();
                exclusiveFields=decisionNumber.getExclusionFields();
                accessibilityAttribute=decisionNumber.getAccessibilityAttribute();

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                String leaveFrom = "";
                String leaveTo = "";
                try{
                    dateLeaveFrom =  df.parse(decisionNumber.getStartLeave());
                    leaveFrom=sdf.format(dateLeaveFrom);
                    dateLeaveTo =  df.parse(decisionNumber.getEndLeave());
                    leaveTo=sdf.format(dateLeaveTo);
                }catch (Exception e)
                {

                }

                txtLeaveFromCancelDetail.setText(leaveFrom);
                txtLeaveToCancelDetail.setText(leaveTo);

                if(txtLeaveFromCancelDetail.getText().toString().matches(txtLeaveToCancelDetail.getText().toString()))
                {
                    imgCancelFromDateCancelDetail.setEnabled(false);
                    imgCancelToDateCancelDetail.setEnabled(false);
                    txtCancelFromCancelDetail.setText(leaveFrom);
                    txtCancelToCancelDetail.setText(leaveTo);
                }
                else
                {
                    if(txtLeaveFromCancelDetail.getText().toString().matches(""))
                    {
                        imgCancelFromDateCancelDetail.setEnabled(false);
                        imgCancelToDateCancelDetail.setEnabled(false);
                    }
                    else
                    {
                        imgCancelFromDateCancelDetail.setEnabled(true);
                        imgCancelToDateCancelDetail.setEnabled(true);
                    }

                    if(editLeaveCancelation != null && editLeaveCancelation.getLeaveRequestTransactionID().equals(decisionNumber.getID()))
                    {
                        txtCancelToCancelDetail.setText(cancelTo);
                        txtCancelFromCancelDetail.setText(cancelFrom);
                    }
                    else{
                        txtCancelToCancelDetail.setText("");
                        txtCancelFromCancelDetail.setText("");
                    }
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public int getTimeRemaining(Date date1, Date date2)
    {
        Calendar sDate = toCalendar(date1);
        Calendar eDate = toCalendar(date2);

        // Get the represented date in milliseconds
        long milis1 = sDate.getTimeInMillis();
        long milis2 = eDate.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = Math.abs(milis2 - milis1);

        return (int)(diff / (24 * 60 * 60 * 1000));
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public void initSpinner()
    {

        if(progressDialog==null)
        {
            progressDialog= new ProgressDialog(LeaveCancelationDetailActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }
        else if(!progressDialog.isShowing())
            progressDialog.show();

        listDecisionNumber= new ArrayList<>();
        ReturnValue returnValue=new ReturnValue();
        returnValue.setID("");
        returnValue.setDecisionNumber("");
        listDecisionNumber.add(returnValue);

        apiInterface = APIClient.getDecisionNumber(GlobalVar.getToken()).create(APIInterfaceRest.class);
        String employeeId= GlobalVar.getEmployeeId();
        apiInterface.getDecisionNumber(GlobalVar.getEmployeeId()).enqueue(new Callback<DecisionNumber>() {
            @Override
            public void onResponse(Call<DecisionNumber> call, Response<DecisionNumber> response) {

                if (response.isSuccessful()) {


                    listDecisionNumber.addAll(response.body().getReturnValue());

                    setupSpinner();

                } else {

                    Toast.makeText(LeaveCancelationDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
             public void onFailure(Call<DecisionNumber> call, Throwable t) {
                Toast.makeText(LeaveCancelationDetailActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
                setupSpinner();
            }
        });
    }

    public void setupSpinner()
    {
        ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(LeaveCancelationDetailActivity.this,
                android.R.layout.simple_spinner_item, listDecisionNumber);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDecisionNumberCancelDetail.setAdapter(adapter);

        if(editLeaveCancelation!=null)
        {
            int counter=0;
            for(ReturnValue decisionNumber:listDecisionNumber)
            {
                if(editLeaveCancelation.getLeaveRequestTransactionID().equals(decisionNumber.getID())) break;
                counter++;
            }
            spnDecisionNumberCancelDetail.setSelection(counter);
        }
        if(progressDialog.isShowing())
            progressDialog.dismiss();

    }

    public void processDatePickerResult(int year, int month, int day, EditText editText){
        String month_String;
        if(Integer.toString(month+1).length()<2) month_String = "0" + Integer.toString(month+1);
        else month_String = Integer.toString(month+1);

        String day_string;
        if(Integer.toString(day).length()<2) day_string = "0" + Integer.toString(day);
        else day_string =  Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string+"/"+month_String+"/"+year_string);
        editText.setText(dateMessage);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                imgCancelDetail.setImageBitmap(selectedImage);
                photo = encodeImage(selectedImage);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(LeaveCancelationDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(LeaveCancelationDetailActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void saveLeaveCollection() {

        progressDialog = new ProgressDialog(LeaveCancelationDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        JSONObject paramObject= new JSONObject();
        try {

            paramObject.put("ID",id);
            paramObject.put("EmployeeID", GlobalVar.getEmployeeId());
            paramObject.put("RequestorID",3);
            paramObject.put("LeaveRequestDecisionNumber",spnDecisionNumberCancelDetail.getSelectedItem().toString());
            paramObject.put("LeaveRequestTransactionID",leaveRequestTransactionID);
            paramObject.put("LeaveRequestType",requestType);
            paramObject.put("LeaveRequestRuleID", leaveRequestRuleID);


            Date date=new Date();
            String patternSQLServer = "yyyy-MM-dd'T'HH:mm:ss.sssssZ";
            SimpleDateFormat formatTimeSQLServer = new SimpleDateFormat(patternSQLServer);

            paramObject.put("RequestDate",formatTimeSQLServer.format(date).toString());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String leaveFrom = "";
            String leaveTo = "";
            String cancelFrom="";
            String cancelTo="";
            Date convertDateLeaveFrom, convertDateLeaveTo, convertDateCancelFrom, convertDateCancelTo;
            try{
                convertDateLeaveFrom =  sdf.parse(txtLeaveFromCancelDetail.getText().toString());
                leaveFrom=df.format(convertDateLeaveFrom);
                convertDateLeaveTo =  sdf.parse(txtLeaveToCancelDetail.getText().toString());
                leaveTo=df.format(convertDateLeaveTo);
                convertDateCancelFrom=sdf.parse(txtCancelFromCancelDetail.getText().toString());
                cancelFrom=df.format(convertDateCancelFrom);
                convertDateCancelTo=sdf.parse(txtCancelToCancelDetail.getText().toString());
                cancelTo=df.format(convertDateCancelTo);
            }catch (Exception e)
            {

            }
            paramObject.put("LeaveFrom",leaveFrom);
            paramObject.put("LeaveTo",leaveTo);
            paramObject.put("CancelFrom",cancelFrom);
            paramObject.put("CancelTo", cancelTo);
            paramObject.put("Notes", txtNotesCancelDetail.getText().toString());
            paramObject.put("AttachmentFile", photo);
            paramObject.put("AttachmentID", null);
            paramObject.put("AdditionalBalance", null);
            paramObject.put("TransactionStatusID", null);
            paramObject.put("TotalUnitReduce", 17);
            paramObject.put("TransactionStatusSaveOrSubmit", "Save");
            paramObject.put("FullAccess", fullAccess);
            paramObject.put("ExclusiveFields", exclusiveFields);
            paramObject.put("AccessibilityAttribute", accessibilityAttribute);

        }catch (Exception e)
        {

        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),paramObject.toString());
        final APIInterfaceRest apiInterface = APIClient.saveLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<MessageReturn> call3 = apiInterface.saveLeaveCancelation(body, "Save");

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
                Intent intent = new Intent(LeaveCancelationDetailActivity.this, LeaveCancelationActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

    public void submitLeaveCollection() {

        progressDialog = new ProgressDialog(LeaveCancelationDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        JSONObject paramObject= new JSONObject();
        try {

            paramObject.put("ID",id);
            paramObject.put("EmployeeID", GlobalVar.getEmployeeId());
            paramObject.put("RequestorID",3);
            paramObject.put("LeaveRequestDecisionNumber",spnDecisionNumberCancelDetail.getSelectedItem().toString());
            paramObject.put("LeaveRequestTransactionID",leaveRequestTransactionID);
            paramObject.put("LeaveRequestType",requestType);
            paramObject.put("LeaveRequestRuleID", leaveRequestRuleID);


            Date date=new Date();
            String patternSQLServer = "yyyy-MM-dd'T'HH:mm:ss.sssssZ";
            SimpleDateFormat formatTimeSQLServer = new SimpleDateFormat(patternSQLServer);

            paramObject.put("RequestDate",formatTimeSQLServer.format(date).toString());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String leaveFrom = "";
            String leaveTo = "";
            String cancelFrom="";
            String cancelTo="";
            Date convertDateLeaveFrom, convertDateLeaveTo, convertDateCancelFrom, convertDateCancelTo;
            try{
                convertDateLeaveFrom =  sdf.parse(txtLeaveFromCancelDetail.getText().toString());
                leaveFrom=df.format(convertDateLeaveFrom);
                convertDateLeaveTo =  sdf.parse(txtLeaveToCancelDetail.getText().toString());
                leaveTo=df.format(convertDateLeaveTo);
                convertDateCancelFrom=sdf.parse(txtCancelFromCancelDetail.getText().toString());
                cancelFrom=df.format(convertDateCancelFrom);
                convertDateCancelTo=sdf.parse(txtCancelToCancelDetail.getText().toString());
                cancelTo=df.format(convertDateCancelTo);
            }catch (Exception e)
            {

            }
            paramObject.put("LeaveFrom",leaveFrom);
            paramObject.put("LeaveTo",leaveTo);
            paramObject.put("CancelFrom",cancelFrom);
            paramObject.put("CancelTo", cancelTo);
            paramObject.put("Notes", txtNotesCancelDetail.getText().toString());
            paramObject.put("AttachmentFile", photo);
            paramObject.put("AttachmentID", 1);
            paramObject.put("AdditionalBalance", 15);
            paramObject.put("TransactionStatusID", 16);
            paramObject.put("TotalUnitReduce", 17);
            paramObject.put("TransactionStatusSaveOrSubmit", "Submit");
            paramObject.put("FullAccess", fullAccess);
            paramObject.put("ExclusiveFields", exclusiveFields);
            paramObject.put("AccessibilityAttribute", accessibilityAttribute);

        }catch (Exception e)
        {

        }

       RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),paramObject.toString());
        final APIInterfaceRest apiInterface = APIClient.detailRequestConfirmationCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<MessageReturn> call3 = apiInterface.saveLeaveCancelation(body, "Submit");

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
                Intent intent = new Intent(LeaveCancelationDetailActivity.this, LeaveCancelationActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }

    public void getData(String id)
    {
        progressDialog= new ProgressDialog(LeaveCancelationDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.editDraftLeaveCancelation(id).enqueue(new Callback<EditLeaveCancelation>() {
            @Override
            public void onResponse(Call<EditLeaveCancelation> call, Response<EditLeaveCancelation> response) {

                if (response.body().isIsSucceed()) {
                    editLeaveCancelation= response.body().getReturnValue();

                    txtLeaveFromCancelDetail.setText(dateFormat(editLeaveCancelation.getLeaveFrom())  );
                    txtLeaveToCancelDetail.setText(dateFormat(editLeaveCancelation.getLeaveTo())  );
                    cancelFrom=dateFormat(editLeaveCancelation.getCancelFrom());
                    cancelTo=dateFormat(editLeaveCancelation.getCancelTo());
//                    txtCancelFromCancelDetail.setText(dateFormat(editLeaveCancelation.getCancelFrom())  );
//                    txtCancelToCancelDetail.setText(dateFormat(editLeaveCancelation.getCancelTo())  );
                    txtNotesCancelDetail.setText(editLeaveCancelation.getNotes());
                    photo=editLeaveCancelation.getAttachmentFile();

                    if(photo != null) {
                        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imgCancelDetail.setImageBitmap(bitmap);
                    }

                    editLeaveCancelation=response.body().getReturnValue();


                } else {

                    Toast.makeText(LeaveCancelationDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                initSpinner();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<EditLeaveCancelation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveCancelationDetailActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
                initSpinner();
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

}


