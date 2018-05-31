package com.example.android.starbridges.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.MessageReturn.MessageReturn;
import com.example.android.starbridges.model.ReimbursementType.ReimbursementType;
import com.example.android.starbridges.model.ReimbursementType.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

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
import java.util.Locale;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReimburseDetailActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 999;

    EditText txtDescriptionReimburseDetail, txtAmountReimburseDetail, txtTransactionDateReimburseDetail;
    Spinner spnTypeReimburseDetail;

    Button btnUploadReimburseDetail, btnSubmitReimburseDetail, btnSaveReimburseDetail, btnCancelReimburseDetail;

    ImageView imgTransactionDateReimburseDetail, imgReimburseDetail;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    List<com.example.android.starbridges.model.ReimbursementType.ReturnValue> lstReimbursementType;

    List<Object> exclusiveFields;

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

    String photo;
    int reimbursementTypeID;

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
        imgReimburseDetail=(ImageView)findViewById(R.id.imgReimburseDetail);

        imgTransactionDateReimburseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ReimburseDetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnUploadReimburseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        spnTypeReimburseDetail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ReturnValue reimbusementType=(ReturnValue)spnTypeReimburseDetail.getItemAtPosition(i);

                reimbursementTypeID=reimbusementType.getValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSaveReimburseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveReimbursement();
            }
        });

        initSpinner();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                imgReimburseDetail.setImageBitmap(selectedImage);
                photo = encodeImage(selectedImage);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(ReimburseDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(ReimburseDetailActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
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

    public void saveReimbursement() {

        progressDialog = new ProgressDialog(ReimburseDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        JSONObject paramObject= new JSONObject();
        try {

            paramObject.put("ID",null);
            paramObject.put("EmployeeID",GlobalVar.getEmployeeId());
            paramObject.put("Description",txtDescriptionReimburseDetail.getText().toString());
            paramObject.put("Amount",txtAmountReimburseDetail.getText().toString());
            paramObject.put("ReimbursementTypeID",reimbursementTypeID);
            paramObject.put("IsPreProcess",false);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String transactionDate = "";
            Date convertTransactionDate;
            try{
                convertTransactionDate =  sdf.parse(txtTransactionDateReimburseDetail.getText().toString());
                transactionDate=df.format(convertTransactionDate);
            }catch (Exception e)
            {

            }

            paramObject.put("TransactionDate",transactionDate);
            paramObject.put("TransactionStatusID",10);
            paramObject.put("DecisionNumber",null);
            paramObject.put("AttachmentFile",photo);
            paramObject.put("AttachmentID",null);
            paramObject.put("Message",null);
            paramObject.put("FullAccess",true);
            paramObject.put("ExclusiveFields",exclusiveFields);
            paramObject.put("FullAccess",true);
            paramObject.put("AccessibilityAttribute","");

        }catch (Exception e)
        {

        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),paramObject.toString());
        final APIInterfaceRest apiInterface = APIClient.saveLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<MessageReturn> call3 = apiInterface.saveDetailReimbursement(body, "Save");

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
                Intent intent = new Intent(ReimburseDetailActivity.this, ReimburseActivity.class);
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

}
