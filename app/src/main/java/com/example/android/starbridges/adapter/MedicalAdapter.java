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
import com.example.android.starbridges.model.listmedicalclaim.ReturnValue;

import java.util.List;

/**
 * Created by user on 6/4/2018.
 */

public class MedicalAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> medicalList;

    public MedicalAdapter(Context context, List<ReturnValue> medicalList ){
        super(context, R.layout.list_medical, medicalList);

        this.context = context;
        this.medicalList = medicalList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowView from inflater
        View rowView = inflater.inflate(R.layout.list_medical, parent, false);

        // get the text view from the rowView
        TextView decisionNumber = (TextView) rowView.findViewById(R.id.decisionNumberView);
        TextView policy = (TextView) rowView.findViewById(R.id.policyView);
        TextView family = (TextView) rowView.findViewById(R.id.familyView);
        TextView claim = (TextView) rowView.findViewById(R.id.claimView);
        TextView remainingBalance = (TextView) rowView.findViewById(R.id.remainingBalanceView);
        TextView reimbursement = (TextView) rowView.findViewById(R.id.reimbursementView);

        //decisionNumber.setText(medicalList.get(position).getD);
        policy.setText(medicalList.get(position).getPolicyName());
        family.setText(medicalList.get(position).getFamily());
        claim.setText(medicalList.get(position).getClaim());
        //remainingBalance.setText(medicalList.get(position).getre);
        reimbursement.setText(medicalList.get(position).getReimbursement());

        // return rowView
        return rowView;
    }
}
