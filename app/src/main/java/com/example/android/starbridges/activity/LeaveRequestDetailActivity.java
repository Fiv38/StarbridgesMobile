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
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.balanceType.BalanceType;
import com.example.android.starbridges.model.editleaverequest.EditLeaveRequest;
import com.example.android.starbridges.model.requestconfirmation.RequestConfirmation;
import com.example.android.starbridges.model.requesttype.RequestType;
import com.example.android.starbridges.model.requesttype.ReturnValue;
import com.example.android.starbridges.model.saveLeaveRequest.SaveLeaveRequest;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;
import com.example.android.starbridges.utility.SessionManagement;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveRequestDetailActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int MY_GALLERY_REQUEST_CODE = 100;
    private static final int PICK_IMAGE = 999;

    // declara var instance
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog, progressDialog2;
    private EditText startDate, endDate, notes, timeDateEnd, timeDateStart;
    private Spinner spinnerRequestType, spinnerBalanceType;
    private ImageView imageView, imgStartDate, imgEndDate, imgStartTime, imgEndTime;

    Calendar myCalendar = Calendar.getInstance();
    Button saveBtn, submitBtn, uploadBtn, cancelBtn;
    String saveStr, submitStr;

    Intent intent;

    // declare parameter (balikan dari api)
    private String transactionStatus = "";

    private String id = "";
    private Integer employeeID = 0;
    private String roster = "";
    private String requestDate = "";
    private String employeeNIK = "";
    private String employeeName = "";
    private Integer leaveRequestRuleID = 0;
    private String leaveRequestType = "";
    private String employeeLeaveBalanceUID = "";
    private String currentBalance = "";
    private String balanceExpireDate = "";
    private String totalUnit = "";
    private String totalUnitReduce = "";
    private String startLeave = "";
    private String endLeave = "";
    private String leaveAt = "";
    private String returnAt = "";
    private Boolean minIntervalViolation = true;
    private Boolean unitLimitViolation = true;
    private Boolean occurenceViolation = true;
    private String notesStr = "";
    private String attachmentFile = "";
    private String attachmentID = "";
    private String decisionNumber = "";
    private String transactionStatusID = "";
    private String approveDate = "";
    private Boolean isHalfDay = true;
    private String submitType = "";
    private String message = "";
    private String transactionStatusSaveOrSubmit = "";
    private String photo = "";
    private Boolean fullAccess = true;
    private List<String> exclusionFields = new ArrayList<String>();
    private String accessibilityAttribute = "";

    int idRequestType = 0;
    String balanceUID = "";

    // get data from request confirmation
    com.example.android.starbridges.model.requestconfirmation.ReturnValue requestConfirmation;

    // get data from edit leave request
    com.example.android.starbridges.model.editleaverequest.ReturnValue editLeaveRequest;

    // get data from spinner balance type
    List<com.example.android.starbridges.model.balanceType.ReturnValue> LocItems1;
    List<com.example.android.starbridges.model.balanceType.ReturnValue> listReturnValue1;

    // get data from spinner request type
    List<ReturnValue> LocItems;
    List<ReturnValue> listReturnValue;

    // declare session
    SessionManagement session;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabel();
            if (leaveRequestType.isEmpty()) {
                updateLabel2(myCalendar);
            } else {
                defaultLabelEndDate(leaveRequestRuleID);
            }


        }
    };

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String cd = sdf.format(myCalendar.getTime());
//            Date tmp11 = null;
//            try {
//                tmp11 = sdf.parse(cd);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
//
////            Calendar tmp2 = myCalendar;
//            String cd2 = sdf.format(myCalendar.getTime());
//            Date tmp22 = null;
//            try {
//                tmp22 = sdf.parse(cd2);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            if (tmp11.after(tmp22)) {
//                alertNotif("", "end date tidak boleh lebih kecil dari start date ");
//                endDate.setText(startDate.getText());
//                endLeave = sdf.format(Calendar.getInstance().getTime());
//            } else {
            updateLabel2(myCalendar);
