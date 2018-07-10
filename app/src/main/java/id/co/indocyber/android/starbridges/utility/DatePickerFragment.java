package id.co.indocyber.android.starbridges.utility;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import id.co.indocyber.android.starbridges.activity.LeaveCancelationDetailActivity;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText editText;
    Date date;
    int dateBetween;

    public DatePickerFragment()
    {}

    @SuppressLint("ValidFragment")
    public DatePickerFragment(EditText editText, Date date, int dateBetween) {
        this.editText = editText;
        this.date = date;
        this.dateBetween = dateBetween;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog a = new DatePickerDialog(getActivity(),this,year,month,day);
//        return  new DatePickerDialog(getActivity(),this,year,month,day);
        c.add(Calendar.DATE,dateBetween);
        a.getDatePicker().setMaxDate(c.getTimeInMillis());
        c.add(Calendar.DATE,-dateBetween);
        a.getDatePicker().setMinDate(c.getTimeInMillis());
        return a;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int mont, int day) {
        LeaveCancelationDetailActivity activity = (LeaveCancelationDetailActivity) getActivity();
        activity.processDatePickerResult(year,mont,day,editText);
    }
}
