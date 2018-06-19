package com.example.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.ListShiftExchange.ReturnValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 6/12/2018.
 */

public class ListShiftExchangeAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> shiftExchange;


    public ListShiftExchangeAdapter(@NonNull Context context, List<ReturnValue> shiftExchange) {
        super(context, R.layout.list_shift_exchange,shiftExchange);
        this.context = context;
        this.shiftExchange = shiftExchange;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_shift_exchange, parent, false);

        // get the text view from the rowView
        TextView decisionNumber = (TextView) rowView.findViewById(R.id.lblDecisionNumberShiftExchange);
        TextView employee = (TextView) rowView.findViewById(R.id.lblEmployeeShiftExchange);
        TextView dateSE = (TextView) rowView.findViewById(R.id.lblDateShiftExchange);
        TextView shift = (TextView) rowView.findViewById(R.id.lblShiftShiftExchange);
        TextView notes = (TextView) rowView.findViewById(R.id.lblNotesShiftExchange);

        decisionNumber.setText(shiftExchange.get(position).getDecisionNumber());
        employee.setText(shiftExchange.get(position).getEmployeeName());
        dateSE.setText(shiftExchange.get(position).getDate());
        shift.setText(shiftExchange.get(position).getShift());
        notes.setText(shiftExchange.get(position).getNotes());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String dateResult = "";
        try {
            Date result = df.parse(shiftExchange.get(position).getDate());
            dateResult = sdf.format(result);
        } catch (Exception e) {

        }

        dateSE.setText(dateResult);

        // return rowView
        return rowView;
//        View itemView = convertView;


    }


}
