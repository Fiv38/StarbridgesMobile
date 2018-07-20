package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.model.ListLoanHistory.ReturnValue;
import id.co.indocyber.android.starbridges.network.StringConverter;

public class LoanHistoryAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> listTransaction;

    public LoanHistoryAdapter(@NonNull Context context, List<ReturnValue> listTransaction) {
        super(context, R.layout.list_loan_history, listTransaction);
        this.context = context;
        this.listTransaction = listTransaction;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_loan_history, parent, false);

        // get the text view from the rowView
        TextView txtDateHistoryLoan = (TextView) rowView.findViewById(R.id.txtDateHistoryLoan);
        TextView txtActivityHistoryLoan = (TextView) rowView.findViewById(R.id.txtActivityHistoryLoan);
        TextView txtAmountHistoryLoan = (TextView) rowView.findViewById(R.id.txtAmountHistoryLoan);
        TextView txtLoanHistoryLoan = (TextView) rowView.findViewById(R.id.txtLoanHistoryLoan);

        txtDateHistoryLoan.setText(dateFormat(listTransaction.get(position).getDate()));
        txtActivityHistoryLoan.setText(listTransaction.get(position).getActivity());

        StringConverter stringConverter=new StringConverter();

        txtAmountHistoryLoan.setText("Amount: "+ stringConverter.numberFormat(listTransaction.get(position).getAmount()+""));
        txtLoanHistoryLoan.setText("Loan: "+stringConverter.numberFormat(listTransaction.get(position).getLoan()+""));

        return rowView;
//        View itemView = convertView;


    }

    public String dateFormat(String dateInput)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String dateResult = "";
        try{
            Date result =  df.parse(dateInput);
            dateResult=sdf.format(result);
        }catch (Exception e)
        {

        }

        return dateResult;
    }
}