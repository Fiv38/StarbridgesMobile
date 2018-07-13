package id.co.indocyber.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import id.co.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.model.CorrectionDetail.CorrectionDetail;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.model.OLocation.OLocation;
import id.co.indocyber.android.starbridges.model.OLocation.ReturnValue;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CorrectionDetailActivity extends AppCompatActivity {

    TextView txtLogDateCDetails, txtShiftCDetails, lblLocationErrorCDetails;
    EditText txtLogInCDetails,txtBreakStartCDetails,txtBreakEndCDetails;
    EditText txtLogOutCDetails, txtOverTimeInCDetails, txtOverTimeOutCDetails, txtNotesCDetails;
    Spinner spnLocationCDetails;
    String uid, locationId, locationIdSpinner;
    ProgressDialog progressDialog;
    id.co.indocyber.android.starbridges.model.CorrectionDetail.ReturnValue valueCorrectionDetail;
    Button btnSubmitCDetails, btnSaveCDetails, btnCancelCDetails;
    APIInterfaceRest apiInterface;
    List<ReturnValue> locItems;
    List<ReturnValue> listReturnValue;

    ImageView imgLogInCDetails,imgBreakStartCDetails,imgBreakEndCDetails;
    ImageView imgLogOutCDetails, imgOverTimeInCDetails, imgOverTimeOutCDetails;

    ImageView imgClearLogInCDetails,imgClearBreakStartCDetails,imgClearBreakEndCDetails;
    ImageView imgClearLogOutCDetails, imgClearOverTimeInCDetails, imgClearOverTimeOutCDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction_detail);

        setTitle("Detail Correction");

        final Intent intent = getIntent();
        uid = intent.getStringExtra("uid");

        txtLogDateCDetails=(TextView)findViewById(R.id.txtLogDateCDetails);
        txtShiftCDetails=(TextView)findViewById(R.id.txtShiftCDetails);

        txtLogInCDetails=(EditText)findViewById(R.id.txtLogInCDetails);
        imgLogInCDetails=(ImageView)findViewById(R.id.imgLogInCDetails);
        imgClearLogInCDetails=(ImageView)findViewById(R.id.imgClearLogInCDetails);

        lblLocationErrorCDetails=(TextView)findViewById(R.id.lblLocationErrorCDetails);

        imgClearLogInCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLogInCDetails.setText("");
            }
        });
        txtLogInCDetails.setFocusable(false);
        txtLogInCDetails.setClickable(true);
        imgLogInCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogInCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtLogInCDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtLogInCDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogInCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txtBreakEndCDetails=(EditText)findViewById(R.id.txtBreakEndCDetails);
        imgBreakEndCDetails=(ImageView)findViewById(R.id.imgBreaEndCDetails);
        imgClearBreakEndCDetails=(ImageView)findViewById(R.id.imgClearBreaEndCDetails);
        imgClearBreakEndCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtBreakEndCDetails.setText("");
            }
        });
        txtBreakEndCDetails.setFocusable(false);
        txtBreakEndCDetails.setClickable(true);
        imgBreakEndCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtBreakEndCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtBreakEndCDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtBreakEndCDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtBreakEndCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        txtLogOutCDetails=(EditText)findViewById(R.id.txtLogOutCDetails);
        imgLogOutCDetails=(ImageView)findViewById(R.id.imgLogOutCDetails);
        imgClearLogOutCDetails=(ImageView)findViewById(R.id.imgClearLogOutCDetails);
        imgClearLogOutCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtLogOutCDetails.setText("");
            }
        });
        txtLogOutCDetails.setFocusable(false);
        txtLogOutCDetails.setClickable(true);
        imgLogOutCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogOutCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtLogOutCDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtLogOutCDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogOutCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txtOverTimeInCDetails=(EditText)findViewById(R.id.txtOverTimeInCDetails);
        imgOverTimeInCDetails=(ImageView)findViewById(R.id.imgOverTimeInCDetails);
        imgClearOverTimeInCDetails=(ImageView)findViewById(R.id.imgClearOverTimeInCDetails);
        imgClearOverTimeInCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtOverTimeInCDetails.setText("");
            }
        });
        txtOverTimeInCDetails.setFocusable(false);
        txtOverTimeInCDetails.setClickable(true);
        imgOverTimeInCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeInCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtOverTimeInCDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtOverTimeInCDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeInCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txtOverTimeOutCDetails=(EditText)findViewById(R.id.txtOverTimeOutCDetails);
        imgOverTimeOutCDetails=(ImageView)findViewById(R.id.imgOverTimeOutCDetails);
        imgClearOverTimeOutCDetails=(ImageView)findViewById(R.id.imgClearOverTimeOutCDetails);
        imgClearOverTimeOutCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtOverTimeOutCDetails.setText("");
            }
        });
        txtOverTimeOutCDetails.setFocusable(false);
        txtOverTimeOutCDetails.setClickable(true);
        imgOverTimeOutCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeOutCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtOverTimeOutCDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtOverTimeOutCDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeOutCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        spnLocationCDetails=(Spinner)findViewById(R.id.spnLocationCDetails);
