package id.co.indocyber.android.starbridges.activity;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.ListDraftLoanTransactionApprovedAdapter;
import id.co.indocyber.android.starbridges.model.ListDraftTransactionLoanApproved.ListDraftTransactionLoanApproved;
import id.co.indocyber.android.starbridges.model.LoanSchedule.LoanSchedule;
import id.co.indocyber.android.starbridges.model.LoanTransactionType.LoanTransactionType;
import id.co.indocyber.android.starbridges.model.LoanTransactionType.ReturnValue;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanDetailPostPoneActivity extends AppCompatActivity {


    Spinner spnTransactionTypePostpone, spnSchedullePostpone;
    EditText txtAmountPostpone, txtDescriptionPostpone;
    Button btnSubmitPostpone, btnSavePostpone, btnCancelPostpone;

    String loanBalanceID;

    APIInterfaceRest apiInterface;

    ProgressDialog progressDialog;

    List<ReturnValue> lstLoanTransactionType;

    List<id.co.indocyber.android.starbridges.model.LoanSchedule.ReturnValue> lstLoanSchedule;

    String transactionStatusID, transactionTypeID, employeeLoanScheduleID;

    List<Object> exclusiveFields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_postpone);

        spnTransactionTypePostpone=(Spinner)findViewById(R.id.spnTransactionTypePostpone);
        spnSchedullePostpone=(Spinner)findViewById(R.id.spnSchedullePostpone);
        txtAmountPostpone=(EditText)findViewById(R.id.txtAmountPostpone);
        txtDescriptionPostpone=(EditText)findViewById(R.id.txtDescriptionPostpone);
        btnSubmitPostpone=(Button)findViewById(R.id.btnSubmitPostpone);
        btnSavePostpone=(Button)findViewById(R.id.btnSavePostpone);
        btnCancelPostpone=(Button)findViewById(R.id.btnCancelPostpone);

        loanBalanceID= getIntent().getStringExtra("LoanBalanceId");

        initSpinnerTransactionType();

        initSpinnerSchedule();

        spnTransactionTypePostpone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ReturnValue transactionType=(ReturnValue)spnTransactionTypePostpone.getItemAtPosition(i);
                transactionTypeID=transactionType.getId()+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnSchedullePostpone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id.co.indocyber.android.starbridges.model.LoanSchedule.ReturnValue loanSchedule=(id.co.indocyber.android.starbridges.model.LoanSchedule.ReturnValue)spnSchedullePostpone.getItemAtPosition(i);
                employeeLoanScheduleID=loanSchedule.getId()+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSavePostpone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubmitData("Save");
            }
        });

        btnSubmitPostpone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubmitData("Submit");
            }
        });
    }

    public void initSpinnerTransactionType()
    {
        progressDialog= new ProgressDialog(LoanDetailPostPoneActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        lstLoanTransactionType=new ArrayList<>();
        ReturnValue returnValue=new ReturnValue();
        lstLoanTransactionType.add(returnValue);

        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getLoanTransactionType().enqueue(new Callback<LoanTransactionType>() {
            @Override
            public void onResponse(Call<LoanTransactionType> call, Response<LoanTransactionType> response) {

                if (response.body().getIsSucceed()) {
                    lstLoanTransactionType.addAll(response.body().getReturnValue());
                    setupSpinnerTransactionType();
                } else {
                    Toast.makeText(LoanDetailPostPoneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoanTransactionType> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanDetailPostPoneActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setupSpinnerTransactionType()
    {
        ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(LoanDetailPostPoneActivity.this,
                android.R.layout.simple_spinner_item, lstLoanTransactionType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTransactionTypePostpone.setAdapter(adapter);



    }

    public void initSpinnerSchedule()
    {
        if(progressDialog==null||!progressDialog.isShowing())
        {
            progressDialog= new ProgressDialog(LoanDetailPostPoneActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }


        lstLoanSchedule=new ArrayList<>();
        id.co.indocyber.android.starbridges.model.LoanSchedule.ReturnValue returnValue=new id.co.indocyber.android.starbridges.model.LoanSchedule.ReturnValue();
        lstLoanSchedule.add(returnValue);

        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getLoanSchedule(loanBalanceID).enqueue(new Callback<LoanSchedule>() {
            @Override
            public void onResponse(Call<LoanSchedule> call, Response<LoanSchedule> response) {

                if (response.body().getIsSucceed()) {
                    lstLoanSchedule.addAll(response.body().getReturnValue());
                    setupSpinnerLoanSchedule();
                } else {
                    Toast.makeText(LoanDetailPostPoneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoanSchedule> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanDetailPostPoneActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setupSpinnerLoanSchedule()
    {
        ArrayAdapter<id.co.indocyber.android.starbridges.model.LoanSchedule.ReturnValue> adapter = new ArrayAdapter<id.co.indocyber.android.starbridges.model.LoanSchedule.ReturnValue>(LoanDetailPostPoneActivity.this, android.R.layout.simple_spinner_item, lstLoanSchedule);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSchedullePostpone.setAdapter(adapter);

    }

    public void saveSubmitData(String transactionStatus)
    {

        progressDialog= new ProgressDialog(LoanDetailPostPoneActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        boolean fullAccess=true;
        String accessibilityAttribute="";
        JSONObject paramObject= new JSONObject();
        try {

            paramObject.put("ID",null);
            paramObject.put("EmployeeID", GlobalVar.getEmployeeId());
            paramObject.put("EmployeeLoanBalanceID",  loanBalanceID);
            paramObject.put("DecisionNumber",null);
            paramObject.put("TransactionStatusID",null);
            paramObject.put("LoanTransactionTypeID",transactionTypeID);
            paramObject.put("LoanPolicyID", null);



            Date date=new Date();
            String patternSQLServer = "yyyy-MM-dd'T'HH:mm:ss.sssssZ";
            SimpleDateFormat formatTimeSQLServer = new SimpleDateFormat(patternSQLServer);

            paramObject.put("StartNewLoanDate",formatTimeSQLServer.format(date).toString());
            paramObject.put("CreditAmount",null);
            paramObject.put("EmployeeLoanScheduleID",employeeLoanScheduleID);
            paramObject.put("Amount", txtAmountPostpone.getText().toString());
            paramObject.put("Description", txtDescriptionPostpone.getText().toString());
            paramObject.put("LoanSettingName", null);
            paramObject.put("Limit", null);
            paramObject.put("AdditionalBalance", null);
            paramObject.put("TransactionStatusID", null);
            paramObject.put("FullAccess", fullAccess);
            paramObject.put("ExclusiveFields", exclusiveFields);
            paramObject.put("AccessibilityAttribute", accessibilityAttribute);

        }catch (Exception e)
        {

        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),paramObject.toString());
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.saveExpeditePostpone(body, transactionStatus).enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {

                if (response.body().isIsSucceed()) {
                    Toast.makeText(LoanDetailPostPoneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoanDetailPostPoneActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                Intent intent=new Intent(LoanDetailPostPoneActivity.this, LoanTransactionActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanDetailPostPoneActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });


    }

}
