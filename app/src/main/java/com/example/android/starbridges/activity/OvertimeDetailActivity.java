package com.example.android.starbridges.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.android.starbridges.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OvertimeDetailActivity extends AppCompatActivity {


    EditText reqDate,ovStart,ovEnd,notes;
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabelDate();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtime_detail);
        reqDate=(EditText)findViewById(R.id.txt_req_date);
        ovStart = (EditText)findViewById(R.id.txt_req_ov_start);
        ovEnd = (EditText)findViewById(R.id.txt_req_ov_end);
        setTitle("Overtime Request");


        reqDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OvertimeDetailActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ovStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(OvertimeDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ovStart.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();


            }
        });

        ovEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(OvertimeDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ovEnd.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();


            }
        });
    }





    private void updateLabelDate() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        reqDate.setText(sdf.format(myCalendar.getTime()));
    }

}
