package id.co.indocyber.android.starbridges.activity;

import android.Manifest;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.model.LoanPolicy.LoanPolicy;
import id.co.indocyber.android.starbridges.model.LoanPolicy.ReturnValue;
import id.co.indocyber.android.starbridges.model.LoanSettingLimit.LoanSettingLimit;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanRequestCreateActivity extends AppCompatActivity {

    TextView txtLoanSettingCreate, txtLimitCreate;
    Spinner spnPolicyCreate;
    EditText txtStartDateCreate, txtAmountCreate, txtCreditAmountCreate, txtDescriptionCreate;
    ImageView imgStartDateCreate;
    Button btnSubmitCreate, btnSaveCreate, btnCancelCreate;

    ProgressDialog progressDialog;
    APIInterfaceRest apiInterface;

    List<ReturnValue> lstLoanPolicies;

    String loanPolicyId, employeeLoanScheduleID;

    List<Object> exclusiveFields;

    id.co.indocyber.android.starbridges.model.LoanSettingLimit.ReturnValue loanLimit;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtStartDateCreate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_create);

        txtLoanSettingCreate=(TextView)findViewById(R.id.txtLoanSettingCreate);
        txtLimitCreate=(TextView)findViewById(R.id.txtLimitCreate);
        spnPolicyCreate=(Spinner)findViewById(R.id.spnPolicyCreate);
        txtStartDateCreate=(EditText)findViewById(R.id.txtStartDateCreate);
        txtAmountCreate=(EditText)findViewById(R.id.txtAmountCreate);
        txtCreditAmountCreate=(EditText)findViewById(R.id.txtCreditAmountCreate);
        txtDescriptionCreate=(EditText)findViewById(R.id.txtDescriptionCreate);
        imgStartDateCreate=(ImageView)findViewById(R.id.imgStartDateCreate);
        btnSubmitCreate=(Button)findViewById(R.id.btnSubmitCreate);
        btnSaveCreate=(Button)findViewById(R.id.btnSaveCreate);
        btnCancelCreate=(Button)findViewById(R.id.btnCancelCreate);

        getLimitLoan();
        initSpinnerLoanPolicy();

        imgStartDateCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(LoanRequestCreateActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        spnPolicyCreate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ReturnValue loanPolicy=(ReturnValue)spnPolicyCreate.getItemAtPosition(i);
                loanPolicyId=loanPolicy.getID()+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSaveCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSubmitData("Save");
            }
        });
    }

    public void getLimitLoan()
    {
        progressDialog= new ProgressDialog(LoanRequestCreateActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getLoanSettingLimit().enqueue(new Callback<LoanSettingLimit>() {
            @Override
            public void onResponse(Call<LoanSettingLimit> call, Response<LoanSettingLimit> response) {


                if (response.body().getIsSucceed()) {
                    loanLimit=response.body().getReturnValue();
                    txtLimitCreate.setText(response.body().getReturnValue().getLimit());
                    txtLoanSettingCreate.setText(response.body().getReturnValue().getNameLoanSetting());
                } else {
                    Toast.makeText(LoanRequestCreateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<LoanSettingLimit> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanRequestCreateActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void initSpinnerLoanPolicy()
    {
        if(progressDialog==null||!progressDialog.isShowing())
        {
            progressDialog= new ProgressDialog(LoanRequestCreateActivity.this);
            progressDialog.setTitle("Loading");
            progressDialog.show();
        }
        lstLoanPolicies=new ArrayList<>();
        ReturnValue loanPolicy=new ReturnValue();
        lstLoanPolicies.add(loanPolicy);

        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getLoanPolicy().enqueue(new Callback<LoanPolicy>() {
            @Override
            public void onResponse(Call<LoanPolicy> call, Response<LoanPolicy> response) {


                if (response.body().getIsSucceed()) {
                    lstLoanPolicies.addAll(response.body().getReturnValue());
                    setupSpinnerLoanPolicy();

                } else {
                    Toast.makeText(LoanRequestCreateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoanPolicy> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanRequestCreateActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setupSpinnerLoanPolicy()
    {
        ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(LoanRequestCreateActivity.this, android.R.layout.simple_spinner_item, lstLoanPolicies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPolicyCreate.setAdapter(adapter);
    }

    public void saveSubmitData(String transactionStatus)
    {

        progressDialog= new ProgressDialog(LoanRequestCreateActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        boolean fullAccess=true;
        String accessibilityAttribute="";
        JSONObject paramObject= new JSONObject();
        try {

            paramObject.put("ID",null);
            paramObject.put("EmployeeID", GlobalVar.getEmployeeId());
            paramObject.put("EmployeeLoanBalanceID",  null);
            paramObject.put("DecisionNumber",null);
            paramObject.put("TransactionStatusID",null);
            paramObject.put("LoanTransactionTypeID",null);
            paramObject.put("LoanPolicyID", loanPolicyId);



            Date date=new Date();
            String patternSQLServer = "yyyy-MM-dd'T'HH:mm:ss.sssssZ";
            String patternDate="dd/MM/yyyy";
            SimpleDateFormat formatTimeSQLServer = new SimpleDateFormat(patternSQLServer);
            SimpleDateFormat formatTimeStarbridge=new SimpleDateFormat(patternDate);
            Date dStartDate=new Date();
            String startDate="";
            try
            {
                dStartDate=formatTimeStarbridge.parse(txtStartDateCreate.getText().toString());
                startDate=formatTimeSQLServer.format(dStartDate);
            }
            catch (Exception e)
            {

            }

            paramObject.put("StartNewLoanDate",startDate);
            paramObject.put("CreditAmount",txtCreditAmountCreate.getText().toString());
            paramObject.put("EmployeeLoanScheduleID",employeeLoanScheduleID);
            paramObject.put("Amount", txtAmountCreate.getText().toString());
            paramObject.put("Description", txtDescriptionCreate.getText().toString());
            paramObject.put("LoanSettingName", loanLimit.getNameLoanSetting());
            paramObject.put("Limit", loanLimit.getLimit());
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
        apiInterface.saveLoanRequest(body, transactionStatus).enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {

                if (response.body().isIsSucceed()) {
                    Toast.makeText(LoanRequestCreateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoanRequestCreateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                Intent intent=new Intent(LoanRequestCreateActivity.this, LoanTransactionMainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanRequestCreateActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });


    }

}
