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

import id.co.indocyber.android.starbridges.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.model.ListDraftShiftExchange.ReturnValue;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;

/**
 * Created by user on 6/7/2018.
 */

public class ListDraftShiftExchangeAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> draftShiftExchange;
    public static List<String> listID = new ArrayList<>();
    private SparseBooleanArray mSelectedItemsIds;
    LayoutInflater inflater;

    public ListDraftShiftExchangeAdapter(Context context, int resource, List<ReturnValue> draftShiftExchange) {
        super(context, resource, draftShiftExchange);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.draftShiftExchange = draftShiftExchange;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView lblEmployeeDraft;
        TextView lblDateDraft;
        TextView lblShiftDraft;
        TextView lblNotesDraft;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View itemView = convertView;
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_draft_shift_exchange, null);
            // Locate the TextViews in listview_item.xml

            holder.lblEmployeeDraft = (TextView) convertView.findViewById(R.id.lblEmployeeDraftShiftExchange);
            holder.lblDateDraft = (TextView) convertView.findViewById(R.id.lblDateDraftShiftExchange);
            holder.lblShiftDraft = (TextView) convertView.findViewById(R.id.lblShiftDraftShiftExchange);
            holder.lblNotesDraft = (TextView) convertView.findViewById(R.id.lblNotesDraftShiftExchange);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String dateResult = "";
        try {
            Date result = df.parse(draftShiftExchange.get(position).getDate());
            dateResult = sdf.format(result);
        } catch (Exception e) {

        }

        holder.lblDateDraft.setText(dateResult);

        holder.lblEmployeeDraft.setText(draftShiftExchange.get(position).getEmployeeName());
        holder.lblShiftDraft.setText(draftShiftExchange.get(position).getShift());
        holder.lblNotesDraft.setText(draftShiftExchange.get(position).getNotes());

        return convertView;

/*
        itemView = LayoutInflater.from(context).inflate(R.layout.lst_correction_draft2,parent,false);
        final ReturnValue value=lstorder.get(position);


        TextView txtLogDateCorrectionDraft = (TextView) itemView.findViewById(R.id.txtLogDateCorrectionDraft);
        TextView txtLogInCorrectoinDraft = (TextView) itemView.findViewById(R.id.txtLogInCorrectionDraft);
        TextView txtLogOutCorrectionDraft = (TextView) itemView.findViewById(R.id.txtLogOutCorrectionDraft);

        Button btnDetailCorrectionDraft = (Button) itemView.findViewById(R.id.btnDetailCorrectionDraft);
        Button btnDeleteCorrectionDraft = (Button) itemView.findViewById(R.id.btnDeleteCorrectionDraft);

        txtLogDateCorrectionDraft.setText(value.getLogDate());
        txtLogInCorrectoinDraft.setText(value.getActualLogIn());
        txtLogOutCorrectionDraft.setText(value.getActualLogOut());

        btnDetailCorrectionDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DraftDetailCorrectionActivity.class);
                intent.putExtra("id", value.getID());
                context.startActivity(intent);
            }
        });

        return itemView;
  */
    }


    @Override
    public void remove(ReturnValue object) {
        draftShiftExchange.remove(object);
        listID.add(object.getID());
        SharedPreferenceUtils.setSetting(context, "listID", listID.toString());
        notifyDataSetChanged();
    }

    public List<ReturnValue> getLstorder() {
        return draftShiftExchange;
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
