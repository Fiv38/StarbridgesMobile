package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import java.util.List;

import id.co.indocyber.android.starbridges.model.listmedicalclaim.ReturnValue;

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
        //TextView remainingBalance = (TextView) rowView.findViewById(R.id.remainingBalanceView);
        TextView reimbursement = (TextView) rowView.findViewById(R.id.reimbursementView);

        decisionNumber.setText(medicalList.get(position).getDecisionNumber());
        policy.setText(medicalList.get(position).getPolicyName());
        family.setText(medicalList.get(position).getFamily());
        claim.setText("Claim: "+medicalList.get(position).getClaim());
        //remainingBalance.setText(medicalList.get(position).get);
        reimbursement.setText("Reimburse: "+medicalList.get(position).getReimbursement());

        // return rowView
        return rowView;
    }
}
