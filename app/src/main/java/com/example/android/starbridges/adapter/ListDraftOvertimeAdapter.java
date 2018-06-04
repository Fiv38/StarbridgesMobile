package com.example.android.starbridges.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.activity.OvertimeDetailActivity;
import com.example.android.starbridges.model.ListDraftOvertime.ReturnValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListDraftOvertimeAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> draftOvertimeList;
    public static List<String> listID = new ArrayList<>();
    public ListDraftOvertimeAdapter(Context context, List<ReturnValue> draftOvertimeList){
        super(context, R.layout.list_draft_overtime, draftOvertimeList);

        this.context = context;
        this.draftOvertimeList = draftOvertimeList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_draft_overtime, parent, false);

        // get the text view from the rowView
        TextView dateDraft = (TextView) rowView.findViewById(R.id.DateDraftView);
        TextView startDraft = (TextView) rowView.findViewById(R.id.StartDraftView);
        TextView endDraft = (TextView) rowView.findViewById(R.id.EndDraftView);
        Button btnEdit = (Button) rowView.findViewById(R.id.btnEditDraft);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox_overtime);

        dateDraft.setText(dateFormat(draftOvertimeList.get(position).getOvertimeDate()) );
        startDraft.setText(draftOvertimeList.get(position).getStart().substring(11,16));
        endDraft.setText(draftOvertimeList.get(position).getEnd().substring(11,16));


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OvertimeDetailActivity.class);
                intent.putExtra("ID", draftOvertimeList.get(position).getID());
                context.startActivity(intent);
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CompoundButton) v).isChecked())
                    listID.add(draftOvertimeList.get(position).getID()); // add to cb array
                else
                    listID.remove(draftOvertimeList.get(position).getID());
            }
        });
        // return rowView
        return rowView;
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
}
