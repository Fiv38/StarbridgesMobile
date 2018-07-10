package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.model.ListDraftReimbursement.ReturnValue;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;

public class ListDraftReimbursementAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> draftLeaveReimbursement;
    public static List<String> listID = new ArrayList<>();
    LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;
    List<String> lstIdSelected= new ArrayList<>();


    public ListDraftReimbursementAdapter(Context context, List<ReturnValue> draftLeaveReimbursement){
        super(context, R.layout.list_draft_leave_cancelation, draftLeaveReimbursement);

        mSelectedItemsIds=new SparseBooleanArray();
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.draftLeaveReimbursement = draftLeaveReimbursement;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_draft_reimburse, parent, false);

        // get the text view from the rowView
        TextView txtDesciptionDraftReimburse = (TextView) rowView.findViewById(R.id.txtDesciptionDraftReimburse);
        TextView txtAmountDraftReimburse = (TextView) rowView.findViewById(R.id.txtAmountDraftReimburse);
        TextView txtTypeDraftReimburse = (TextView) rowView.findViewById(R.id.txtTypeDraftReimburse);
        TextView txtTransactionDateDraftReimburse = (TextView) rowView.findViewById(R.id.txtTransactionDateDraftReimburse);

        txtDesciptionDraftReimburse.setText(draftLeaveReimbursement.get(position).getDescription());
        txtAmountDraftReimburse.setText( draftLeaveReimbursement.get(position).getAmount());
        txtTypeDraftReimburse.setText( draftLeaveReimbursement.get(position).getType());
        txtTransactionDateDraftReimburse.setText(dateFormat(draftLeaveReimbursement.get(position).getTransactionDate()) );

        // return rowView
        return rowView;
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


    @Override
    public void remove(ReturnValue object) {
        draftLeaveReimbursement.remove(object);
        lstIdSelected.add(object.getID());
        SharedPreferenceUtils.setSetting(context,"lstIdSelected", lstIdSelected.toString());
        notifyDataSetChanged();
    }

    public List<ReturnValue> getLstorder() {
        return draftLeaveReimbursement;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

}

