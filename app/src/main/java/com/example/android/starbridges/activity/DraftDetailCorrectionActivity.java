package com.example.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.CorrectionDetail.CorrectionDetail;
import com.example.android.starbridges.model.CorrectionDetail.ReturnValue;
import com.example.android.starbridges.model.OLocation.OLocation;
import com.example.android.starbridges.model.MessageReturn.MessageReturn;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DraftDetailCorrectionActivity extends AppCompatActivity {
    TextView txtLogDateCDraftDetails, txtShiftCDraftDetails;
    EditText txtLogInCDraftDetails,txtBreakStartCDraftDetails,txtBreakEndCDraftDetails, txtLocationCDraftDetails;
    EditText txtLogOutCDraftDetails, txtOverTimeInCDraftDetails, txtOverTimeOutCDraftDetails, txtNotesCDraftDetails;
    Spinner spnLocationCDraftDetails;
    String id, locationIdSpinner, locationId;
    ProgressDialog progressDialog;
    ReturnValue valueCorrectionDetail;
    Button btnSubmitCDraftDetails, btnSaveCDraftDetails, btnCancelCDraftDetails;
    APIInterfaceRest apiInterface;
    List<com.example.android.starbridges.model.OLocation.ReturnValue> locItems;
    List<com.example.android.starbridges.model.OLocation.ReturnValue> listReturnValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_detail_correction);

        setTitle("Draft Detail Correction");

        final Intent intent = getIntent();
        id = intent.getStringExtra("id");

        txtLogDateCDraftDetails=(TextView)findViewById(R.id.txtLogDateCDraftDetails);
        txtShiftCDraftDetails=(TextView)findViewById(R.id.txtShiftCDraftDetails);

        txtLogInCDraftDetails=(EditText)findViewById(R.id.txtLogInCDraftDetails);
        txtLogInCDraftDetails.setFocusable(false);
        txtLogInCDraftDetails.setClickable(true);
        txtLogInCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogInCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtLogInCDraftDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtLogInCDraftDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogInCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txtBreakEndCDraftDetails=(EditText)findViewById(R.id.txtBreakEndDetails);
        txtBreakEndCDraftDetails.setFocusable(false);
        txtBreakEndCDraftDetails.setClickable(true);
        txtBreakEndCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtBreakEndCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtBreakEndCDraftDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtBreakEndCDraftDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtBreakEndCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txtLocationCDraftDetails=(EditText)findViewById(R.id.txtLocationCDraftDetails);

        txtLogOutCDraftDetails=(EditText)findViewById(R.id.txtLogOutCDraftDetails);
        txtLogOutCDraftDetails.setFocusable(false);
        txtLogOutCDraftDetails.setClickable(true);
        txtLogOutCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogOutCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtLogOutCDraftDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtLogOutCDraftDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtLogOutCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txtOverTimeInCDraftDetails=(EditText)findViewById(R.id.txtOverTimeInCDraftDetails);
        txtOverTimeInCDraftDetails.setFocusable(false);
        txtOverTimeInCDraftDetails.setClickable(true);
        txtOverTimeInCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeInCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtOverTimeInCDraftDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtOverTimeInCDraftDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeInCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        txtOverTimeOutCDraftDetails=(EditText)findViewById(R.id.txtOverTimeOutCDraftDetails);
        txtOverTimeOutCDraftDetails.setFocusable(false);
        txtOverTimeOutCDraftDetails.setClickable(true);
        txtOverTimeOutCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try{
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeOutCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtOverTimeOutCDraftDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtOverTimeOutCDraftDetails.getText().toString().substring(3,5)), true);
                } catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtOverTimeOutCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        spnLocationCDraftDetails=(Spinner)findViewById(R.id.spnLocationCDraftDetails);
