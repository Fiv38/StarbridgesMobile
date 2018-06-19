package com.example.android.starbridges.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.activity.LeaveRequestDetailActivity;
import com.example.android.starbridges.model.listdraftleaverequest.ReturnValue;
import com.example.android.starbridges.utility.SharedPreferenceUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/22/2018.
 */

public class ListDraftLeaveRequestAdapter2 extends ArrayAdapter<ReturnValue> {
    private Context context;
    private List<ReturnValue> draftLeaveRequestList = new ArrayList<>();
    private List<String> listID = new ArrayList<>();
    private LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;


    public ListDraftLeaveRequestAdapter2(@NonNull Context context, int resource, @NonNull List<ReturnValue> objects) {
        super(context, resource, objects);
        this.context = context;
        this.draftLeaveRequestList = objects;
        mSelectedItemsIds = new SparseBooleanArray();
        inflater = LayoutInflater.from(context);
    }
    private class ViewHolder {
        TextView requestType;
        TextView leave;
        TextView unitReduce;
        TextView note;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_draft_leave_request2,null);
            holder.requestType = (TextView) convertView.findViewById(R.id.requestTypeView2);
            holder.leave = (TextView) convertView.findViewById(R.id.leaveView2);
//            holder.unitReduce= (TextView) convertView.findViewById(R.id.unitReduceView2);
            holder.note= (TextView) convertView.findViewById(R.id.notesView2);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.requestType.setText(draftLeaveRequestList.get(position).getLeaveType());
        holder.leave.setText(dateFormat(draftLeaveRequestList.get(position).getStartLeave())  + " - " + dateFormat(draftLeaveRequestList.get(position).getEndLeave()) );
//        holder.unitReduce.setText(draftLeaveRequestList.get(position).getTotalUnit().toString());
        holder.note.setText(draftLeaveRequestList.get(position).getNotes());

        return convertView;
    }

    public String dateFormat(String dateString)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Date date1;
        String result;
        try{
            date1=df.parse(dateString);
            result=sdf.format(date1);
        }catch (Exception e)
        {
            result="";
        }
        return result;
    }


    @Override
    public void remove(@Nullable ReturnValue object) {
        draftLeaveRequestList.remove(object);
        listID.add(object.getID());
        SharedPreferenceUtils.setSetting(context,"lstIdSelected", listID.toString());
        notifyDataSetChanged();
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
