package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import id.co.indocyber.android.starbridges.activity.DraftDetailCorrectionActivity;
import id.co.indocyber.android.starbridges.model.ListDraftCorrection.ReturnValue;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;

public class DraftCorrectionAdapter extends RecyclerView.Adapter<DraftCorrectionAdapter.ViewHolder> {

    TextView txtLogDateCorrectionDraft, txtLogInCorrectoinDraft, txtLogOutCorrectionDraft;
    Button btnDetailCorrectionDraft, btnDeleteCorrectionDraft, btnDeleteCDrafts;
    private Context context;
    private List<ReturnValue> corrections;

    JSONArray jsonArrayIdSelected = new JSONArray();

    List<String> lstIdSelected= new ArrayList<>();

    public DraftCorrectionAdapter(Context context, List<ReturnValue> corrections) {

        this.context = context;
        this.corrections = corrections;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lst_correction_draft, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ReturnValue value = corrections.get(position);

        holder.txtLogDateCorrectionDraft.setText(value.getLogDate());
        holder.txtLogInCorrectoinDraft.setText(value.getActualLogIn());
        holder.txtLogOutCorrectionDraft.setText(value.getActualLogOut());

        holder.btnDetailCorrectionDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DraftDetailCorrectionActivity.class);
                intent.putExtra("id", value.getID());
                context.startActivity(intent);
            }
        });


//        holder.view.setBackgroundColor(value.isSelected() ? Color.CYAN : Color.WHITE);
        if(value.isSelected())
        {
            holder.view.setBackgroundColor(Color.CYAN);
            lstIdSelected.add(value.getID());
        }
        else
        {
            holder.view.setBackgroundColor(Color.WHITE);
            lstIdSelected.remove(new String(value.getID()));
        }

        holder.btnDeleteCorrectionDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value.setSelected(!value.isSelected());
//                holder.view.setBackgroundColor(value.isSelected() ? Color.CYAN : Color.WHITE);
                if(value.isSelected())
                {
                    holder.view.setBackgroundColor(Color.CYAN);
                    lstIdSelected.add(value.getID());
//                    Intent intent=new Intent(context, DraftCorrectionActivity.class);
//                    intent.putExtra("lstIdSelected", lstIdSelected.toString());
//                    context.startActivity(intent);

                }
                else
                {
                    holder.view.setBackgroundColor(Color.WHITE);
                    lstIdSelected.remove(new String(value.getID()));
//                    Intent intent=new Intent(context, DraftCorrectionActivity.class);
//                    intent.putExtra("lstIdSelected", lstIdSelected.toString());
//                    context.startActivity(intent);

                }

                Log.d("jsonArrayIdSelected ",lstIdSelected.toString());
                SharedPreferenceUtils.setSetting(context,"lstIdSelected", lstIdSelected.toString());

//                if(lstIdSelected.size()>0)
//                    holder.imgDeleteCDrafts.setVisibility(View.VISIBLE);
//                else
//                    holder.imgDeleteCDrafts.setVisibility(View.GONE);

//                Log.d("sizejsonarray ",jsonArray.length()+"");
//                if(jsonArray.length()>0)
//                    btnDeleteCDrafts.setVisibility(View.VISIBLE);
//                    Log.d("sizejsonarrayif ",jsonArray.length()+"");
//                    holder.btnDeleteCDrafts.setVisibility(View.VISIBLE);
//                else
//                    btnDeleteCDrafts.setVisibility(View.GONE);
//                    holder.btnDeleteCDrafts.setVisibility(View.GONE);
//                    Log.d("sizejsonarrayelse ",jsonArray.length()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return corrections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtLogDateCorrectionDraft, txtLogInCorrectoinDraft, txtLogOutCorrectionDraft;
        Button btnDetailCorrectionDraft, btnDeleteCorrectionDraft;
        ImageView imgDeleteCDrafts;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);

            txtLogDateCorrectionDraft = (TextView) itemView.findViewById(R.id.txtLogDateCorrectionDraft);
            txtLogInCorrectoinDraft = (TextView) itemView.findViewById(R.id.txtLogInCorrectionDraft);
            txtLogOutCorrectionDraft = (TextView) itemView.findViewById(R.id.txtLogOutCorrectionDraft);

            btnDetailCorrectionDraft = (Button) itemView.findViewById(R.id.btnDetailCorrectionDraft);
            btnDeleteCorrectionDraft = (Button) itemView.findViewById(R.id.btnDeleteCorrectionDraft);

            imgDeleteCDrafts=(ImageView) itemView.findViewById(R.id.imgDeleteCDrafts);

            view=itemView;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            final Intent intent=new Intent(context, CorrectionDetailActivity.class);
//            context.startActivity(intent);

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
