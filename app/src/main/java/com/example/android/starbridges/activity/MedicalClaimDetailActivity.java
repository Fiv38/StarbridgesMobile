package com.example.android.starbridges.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.getclaimpolicy.GetClaimPolicy;
import com.example.android.starbridges.model.getemployeefamily.GetEmployeeFamily;
import com.example.android.starbridges.model.getmedicalpolicy.GetMedicalPolicy;
import com.example.android.starbridges.model.getmedicalsupport.GetMedicalSupport;
import com.example.android.starbridges.model.requesttype.ReturnValue;
import com.example.android.starbridges.model.saveLeaveRequest.SaveLeaveRequest;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalClaimDetailActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int MY_GALLERY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE = 999;

    private Spinner spinnerMedicalPolicy, spinnerEmployeeFamily, spinnerClaimPolicy;
    private TextView medicalGrade;
    private Button uploadButton;
    private ImageView imageView;

    // Declare var instance
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog;


    private String medicalSupportID, medicalSupportName;
    private String medicalPolicyName, medicalPolicyID, medicalPolicyTypeID;
    private String employeeFamilyID, employeeFamilyName;
    private String claimPolicyID, claimPolicyName;
    private String attachmentFile;

    // get data from spinner Policy Name
    List<com.example.android.starbridges.model.getmedicalpolicy.ReturnValue> PolicyItems;
    List<com.example.android.starbridges.model.getmedicalpolicy.ReturnValue> listPolicyReturnValue;

    // get data from spinner Employee Family
    List<com.example.android.starbridges.model.getemployeefamily.ReturnValue> employeeItems;
    List<com.example.android.starbridges.model.getemployeefamily.ReturnValue> listEmployeeReturnValue;

    // get data from spinner Claim Policy
    List<com.example.android.starbridges.model.getclaimpolicy.ReturnValue> claimPolicyItems;
    List<com.example.android.starbridges.model.getclaimpolicy.ReturnValue> listClaimPolicyReturnValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_claim_detail);
        setTitle("Medical Support");

        // get reference to component
        medicalGrade = (TextView) findViewById(R.id.medicalGradeView);
        spinnerMedicalPolicy = (Spinner) findViewById(R.id.policyTypeSpinner);
        spinnerEmployeeFamily = (Spinner) findViewById(R.id.familyTypeSpinner);
        spinnerClaimPolicy = (Spinner) findViewById(R.id.claimPolicyTypeSpinner);
        uploadButton = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imageView);

        // load api
        initMedicalSupport();
        // load spinner
        //initSpinnerMedicalPolicy();
        //initSpinnerEmployeeFamily();
        //initSpinnerClaimPolicy();

        spinnerMedicalPolicy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    final com.example.android.starbridges.model.getmedicalpolicy.ReturnValue returnValue = (com.example.android.starbridges.model.getmedicalpolicy.ReturnValue) spinnerMedicalPolicy.getItemAtPosition(i);
                    medicalPolicyID = returnValue.getID();
                    medicalPolicyName = returnValue.getPolicyName();
                    medicalPolicyTypeID = returnValue.getPolicyTypeID();

                    if(medicalPolicyTypeID.equals("2")) {
                        initSpinnerEmployeeFamily();
                        spinnerEmployeeFamily.setEnabled(true);
                    }else{
                        employeeFamilyID = "";
                        employeeFamilyName = "";
                        spinnerEmployeeFamily.setSelection(0);
                        spinnerEmployeeFamily.setEnabled(false);
                    }

                    // call init spinner claim policy
                    initSpinnerClaimPolicy();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEmployeeFamily.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if(i>0){
                    final com.example.android.starbridges.model.getemployeefamily.ReturnValue returnValue1 = (com.example.android.starbridges.model.getemployeefamily.ReturnValue) spinnerEmployeeFamily.getItemAtPosition(i);
                    employeeFamilyID = returnValue1.getID();
                    employeeFamilyName = returnValue1.getName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerClaimPolicy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if(i > 0){
                    final com.example.android.starbridges.model.getclaimpolicy.ReturnValue returnValue2 = (com.example.android.starbridges.model.getclaimpolicy.ReturnValue) spinnerClaimPolicy.getItemAtPosition(i);
                    claimPolicyID = returnValue2.getValue().toString();
                    claimPolicyName = returnValue2.getText();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MedicalClaimDetailActivity.this, new String[]{Manifest.permission.CAMERA}, MY_GALLERY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                imageView.setImageBitmap(selectedImage);
                attachmentFile = encodeImage(selectedImage);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(MedicalClaimDetailActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    public void initMedicalSupport(){
        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(MedicalClaimDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<GetMedicalSupport> call3 = apiInterface.getMedicalSupport();
        call3.enqueue(new Callback<GetMedicalSupport>() {

            @Override
            public void onResponse(Call<GetMedicalSupport> call, Response<GetMedicalSupport> response) {
                progressDialog.dismiss();
                GetMedicalSupport data = response.body();

                if (data != null && data.getIsSucceed()) {
                    medicalSupportID = response.body().getReturnValue().getMedicalSupportID().toString();
                    medicalSupportName = response.body().getReturnValue().getMedicalSupportName();

                    // set text medical grade
                    medicalGrade.setText(medicalSupportName);

                    // init spinner medical policy
                    initSpinnerMedicalPolicy();

                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetMedicalSupport> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    // load spinner Claim Policy
    public void initSpinnerClaimPolicy(){
        listClaimPolicyReturnValue = new ArrayList<>();
        com.example.android.starbridges.model.getclaimpolicy.ReturnValue returnValue2 = new com.example.android.starbridges.model.getclaimpolicy.ReturnValue();
        returnValue2.setText("");
        returnValue2.setValue(0);
        listClaimPolicyReturnValue.add(returnValue2);

        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);

        Call<GetClaimPolicy> call3 = apiInterface.getClaimPolicy(medicalPolicyID);
        call3.enqueue(new Callback<GetClaimPolicy>() {

            @Override
            public void onResponse(Call<GetClaimPolicy> call, Response<GetClaimPolicy> response) {
                //progressDialog.dismiss();
                GetClaimPolicy data = response.body();

                if (data != null && data.getIsSucceed()) {
                    claimPolicyItems = response.body().getReturnValue();
                    listClaimPolicyReturnValue.addAll(claimPolicyItems);

                    ArrayAdapter<com.example.android.starbridges.model.getclaimpolicy.ReturnValue> adapter = new ArrayAdapter<com.example.android.starbridges.model.getclaimpolicy.ReturnValue>(MedicalClaimDetailActivity.this,
                            android.R.layout.simple_spinner_item, listClaimPolicyReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerClaimPolicy.setAdapter(adapter);

                    // set text while edit
                    //setupSpinner();
                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetClaimPolicy> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    // load spinner Medical Employee Family
    public void initSpinnerEmployeeFamily(){
        listEmployeeReturnValue = new ArrayList<>();
        com.example.android.starbridges.model.getemployeefamily.ReturnValue returnValue1 = new com.example.android.starbridges.model.getemployeefamily.ReturnValue();
        returnValue1.setID("");
        returnValue1.setName("");
        listEmployeeReturnValue.add(returnValue1);

        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);

        Call<GetEmployeeFamily> call3 = apiInterface.getEmployeeFamily("13");
        call3.enqueue(new Callback<GetEmployeeFamily>() {

            @Override
            public void onResponse(Call<GetEmployeeFamily> call, Response<GetEmployeeFamily> response) {
                //progressDialog.dismiss();
                GetEmployeeFamily data = response.body();

                if (data != null && data.getIsSucceed()) {
                    employeeItems = response.body().getReturnValue();
                    listEmployeeReturnValue.addAll(employeeItems);

                    ArrayAdapter<com.example.android.starbridges.model.getemployeefamily.ReturnValue> adapter = new ArrayAdapter<com.example.android.starbridges.model.getemployeefamily.ReturnValue>(MedicalClaimDetailActivity.this,
                            android.R.layout.simple_spinner_item, listEmployeeReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerEmployeeFamily.setAdapter(adapter);

                    // set text while edit
                    //setupSpinner();
                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetEmployeeFamily> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    // load spinner Medical Policy Type
    public void initSpinnerMedicalPolicy(){
        listPolicyReturnValue = new ArrayList<>();
        com.example.android.starbridges.model.getmedicalpolicy.ReturnValue returnValue = new com.example.android.starbridges.model.getmedicalpolicy.ReturnValue();
        returnValue.setID("");
        returnValue.setPolicyName("");
        returnValue.setPolicyTypeID("");
        returnValue.setPolicyType("");
        listPolicyReturnValue.add(returnValue);

        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);

        Call<GetMedicalPolicy> call3 = apiInterface.getMedicalPolicy(medicalSupportID);
        call3.enqueue(new Callback<GetMedicalPolicy>() {

            @Override
            public void onResponse(Call<GetMedicalPolicy> call, Response<GetMedicalPolicy> response) {
                //progressDialog.dismiss();
                GetMedicalPolicy data = response.body();

                if (data != null && data.getIsSucceed()) {
                    PolicyItems = response.body().getReturnValue();
                    listPolicyReturnValue.addAll(PolicyItems);

                    ArrayAdapter<com.example.android.starbridges.model.getmedicalpolicy.ReturnValue> adapter = new ArrayAdapter<com.example.android.starbridges.model.getmedicalpolicy.ReturnValue>(MedicalClaimDetailActivity.this,
                            android.R.layout.simple_spinner_item, listPolicyReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerMedicalPolicy.setAdapter(adapter);

                    // set text while edit
                    //setupSpinner();
                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetMedicalPolicy> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
}
