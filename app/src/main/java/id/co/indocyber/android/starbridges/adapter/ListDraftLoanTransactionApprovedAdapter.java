package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.model.ListDraftTransactionLoanApproved.ReturnValue;
import id.co.indocyber.android.starbridges.network.StringConverter;

public class ListDraftLoanTransactionApprovedAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> listTransaction;

    public ListDraftLoanTransactionApprovedAdapter(@NonNull Context context, List<ReturnValue> listTransaction) {
        super(context, R.layout.list_draft_loan_main_transaction, listTransaction);
        this.context = context;
        this.listTransaction = listTransaction;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_draft_loan_main_transaction, parent, false);

        // get the text view from the rowView
        TextView txtPolicyDraftLoan = (TextView) rowView.findViewById(R.id.txtPolicyDraftLoan);
        TextView txtStartDateDraftLoan = (TextView) rowView.findViewById(R.id.txtStartDateDraftLoan);
        TextView txtAmountDraftLoan = (TextView) rowView.findViewById(R.id.txtAmountDraftLoan);
        TextView txtCreditAmountDraftLoan = (TextView) rowView.findViewById(R.id.txtCreditAmountDraftLoan);

        txtPolicyDraftLoan.setText(listTransaction.get(position).getPolicy());

        StringConverter stringConverter=new StringConverter();
        try{
            txtStartDateDraftLoan.setText(stringConverter.dateFormat3(listTransaction.get(position).getStartDate()) );
            txtAmountDraftLoan.setText("Amount: "+ stringConverter.numberFormat(listTransaction.get(position).getAmount()+""));
            txtCreditAmountDraftLoan.setText("Credit Amount: "+stringConverter.numberFormat(listTransaction.get(position).getCreditAmount()+""));
        }
        catch (Exception e)
        {

        }




        return rowView;
//        View itemView = convertView;


    }
}