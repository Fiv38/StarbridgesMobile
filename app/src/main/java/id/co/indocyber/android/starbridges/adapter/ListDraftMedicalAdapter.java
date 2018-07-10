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

import java.util.ArrayList;
import java.util.List;

import id.co.indocyber.android.starbridges.model.listdraftmedical.ReturnValue;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;

/**
 * Created by user on 6/4/2018.
 */

public class ListDraftMedicalAdapter extends ArrayAdapter<ReturnValue> {

    private final Context context;
    private final List<ReturnValue> draftMedicalList;
    public static List<String> listID = new ArrayList<>();
    private SparseBooleanArray mSelectedItemsIds;
    LayoutInflater inflater;

    public ListDraftMedicalAdapter(Context context, List<ReturnValue> draftMedicalList) {
        super(context, R.layout.list_draft_medical, draftMedicalList);

        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.draftMedicalList = draftMedicalList;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView lblPolicy;
        TextView lblFamily;
        TextView lblClaim;
        TextView lblReimburse;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ListDraftMedicalAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new ListDraftMedicalAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.list_draft_medical, null);
            // Locate the TextViews in listview_item.xml

            holder.lblPolicy = (TextView) convertView.findViewById(R.id.policyView);
            holder.lblFamily = (TextView) convertView.findViewById(R.id.familyView);
            holder.lblClaim = (TextView) convertView.findViewById(R.id.claimView);
            holder.lblReimburse = (TextView) convertView.findViewById(R.id.reimbursementView);

            convertView.setTag(holder);
        } else {
            holder = (ListDraftMedicalAdapter.ViewHolder) convertView.getTag();
        }


        holder.lblPolicy.setText(draftMedicalList.get(position).getPolicyName());
        holder.lblFamily.setText(draftMedicalList.get(position).getFamily());
        holder.lblClaim.setText("Claim: " + draftMedicalList.get(position).getClaim());
        holder.lblReimburse.setText("Reimburse: " + draftMedicalList.get(position).getReimbursement());

        return convertView;

    }

    @Override
    public void remove(ReturnValue object) {
        draftMedicalList.remove(object);
        listID.add(object.getID());
        SharedPreferenceUtils.setSetting(context, "listID", listID.toString());
        notifyDataSetChanged();
    }

    public List<ReturnValue> getLstorder() {
        return draftMedicalList;
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
