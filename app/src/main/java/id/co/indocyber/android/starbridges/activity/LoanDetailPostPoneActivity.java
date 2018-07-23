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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.ListDraftLoanTransactionApprovedAdapter;
import id.co.indocyber.android.starbridges.model.ListDraftTransactionLoanApproved.ListDraftTransactionLoanApproved;
import id.co.indocyber.android.starbridges.model.LoanSchedule.LoanSchedule;
import id.co.indocyber.android.starbridges.model.LoanTransactionType.LoanTransactionType;
import id.co.indocyber.android.starbridges.model.LoanTransactionType.ReturnValue;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
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
        progressDialog= new ProgressDialog(LoanDetailPostPoneActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

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
        ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(LoanDetailPostPoneActivity.this,
                android.R.layout.simple_spinner_item, lstLoanTransactionType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSchedullePostpone.setAdapter(adapter);



    }

}
