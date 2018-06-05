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
import com.example.android.starbridges.activity.LeaveCancelationDetailActivity;
import com.example.android.starbridges.model.ListDraftLeaveCancelation.ReturnValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListDraftLeaveCancelationAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> draftLeaveCancelationList;
    public static List<String> listID = new ArrayList<>();

    public ListDraftLeaveCancelationAdapter(Context context, List<ReturnValue> draftLeaveCancelationList){
        super(context, R.layout.list_draft_leave_cancelation, draftLeaveCancelationList);

        this.context = context;
        this.draftLeaveCancelationList = draftLeaveCancelationList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_draft_leave_cancelation, parent, false);

        // get the text view from the rowView
        TextView txtRequestTypeDraftCancelation = (TextView) rowView.findViewById(R.id.txtRequestTypeDraftCancelation);
        TextView txtLeaveDraftCancelation = (TextView) rowView.findViewById(R.id.txtLeaveDraftCancelation);
        TextView txtCancelationDraftCancelation = (TextView) rowView.findViewById(R.id.txtCancelationDraftCancelation);
        TextView txtAdditionalUnitDraftCancelation = (TextView) rowView.findViewById(R.id.txtAdditionalUnitDraftCancelation);
        TextView txtNotesDraftCancelation = (TextView) rowView.findViewById(R.id.txtNotesDraftCancelation);
        Button btnEditDraftCanceltion = (Button) rowView.findViewById(R.id.btnEditDraftCanceltion);
        CheckBox chcDraftCancelation = (CheckBox) rowView.findViewById(R.id.chcDraftCancelation);

        txtRequestTypeDraftCancelation.setText(draftLeaveCancelationList.get(position).getRequestType());
        txtLeaveDraftCancelation.setText( dateFormat(draftLeaveCancelationList.get(position).getRequestFrom())  + " - " + dateFormat(draftLeaveCancelationList.get(position).getRequestTo()));
        txtCancelationDraftCancelation.setText( dateFormat(draftLeaveCancelationList.get(position).getCancelFrom())  + " - " + dateFormat( draftLeaveCancelationList.get(position).getCancelTo()));
        txtAdditionalUnitDraftCancelation.setText(draftLeaveCancelationList.get(position).getAdditionalBalance()+"");
        txtNotesDraftCancelation.setText(draftLeaveCancelationList.get(position).getNotes());

        chcDraftCancelation.setChecked(draftLeaveCancelationList.get(position).getSelected());

        btnEditDraftCanceltion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LeaveCancelationDetailActivity.class);
                intent.putExtra("id", draftLeaveCancelationList.get(position).getID());
                context.startActivity(intent);
            }
        });

        chcDraftCancelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CompoundButton) v).isChecked())
                {
                    listID.add(draftLeaveCancelationList.get(position).getID()); // add to cb array
                    draftLeaveCancelationList.get(position).setSelected(true);
                }
                else
                {
                    listID.remove(draftLeaveCancelationList.get(position).getID());
                    draftLeaveCancelationList.get(position).setSelected(false);
                }

            }
        });

        // return rowView
        return rowView;
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
}
