package com.example.android.starbridges.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.ReimbursementType.ReimbursementType;
import com.example.android.starbridges.model.ReimbursementType.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReimburseDetailActivity extends AppCompatActivity {

    EditText txtDescriptionReimburseDetail, txtAmountReimburseDetail, txtTransactionDateReimburseDetail;
    Spinner spnTypeReimburseDetail;

    Button btnUploadReimburseDetail, btnSubmitReimburseDetail, btnSaveReimburseDetail, btnCancelReimburseDetail;

    ImageView imgTransactionDateReimburseDetail;

    APIInterfaceRest apiInterface;

    List<com.example.android.starbridges.model.ReimbursementType.ReturnValue> lstReimbursementType;

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

        txtTransactionDateReimburseDetail.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse_detail);

        txtDescriptionReimburseDetail=(EditText)findViewById(R.id.txtDescriptionReimburseDetail);
        txtAmountReimburseDetail=(EditText)findViewById(R.id.txtAmountReimburseDetail);
        txtTransactionDateReimburseDetail=(EditText)findViewById(R.id.txtTransactionDateReimburseDetail);
        spnTypeReimburseDetail=(Spinner)findViewById(R.id.spnTypeReimburseDetail);
        btnUploadReimburseDetail=(Button)findViewById(R.id.btnUploadReimburseDetail);
        btnSubmitReimburseDetail=(Button)findViewById(R.id.btnSubmitReimburseDetail);
        btnSaveReimburseDetail=(Button)findViewById(R.id.btnSaveReimburseDetail);
        btnCancelReimburseDetail=(Button)findViewById(R.id.btnCancelReimburseDetail);
        imgTransactionDateReimburseDetail=(ImageView)findViewById(R.id.imgTransactionDateReimburseDetail);

        imgTransactionDateReimburseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ReimburseDetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        initSpinner();
    }

    public void initSpinner()
    {
        lstReimbursementType= new ArrayList<>();
        ReturnValue returnValue=new ReturnValue();
        returnValue.setText("");
        returnValue.setValue(0);
        lstReimbursementType.add(returnValue);

        apiInterface = APIClient.getReimbursementType(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getReimbursementType().enqueue(new Callback<ReimbursementType>() {
            @Override
            public void onResponse(Call<ReimbursementType> call, Response<ReimbursementType> response) {

                if (response.isSuccessful()) {

                    lstReimbursementType.addAll(response.body().getReturnValue());

                    setupSpinner();

                } else {

                    Toast.makeText(ReimburseDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReimbursementType> call, Throwable t) {
                Toast.makeText(ReimburseDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                setupSpinner();
            }
        });
    }

    public void setupSpinner()
    {
        ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(ReimburseDetailActivity.this,
                android.R.layout.simple_spinner_item, lstReimbursementType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTypeReimburseDetail.setAdapter(adapter);

    }

}
