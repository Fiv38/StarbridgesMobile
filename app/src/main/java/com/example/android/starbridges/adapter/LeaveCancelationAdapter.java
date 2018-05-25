package com.example.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.ListLeaveCancelation.ReturnValue;
import com.example.android.starbridges.utility.SharedPreferenceUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveCancelationAdapter extends ArrayAdapter<com.example.android.starbridges.model.ListLeaveCancelation.ReturnValue> {

    Context context;
    List<com.example.android.starbridges.model.ListLeaveCancelation.ReturnValue> lstorder = new ArrayList<com.example.android.starbridges.model.ListLeaveCancelation.ReturnValue>();
    LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;
    List<String> lstIdSelected= new ArrayList<>();


    public LeaveCancelationAdapter(@NonNull Context context, int resource, @NonNull List<ReturnValue> objects) {
        super(context, resource, objects);
        mSelectedItemsIds=new SparseBooleanArray();
        this.context = context;
        this.lstorder = objects;
        inflater=LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView txtDecisionNumberCancelation, txtRequestTypeCancelation, txtCancelationCancelation;
        TextView txtLeaveCancelation, txtAdditionalUnitCancelation, txtNotesCancelation;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View itemView = convertView;
        final LeaveCancelationAdapter.ViewHolder holder;

        if(convertView==null)
        {
            holder = new LeaveCancelationAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.lst_cancelation, null);
            // Locate the TextViews in listview_item.xml
            holder.txtDecisionNumberCancelation = (TextView) convertView.findViewById(R.id.txtDecisionNumberCancelation);
            holder.txtRequestTypeCancelation = (TextView) convertView.findViewById(R.id.txtRequestTypeCancelation);
            holder.txtCancelationCancelation = (TextView) convertView.findViewById(R.id.txtCancelationCancelation);
            holder.txtLeaveCancelation = (TextView) convertView.findViewById(R.id.txtLeaveCancelation);
            holder.txtAdditionalUnitCancelation = (TextView) convertView.findViewById(R.id.txtAdditionalUnitCancelation);
            holder.txtNotesCancelation = (TextView) convertView.findViewById(R.id.txtNotesCancelation);

            convertView.setTag(holder);
        }
        else
        {
            holder = (LeaveCancelationAdapter.ViewHolder) convertView.getTag();
        }

//        holder.txtLogDateCorrectionDraft.setText(dateResult);
//
//        holder.txtLogInCorrectoinDraft.setText(lstorder.get(position).getActualLogIn());
//        holder.txtLogOutCorrectionDraft.setText(lstorder.get(position).getActualLogOut());

        holder.txtDecisionNumberCancelation.setText(lstorder.get(position).getDecisionNumber());
        holder.txtRequestTypeCancelation.setText(lstorder.get(position).getRequestType());
        holder.txtCancelationCancelation.setText(lstorder.get(position).getCancelFrom()+"-"+lstorder.get(position).getCancelTo());
        holder.txtLeaveCancelation.setText(lstorder.get(position).getRequestFrom()+"-"+lstorder.get(position).getRequestTo());
        holder.txtAdditionalUnitCancelation.setText(lstorder.get(position).getAdditionalUnit()+"");
        holder.txtNotesCancelation.setText(lstorder.get(position).getNotes());

        return convertView;

/*
        itemView = LayoutInflater.from(context).inflate(R.layout.lst_correction_draft2,parent,false);
        final ReturnValue value=lstorder.get(position);


        TextView txtLogDateCorrectionDraft = (TextView) itemView.findViewById(R.id.txtLogDateCorrectionDraft);
        TextView txtLogInCorrectoinDraft = (TextView) itemView.findViewById(R.id.txtLogInCorrectionDraft);
        TextView txtLogOutCorrectionDraft = (TextView) itemView.findViewById(R.id.txtLogOutCorrectionDraft);

        Button btnDetailCorrectionDraft = (Button) itemView.findViewById(R.id.btnDetailCorrectionDraft);
        Button btnDeleteCorrectionDraft = (Button) itemView.findViewById(R.id.btnDeleteCorrectionDraft);

        txtLogDateCorrectionDraft.setText(value.getLogDate());
        txtLogInCorrectoinDraft.setText(value.getActualLogIn());
        txtLogOutCorrectionDraft.setText(value.getActualLogOut());

        btnDetailCorrectionDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DraftDetailCorrectionActivity.class);
                intent.putExtra("id", value.getID());
                context.startActivity(intent);
            }
        });

        return itemView;
  */
    }


}
