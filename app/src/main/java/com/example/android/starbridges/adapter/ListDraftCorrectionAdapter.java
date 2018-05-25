package com.example.android.starbridges.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.activity.DraftCorrectionListActivity;
import com.example.android.starbridges.activity.DraftDetailCorrectionActivity;
import com.example.android.starbridges.model.ListDraftCorrection.ListDraftCorrection;
import com.example.android.starbridges.model.ListDraftCorrection.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;
import com.example.android.starbridges.utility.SharedPreferenceUtils;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftCorrectionAdapter extends ArrayAdapter<ReturnValue> {

    Context context;
    List<ReturnValue> lstorder = new ArrayList<ReturnValue>();
    LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;
    List<String> lstIdSelected= new ArrayList<>();


    public ListDraftCorrectionAdapter(@NonNull Context context, int resource, @NonNull List<ReturnValue> objects) {
        super(context, resource, objects);
        mSelectedItemsIds=new SparseBooleanArray();
        this.context = context;
        this.lstorder = objects;
        inflater=LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView txtLogDateCorrectionDraft;
        TextView txtLogInCorrectoinDraft;
        TextView txtLogOutCorrectionDraft;

        Button btnDetailCorrectionDraft;
        Button btnDeleteCorrectionDraft;

        CardView crvCDraftAdapter;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View itemView = convertView;
        final ViewHolder holder;

        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.lst_correction_draft3, null);
            // Locate the TextViews in listview_item.xml
            holder.txtLogDateCorrectionDraft = (TextView) convertView.findViewById(R.id.txtLogDateCorrectionDraft);

            holder.txtLogInCorrectoinDraft = (TextView) convertView.findViewById(R.id.txtLogInCorrectionDraft);
            holder.txtLogOutCorrectionDraft = (TextView) convertView.findViewById(R.id.txtLogOutCorrectionDraft);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String dateResult = "";
        try{
            Date result =  df.parse(lstorder.get(position).getLogDate());
            dateResult=sdf.format(result);
        }catch (Exception e)
        {

        }

        holder.txtLogDateCorrectionDraft.setText(dateResult);

        holder.txtLogInCorrectoinDraft.setText(lstorder.get(position).getActualLogIn());
        holder.txtLogOutCorrectionDraft.setText(lstorder.get(position).getActualLogOut());


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
        lstorder.remove(object);
        lstIdSelected.add(object.getID());
        SharedPreferenceUtils.setSetting(context,"lstIdSelected", lstIdSelected.toString());
        notifyDataSetChanged();
    }

    public List<ReturnValue> getLstorder() {
        return lstorder;
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