//        spnLocationCDraftDetails.setFocusable(false);
//        spnLocationCDraftDetails.setClickable(true);

        txtBreakStartCDraftDetails=(EditText)findViewById(R.id.txtBreakStartCDraftDetails);
        txtBreakStartCDraftDetails.setFocusable(false);
        txtBreakStartCDraftDetails.setClickable(true);
        txtBreakStartCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mTime = Calendar.getInstance();
                int hour = mTime.get(Calendar.HOUR_OF_DAY);
                int minute = mTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                try
                {
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtBreakStartCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, Integer.parseInt(txtBreakStartCDraftDetails.getText().toString().substring(0,2)) , Integer.parseInt(txtBreakStartCDraftDetails.getText().toString().substring(3,5)), true);
                }
                catch (Exception e)
                {
                    mTimePicker = new TimePickerDialog(DraftDetailCorrectionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            txtBreakStartCDraftDetails.setText( String.format("%2s",selectedHour).replace(' ','0')  + ":" + String.format("%2s",selectedMinute).replace(' ','0'));
                        }
                    }, hour, minute, true);
                }

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        txtNotesCDraftDetails=(EditText)findViewById(R.id.txtNotesCDraftDetails);

        btnCancelCDraftDetails=(Button)findViewById(R.id.btnCancelCDraftDetails);
        btnSaveCDraftDetails =(Button)findViewById(R.id.btnSaveCDraftDetails);
        btnSubmitCDraftDetails=(Button)findViewById(R.id.btnSubmitCDraftDetails);



        btnCancelCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DraftDetailCorrectionActivity.this);
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

        btnSaveCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DraftDetailCorrectionActivity.this);
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
        });

        btnSubmitCDraftDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DraftDetailCorrectionActivity.this);
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
        });

        getAttendaceCorrection(id);


        spnLocationCDraftDetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                    final com.example.android.starbridges.model.OLocation.ReturnValue returnValue1=(com.example.android.starbridges.model.OLocation.ReturnValue)spnLocationCDraftDetails.getItemAtPosition(i);
                    //Log.d("LocationIdnya", returnValue1.getID());
                    locationIdSpinner=returnValue1.getID();
                }


                try{
                }catch (Exception e)
                {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void getAttendaceCorrection(String id) {

        progressDialog = new ProgressDialog(DraftDetailCorrectionActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        final APIInterfaceRest apiInterface = APIClient.getDetailAttendanceCorrection(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<CorrectionDetail> call3 = apiInterface.getDetailDraftAttendanceCorrection(id);

        call3.enqueue(new Callback<CorrectionDetail>() {
            @Override
            public void onResponse(Call<CorrectionDetail> call, Response<CorrectionDetail> response) {
                CorrectionDetail data = response.body();
                if (data != null && data.isIsSucceed()) {
                    valueCorrectionDetail=data.getReturnValue();
                    locationId=valueCorrectionDetail.getLocationID();
                    initSpinnerLoc();
                    setText(valueCorrectionDetail);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(DraftDetailCorrectionActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(DraftDetailCorrectionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<CorrectionDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                initSpinnerLoc();
                call.cancel();
            }
        });

    }

    public void saveSubmitAttendanceCorrection(String saveOrSubmit) {

        progressDialog = new ProgressDialog(DraftDetailCorrectionActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        valueCorrectionDetail.setID("");
        valueCorrectionDetail.setActualLogIn(txtLogInCDraftDetails.getText().toString());
        valueCorrectionDetail.setActualBreakEnd(txtBreakEndCDraftDetails.getText().toString());
        valueCorrectionDetail.setLocationID(txtLocationCDraftDetails.getText().toString());
        valueCorrectionDetail.setActualLogOut(txtLogOutCDraftDetails.getText().toString());
        valueCorrectionDetail.setActualOvertimeIn(txtOverTimeInCDraftDetails.getText().toString());
        valueCorrectionDetail.setActualOvertimeOut(txtOverTimeOutCDraftDetails.getText().toString());
        valueCorrectionDetail.setActualBreakStart(txtBreakStartCDraftDetails.getText().toString());
        valueCorrectionDetail.setNotes(txtNotesCDraftDetails.getText().toString());
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
//                Intent intent = new Intent(DraftDetailCorrectionActivity.this, CorrectionActivity.class);
//                startActivity(intent);
                finish();

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    public void setText(ReturnValue valueCorrectionDetail)
    {
        txtLogDateCDraftDetails.setText(valueCorrectionDetail.getLogDate());
        txtShiftCDraftDetails.setText(valueCorrectionDetail.getShift());
        txtLogInCDraftDetails.setText(valueCorrectionDetail.getActualLogIn() == null ? "": valueCorrectionDetail.getActualLogIn().substring(11,16));
        txtBreakEndCDraftDetails.setText(valueCorrectionDetail.getActualBreakEnd() == null? "":valueCorrectionDetail.getActualBreakEnd().substring(11,16));
        txtLogOutCDraftDetails.setText(valueCorrectionDetail.getActualLogOut() == null? "":valueCorrectionDetail.getActualLogOut().substring(11,16));
        txtOverTimeInCDraftDetails.setText(valueCorrectionDetail.getActualOvertimeIn() == null? "":valueCorrectionDetail.getActualOvertimeIn().substring(11,16));
        txtOverTimeOutCDraftDetails.setText(valueCorrectionDetail.getActualOvertimeOut() == null? "":valueCorrectionDetail.getActualOvertimeOut().substring(11,16));
        //spnLocationCDraftDetails=(Spinner)findViewById(R.id.spnLocationCDraftDetails);
        txtBreakStartCDraftDetails.setText(valueCorrectionDetail.getActualBreakStart() == null? "":valueCorrectionDetail.getActualBreakEnd().substring(11,16));
        txtNotesCDraftDetails.setText(valueCorrectionDetail.getNotes() == null? "":valueCorrectionDetail.getNotes());


        progressDialog.dismiss();
    }

    public void initSpinnerLoc() {
        listReturnValue= new ArrayList<>();
        com.example.android.starbridges.model.OLocation.ReturnValue returnValue=new com.example.android.starbridges.model.OLocation.ReturnValue();
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

                    setupSpinner();

                } else {

                    Toast.makeText(DraftDetailCorrectionActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OLocation> call, Throwable t) {
                Toast.makeText(DraftDetailCorrectionActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void setupSpinner()
    {
        int spinnerIdSelected=0;
        if(locationId!=null)
        {
            for(com.example.android.starbridges.model.OLocation.ReturnValue x: listReturnValue)
            {
                if(x.getID().equals(locationId))
                    break;
                spinnerIdSelected++;
            }
        }

        ArrayAdapter<com.example.android.starbridges.model.OLocation.ReturnValue> adapter = new ArrayAdapter<com.example.android.starbridges.model.OLocation.ReturnValue>(DraftDetailCorrectionActivity.this,
                android.R.layout.simple_spinner_item, listReturnValue);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLocationCDraftDetails.setAdapter(adapter);

        spnLocationCDraftDetails.setSelection(spinnerIdSelected);
    }
}
