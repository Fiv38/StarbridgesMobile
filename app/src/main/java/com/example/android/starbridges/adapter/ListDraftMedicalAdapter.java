package com.example.android.starbridges.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
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
import com.example.android.starbridges.utility.SharedPreferenceUtils;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
