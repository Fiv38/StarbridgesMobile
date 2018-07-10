package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.activity.LeaveRequestDetailActivity;
import id.co.indocyber.android.starbridges.model.listdraftleaverequest.ReturnValue;

/**
 * Created by user on 5/22/2018.
 */

public class ListDraftLeaveRequestAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> draftLeaveRequestList;
    public static List<String> listID = new ArrayList<>();

    public ListDraftLeaveRequestAdapter(Context context, List<ReturnValue> draftLeaveRequestList){
        super(context, R.layout.list_draft_leave_request, draftLeaveRequestList);

        this.context = context;
        this.draftLeaveRequestList = draftLeaveRequestList;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_draft_leave_request, parent, false);

        // get the text view from the rowView
        TextView requestType = (TextView) rowView.findViewById(R.id.requestTypeView);
        TextView leave = (TextView) rowView.findViewById(R.id.leaveView);
        TextView unitReduce = (TextView) rowView.findViewById(R.id.unitReduceView);
        TextView notes = (TextView) rowView.findViewById(R.id.notesView);
        Button btnEdit = (Button) rowView.findViewById(R.id.btnEditDraft);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);

        requestType.setText(draftLeaveRequestList.get(position).getLeaveType());
        leave.setText(dateFormat(draftLeaveRequestList.get(position).getStartLeave())  + " - " + dateFormat(draftLeaveRequestList.get(position).getEndLeave()) );
        unitReduce.setText(draftLeaveRequestList.get(position).getTotalUnit().toString());
        notes.setText(draftLeaveRequestList.get(position).getNotes());

        checkBox.setChecked(draftLeaveRequestList.get(position).getSelected());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LeaveRequestDetailActivity.class);
                intent.putExtra("ID", draftLeaveRequestList.get(position).getID());
                context.startActivity(intent);
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CompoundButton) v).isChecked()) {
                    listID.add(draftLeaveRequestList.get(position).getID()); // add to cb array
                    draftLeaveRequestList.get(position).setSelected(true);
                }
                else {
                    listID.remove(draftLeaveRequestList.get(position).getID());
                    draftLeaveRequestList.get(position).setSelected(false);
                }
            }
        });

        // return rowView
        return rowView;
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
