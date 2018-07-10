package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.indocyber.android.starbridges.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.model.Reimbursement.ReturnValue;

public class ReimburseAdapter extends ArrayAdapter<ReturnValue> {

    Context context;
    List<ReturnValue> lstorder = new ArrayList<ReturnValue>();
    LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;
    List<String> lstIdSelected= new ArrayList<>();


    public ReimburseAdapter(@NonNull Context context, int resource, @NonNull List<ReturnValue> objects) {
        super(context, resource, objects);
        mSelectedItemsIds=new SparseBooleanArray();
        this.context = context;
        this.lstorder = objects;
        inflater=LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView txtDecisionNumberReimburse, txtDescriptionReimburse, txtAmountReimburse, txtTypeReimburse;
        TextView txtTransactionDateReimburse;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View itemView = convertView;
        final ReimburseAdapter.ViewHolder holder;

        if(convertView==null)
        {
            holder = new ReimburseAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.list_reimburse, null);
            // Locate the TextViews in listview_item.xml
            holder.txtDecisionNumberReimburse = (TextView) convertView.findViewById(R.id.txtDecisionNumberReimburse);
            holder.txtDescriptionReimburse = (TextView) convertView.findViewById(R.id.txtDescriptionReimburse);
            holder.txtAmountReimburse = (TextView) convertView.findViewById(R.id.txtAmountReimburse);
            holder.txtTypeReimburse = (TextView) convertView.findViewById(R.id.txtTypeReimburse);
            holder.txtTransactionDateReimburse = (TextView) convertView.findViewById(R.id.txtTransactionDateReimburse);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ReimburseAdapter.ViewHolder) convertView.getTag();
        }

        holder.txtDecisionNumberReimburse.setText(lstorder.get(position).getDecisionNumber());
        holder.txtDescriptionReimburse.setText(lstorder.get(position).getDescription());
        holder.txtAmountReimburse.setText( lstorder.get(position).getAmount() );
        holder.txtTypeReimburse.setText( lstorder.get(position).getType() );
        holder.txtTransactionDateReimburse.setText(dateFormat(lstorder.get(position).getTransactionDate()));

        return convertView;
    }

    public String dateFormat(String dateString)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
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

