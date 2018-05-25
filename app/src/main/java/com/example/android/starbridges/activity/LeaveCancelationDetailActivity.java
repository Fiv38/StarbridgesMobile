package com.example.android.starbridges.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.DecisionNumber.DecisionNumber;
import com.example.android.starbridges.model.DecisionNumber.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.DatePickerFragment;
import com.example.android.starbridges.utility.GlobalVar;
import com.example.android.starbridges.utility.SessionManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveCancelationDetailActivity extends AppCompatActivity {

    Spinner spnDecisionNumberCancelDetail;
    EditText txtLeaveFromCancelDetail, txtLeaveToCancelDetail, txtCancelFromCancelDetail, txtCancelToCancelDetail, txtNotesCancelDetail;
    Button btnUploadCancelDetail, btnSubmitCancelDetail, btnSaveCancelDetail, btnCancelCancelDetail;
    List<ReturnValue> listDecisionNumber;
    APIInterfaceRest apiInterface;
    SessionManagement session;

    final Calendar myCalendar = Calendar.getInstance();

    Date dateLeaveFrom, dateLeaveTo;

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



        txtCancelFromCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int daysBetween=getTimeRemaining(dateLeaveFrom, dateLeaveTo);

                DialogFragment newFragment = new DatePickerFragment(txtCancelFromCancelDetail, dateLeaveFrom, daysBetween);
                newFragment.show(getFragmentManager(),getString(R.string.date_picker));
            }
        });

        txtCancelToCancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int daysBetween=getTimeRemaining(dateLeaveFrom, dateLeaveTo);

                DialogFragment newFragment = new DatePickerFragment(txtCancelToCancelDetail, dateLeaveFrom, daysBetween);
                newFragment.show(getFragmentManager(),getString(R.string.date_picker));
            }
        });

        initSpinner();

        spnDecisionNumberCancelDetail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //final com.example.android.starbridges.model.OLocation.ReturnValue returnValue1=(com.example.android.starbridges.model.OLocation.ReturnValue)spnLocationCDraftDetails.getItemAtPosition(i);
                ReturnValue decisionNumber=(ReturnValue)spnDecisionNumberCancelDetail.getItemAtPosition(i);

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
        listDecisionNumber= new ArrayList<>();
        ReturnValue returnValue=new ReturnValue();
        returnValue.setID("");
        returnValue.setDecisionNumber("");
        listDecisionNumber.add(returnValue);

        apiInterface = APIClient.getDecisionNumber(GlobalVar.getToken()).create(APIInterfaceRest.class);
        String employeeId=GlobalVar.getEmployeeId();
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
                Toast.makeText(LeaveCancelationDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
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
    }

    public void processDatePickerResult(int year, int month, int day, EditText editText){
        String month_String = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string+"/"+month_String+"/"+year_string);
        Toast.makeText(this,getString(R.string.date_picker)+dateMessage,Toast.LENGTH_LONG).show();
        editText.setText(dateMessage);
    }


}


