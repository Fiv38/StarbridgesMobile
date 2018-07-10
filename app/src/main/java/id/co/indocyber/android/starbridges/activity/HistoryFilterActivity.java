package id.co.indocyber.android.starbridges.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import id.co.indocyber.android.starbridges.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HistoryFilterActivity extends AppCompatActivity {
    private EditText mFromDate, mToDate;
    private Button mShowHistory;
    //private String sFrom, sTo;
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

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myCalendar.set(Calendar.YEAR, i);
            myCalendar.set(Calendar.MONTH, i1);
            myCalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateLabel2();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_filter);
        setTitle("Filter History");

        mFromDate = (EditText) findViewById(R.id.txt_from_date);
        mToDate = (EditText) findViewById(R.id.txt_to_date);

        mFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(HistoryFilterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(HistoryFilterActivity.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mShowHistory = (Button) findViewById(R.id.btn_show);
        mShowHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowHistory();
            }
        });

        Calendar aCalendar = Calendar.getInstance();
        // add -1 month to current month
        // set DATE to 1, so first date of previous month
        aCalendar.set(Calendar.DATE, 1);

        Date firstDateOfMonth = aCalendar.getTime();

        // set actual maximum date of previous month
        aCalendar.set(Calendar.DATE,     aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //read it
        Date dateOfMonth = aCalendar.getTime();

        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");


        mFromDate.setText(sdf.format(firstDateOfMonth));
        mToDate.setText(sdf.format(dateOfMonth));
    }

    private void ShowHistory() {
        Intent history = new Intent(this, HistoriesActivity.class);
        history.putExtra("from", mFromDate.getText().toString());
        history.putExtra("to", mToDate.getText().toString());
        startActivity(history);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mFromDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mToDate.setText(sdf.format(myCalendar.getTime()));
    }


}
