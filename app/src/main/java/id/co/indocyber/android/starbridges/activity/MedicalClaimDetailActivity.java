package id.co.indocyber.android.starbridges.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import id.co.indocyber.android.starbridges.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import id.co.indocyber.android.starbridges.model.editmedical.EditMedical;
import id.co.indocyber.android.starbridges.model.getclaimpolicy.GetClaimPolicy;
import id.co.indocyber.android.starbridges.model.getemployeefamily.GetEmployeeFamily;
import id.co.indocyber.android.starbridges.model.getmedicalpolicy.GetMedicalPolicy;
import id.co.indocyber.android.starbridges.model.getmedicalpolicy.ReturnValue;
import id.co.indocyber.android.starbridges.model.getmedicalsupport.GetMedicalSupport;
import id.co.indocyber.android.starbridges.model.medicalrequestconfirmation.MedicalRequestConfirmation;
import id.co.indocyber.android.starbridges.model.medicalsavedetail.MedicalSaveDetail;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalClaimDetailActivity extends AppCompatActivity {
    private static final int MY_GALLERY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE = 999;

    private Spinner spinnerMedicalPolicy, spinnerEmployeeFamily, spinnerClaimPolicy;
    private TextView medicalGrade;
    private EditText claimEditText;
    private Button uploadButton, saveBtn, submitBtn, cancelBtn;
    private ImageView imageView;

    // Declare var instance
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog;

    // declare session
    SessionManagement session;

    private String transactionStatus = "";
    private String employeeID = "";
    private String ID = "";
    private String medicalSupportID = "";
    private String medicalSupportName = "";
    private String medicalPolicyID = "";
    private String medicalPolicyTypeID = "";
    private String medicalPolicyName = "";
    private String remainingBalance = "";
    private String employeeFamilyID = "";
    private String employeeFamilyName = "";
    private String medicalClaimPolicyID = "";
    private String totalClaim = "";
    private String totalReimbursement = "";
    private String attachmentFile = "";
    private String attachmentID = "";
    private String receiptDate = "";
    private String decisionNumber = "";
    private String transactionStatusID = "";
    private String approvedDate = "";
    private String claim = "";
    private String transactionStatusSaveOrSubmit = "";
    private String fullAccess = "";
    private List<String> exclusionFields = new ArrayList<String>();
    private String accessibilityAttribute = "";

    // kebutuhan edit
    private String medicalClaimPolicyIDEdit = "";
    private String employeeFamilyIDEdit = "";

    Intent intent;

    String id;

    private String claimPolicyID, claimPolicyName;

    // get data from spinner Policy Name
    List<ReturnValue> PolicyItems;
    List<ReturnValue> listPolicyReturnValue;

    // get data from spinner Employee Family
    List<id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue> employeeItems;
    List<id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue> listEmployeeReturnValue;

    // get data from spinner Claim Policy
    List<id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue> claimPolicyItems;
    List<id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue> listClaimPolicyReturnValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_claim_detail);
        setTitle("Medical Claim Detail");

        // get reference to component
        medicalGrade = (TextView) findViewById(R.id.medicalGradeView);
        spinnerMedicalPolicy = (Spinner) findViewById(R.id.policyTypeSpinner);
        spinnerEmployeeFamily = (Spinner) findViewById(R.id.familyTypeSpinner);
        spinnerClaimPolicy = (Spinner) findViewById(R.id.claimPolicyTypeSpinner);
        uploadButton = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imageView);
        saveBtn = (Button) findViewById(R.id.btnSave);
        submitBtn = (Button) findViewById(R.id.btnSubmit);
        cancelBtn = (Button) findViewById(R.id.btnCancel);

        claimEditText = (EditText) findViewById(R.id.claimEditText);

        // get session
        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String employeeID = user.get(SessionManagement.KEY_EMPLOYEE_ID);
        GlobalVar.setEmployeeId(employeeID);

        // set employee id
        employeeID = GlobalVar.getEmployeeId();

        // set clickable to image
        imageView.setClickable(true);

        progressDialog = new ProgressDialog(MedicalClaimDetailActivity.this);
        progressDialog.setTitle("Loading");
        // load api
        initMedicalSupport();

        // Get intent from adapter
        intent = getIntent();
        if(intent.getStringExtra("ID") != null){
            id = intent.getStringExtra("ID");
            editMedical(id);
        }

        spinnerMedicalPolicy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    final ReturnValue returnValue = (ReturnValue) spinnerMedicalPolicy.getItemAtPosition(i);
                    medicalPolicyID = returnValue.getID();
                    medicalPolicyName = returnValue.getPolicyName();
                    medicalPolicyTypeID = returnValue.getPolicyTypeID();

                    employeeFamilyID = "";
                    employeeFamilyName = "";

                    // reset data spinner medical claim policy
                    medicalClaimPolicyID = "";
                    claimPolicyName = "";

                    if(medicalPolicyTypeID.equals("2")) {
                        initSpinnerEmployeeFamily();
                        spinnerEmployeeFamily.setEnabled(true);

                        // call init spinner claim policy
                        initSpinnerClaimPolicy();
                    }else{
                        spinnerEmployeeFamily.setSelection(0);
                        spinnerEmployeeFamily.setEnabled(false);

                        // call init spinner claim policy
                        initSpinnerClaimPolicy();
                    }

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
                    final id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue returnValue1 = (id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue) spinnerEmployeeFamily.getItemAtPosition(i);
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
                    final id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue returnValue2 = (id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue) spinnerClaimPolicy.getItemAtPosition(i);
                    medicalClaimPolicyID = returnValue2.getValue().toString();
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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set transaction status to Save
                transactionStatus = "Save";
                if(spinnerMedicalPolicy.getSelectedItem().toString().matches(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                    alert.setTitle("Please fill medical policy");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else if(spinnerClaimPolicy.getSelectedItem().toString().matches(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                    alert.setTitle("Please fill claim policy");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else if(spinnerMedicalPolicy.getSelectedItem().toString().matches("Family Rawat Jalan")&&spinnerEmployeeFamily.getSelectedItem().toString().matches(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                    alert.setTitle("Please fill the family member if claim policy is Family Rawat Jalan");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else if(claimEditText.getText().toString().matches(""))
                {
                    claimEditText.setError("Please fill claim");
                }
                else
                    requestConfirmation();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set transaction status to Submit
                transactionStatus = "Submit";

                if(spinnerMedicalPolicy.getSelectedItem().toString().matches(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                    alert.setTitle("Please fill medical policy");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else if(spinnerClaimPolicy.getSelectedItem().toString().matches(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                    alert.setTitle("Please fill claim policy");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else if(spinnerMedicalPolicy.getSelectedItem().toString().matches("Family Rawat Jalan")&&spinnerEmployeeFamily.getSelectedItem().toString().matches(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                    alert.setTitle("Please fill the family member if claim policy is Family Rawat Jalan");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else if(claimEditText.getText().toString().matches(""))
                {
                    claimEditText.setError("Please fill claim");
                }
                else
                    requestConfirmation();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
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

        /*
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                alert.setTitle("Delete this image ?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        imageView.setImageResource(0);
                        attachmentFile = "";
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
        */

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
        progressDialog.setTitle("Initialize Medical Support");
        progressDialog.show();

        Call<GetMedicalSupport> call3 = apiInterface.getMedicalSupport();
        call3.enqueue(new Callback<GetMedicalSupport>() {

            @Override
            public void onResponse(Call<GetMedicalSupport> call, Response<GetMedicalSupport> response) {
                GetMedicalSupport data = response.body();
                if (data != null && data.getIsSucceed()) {
                    medicalSupportID = response.body().getReturnValue().getMedicalSupportID().toString();
                    medicalSupportName = response.body().getReturnValue().getMedicalSupportName();

                    // set text medical grade
                    medicalGrade.setText(medicalSupportName);

                    // init spinner medical policy
                    progressDialog.dismiss();
                    initSpinnerMedicalPolicy();
                    initSpinnerClaimPolicy();
                    progressDialog.dismiss();
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

                initSpinnerClaimPolicy();
            }
        });
    }

    // load spinner Claim Policy
    public void initSpinnerClaimPolicy(){
        progressDialog.setTitle("Initialize Claim Policy");
        progressDialog.show();
        listClaimPolicyReturnValue = new ArrayList<>();
        id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue returnValue2 = new id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue();
        returnValue2.setText("");
        returnValue2.setValue(0);
        listClaimPolicyReturnValue.add(returnValue2);

        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);

        Call<GetClaimPolicy> call3 = apiInterface.getClaimPolicy(medicalPolicyID);
        call3.enqueue(new Callback<GetClaimPolicy>() {

            @Override
            public void onResponse(Call<GetClaimPolicy> call, Response<GetClaimPolicy> response) {

                GetClaimPolicy data = response.body();

                if (data != null && data.getIsSucceed()) {
                    claimPolicyItems = response.body().getReturnValue();
                    listClaimPolicyReturnValue.addAll(claimPolicyItems);

                    ArrayAdapter<id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue> adapter = new ArrayAdapter<id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue>(MedicalClaimDetailActivity.this,
                            android.R.layout.simple_spinner_item, listClaimPolicyReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerClaimPolicy.setAdapter(adapter);

                    // set text while edit
                    setupSpinnerMedicalClaim();
                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    //finish();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<GetClaimPolicy> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();

                setupSpinnerMedicalClaim();
            }
        });
    }

    // load spinner Medical Employee Family
    public void initSpinnerEmployeeFamily(){
        progressDialog.setTitle("Initialize Family");
        progressDialog.show();
        listEmployeeReturnValue = new ArrayList<>();
        id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue returnValue1 = new id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue();
        returnValue1.setID("");
        returnValue1.setName("");
        listEmployeeReturnValue.add(returnValue1);

        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);

        Call<GetEmployeeFamily> call3 = apiInterface.getEmployeeFamily(medicalSupportID);
        call3.enqueue(new Callback<GetEmployeeFamily>() {

            @Override
            public void onResponse(Call<GetEmployeeFamily> call, Response<GetEmployeeFamily> response) {
                GetEmployeeFamily data = response.body();

                if (data != null && data.getIsSucceed()) {
                    employeeItems = response.body().getReturnValue();
                    listEmployeeReturnValue.addAll(employeeItems);

                    ArrayAdapter<id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue> adapter1 = new ArrayAdapter<id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue>(MedicalClaimDetailActivity.this,
                            android.R.layout.simple_spinner_item, listEmployeeReturnValue);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerEmployeeFamily.setAdapter(adapter1);

                    setupSpinnerMedicalFamily();

                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    //finish();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetEmployeeFamily> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();

                setupSpinnerMedicalFamily();
            }
        });
    }

    // load spinner Medical Policy Type
    public void initSpinnerMedicalPolicy(){
        progressDialog.setTitle("Initialize Policy");
        progressDialog.show();
        listPolicyReturnValue = new ArrayList<>();
        ReturnValue returnValue = new ReturnValue();
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

                    ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(MedicalClaimDetailActivity.this,
                            android.R.layout.simple_spinner_item, listPolicyReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerMedicalPolicy.setAdapter(adapter);

                    // set text while edit
                    setupSpinnerMedicalPolicy();

                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    //finish();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetMedicalPolicy> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();

                setupSpinnerMedicalPolicy();
            }
        });
    }

    // setting untuk "Policy Spinner"
    public void setupSpinnerMedicalPolicy()
    {
        int spinnerIdSelected=0;
        String contoh = medicalPolicyID;
        if(medicalPolicyID != "")
        {
            for(ReturnValue x: listPolicyReturnValue)
            {
                if(x.getID().equals(medicalPolicyID))
                    break;
                spinnerIdSelected++;
            }
        }

        //spinnerRequestType.setSelection(spinnerIdSelected);
        spinnerMedicalPolicy.setSelection(spinnerIdSelected);
    }

    // setting untuk "Family Spinner"
    public void setupSpinnerMedicalFamily()
    {
        int spinnerIdSelected=0;
        String contoh2 = employeeFamilyIDEdit;
        if(employeeFamilyIDEdit != "")
        {
            for(id.co.indocyber.android.starbridges.model.getemployeefamily.ReturnValue x: listEmployeeReturnValue)
            {
                if(x.getID().toString().equals(employeeFamilyIDEdit))
                    break;
                spinnerIdSelected++;
            }
        }

        //spinnerRequestType.setSelection(spinnerIdSelected);
        spinnerEmployeeFamily.setSelection(spinnerIdSelected);
    }

    // setting untuk "Claim Spinner"
    public void setupSpinnerMedicalClaim()
    {
        int spinnerIdSelected = 0;
        String contoh3 = medicalClaimPolicyIDEdit;
        if(medicalClaimPolicyIDEdit != "")
        {
            for(id.co.indocyber.android.starbridges.model.getclaimpolicy.ReturnValue x: listClaimPolicyReturnValue)
            {
                String val1 = x.getText();
                String val2 = x.getValue().toString();

                if(val2.equals(medicalClaimPolicyIDEdit))
                    break;
                spinnerIdSelected++;
            }
        }

        if(spinnerIdSelected >= listClaimPolicyReturnValue.size())
            spinnerIdSelected=0;

        spinnerClaimPolicy.setSelection(spinnerIdSelected);
    }



    public void requestConfirmation(){
        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(MedicalClaimDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        // get Data
        claim = claimEditText.getText().toString();
        totalClaim = claimEditText.getText().toString();

        JSONObject paramObject= new JSONObject();
        try {

            paramObject.put("EmployeeID", GlobalVar.getEmployeeId());
            paramObject.put("ID",id);
            paramObject.put("MedicalSupportID", medicalSupportID);
            paramObject.put("MedicalSupportName",medicalSupportName);
            paramObject.put("MedicalPolicyID",medicalPolicyID);
            paramObject.put("PolicyTypeID",medicalPolicyTypeID);
            paramObject.put("MedicalPolicyName",medicalPolicyName);
            paramObject.put("RemainingBalance", remainingBalance);
            paramObject.put("EmployeeFamilyID",employeeFamilyID);
            paramObject.put("EmployeeFamilyName",employeeFamilyName);
            paramObject.put("MedicalClaimPolicyID",medicalClaimPolicyID);
            paramObject.put("TotalClaim", totalClaim);
            paramObject.put("TotalReimbursement", totalReimbursement);
            paramObject.put("AttachmentFile", attachmentFile);
            paramObject.put("AttachmentID", attachmentID);
            paramObject.put("ReceiptDate", receiptDate);
            paramObject.put("DecisionNumber", decisionNumber);
            paramObject.put("TransactionStatusID", transactionStatusID);
            paramObject.put("ApprovedDate", approvedDate);
            paramObject.put("Claim", claim);
            paramObject.put("TransactionStatusSaveOrSubmit", transactionStatusSaveOrSubmit);
            paramObject.put("FullAccess", fullAccess);
            paramObject.put("ExclusionFields", exclusionFields);
            paramObject.put("AccessibilityAttribute",accessibilityAttribute);

        }catch (Exception e)
        {

        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),paramObject.toString());

        Call<MedicalRequestConfirmation> call3 = apiInterface.medicalRequestConfirmation2(
                transactionStatus, body);
        call3.enqueue(new Callback<MedicalRequestConfirmation>() {

            @Override
            public void onResponse(Call<MedicalRequestConfirmation> call, Response<MedicalRequestConfirmation> response) {
                progressDialog.dismiss();
                MedicalRequestConfirmation data = response.body();

                if (data != null && data.getIsSucceed()) {

                    employeeID = data.getReturnValue().getEmployeeID();
                    ID = (data.getReturnValue().getID() == null) ? "" : data.getReturnValue().getID();
                    medicalSupportID = (data.getReturnValue().getMedicalSupportID() == 0) ? "" : data.getReturnValue().getMedicalSupportID()+"";
                    medicalSupportName = data.getReturnValue().getMedicalSupportName();
                    medicalPolicyID = data.getReturnValue().getMedicalPolicyID()+"";
                    medicalPolicyTypeID = data.getReturnValue().getPolicyTypeID();
                    medicalPolicyName = data.getReturnValue().getMedicalPolicyName();
                    remainingBalance = (data.getReturnValue().getRemainingBalance() == 0) ? "" : data.getReturnValue().getRemainingBalance()+"";
                    employeeFamilyID = (data.getReturnValue().getEmployeeFamilyID() == null) ? "" : data.getReturnValue().getEmployeeFamilyID().toString();
                    employeeFamilyName = data.getReturnValue().getEmployeeFamilyName();
                    medicalClaimPolicyID = (data.getReturnValue().getMedicalClaimPolicyID() == 0) ? "" : data.getReturnValue().getMedicalClaimPolicyID()+"";
                    totalClaim = (data.getReturnValue().getTotalClaim() == 0) ? "" : data.getReturnValue().getTotalClaim()+"";
                    totalReimbursement = (data.getReturnValue().getTotalReimbursement() == 0) ? "" : data.getReturnValue().getTotalReimbursement()+"";
//                    attachmentFile = data.getReturnValue().getAttachmentFile().toString();
                    attachmentID = (data.getReturnValue().getAttachmentID() == null) ? null : data.getReturnValue().getAttachmentID().toString();
                    receiptDate = data.getReturnValue().getReceiptDate();
//                    decisionNumber = data.getReturnValue().getDecisionNumber().toString();
                    transactionStatusID = (data.getReturnValue().getTransactionStatusID() == 0) ? "" : data.getReturnValue().getTransactionStatusID()+"";
                    approvedDate = (data.getReturnValue().getApprovedDate() == null) ? "" : data.getReturnValue().getApprovedDate().toString();
                    claim = (data.getReturnValue().getClaim() == 0) ? "" : data.getReturnValue().getClaim()+"";
                    transactionStatusSaveOrSubmit = data.getReturnValue().getTransactionStatusSaveOrSubmit();
                    fullAccess = data.getReturnValue().isFullAccess()+"";
                    exclusionFields  = data.getReturnValue().getExclusionFields();
                    accessibilityAttribute = data.getReturnValue().getAccessibilityAttribute();

                    AlertDialog.Builder alert = new AlertDialog.Builder(MedicalClaimDetailActivity.this);
                    alert.setTitle("Request Confirmation");
                    alert.setMessage(
                            "Policy : \n" + medicalSupportName +
                                    "\nFamily : \n" + employeeFamilyName +
                                    "\nRemaining Balance : \n" + remainingBalance +
                                    "\nClaim : \n" + totalClaim +
                                    "\nReimbursement : \n" + totalReimbursement
                    );
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            saveMedical();
                        }
                    });

                    alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // if this button is clicked, just close the dialog box
                            dialogInterface.cancel();
                        }
                    });

                    alert.show();


                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<MedicalRequestConfirmation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void editMedical(String id){
        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        //progressDialog.show();

        Call<EditMedical> call3 = apiInterface.editMedical(id);
        call3.enqueue(new Callback<EditMedical>() {

            @Override
            public void onResponse(Call<EditMedical> call, Response<EditMedical> response) {

                EditMedical data = response.body();

                if (data != null && data.getIsSucceed()) {

                    employeeID = data.getReturnValue().getEmployeeID();
                    ID = (data.getReturnValue().getID() == null) ? "" : data.getReturnValue().getID();
                    medicalSupportID = (data.getReturnValue().getMedicalSupportID() == null) ? "" : data.getReturnValue().getMedicalSupportID().toString();
                    medicalSupportName = data.getReturnValue().getMedicalSupportName();
                    medicalPolicyID = data.getReturnValue().getMedicalPolicyID().toString();
                    medicalPolicyTypeID = data.getReturnValue().getPolicyTypeID();
                    medicalPolicyName = data.getReturnValue().getMedicalPolicyName();
                    remainingBalance = (data.getReturnValue().getRemainingBalance() == null) ? "" : data.getReturnValue().getRemainingBalance().toString();
                    employeeFamilyID = (data.getReturnValue().getEmployeeFamilyID() == null) ? "" : data.getReturnValue().getEmployeeFamilyID().toString();
                    employeeFamilyName = data.getReturnValue().getEmployeeFamilyName();
                    medicalClaimPolicyID = (data.getReturnValue().getMedicalClaimPolicyID() == null) ? "" : data.getReturnValue().getMedicalClaimPolicyID().toString();
                    totalClaim = (data.getReturnValue().getTotalClaim() == null) ? "" : data.getReturnValue().getTotalClaim().toString();
                    totalReimbursement = (data.getReturnValue().getTotalReimbursement() == null) ? "" : data.getReturnValue().getTotalReimbursement().toString();
                    attachmentFile = data.getReturnValue().getAttachmentFile();
                    attachmentID = (data.getReturnValue().getAttachmentID() == null) ? null : data.getReturnValue().getAttachmentID().toString();
                    receiptDate = data.getReturnValue().getReceiptDate();
                    decisionNumber = data.getReturnValue().getDecisionNumber();
                    transactionStatusID = (data.getReturnValue().getTransactionStatusID() == null) ? "" : data.getReturnValue().getTransactionStatusID().toString();
                    approvedDate = (data.getReturnValue().getApprovedDate() == null) ? "" : data.getReturnValue().getApprovedDate().toString();
                    claim = (data.getReturnValue().getClaim() == null) ? "" : data.getReturnValue().getClaim().toString();
                    transactionStatusSaveOrSubmit = data.getReturnValue().getTransactionStatusSaveOrSubmit();
                    fullAccess = (data.getReturnValue().getFullAccess() == null) ? "" : data.getReturnValue().getFullAccess().toString();
                    exclusionFields  = data.getReturnValue().getExclusionFields();
                    accessibilityAttribute = data.getReturnValue().getAccessibilityAttribute();

                    // u/ kebutuhan edit
                    medicalClaimPolicyIDEdit = (data.getReturnValue().getMedicalClaimPolicyID() == null) ? "" : data.getReturnValue().getMedicalClaimPolicyID().toString();
                    employeeFamilyIDEdit = (data.getReturnValue().getEmployeeFamilyID() == null) ? "" : data.getReturnValue().getEmployeeFamilyID().toString();


                    // set text claim
                    claimEditText.setText(totalClaim);

                    // load Image if exist
                    if(attachmentFile != null) {
                        byte[] decodedString = Base64.decode(attachmentFile, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageView.setImageBitmap(bitmap);
                    }

                    // init spinner medical policy
                    initSpinnerMedicalPolicy();

                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    //finish();

                    initSpinnerMedicalPolicy();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<EditMedical> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MedicalClaimDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public void saveMedical(){
        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(MedicalClaimDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        // get Data
        claim = claimEditText.getText().toString();

        Call<MedicalSaveDetail> call3 = apiInterface.medicalSaveDetail(
                employeeID,ID, medicalSupportID, medicalSupportName, medicalPolicyID,
                medicalPolicyTypeID, medicalPolicyName, remainingBalance, employeeFamilyID, employeeFamilyName,
                medicalClaimPolicyID, totalClaim, totalReimbursement, attachmentFile, null,
                receiptDate, decisionNumber, transactionStatusID, approvedDate, claim,
                transactionStatusSaveOrSubmit, fullAccess, exclusionFields, accessibilityAttribute);
        call3.enqueue(new Callback<MedicalSaveDetail>() {

            @Override
            public void onResponse(Call<MedicalSaveDetail> call, Response<MedicalSaveDetail> response) {
                progressDialog.dismiss();
                MedicalSaveDetail data = response.body();

                if (data != null && data.getIsSucceed()) {

                    Toast.makeText(MedicalClaimDetailActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    intent1.putExtra("data","Saving berhasil");
                    setResult(1100,intent1);
                    finish();

                } else {
                    Toast.makeText(MedicalClaimDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    //finish();
                }
            }

            @Override
            public void onFailure(Call<MedicalSaveDetail> call, Throwable t) {
                progressDialog.dismiss();
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
