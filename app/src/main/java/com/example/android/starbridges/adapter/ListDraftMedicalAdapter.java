package com.example.android.starbridges.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.activity.MedicalClaimDetailActivity;
import com.example.android.starbridges.model.listdraftmedical.ReturnValue;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/4/2018.
 */

public class ListDraftMedicalAdapter extends ArrayAdapter<ReturnValue> {

    private final Context context;
    private final List<ReturnValue> draftMedicalList;
    public static List<String> listID = new ArrayList<>();

    public ListDraftMedicalAdapter(Context context, List<ReturnValue> draftMedicalList){
        super(context, R.layout.list_draft_medical, draftMedicalList);

        this.context = context;
        this.draftMedicalList = draftMedicalList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowview = inflater.inflate(R.layout.list_draft_medical, parent, false);

        // get the text view from the rowView
        TextView policy = (TextView) rowview.findViewById(R.id.policyView);
        TextView family = (TextView) rowview.findViewById(R.id.familyView);
        TextView claim = (TextView) rowview.findViewById(R.id.claimView);
        TextView reimbursement = (TextView) rowview.findViewById(R.id.reimbursementView);
        Button btnEdit = (Button) rowview.findViewById(R.id.btnEditDraft);
        CheckBox checkBox = (CheckBox) rowview.findViewById(R.id.checkBox);

        policy.setText(draftMedicalList.get(position).getPolicyName());
        family.setText(draftMedicalList.get(position).getFamily());
        claim.setText(draftMedicalList.get(position).getClaim());
        reimbursement.setText(draftMedicalList.get(position).getReimbursement());

        checkBox.setChecked(draftMedicalList.get(position).getSelected());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MedicalClaimDetailActivity.class);
                intent.putExtra("ID", draftMedicalList.get(position).getID());
                //context.startActivity(intent);
                ((Activity) context).startActivityForResult(intent,1000);
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CompoundButton) v).isChecked()) {
                    listID.add(draftMedicalList.get(position).getID());
                    draftMedicalList.get(position).setSelected(true);
                }
                else {
                    listID.remove(draftMedicalList.get(position).getID());
                    draftMedicalList.get(position).setSelected(false);
                }
            }
        });

        return rowview;

    }
}