//        spnLocationCDetails.setFocusable(false);
//        spnLocationCDetails.setClickable(true);

        txtBreakStartCDetails=(EditText)findViewById(R.id.txtBreakStartCDetails);
        imgBreakStartCDetails=(ImageView) findViewById(R.id.imgBreakStartCDetails);
        imgClearBreakStartCDetails=(ImageView) findViewById(R.id.imgClearBreakStartCDetails);
        imgClearBreakStartCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtBreakStartCDetails.setText("");
            }
        });
        txtBreakStartCDetails.setFocusable(false);
        txtBreakStartCDetails.setClickable(true);
        imgBreakStartCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Calendar mTime = Calendar.getInstance();
            int hour = mTime.get(Calendar.HOUR_OF_DAY);
            int minute = mTime.get(Calendar.MINUTE);

            TimePickerDialog mTimePicker;
            try
            {
                mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txtBreakStartCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                    }
                }, Integer.parseInt(txtBreakStartCDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtBreakStartCDetails.getText().toString().substring(3,5)), true);
            }
            catch (Exception e)
            {
                mTimePicker = new TimePickerDialog(CorrectionDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txtBreakStartCDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                    }
                }, hour, minute, true);
            }

            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
            }
        });


        txtNotesCDetails=(EditText)findViewById(R.id.txtNotesCDetails);

        btnCancelCDetails=(Button)findViewById(R.id.btnCancelCDetails);
        btnSaveCDetails=(Button)findViewById(R.id.btnSaveCDetails);
        btnSubmitCDetails=(Button)findViewById(R.id.btnSubmitCDetails);



        btnCancelCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CorrectionDetailActivity.this);
                alert.setTitle("Confirmation");
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

        btnSaveCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtLogInCDetails.getText().toString().matches(""))
                {
                    txtLogInCDetails.setError("Please fill the blank");
                }
                else if(txtLogOutCDetails.getText().toString().matches(""))
                {
                    txtLogOutCDetails.setError("Please fill the blank");
                }
                else if(spnLocationCDetails.getSelectedItem().toString().matches(""))
                {
                    lblLocationErrorCDetails.setError("");
                    lblLocationErrorCDetails.setTextColor(Color.RED);//just to highlight that this is an error
                    lblLocationErrorCDetails.setText(" Please choose location ");//changes the selected item text to this
                }
                else if(txtNotesCDetails.getText().toString().matches(""))
                {
                    txtNotesCDetails.setError("Please fill notes");
                }
                else if(Double.parseDouble(txtLogOutCDetails.getText().toString().substring(0,2)+"."+txtLogOutCDetails.getText().toString().substring(3,5))<Double.parseDouble(txtLogInCDetails.getText().toString().substring(0,2)+"."+txtLogInCDetails.getText().toString().substring(3,5)))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CorrectionDetailActivity.this);
                    alert.setTitle("Please make sure log out > log in");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CorrectionDetailActivity.this);
                    alert.setTitle("Confirmation");
                    alert.setTitle("This information will be saved as draft");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            saveSubmitAttendanceCorrection("Save");
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
        });

        btnSubmitCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtLogInCDetails.getText().toString().matches(""))
                {
                    txtLogInCDetails.setError("Please fill the blank");
                }
                else if(txtLogOutCDetails.getText().toString().matches(""))
                {
                    txtLogOutCDetails.setError("Please fill the blank");
                }
                else if(spnLocationCDetails.getSelectedItem().toString().matches(""))
                {
                    lblLocationErrorCDetails.setError(" Please choose location ");
                }
                else if(txtNotesCDetails.getText().toString().matches(""))
                {
                    txtNotesCDetails.setError("Please fill notes");
                }
                else if(Double.parseDouble(txtLogOutCDetails.getText().toString().substring(0,2)+"."+txtLogOutCDetails.getText().toString().substring(3,5))<Double.parseDouble(txtLogInCDetails.getText().toString().substring(0,2)+"."+txtLogInCDetails.getText().toString().substring(3,5)))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CorrectionDetailActivity.this);
                    alert.setTitle("Please make sure log out > log in");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alert.show();
                }
                else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CorrectionDetailActivity.this);
                    alert.setTitle("Confirmation");
                    alert.setTitle("This information will be send and wait for approval");
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            saveSubmitAttendanceCorrection("Submit");
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
        });

        spnLocationCDetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final ReturnValue returnValue1=(ReturnValue)spnLocationCDetails.getItemAtPosition(i);
                //Log.d("LocationIdnya", returnValue1.getID());
                locationIdSpinner=returnValue1.getID();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        initSpinnerLoc();

    }

    public void getAttendaceCorrection(String uid) {



        final APIInterfaceRest apiInterface = APIClient.getDetailAttendanceCorrection(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<CorrectionDetail> call3 = apiInterface.getDetailAttendanceCorrection(uid);

        call3.enqueue(new Callback<CorrectionDetail>() {
            @Override
            public void onResponse(Call<CorrectionDetail> call, Response<CorrectionDetail> response) {
                CorrectionDetail data = response.body();
                if (data != null && data.isIsSucceed()) {
                    valueCorrectionDetail=data.getReturnValue();
                    locationId=valueCorrectionDetail.getLocationID();

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CorrectionDetailActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(CorrectionDetailActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                setText(valueCorrectionDetail);
                setupSpinner();
            }

            @Override
            public void onFailure(Call<CorrectionDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                initSpinnerLoc();
                call.cancel();
            }
        });

    }

    public void saveSubmitAttendanceCorrection(String saveOrSubmit) {

        progressDialog = new ProgressDialog(CorrectionDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        valueCorrectionDetail.setID("");
        valueCorrectionDetail.setActualLogIn(txtLogInCDetails.getText().toString());
        valueCorrectionDetail.setActualBreakEnd(txtBreakEndCDetails.getText().toString());
        valueCorrectionDetail.setActualLogOut(txtLogOutCDetails.getText().toString());
        valueCorrectionDetail.setActualOvertimeIn(txtOverTimeInCDetails.getText().toString());
        valueCorrectionDetail.setActualOvertimeOut(txtOverTimeOutCDetails.getText().toString());
        valueCorrectionDetail.setActualBreakStart(txtBreakStartCDetails.getText().toString());
        valueCorrectionDetail.setNotes(txtNotesCDetails.getText().toString());
        valueCorrectionDetail.setLocationID(locationIdSpinner);

        final APIInterfaceRest apiInterface = APIClient.asveSubmitAttendanceCorrection(GlobalVar.getToken()).create(APIInterfaceRest.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(valueCorrectionDetail).toString());
        Call<MessageReturn> call3 = apiInterface.saveSubmitAttendanceCorrection(saveOrSubmit, body);

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
                Intent intent = new Intent(CorrectionDetailActivity.this, CorrectionListActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    public void setText(id.co.indocyber.android.starbridges.model.CorrectionDetail.ReturnValue valueCorrectionDetail)
    {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String dateResult = "";
        try{
            Date result =  df.parse(valueCorrectionDetail.getLogDate());
            dateResult=sdf.format(result);
        }catch (Exception e)
        {

        }

        txtLogDateCDetails.setText(dateResult);
        txtShiftCDetails.setText(valueCorrectionDetail.getShift());
        txtLogInCDetails.setText(valueCorrectionDetail.getActualLogIn() == null ? "": valueCorrectionDetail.getActualLogIn().substring(11,16));
        txtBreakEndCDetails.setText(valueCorrectionDetail.getActualBreakEnd() == null? "":valueCorrectionDetail.getActualBreakEnd().substring(11,16));
        txtLogOutCDetails.setText(valueCorrectionDetail.getActualLogOut() == null? "":valueCorrectionDetail.getActualLogOut().substring(11,16));
        txtOverTimeInCDetails.setText(valueCorrectionDetail.getActualOvertimeIn() == null? "":valueCorrectionDetail.getActualOvertimeIn().substring(11,16));
        txtOverTimeOutCDetails.setText(valueCorrectionDetail.getActualOvertimeOut() == null? "":valueCorrectionDetail.getActualOvertimeOut().substring(11,16));
        //spnLocationCDraftDetails=(Spinner)findViewById(R.id.spnLocationCDraftDetails);
        txtBreakStartCDetails.setText(valueCorrectionDetail.getActualBreakStart() == null? "":valueCorrectionDetail.getActualBreakStart().substring(11,16));
        txtNotesCDetails.setText(valueCorrectionDetail.getNotes() == null? "":valueCorrectionDetail.getNotes());

    }

    public void initSpinnerLoc() {
        progressDialog = new ProgressDialog(CorrectionDetailActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        listReturnValue= new ArrayList<>();
        ReturnValue returnValue=new ReturnValue();
        returnValue.setID("");
        returnValue.setAddress("");
        returnValue.setCode("");
        returnValue.setDescription("");
        returnValue.setName("");
        listReturnValue.add(returnValue);

        apiInterface = APIClient.getLocationValue(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getLocation().enqueue(new Callback<OLocation>() {
            @Override
            public void onResponse(Call<OLocation> call, Response<OLocation> response) {

                if (response.isSuccessful()) {

                    locItems = response.body().getReturnValue();
                    listReturnValue.addAll(locItems);

                    ArrayAdapter<ReturnValue> adapter = new ArrayAdapter<ReturnValue>(CorrectionDetailActivity.this,
                            android.R.layout.simple_spinner_item, listReturnValue);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnLocationCDetails.setAdapter(adapter);

                    getAttendaceCorrection(uid);



                } else {

                    Toast.makeText(CorrectionDetailActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OLocation> call, Throwable t) {
                Toast.makeText(CorrectionDetailActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void setupSpinner()
    {
        int spinnerIdSelected=0;
        if(locationId!=null)
        {
            for(ReturnValue x: listReturnValue)
            {
                if(x.getID().equals(locationId))
                    break;
                spinnerIdSelected++;
            }
        }

        if(spinnerIdSelected!=0)
           spnLocationCDetails.setSelection(spinnerIdSelected);

        progressDialog.dismiss();
    }

}
