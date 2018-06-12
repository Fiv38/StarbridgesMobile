package com.example.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.ListShiftExchange.ReturnValue;
import com.example.android.starbridges.utility.SharedPreferenceUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 6/12/2018.
 */

public class ListShiftExchangeAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> shiftExchange;
    public static List<String> listID = new ArrayList<>();
    private SparseBooleanArray mSelectedItemsIds;
    LayoutInflater inflater;

    public ListShiftExchangeAdapter(@NonNull Context context, int resource,List<ReturnValue> shiftExchange) {
        super(context, resource);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.shiftExchange = shiftExchange;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView lblDecisionNumber;
        TextView lblEmployee;
        TextView lblDate;
        TextView lblShift;
        TextView lblNotes;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View itemView = convertView;
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_shift_exchange, null);
            // Locate the TextViews in listview_item.xml

            holder.lblDecisionNumber = (TextView) convertView.findViewById(R.id.lblDecisionNumberShiftExchange);
            holder.lblEmployee = (TextView) convertView.findViewById(R.id.lblEmployeeShiftExchange);
            holder.lblDate = (TextView) convertView.findViewById(R.id.lblDateShiftExchange);
            holder.lblShift = (TextView) convertView.findViewById(R.id.lblShiftShiftExchange);
            holder.lblNotes = (TextView) convertView.findViewById(R.id.lblNotesShiftExchange);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String dateResult = "";
        try {
            Date result = df.parse(shiftExchange.get(position).getDate());
            dateResult = sdf.format(result);
        } catch (Exception e) {

        }

        holder.lblDate.setText(dateResult);

        holder.lblEmployee.setText(shiftExchange.get(position).getEmployeeName());
        holder.lblShift.setText(shiftExchange.get(position).getShift());
        holder.lblNotes.setText(shiftExchange.get(position).getNotes());
        holder.lblDecisionNumber.setText(shiftExchange.get(position).getDecisionNumber());

        return convertView;

    }

    @Override
    public void remove(ReturnValue object) {
        shiftExchange.remove(object);
        listID.add(object.getDecisionNumber());
        SharedPreferenceUtils.setSetting(context, "listID", listID.toString());
        notifyDataSetChanged();
    }

    public List<ReturnValue> getLstorder() {
        return shiftExchange;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
