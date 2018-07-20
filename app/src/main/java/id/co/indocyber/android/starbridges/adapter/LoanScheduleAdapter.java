package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.model.ListLoanSchedule.ReturnValue;
import id.co.indocyber.android.starbridges.network.StringConverter;

public class LoanScheduleAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> listTransaction;

    public LoanScheduleAdapter(@NonNull Context context, List<ReturnValue> listTransaction) {
        super(context, R.layout.list_loan_schedule, listTransaction);
        this.context = context;
        this.listTransaction = listTransaction;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_loan_schedule, parent, false);

        // get the text view from the rowView
        TextView txtInstalmentLoanSchedule = (TextView) rowView.findViewById(R.id.txtInstalmentLoanSchedule);
        TextView txtAmountLoanSchedule = (TextView) rowView.findViewById(R.id.txtAmountLoanSchedule);
        TextView txtProcessStepLoanSchedule = (TextView) rowView.findViewById(R.id.txtProcessStepLoanSchedule);

        StringConverter stringConverter=new StringConverter();

        txtInstalmentLoanSchedule.setText("Installment: "+listTransaction.get(position).getInstallment()+"");
        txtAmountLoanSchedule.setText("Amount: "+ stringConverter.numberFormat(listTransaction.get(position).getAmount()+""));
        txtProcessStepLoanSchedule.setText(listTransaction.get(position).getProcessStep());

        return rowView;
//        View itemView = convertView;


    }
}