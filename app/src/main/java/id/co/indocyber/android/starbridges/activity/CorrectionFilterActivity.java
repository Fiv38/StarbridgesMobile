package id.co.indocyber.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.co.indocyber.android.starbridges.R;

public class CorrectionFilterActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_correction_filter);

        setTitle("Filter History");

        mFromDate = (EditText) findViewById(R.id.txt_from_date_correction);
        mToDate = (EditText) findViewById(R.id.txt_to_date_correction);

        mFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CorrectionFilterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CorrectionFilterActivity.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mShowHistory = (Button) findViewById(R.id.btn_show_correction);
        mShowHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date fromDate = new Date();
                Date toDate = new Date();

                DateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
                try{
                    fromDate=sdf.parse(mFromDate.getText().toString());
                    toDate= sdf.parse(mToDate.getText().toString());
                }
                catch (Exception e)
                {

                }

                if(fromDate.after(toDate))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CorrectionFilterActivity.this);
                    alert.setTitle("Please make sure From is lower or equals To") ;
                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    alert.show();
                }
                else
                    ShowCorrection();
            }
        });

        Calendar aCalendar = Calendar.getInstance();
        // add -1 month to current month
        aCalendar.add(Calendar.MONTH, -1);
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

    private void ShowCorrection() {
        Intent intent = new Intent(this, CorrectionListActivity.class);
        intent.putExtra("from", mFromDate.getText().toString());
        intent.putExtra("to", mToDate.getText().toString());
        startActivity(intent);
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
