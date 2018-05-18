package com.example.android.starbridges.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.activity.CorrectionDetailActivity;
import com.example.android.starbridges.model.ListAttendaceCorrection.ReturnValue;

import java.util.List;

public class CorrectionAdapter extends RecyclerView.Adapter<CorrectionAdapter.ViewHolder> {

    TextView txtLogDateCorrection, txtStatusCorrection, txtDayCorrection, txtDayTypeCorrection, txtStatusCodeCorrection;
    Button btnDetailCorrection;
    private Context context;
    private List<ReturnValue> corrections;


    public CorrectionAdapter(Context context, List<ReturnValue> corrections) {

        this.context = context;
        this.corrections = corrections;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lst_correction, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReturnValue value = corrections.get(position);

        holder.txtStatusCodeCorrection.setText(value.getStatusCode());
        holder.txtDayTypeCorrection.setText(value.getDayType());
        holder.txtLogDateCorrection.setText(value.getLogDate());
        holder.txtStatusCorrection.setText(value.getStatusCode());
        holder.txtDayCorrection.setText(value.getDay());

        holder.btnDetailCorrection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CorrectionDetailActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return corrections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtLogDateCorrection, txtStatusCorrection, txtDayCorrection, txtDayTypeCorrection, txtStatusCodeCorrection;
        Button btnDetailCorrection;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDayCorrection=(TextView)itemView.findViewById(R.id.txtDayCorrection);
            txtStatusCorrection=(TextView)itemView.findViewById(R.id.txtStatusCorrection);
            txtLogDateCorrection=(TextView)itemView.findViewById(R.id.txtLogDateCorrection);
            txtDayTypeCorrection=(TextView)itemView.findViewById(R.id.txtDayTypeCorrection);
            txtStatusCodeCorrection=(TextView)itemView.findViewById(R.id.txtStatusCodeCorrection);
            btnDetailCorrection=(Button)itemView.findViewById(R.id.btnDetailCorrection);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent=new Intent(context, CorrectionDetailActivity.class);
            context.startActivity(intent);

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
