package com.example.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.EditShiftExchange.EditShiftExchange;
import com.example.android.starbridges.model.ListEmployee.ListEmployee;
import com.example.android.starbridges.model.ListShift.ListShift;
import com.example.android.starbridges.model.SaveShiftExchange.SaveShiftExchange;
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

public class ShiftExchangeDetailActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 999;
    private int TotalSpinner;
    private String TransID;
    EditText txtNotesShiftEx, txtDateShiftEx;

    Button btnUploadShiftExDetail, btnSubmitShiftExDetail, btnSaveShiftExDetail, btnCancelShiftExDetail;
    Spinner mEmployeeSpinner, mShiftSpinner;
    ImageView imgDateShiftExDetail, imgShiftExDetail;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    List<com.example.android.starbridges.model.ListEmployee.ReturnValue> lstEmployee;
    List<com.example.android.starbridges.model.ListShift.ReturnValue> lstShift;

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
    String empID;
    String shiftID;

    com.example.android.starbridges.model.EditShiftExchange.ReturnValue editShiftChange;

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtDateShiftEx.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_exchange_detail);
        setTitle("Shift Exchange Detail");

        progressDialog = new ProgressDialog(ShiftExchangeDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        txtDateShiftEx = (EditText) findViewById(R.id.txtDateShiftEx);
        txtNotesShiftEx = (EditText) findViewById(R.id.txtNotesShiftEx);
        btnUploadShiftExDetail = (Button) findViewById(R.id.btnUploadShiftEx);
        btnSubmitShiftExDetail = (Button) findViewById(R.id.btnSubmitShiftEx);
        btnSaveShiftExDetail = (Button) findViewById(R.id.btnSaveShiftEx);
        btnCancelShiftExDetail = (Button) findViewById(R.id.btnCancelShiftEx);
        imgDateShiftExDetail = (ImageView) findViewById(R.id.imgDateShiftEx);
        imgShiftExDetail = (ImageView) findViewById(R.id.imageUploadShiftEx);

        mEmployeeSpinner = (Spinner) findViewById(R.id.spEmployeeShiftEx);
        mShiftSpinner = (Spinner) findViewById(R.id.spShift);

        imgDateShiftExDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ShiftExchangeDetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final Intent intent = getIntent();
        TransID = intent.getStringExtra("id");
        TotalSpinner = 0;
        initSpinnerEmployee();
        initSpinnerShift();

        btnUploadShiftExDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        mEmployeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                com.example.android.starbridges.model.ListEmployee.ReturnValue employee = (com.example.android.starbridges.model.ListEmployee.ReturnValue) mEmployeeSpinner.getItemAtPosition(i);

                empID = employee.getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mShiftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                com.example.android.starbridges.model.ListShift.ReturnValue shift = (com.example.android.starbridges.model.ListShift.ReturnValue) mShiftSpinner.getItemAtPosition(i);
                //if (shift != null) {
                shiftID = shift.getID();
                //}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSaveShiftExDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ShiftExchangeDetailActivity.this);
                alert.setTitle("Shift Exchange Confirmation");
                alert.setMessage("Employee\n" +
                        "\t" + mEmployeeSpinner.getSelectedItem().toString() + "" +
                        "\nDate\n" +
                        "\t" + dateFormat2(txtDateShiftEx.getText().toString()) + "" +
                        "\nShift\n" +
                        "\t" + mShiftSpinner.getSelectedItem().toString() + "" +
                        "\nNotes\n" +
                        "\t" + txtNotesShiftEx.getText().toString() + "\n\n" +
                        "This information will be saved in draft");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveSubmitShiftEx("Save");
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

        btnSubmitShiftExDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ShiftExchangeDetailActivity.this);
                alert.setTitle("Shift Exchange Confirmation");
                alert.setMessage("Employee\n" +
                        "\t" + mEmployeeSpinner.getSelectedItem().toString() + "" +
                        "\nDate\n" +
                        "\t" + dateFormat2(txtDateShiftEx.getText().toString()) + "" +
                        "\nShift\n" +
                        "\t" + mShiftSpinner.getSelectedItem().toString() + "" +
                        "\nNotes\n" +
                        "\t" + txtNotesShiftEx.getText().toString() + "\n\n");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveSubmitShiftEx("Submit");
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

        btnCancelShiftExDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ShiftExchangeDetailActivity.this);
                alert.setTitle("Reimbursement Confirmation");
                alert.setMessage("Employee\n" +
                        "\t" + mEmployeeSpinner.getSelectedItem().toString() + "" +
                        "\nDate\n" +
                        "\t" + dateFormat2(txtDateShiftEx.getText().toString()) + "" +
                        "\nShift\n" +
                        "\t" + mShiftSpinner.getSelectedItem().toString() + "" +
                        "\nNotes\n" +
                        "\t" + txtNotesShiftEx.getText().toString() + "\n\n" +
                        "Your information will not be saved");
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
    }

    public void getData(String id) {

        apiInterface = APIClient.editShiftExchange(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.editShiftExchange(id).enqueue(new Callback<EditShiftExchange>() {
            @Override
            public void onResponse(Call<EditShiftExchange> call, Response<EditShiftExchange> response) {

                if (response.body().getIsSucceed()) {
                    editShiftChange = response.body().getReturnValue();

                    txtDateShiftEx.setText(dateFormat(editShiftChange.getDate()));
                    txtNotesShiftEx.setText(editShiftChange.getNotes());

                    photo = (String) editShiftChange.getAttachmentFile();

                    empID = editShiftChange.getEmployeeID();
                    shiftID = editShiftChange.getShiftID();

                    if (photo != null) {
                        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imgShiftExDetail.setImageBitmap(bitmap);
                    }

                    if (empID != null) {
                        int counterE = 0;
                        for (com.example.android.starbridges.model.ListEmployee.ReturnValue employee : lstEmployee) {
                            if (employee.getID().matches(empID)) break;
                            counterE++;
                        }
                        mEmployeeSpinner.setSelection(counterE);
                    }

                    if (shiftID != null) {
                        int counterL = 0;
                        for (com.example.android.starbridges.model.ListShift.ReturnValue shift : lstShift) {
                            if (shift.getID().matches(shiftID)) break;
                            counterL++;
                        }
                        mShiftSpinner.setSelection(counterL);
                    }
                } else {

                    Toast.makeText(ShiftExchangeDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<EditShiftExchange> call, Throwable t) {
                Toast.makeText(ShiftExchangeDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                initSpinnerEmployee();
                initSpinnerShift();
                progressDialog.dismiss();
            }
        });
    }

    public String dateFormat(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1;
        String result;
        try {
            date1 = df.parse(dateString);
            result = sdf.format(date1);
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    public String dateFormat2(String dateString) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

        String dateResult = "";

        Date convertDate;
        try {
            convertDate = sdf.parse(dateString.toString());
            dateResult = df.format(convertDate);
        } catch (Exception e) {

        }
        return dateResult;
    }

    public void initSpinnerEmployee() {
        lstEmployee = new ArrayList<>();
        com.example.android.starbridges.model.ListEmployee.ReturnValue returnValue = new com.example.android.starbridges.model.ListEmployee.ReturnValue();
        returnValue.setID("");
        returnValue.setFullName("");
        lstEmployee.add(returnValue);

        apiInterface = APIClient.getListEmployee(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListShiftEmployee().enqueue(new Callback<ListEmployee>() {
            @Override
            public void onResponse(Call<ListEmployee> call, Response<ListEmployee> response) {

                if (response.isSuccessful()) {

                    List<com.example.android.starbridges.model.ListEmployee.ReturnValue> EmpItems = response.body().getReturnValue();
                    if (EmpItems != null) {
                        lstEmployee.addAll(EmpItems);
                    } else {
                        Toast.makeText(ShiftExchangeDetailActivity.this, "spinner Tidak dapat data", Toast.LENGTH_LONG).show();
                    }

                    ArrayAdapter<com.example.android.starbridges.model.ListEmployee.ReturnValue> adapter = new ArrayAdapter<com.example.android.starbridges.model.ListEmployee.ReturnValue>(ShiftExchangeDetailActivity.this,
                            android.R.layout.simple_spinner_item, lstEmployee);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mEmployeeSpinner.setAdapter(adapter);
                    TotalSpinner++;
                    if (TotalSpinner == 2) {
                        if (TransID != null)
                            getData(TransID);
                        else progressDialog.dismiss();

                    }
                } else {

                    Toast.makeText(ShiftExchangeDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListEmployee> call, Throwable t) {
                Toast.makeText(ShiftExchangeDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                imgShiftExDetail.setImageBitmap(selectedImage);
                photo = encodeImage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ShiftExchangeDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(ShiftExchangeDetailActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void initSpinnerShift() {
        lstShift = new ArrayList<>();
        com.example.android.starbridges.model.ListShift.ReturnValue returnValue = new com.example.android.starbridges.model.ListShift.ReturnValue();
        returnValue.setID("");
        returnValue.setName("");
        lstShift.add(returnValue);

        apiInterface = APIClient.getListShift(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListShift().enqueue(new Callback<ListShift>() {
            @Override
            public void onResponse(Call<ListShift> call, Response<ListShift> response) {

                if (response.isSuccessful()) {

                    List<com.example.android.starbridges.model.ListShift.ReturnValue> ShiftItems = response.body().getReturnValue();
                    if (ShiftItems != null) {
                        lstShift.addAll(ShiftItems);
                    } else {
                        Toast.makeText(ShiftExchangeDetailActivity.this, "spinner Tidak dapat data", Toast.LENGTH_LONG).show();
                    }

                    ArrayAdapter<com.example.android.starbridges.model.ListShift.ReturnValue> adapter = new ArrayAdapter<com.example.android.starbridges.model.ListShift.ReturnValue>(ShiftExchangeDetailActivity.this,
                            android.R.layout.simple_spinner_item, lstShift);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mShiftSpinner.setAdapter(adapter);
                    TotalSpinner++;
                    if (TotalSpinner == 2) {
                        if (TransID != null)
                            getData(TransID);
                        else progressDialog.dismiss();
                    }
                } else {

                    Toast.makeText(ShiftExchangeDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListShift> call, Throwable t) {
                Toast.makeText(ShiftExchangeDetailActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void saveSubmitShiftEx(String transactionStatus) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        JSONObject paramObject = new JSONObject();
        try {

            paramObject.put("ID", TransID);
            paramObject.put("EmployeeID", empID);
            paramObject.put("ShiftID", shiftID);
            paramObject.put("Notes", txtNotesShiftEx.getText().toString());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String transactionDate = "";
            Date convertTransactionDate;
            try {
                convertTransactionDate = sdf.parse(txtDateShiftEx.getText().toString());
                transactionDate = df.format(convertTransactionDate);
            } catch (Exception e) {

            }

            paramObject.put("Date", transactionDate);
            paramObject.put("TransactionStatusID", null);
            paramObject.put("AttachmentFile", photo);
            paramObject.put("AttachmentID", null);

        } catch (Exception e) {

        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramObject.toString());
        final APIInterfaceRest apiInterface = APIClient.saveShiftExchange(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<SaveShiftExchange> call3 = apiInterface.saveShiftExchange(body, transactionStatus);

        call3.enqueue(new Callback<SaveShiftExchange>() {
            @Override
            public void onResponse(Call<SaveShiftExchange> call, Response<SaveShiftExchange> response) {
                progressDialog.dismiss();
                SaveShiftExchange data = response.body();
                if (data.getIsSucceed()) {
                    Toast.makeText(getApplicationContext(), data.getMessage(), Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            public void onFailure(Call<SaveShiftExchange> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


    }
}
