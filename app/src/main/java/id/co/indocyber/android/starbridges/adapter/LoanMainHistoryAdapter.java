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
import id.co.indocyber.android.starbridges.activity.LoanHistoryActivity;
import id.co.indocyber.android.starbridges.activity.LoanScheduleActivity;
import id.co.indocyber.android.starbridges.model.ListTransactionInformation.ReturnValue;
import id.co.indocyber.android.starbridges.network.StringConverter;

public class LoanMainHistoryAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> listTransaction;

    public LoanMainHistoryAdapter(@NonNull Context context, List<ReturnValue> listTransaction) {
        super(context, R.layout.list_loan_main_history, listTransaction);
        this.context = context;
        this.listTransaction = listTransaction;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_loan_main_history, parent, false);

        // get the text view from the rowView
        TextView txtNameLoanMainHistory = (TextView) rowView.findViewById(R.id.txtNameLoanMainHistory);
        TextView txtRemainingLoan = (TextView) rowView.findViewById(R.id.txtRemainingLoan);
        TextView txtRemainingInstallment = (TextView) rowView.findViewById(R.id.txtRemainingInstallment);
        Button btnScheduleLoan=(Button)rowView.findViewById(R.id.btnScheduleLoan);
        Button btnHistoryLoan=(Button)rowView.findViewById(R.id.btnHistoryLoan);

        txtNameLoanMainHistory.setText(listTransaction.get(position).getPolicyName());

        StringConverter stringConverter=new StringConverter();
        txtRemainingLoan.setText("Remaining Loan: "+ stringConverter.numberFormat(listTransaction.get(position).getRemainingLoan()));
        txtRemainingInstallment.setText("Remaining Installment: "+listTransaction.get(position).getRemainingInstallment());

        btnScheduleLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, LoanScheduleActivity.class);
                intent.putExtra("LoanBalanceId",listTransaction.get(position).getLoanBalanceID());
                intent.putExtra("PolicyName",listTransaction.get(position).getPolicyName());
                intent.putExtra("RemainingLoan",listTransaction.get(position).getRemainingLoan());
                context.startActivity(intent);
            }
        });

        btnHistoryLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, LoanHistoryActivity.class);
                intent.putExtra("LoanBalanceId",listTransaction.get(position).getLoanBalanceID());
                intent.putExtra("PolicyName",listTransaction.get(position).getPolicyName());
                intent.putExtra("RemainingLoan",listTransaction.get(position).getRemainingLoan());
                context.startActivity(intent);
            }
        });

        return rowView;
//        View itemView = convertView;


    }
}