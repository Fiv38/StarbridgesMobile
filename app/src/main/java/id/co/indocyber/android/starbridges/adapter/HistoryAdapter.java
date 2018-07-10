package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.model.history.ReturnValue;

/**
 * Created by user on 5/15/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    TextView txtName, txtLogType,txtTime;
    private Context context;
    private List<ReturnValue> histories;
    CardView cardHistory;


    public HistoryAdapter(Context context, List<ReturnValue> histories) {

        this.context = context;
        this.histories = histories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_attendance, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReturnValue value = histories.get(position);


        holder.txtName.setText(value.getLocationName()+"");
        holder.txtLogType.setText(value.getLogType());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        DateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        String date = "";
        Date convertDate;
        try{
            convertDate =  df.parse(value.getLogDate());
            date=sdf.format(convertDate);
        }catch (Exception e)
        {

        }

        holder.txtTime.setText(date + " - "+ value.getLogTime().substring(11,16));

        holder.cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+value.getLatitude()+","+value.getLongitude());

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                context.startActivity(mapIntent);
                */
                Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?q=loc:" + value.getLatitude()+","+value.getLongitude() + " (unknown)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(mapIntent);
                }
            }
        });


    }
    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName, txtLogType,txtTime;
        CardView cardHistory;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.text_name);
            txtLogType = (TextView) itemView.findViewById(R.id.text_LogType);
            txtTime = (TextView) itemView.findViewById(R.id.text_time);
            cardHistory=(CardView)itemView.findViewById(R.id.cardHistory);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

//
//            Intent i = new Intent(context, UpdateActivity.class);
//            i.putExtra("npm", npm);
//            i.putExtra("nama", nama);
//            i.putExtra("kelas", kelas);
//            i.putExtra("sesi", sesi);
//            context.startActivity(i);
        }
    }
}
