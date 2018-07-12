package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

import id.co.indocyber.android.starbridges.model.ListAttendanceCorrection.ReturnValue;

public class CorrectionListAdapter extends ArrayAdapter<ReturnValue> {

    Context context;
    List<ReturnValue> lstorder = new ArrayList<ReturnValue>();
    LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;
    List<String> lstIdSelected= new ArrayList<>();


    public CorrectionListAdapter(@NonNull Context context, int resource, @NonNull List<ReturnValue> objects) {
        super(context, resource, objects);
        mSelectedItemsIds=new SparseBooleanArray();
        this.context = context;
        this.lstorder = objects;
        inflater=LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView txtLogDateCorrection;
        TextView txtStatusCorrection;
        TextView txtDayCorrection;
        TextView txtDayTypeCorrection;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View itemView = convertView;
        final CorrectionListAdapter.ViewHolder holder;

        if(convertView==null)
        {
            holder = new CorrectionListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.lst_correction2, null);
            // Locate the TextViews in listview_item.xml
            holder.txtLogDateCorrection = (TextView) convertView.findViewById(R.id.txtLogDateCorrection);
            holder.txtStatusCorrection = (TextView) convertView.findViewById(R.id.txtStatusCorrection);
            holder.txtDayCorrection = (TextView) convertView.findViewById(R.id.txtDayCorrection);
            holder.txtDayTypeCorrection = (TextView) convertView.findViewById(R.id.txtDayTypeCorrection);

            convertView.setTag(holder);
        }
        else
        {
            holder = (CorrectionListAdapter.ViewHolder) convertView.getTag();
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

        holder.txtDayTypeCorrection.setText(lstorder.get(position).getDayType());
//        holder.txtLogDateCorrection.setText(value.getLogDate());
        holder.txtStatusCorrection.setText(lstorder.get(position).getStatusCode());
        if(!lstorder.get(position).getStatusCode().matches("Normal")||!lstorder.get(position).getStatusCode().equals("Normal")||!lstorder.get(position).getStatusCode().matches(".*uti*."))
            holder.txtStatusCorrection.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
        else
            holder.txtStatusCorrection.setTextColor(ContextCompat.getColor(context, R.color.common_google_signin_btn_text_light));
        holder.txtLogDateCorrection.setText(dateResult);
        holder.txtDayCorrection.setText(lstorder.get(position).getDay());


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



}
