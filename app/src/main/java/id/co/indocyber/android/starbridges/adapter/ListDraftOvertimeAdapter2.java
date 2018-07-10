package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.model.ListDraftOvertime.ReturnValue;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;

public class ListDraftOvertimeAdapter2 extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> draftOvertimeList;
    public static List<String> listID = new ArrayList<>();
    private LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;

    public ListDraftOvertimeAdapter2(@NonNull Context context, int resource, @NonNull List<ReturnValue> objects) {
        super(context, resource, objects);
        this.context = context;
        this.draftOvertimeList = objects;
        mSelectedItemsIds = new SparseBooleanArray();
        inflater = LayoutInflater.from(context);
    }
    private class ViewHolder{
        TextView dateDraft;
        TextView startDraft;
        TextView endDraft;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView!=null){
            holder =(ViewHolder) convertView.getTag();
        }else{
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_draft_overtime2,null);
            holder.dateDraft = (TextView) convertView.findViewById(R.id.DateDraftView2);
            holder.startDraft = (TextView) convertView.findViewById(R.id.StartDraftView2);
            holder.endDraft = (TextView) convertView.findViewById(R.id.EndDraftView2);
            convertView.setTag(holder);
        }
        holder.dateDraft.setText(dateFormat(draftOvertimeList.get(position).getOvertimeDate()));
        holder.startDraft.setText(draftOvertimeList.get(position).getStart().substring(11,16));
        holder.endDraft.setText(draftOvertimeList.get(position).getEnd().substring(11,16));
        return convertView;
    }

    public String dateFormat(String dateTime)
    {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String date = "";
        Date convertDate;
        try{
            convertDate =  df.parse(dateTime);
            date=sdf.format(convertDate);
        }catch (Exception e)
        {

        }

        return date;
    }
    @Override
    public void remove(@Nullable ReturnValue object) {
        draftOvertimeList.remove(object);
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
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
