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

import id.co.indocyber.android.starbridges.model.ListLeaveCancelation.ReturnValue;

public class LeaveCancelationAdapter extends ArrayAdapter<ReturnValue> {

    Context context;
    List<ReturnValue> lstorder = new ArrayList<ReturnValue>();
    LayoutInflater inflater;
    private SparseBooleanArray mSelectedItemsIds;
    List<String> lstIdSelected= new ArrayList<>();


    public LeaveCancelationAdapter(@NonNull Context context, int resource, @NonNull List<ReturnValue> objects) {
        super(context, resource, objects);
        mSelectedItemsIds=new SparseBooleanArray();
        this.context = context;
        this.lstorder = objects;
        inflater=LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView txtDecisionNumberCancelation, txtRequestTypeCancelation, txtCancelationCancelation;
        TextView txtLeaveCancelation, txtAdditionalUnitCancelation, txtNotesCancelation;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View itemView = convertView;
        final LeaveCancelationAdapter.ViewHolder holder;

        if(convertView==null)
        {
            holder = new LeaveCancelationAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.lst_cancelation2, null);
            // Locate the TextViews in listview_item.xml
            holder.txtDecisionNumberCancelation = (TextView) convertView.findViewById(R.id.txtDecisionNumberCancelation);
            holder.txtRequestTypeCancelation = (TextView) convertView.findViewById(R.id.txtRequestTypeCancelation);
            holder.txtCancelationCancelation = (TextView) convertView.findViewById(R.id.txtCancelationCancelation);
            holder.txtLeaveCancelation = (TextView) convertView.findViewById(R.id.txtLeaveCancelation);
            holder.txtNotesCancelation = (TextView) convertView.findViewById(R.id.txtNotesCancelation);

            convertView.setTag(holder);
        }
        else
        {
            holder = (LeaveCancelationAdapter.ViewHolder) convertView.getTag();
        }

//        holder.txtLogDateCorrectionDraft.setText(dateResult);
//
//        holder.txtLogInCorrectoinDraft.setText(lstorder.get(position).getActualLogIn());
//        holder.txtLogOutCorrectionDraft.setText(lstorder.get(position).getActualLogOut());

        holder.txtDecisionNumberCancelation.setText(lstorder.get(position).getDecisionNumber());
        holder.txtRequestTypeCancelation.setText(lstorder.get(position).getRequestType());
        holder.txtCancelationCancelation.setText( dateFormat(lstorder.get(position).getCancelFrom()) +" - "+ dateFormat(lstorder.get(position).getCancelTo()) );
        holder.txtLeaveCancelation.setText( dateFormat(lstorder.get(position).getRequestFrom()) +" - "+ dateFormat(lstorder.get(position).getRequestTo()) );
        holder.txtNotesCancelation.setText(lstorder.get(position).getNotes());

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

    public String dateFormat(String dateString)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
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