//            }
            hitungJumlahHari();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request_detail);
        setTitle("Leave Request");

        // get reference to component
        startDate = (EditText) findViewById(R.id.dateStart);
        endDate = (EditText) findViewById(R.id.dateEnd);
        notes = (EditText) findViewById(R.id.notes);

        timeDateEnd = (EditText) findViewById(R.id.timeDateEnd);
        timeDateStart = (EditText) findViewById(R.id.timeDateStart);

        spinnerRequestType = (Spinner) findViewById(R.id.requestTypeSpinner);
        spinnerBalanceType = (Spinner) findViewById(R.id.balanceTypeSpinner);
        saveBtn = (Button) findViewById(R.id.btnSave);
        submitBtn = (Button) findViewById(R.id.btnSubmit);
        uploadBtn = (Button) findViewById(R.id.btnUpload);
        cancelBtn = (Button) findViewById(R.id.btnCancel);
        imageView = (ImageView) findViewById(R.id.imageView);

        imgStartDate = (ImageView) findViewById(R.id.imgCalendarStartDate);
        imgEndDate = (ImageView) findViewById(R.id.imgCalendarEndDate);
        imgStartTime = (ImageView) findViewById(R.id.imgStartTime);
        imgEndTime = (ImageView) findViewById(R.id.imgEndTime);

        progressDialog2 = new ProgressDialog(LeaveRequestDetailActivity.this);
        progressDialog2.setTitle("Load Data");
        progressDialog2.show();

        // get session
        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String employeeID = user.get(SessionManagement.KEY_EMPLOYEE_ID);
        GlobalVar.setEmployeeId(employeeID);

        imgStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LeaveRequestDetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        imgStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                try {

                    mTimePicker = new TimePickerDialog(LeaveRequestDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            leaveAt = String.format("%2s", selectedHour).replace(' ', '0') + ":" + String.format("%2s", selectedMinute).replace(' ', '0');

//                            startDate.setText(startLeave + " - " + leaveAt);
                            startDate.setText(startLeave);
                            timeDateStart.setText(leaveAt);
//                            Toast.makeText(LeaveRequestDetailActivity.this, "pertama1 :" + leaveAt, Toast.LENGTH_LONG).show();
                        }
//                    }, Integer.parseInt(startLeave.substring(0, 2)), Integer.parseInt(startLeave.substring(3, 5)), true);
                    }, hour, minute, true);
                } catch (Exception e) {
                    mTimePicker = new TimePickerDialog(LeaveRequestDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            leaveAt = String.format("%2s", selectedHour).replace(' ', '0') + ":" + String.format("%2s", selectedMinute).replace(' ', '0');

//                            startDate.setText(endLeave + " - " + leaveAt);
                            startDate.setText(endLeave);
                            timeDateStart.setText(leaveAt);
//                            Toast.makeText(LeaveRequestDetailActivity.this, "pertama2 : " + leaveAt, Toast.LENGTH_LONG).show();
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");

                mTimePicker.show();
            }
        });

        imgEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LeaveRequestDetailActivity.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        imgEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                try {
                    mTimePicker = new TimePickerDialog(LeaveRequestDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            returnAt = String.format("%2s", selectedHour).replace(' ', '0') + ":" + String.format("%2s", selectedMinute).replace(' ', '0');
//                            }else{
//                            endDate.setText(endLeave + " - " + returnAt);
                            endDate.setText(endLeave);
                            timeDateEnd.setText(returnAt);
//                            Toast.makeText(LeaveRequestDetailActivity.this, "kedua1 :" + returnAt, Toast.LENGTH_LONG).show();
//

                        }
//                    }, Integer.parseInt(startLeave.substring(0, 2)), Integer.parseInt(startLeave.substring(3, 5)), true);
                    }, hour, minute, true);
                } catch (Exception e) {
                    mTimePicker = new TimePickerDialog(LeaveRequestDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            returnAt = String.format("%2s", selectedHour).replace(' ', '0') + ":" + String.format("%2s", selectedMinute).replace(' ', '0');

//                            endDate.setText(endLeave + " - " + returnAt);
                            endDate.setText(endLeave);
                            timeDateEnd.setText(returnAt);
//                            Toast.makeText(LeaveRequestDetailActivity.this, "kedua2 : " + returnAt, Toast.LENGTH_LONG).show();
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");

                mTimePicker.show();
            }
        });

        // set text default date to
        updateLabel();
        updateLabel2(myCalendar);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeLeaveBalanceUID.isEmpty() || leaveRequestType.isEmpty()) {
                    alertNotif("Request Confirmation", "Request Type dan Take from Balance tidak di perkenankan di kosongkan");
                } else {
                    if (validasiDate() == true) {
                        alertNotif("Request Confirmation", "Kolom End Leave harus setelah Start Leave");
                    } else {
                        if (leaveRequestRuleID == 19 && leaveAt.isEmpty() ||
                                leaveRequestRuleID == 19 && returnAt.isEmpty()) {
                            alertNotif("Request Confirmation", "untuk Ijin Pulang kolom leave at dan return at tidak di perkenankan dikosongkan");

                        } else if (leaveRequestRuleID == 20 && leaveAt.isEmpty() ||
                                leaveRequestRuleID == 20 && returnAt.isEmpty()) {
                            alertNotif("Request Confirmation", "untuk ijin terlambat kolom leave at dan return at tidak di perkenankan dikosongkan");

                        } else if (leaveRequestRuleID == 21 && leaveAt.isEmpty() ||
                                leaveRequestRuleID == 21 && returnAt.isEmpty()) {
                            alertNotif("Request Confirmation", "untuk ijin keluar dan kembali terlambat kolom leave at dan return at tidak di perkenankan dikosongkan");

                        } else {
                            if (leaveRequestRuleID == 19 ||
                                    leaveRequestRuleID == 20 ||
                                    leaveRequestRuleID == 21
                                    ) {
                                if (validasiTime() == true) {
                                    alertNotif("Request Confirmation", "Kolom return at harus setelah leave at");
                                } else {
                                    transactionStatus = "Save";
                                    // call method
                                    requestConfirmation();
                                }

                            } else {
                                // set val "Save" to transaction Status
                                transactionStatus = "Save";
                                // call method
                                requestConfirmation();
                            }
//                    // set val "Save" to transaction Status
//                    transactionStatus = "Save";
//                    // call method
//                    requestConfirmation();
                        }
                    }
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeLeaveBalanceUID.isEmpty() || leaveRequestType.isEmpty()) {
                    alertNotif("Request Confirmation", "Request Type dan Take from Balance tidak di perkenankan di kosongkan");

                } else {
                    if (validasiDate() == true) {
                        alertNotif("Request Confirmation", "Kolom End Leave harus setelah Start Leave");
                    } else {
                        if (leaveRequestType.equalsIgnoreCase("ijin pulang ") && leaveAt.isEmpty() ||
                                leaveRequestType.equalsIgnoreCase("ijin pulang ") && returnAt.isEmpty()) {
                            alertNotif("Request Confirmation", "untuk Ijin Pulang kolom leave at dan return at tidak di perkenankan dikosongkan");

                        } else if (leaveRequestType.equalsIgnoreCase("ijin terlambat") && leaveAt.isEmpty() ||
                                leaveRequestType.equalsIgnoreCase("ijin terlambat") && returnAt.isEmpty()) {
                            alertNotif("Request Confirmation", "untuk ijin terlambat kolom leave at dan return at tidak di perkenankan dikosongkan");

                        } else if (leaveRequestType.equalsIgnoreCase("ijin keluar dan kembali") && leaveAt.isEmpty() ||
                                leaveRequestType.equalsIgnoreCase("ijin keluar dan kembali") && returnAt.isEmpty()) {
                            alertNotif("Request Confirmation", "untuk ijin keluar dan kembali terlambat kolom leave at dan return at tidak di perkenankan dikosongkan");

                        } else {
                            if (leaveRequestType.equalsIgnoreCase("ijin pulang ") ||
                                    leaveRequestType.equalsIgnoreCase("ijin terlambat") ||
                                    leaveRequestType.equalsIgnoreCase("ijin keluar dan kembali")
                                    ) {
                                if (validasiTime() == true) {
                                    alertNotif("Request Confirmation", "Kolom return at harus setelah leave at");
                                } else {
                                    transactionStatus = "Save";
                                    // call method
                                    requestConfirmation();
                                }
                            } else {
                                AlertDialog.Builder alert = new AlertDialog.Builder(LeaveRequestDetailActivity.this);
                                alert.setTitle("Request Confirmation");
                                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // set val "Submit" to variable
                                        transactionStatus = "Submit";

                                        //call method
                                        requestConfirmation();
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
                    }
                }


            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(LeaveRequestDetailActivity.this);
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

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                //dispatchTakePictureIntent();
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LeaveRequestDetailActivity.this, new String[]{Manifest.permission.CAMERA}, MY_GALLERY_REQUEST_CODE);
        }

        // call load spinner
        initSpinnerRequestType();

        spinnerRequestType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final ReturnValue returnValue = (ReturnValue) spinnerRequestType.getItemAtPosition(i);

                leaveRequestRuleID = returnValue.getID();
                leaveRequestType = returnValue.getName();
                defaultLabelEndDate(leaveRequestRuleID);
                if (leaveRequestRuleID == 3 ||
                        leaveRequestRuleID == 19 ||
                        leaveRequestRuleID == 20 ||
                        leaveRequestRuleID == 21
                        ) {
                    imgEndDate.setEnabled(false);
                } else {
                    imgEndDate.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // call load spinner balance type
        initSpinnerBalanceType();

        spinnerBalanceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final com.example.android.starbridges.model.balanceType.ReturnValue returnValue1 = (com.example.android.starbridges.model.balanceType.ReturnValue) spinnerBalanceType.getItemAtPosition(i);
                employeeLeaveBalanceUID = returnValue1.getValue().toString();
                //Toast.makeText(LeaveRequestDetailActivity.this, "UID " + balanceUID, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Get intent from adapter
        intent = getIntent();
        if (intent.getStringExtra("ID") != null) {
            id = intent.getStringExtra("ID");
            editLeaveRequest(id);
        }
    }

    public void requestConfirmation() {
        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(LeaveRequestDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        employeeID = Integer.parseInt(GlobalVar.getEmployeeId());
        employeeNIK = GlobalVar.getNIK();
        employeeName = GlobalVar.getFullname();

        // get message
        message = notes.getText().toString();
        notesStr = notes.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(startLeave + " T " + endLeave);
        System.out.println(leaveAt + " T " + returnAt);
        String returN = null, leave = null;
        try {
            Date a = sdf.parse(startLeave);
            Date b = sdf.parse(endLeave);
            String timeA = leaveAt;
            String timeB = returnAt;
            String dateA = sdf2.format(a);
            String dateB = sdf2.format(b);
            if (leaveAt.isEmpty()) {
                leave = dateA;

            } else {
                leave = dateA + "T" + timeA;
            }

            if (returnAt.isEmpty()) {
                returN = dateB;

            } else {

                returN = dateB + "T" + timeB;
            }
            startLeave = leave;
            endLeave = returN;
            if (leaveRequestType.equalsIgnoreCase("ijin pulang ") ||
                    leaveRequestType.equalsIgnoreCase("ijin terlambat") ||
                    leaveRequestType.equalsIgnoreCase("ijin keluar dan kembali")) {
                leaveAt = leave;
                returnAt = returN;
            } else {
                leaveAt = null;
                returnAt = null;
            }

//            leaveAt = sdf2.format(a)+"T"+leaveAt+":00";
//            returnAt = sdf2.format(b)+"T"+returnAt+":00";
            System.out.println("a");
        } catch (Exception e) {
        }

        Call<RequestConfirmation> call3 = apiInterface.requestConfirmation(
                transactionStatus, id, employeeID, roster, requestDate, employeeNIK, employeeName, leaveRequestRuleID, leaveRequestType,
                employeeLeaveBalanceUID, currentBalance, balanceExpireDate, totalUnit, totalUnitReduce, startLeave,
                endLeave, leaveAt, returnAt, minIntervalViolation, unitLimitViolation, occurenceViolation, notesStr,
                attachmentFile, attachmentID, decisionNumber, transactionStatusID, approveDate, isHalfDay, submitType,
                message, transactionStatusSaveOrSubmit, photo, fullAccess, exclusionFields, accessibilityAttribute);
        call3.enqueue(new Callback<RequestConfirmation>() {

            @Override
            public void onResponse(Call<RequestConfirmation> call, Response<RequestConfirmation> response) {
                progressDialog.dismiss();
                RequestConfirmation data = response.body();

                if (data != null && data.getIsSucceed()) {
                    requestConfirmation = data.getReturnValue();

                    id = requestConfirmation.getID();
                    employeeID = Integer.parseInt(requestConfirmation.getEmployeeID());
                    roster = requestConfirmation.getRoster();
                    requestDate = requestConfirmation.getRequestDate();
                    employeeNIK = requestConfirmation.getEmployeeNIK();
                    employeeName = requestConfirmation.getEmployeeName();
                    leaveRequestRuleID = requestConfirmation.getLeaveRequestRuleID();
                    leaveRequestType = requestConfirmation.getLeaveRequestType();
                    employeeLeaveBalanceUID = requestConfirmation.getEmployeeLeaveBalanceUID();
                    currentBalance = requestConfirmation.getCurrentBalance().toString();
                    balanceExpireDate = requestConfirmation.getBalanceExpireDate();
                    totalUnit = requestConfirmation.getTotalUnit().toString();
                    totalUnitReduce = requestConfirmation.getTotalUnit().toString();
                    startLeave = requestConfirmation.getStartLeave().toString();
                    endLeave = requestConfirmation.getEndLeave().toString();
                    leaveAt = requestConfirmation.getLeaveAt();
                    returnAt = requestConfirmation.getReturnAt();
                    minIntervalViolation = requestConfirmation.getMinIntervalViolation();
                    unitLimitViolation = requestConfirmation.getUnitLimitViolation();
                    occurenceViolation = requestConfirmation.getOccurenceViolation();
                    notesStr = requestConfirmation.getNotes();
                    attachmentFile = requestConfirmation.getAttachmentFile();
                    attachmentID = requestConfirmation.getAttachmentID();
                    decisionNumber = requestConfirmation.getDecisionNumber();
                    transactionStatusID = requestConfirmation.getTransactionStatusID().toString();
                    approveDate = requestConfirmation.getApprovedDate();
                    isHalfDay = requestConfirmation.getIsHalfDay();
                    submitType = requestConfirmation.getSubmitType();
                    message = requestConfirmation.getMessage();
                    transactionStatusSaveOrSubmit = requestConfirmation.getTransactionStatusSaveOrSubmit(); //(sudah diinisialisasi di btn listener)
                    photo = requestConfirmation.getPhoto();
                    fullAccess = requestConfirmation.getFullAccess();
                    exclusionFields = requestConfirmation.getExclusionFields();
                    accessibilityAttribute = requestConfirmation.getAccessibilityAttribute();

                    AlertDialog.Builder alert = new AlertDialog.Builder(LeaveRequestDetailActivity.this);
                    alert.setTitle("Request Confirmation");
                    alert.setMessage(
                            "Request Type : \n" + requestConfirmation.getLeaveRequestType() +
                                    "\nLeave : \n" + requestConfirmation.getStartLeave() + " - " + requestConfirmation.getEndLeave() +
                                    "\nTotal Unit : \n" + requestConfirmation.getTotalUnit() +
                                    "\nUnit Reduce : \n" + requestConfirmation.getTotalUnitReduce() +
                                    "\nNotes : \n" + requestConfirmation.getNotes()
                    );
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            saveLeaveRequest();
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
                    Toast.makeText(LeaveRequestDetailActivity.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestConfirmation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveRequestDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }

    public void editLeaveRequest(String ids) {
        // get token
        apiInterface = APIClient.editLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(LeaveRequestDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        //apiInterface = APIClient.editLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<EditLeaveRequest> call3 = apiInterface.editLeaveRequst(ids);
        call3.enqueue(new Callback<EditLeaveRequest>() {

            @Override
            public void onResponse(Call<EditLeaveRequest> call, Response<EditLeaveRequest> response) {
                progressDialog.dismiss();
                EditLeaveRequest data = response.body();

                if (data != null && data.getIsSucceed()) {
                    //com.example.android.starbridges.model.editleaverequest.ReturnValue editLeaveRequest = data.getReturnValue();
                    editLeaveRequest = data.getReturnValue();

                    // used for set text spinner
                    idRequestType = data.getReturnValue().getLeaveRequestRuleID();
                    balanceUID = data.getReturnValue().getEmployeeLeaveBalanceUID();

                    // get data from edit leave request
                    id = editLeaveRequest.getID();
                    employeeID = Integer.parseInt(editLeaveRequest.getEmployeeID());
                    roster = editLeaveRequest.getRoster();
                    requestDate = editLeaveRequest.getRequestDate();
                    employeeNIK = editLeaveRequest.getEmployeeNIK();
                    employeeName = editLeaveRequest.getEmployeeName();
                    leaveRequestRuleID = editLeaveRequest.getLeaveRequestRuleID();
                    leaveRequestType = editLeaveRequest.getLeaveRequestType();
                    employeeLeaveBalanceUID = editLeaveRequest.getEmployeeLeaveBalanceUID();
                    currentBalance = editLeaveRequest.getCurrentBalance().toString();
                    balanceExpireDate = editLeaveRequest.getBalanceExpireDate();
                    totalUnit = editLeaveRequest.getTotalUnit().toString();
                    totalUnitReduce = editLeaveRequest.getTotalUnit().toString();
                    startLeave = formatDate(editLeaveRequest.getStartLeave());
                    endLeave = formatDate(editLeaveRequest.getEndLeave());
                    leaveAt = formatTime(editLeaveRequest.getLeaveAt());
                    returnAt = formatTime(editLeaveRequest.getReturnAt());
                    minIntervalViolation = editLeaveRequest.getMinIntervalViolation();
                    unitLimitViolation = editLeaveRequest.getUnitLimitViolation();
                    occurenceViolation = editLeaveRequest.getOccurenceViolation();
                    notesStr = editLeaveRequest.getNotes();
                    attachmentFile = editLeaveRequest.getAttachmentFile();
                    attachmentID = editLeaveRequest.getAttachmentID();
                    decisionNumber = editLeaveRequest.getDecisionNumber();
                    transactionStatusID = editLeaveRequest.getTransactionStatusID().toString();
                    approveDate = editLeaveRequest.getApprovedDate();
                    isHalfDay = editLeaveRequest.getIsHalfDay();
                    submitType = editLeaveRequest.getSubmitType();
                    message = editLeaveRequest.getMessage();
                    transactionStatusSaveOrSubmit = editLeaveRequest.getTransactionStatusSaveOrSubmit();
                    photo = editLeaveRequest.getPhoto();
                    fullAccess = editLeaveRequest.getFullAccess();
                    exclusionFields = editLeaveRequest.getExclusionFields();
                    accessibilityAttribute = editLeaveRequest.getAccessibilityAttribute();

                    // start leave
//                    startDate.setText(startLeave + " - " + leaveAt);
                    startDate.setText(startLeave);
                    timeDateStart.setText(leaveAt);
                    // end date
//                    endDate.setText(endLeave + " - " + returnAt);
                    endDate.setText(endLeave);
                    timeDateEnd.setText(returnAt);
                    // notes
                    notes.setText(notesStr);

                    // load Image if exist
                    if (attachmentFile != null) {
                        byte[] decodedString = Base64.decode(attachmentFile, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imageView.setImageBitmap(bitmap);
                    }

                } else {
                    Toast.makeText(LeaveRequestDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    //finish();
                }

                initSpinnerRequestType();
                initSpinnerBalanceType();
            }

            @Override
            public void onFailure(Call<EditLeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveRequestDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();

                initSpinnerRequestType();
                initSpinnerBalanceType();
            }
        });

    }

    public void saveLeaveRequest() {
//        if (validasiTime() == true) {
//            alertNotif("", "Return at tidak boleh lebih kecil dari leave at");
//            returnAt = "";
//            timeDateEnd.setText(null);
//        } else {
        apiInterface = APIClient.saveLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(LeaveRequestDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        apiInterface = APIClient.saveLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<SaveLeaveRequest> call3 = apiInterface.saveLeaveRequest(
                id, employeeID, roster, requestDate, employeeNIK, employeeName, leaveRequestRuleID, leaveRequestType,
                employeeLeaveBalanceUID, currentBalance, balanceExpireDate, totalUnit, totalUnitReduce, startLeave,
                endLeave, leaveAt, returnAt, minIntervalViolation, unitLimitViolation, occurenceViolation, notesStr,
                attachmentFile, attachmentID, decisionNumber, transactionStatusID, approveDate, isHalfDay, submitType,
                message, transactionStatusSaveOrSubmit, photo, fullAccess, exclusionFields, accessibilityAttribute
        );
        call3.enqueue(new Callback<SaveLeaveRequest>() {

            @Override
            public void onResponse(Call<SaveLeaveRequest> call, Response<SaveLeaveRequest> response) {
                progressDialog.dismiss();
                SaveLeaveRequest data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(LeaveRequestDetailActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(LeaveRequestDetailActivity.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<SaveLeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveRequestDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
//        }
        // get token


    }

    public void initSpinnerBalanceType() {
        //final List<com.example.android.starbridges.model.balanceType.ReturnValue> listReturnValue1 = new ArrayList<>();
        listReturnValue1 = new ArrayList<>();
        com.example.android.starbridges.model.balanceType.ReturnValue returnValue1 = new com.example.android.starbridges.model.balanceType.ReturnValue();
        returnValue1.setText("");
        returnValue1.setValue("");
        listReturnValue1.add(returnValue1);

        apiInterface = APIClient.getBalanceType(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<BalanceType> call3 = apiInterface.getBalanceType(GlobalVar.getEmployeeId());
        call3.enqueue(new Callback<BalanceType>() {

            @Override
            public void onResponse(Call<BalanceType> call, Response<BalanceType> response) {

                BalanceType data = response.body();

                if (data != null) {

                    LocItems1 = response.body().getReturnValue();
                    listReturnValue1.addAll(LocItems1);

                    ArrayAdapter<com.example.android.starbridges.model.balanceType.ReturnValue> adapter = new ArrayAdapter<com.example.android.starbridges.model.balanceType.ReturnValue>(LeaveRequestDetailActivity.this,
                            android.R.layout.simple_spinner_item, listReturnValue1);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerBalanceType.setAdapter(adapter);

                    // set text data while editing
                    setupSpinnerBalance();
                    progressDialog2.dismiss();
                } else {
                    progressDialog2.dismiss();
                    Toast.makeText(LeaveRequestDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BalanceType> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveRequestDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                setupSpinnerBalance();
            }
        });

    }

    public void initSpinnerRequestType() {
        //final List<ReturnValue> listReturnValue = new ArrayList<>();
        listReturnValue = new ArrayList<>();
        ReturnValue returnValue = new ReturnValue();
        returnValue.setID(0);
        returnValue.setName("");
        listReturnValue.add(returnValue);

        apiInterface = APIClient.getRequestType(GlobalVar.getToken()).create(APIInterfaceRest.class);
        //String emp = GlobalVar.getEmployeeId();
        Call<RequestType> call3 = apiInterface.getRequestType(GlobalVar.getEmployeeId());
        call3.enqueue(new Callback<RequestType>() {

            @Override
            public void onResponse(Call<RequestType> call, Response<RequestType> response) {

                RequestType data = response.body();

                if (data != null) {
                    LocItems = response.body().getReturnValue();
                    listReturnValue.addAll(LocItems);

                    ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(LeaveRequestDetailActivity.this,
                            android.R.layout.simple_spinner_item, listReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRequestType.setAdapter(adapter);

                    // set text while edit
                    setupSpinner();

                } else {

                    Toast.makeText(LeaveRequestDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestType> call, Throwable t) {
                Toast.makeText(LeaveRequestDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                setupSpinner();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        // set text to startLeave
        startLeave = sdf.format(myCalendar.getTime());

        // set text to comp date
        startDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2(Calendar cal) {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        // set text to endLeave
        endLeave = sdf.format(cal.getTime());

        // set text to comp date
        endDate.setText(sdf.format(cal.getTime()));
    }

    private String getDate(String view) {
        DateFormat dateFormat;
        // view -> kebutuhan UI, other -> kirim ke server
        if (view.equals("view"))
            dateFormat = new SimpleDateFormat("dd MMM yyyy");
        else
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = new Date();
        return dateFormat.format(date);
    }

    private String formatDate(String str) {
        String dateStr = "";
        if (str == null) {
            dateStr = "";
        } else {
            int position = str.indexOf("T");

            dateStr = str.substring(0, position);

            String dateArr[] = dateStr.split("-");

            dateStr = dateArr[2] + "/" + dateArr[1] + "/" + dateArr[0];
        }
        return dateStr;
    }

    private String formatTime(String str) {
        String timeStr = "";

        if (str == null) {
            timeStr = "";
        } else {
            int position = str.indexOf("T");

            timeStr = str.substring(position + 1, str.length() - 3);
        }
        return timeStr;
    }

    public void setupSpinner() {
        int spinnerIdSelected = 0;

        if (idRequestType > 0) {
            for (ReturnValue x : listReturnValue) {
                if (x.getID() == idRequestType)
                    break;
                spinnerIdSelected++;
            }
        }

        spinnerRequestType.setSelection(spinnerIdSelected);
    }

    public void setupSpinnerBalance() {
        int spinnerIdSelected = 0;

        if (balanceUID != "") {
            for (com.example.android.starbridges.model.balanceType.ReturnValue x : listReturnValue1) {
                if (x.getValue().equals(balanceUID))
                    break;
                spinnerIdSelected++;
            }
        }

        spinnerBalanceType.setSelection(spinnerIdSelected);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
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
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(LeaveRequestDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(LeaveRequestDetailActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    private void alertNotif(String title, String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(LeaveRequestDetailActivity.this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.show();
    }

    public void deleteTimeLeave(View view) {
        leaveAt = "";
        timeDateStart.setText("");
    }

    public void deleteTimeReturn(View view) {
        returnAt = "";
        timeDateEnd.setText("");
    }

    private boolean validasiTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date tmp1 = null;
        Date tmp2 = null;
        try {
            tmp2 = sdf.parse(returnAt);
            tmp1 = sdf.parse(leaveAt);
        } catch (ParseException e) {
            try {
                tmp2 = sdf.parse(formatTime(returnAt));
                tmp1 = sdf.parse(formatTime(leaveAt));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        boolean test = tmp1.after(tmp2);
        return test;
    }

    private boolean validasiDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date tmp1 = null;
        Date tmp2 = null;
        try {
            tmp1 = sdf.parse(startLeave);
            tmp2 = sdf.parse(endLeave);
        } catch (ParseException e) {
            try {
                tmp1 = sdf.parse(formatDate(startLeave));
                tmp2 = sdf.parse(formatDate(endLeave));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        boolean output = tmp1.after(tmp2);
        return output;
    }

    private void hitungJumlahHari() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date tmp1 = null;
        Date tmp2 = null;
        try {
            tmp1 = sdf.parse(startLeave);
            tmp2 = sdf.parse(endLeave);
        } catch (ParseException e) {
            try {
                tmp1 = sdf.parse(formatDate(startLeave));
                tmp2 = sdf.parse(formatDate(endLeave));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        long a = tmp2.getTime() - tmp1.getTime();
        int b = Integer.parseInt(String.valueOf(TimeUnit.DAYS.convert(a, TimeUnit.MILLISECONDS))) + 1;
        alertNotif("beda hari ", "Days: " + b);

    }

    private void defaultLabelEndDate(int id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date tmpDate = null;

        try {
            tmpDate = sdf.parse(startDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTime(tmpDate);

        int check = 0;
        check = id;
        if (check == 0) {
            updateLabel2(tmpCal);
        } else if (check == 1) {
            tmpCal.add(Calendar.DATE, 6);
            updateLabel2(tmpCal);
        } else if (check == 3) {
            tmpCal.add(Calendar.DATE, 89);
            updateLabel2(tmpCal);
        } else if (check == 4) {
            tmpCal.add(Calendar.DATE, 9);
            updateLabel2(tmpCal);
        } else if (check == 10) {
            tmpCal.add(Calendar.DATE, 4);
            updateLabel2(tmpCal);
        } else if (check == 11) {
            tmpCal.add(Calendar.DATE, 9);
            updateLabel2(tmpCal);
        } else if (check == 12) {
            tmpCal.add(Calendar.DATE, 2);
            updateLabel2(tmpCal);
        } else if (check == 13) {
            tmpCal.add(Calendar.DATE, 0);
            updateLabel2(tmpCal);
        } else if (check == 14) {
            tmpCal.add(Calendar.DATE, 1);
            updateLabel2(tmpCal);
        } else if (check == 15) {
            tmpCal.add(Calendar.DATE, 1);
            updateLabel2(tmpCal);
        } else if (check == 16) {
            tmpCal.add(Calendar.DATE, 1);
            updateLabel2(tmpCal);
        } else if (check == 17) {
            tmpCal.add(Calendar.DATE, 0);
            updateLabel2(tmpCal);
        } else if (check == 18) {
            tmpCal.add(Calendar.DATE, 2);
            updateLabel2(tmpCal);
        } else if (check == 19) {
            tmpCal.add(Calendar.DATE, 0);
            updateLabel2(tmpCal);
        } else if (check == 20) {
            tmpCal.add(Calendar.DATE, 0);
            updateLabel2(tmpCal);
        } else if (check == 21) {
            tmpCal.add(Calendar.DATE, 0);
            updateLabel2(tmpCal);
        } else
            updateLabel2(tmpCal);
    }


}

