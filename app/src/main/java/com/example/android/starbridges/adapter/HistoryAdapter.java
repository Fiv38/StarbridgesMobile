package com.example.android.starbridges.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.history.ReturnValue;

import java.util.List;

/**
 * Created by user on 5/15/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    TextView txtName, txtLogType,txtTime;
    private Context context;
    private List<ReturnValue> histories;


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
        ReturnValue value = histories.get(position);


        holder.txtName.setText(value.getLocationName());
        holder.txtLogType.setText(value.getLogType());
        holder.txtTime.setText(value.getLogTime());


    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName, txtLogType,txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.text_name);
            txtLogType = (TextView) itemView.findViewById(R.id.text_LogType);
            txtTime = (TextView) itemView.findViewById(R.id.text_time);
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
