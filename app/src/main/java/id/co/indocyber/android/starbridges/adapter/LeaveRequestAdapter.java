package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.co.indocyber.android.starbridges.model.leaverequest.ReturnValue;

/**
 * Created by user on 5/25/2018.
 */

public class LeaveRequestAdapter extends ArrayAdapter<ReturnValue> {
    private final Context context;
    private final List<ReturnValue> leaveRequestList;

    public LeaveRequestAdapter(Context context, List<ReturnValue> leaveRequestList ){
        super(context, R.layout.list_leave_request, leaveRequestList);

        this.context = context;
        this.leaveRequestList = leaveRequestList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_leave_request2, parent, false);

        // get the text view from the rowView
        TextView decisionNumber = (TextView) rowView.findViewById(R.id.decisionNumberView2_2);
        TextView requestType = (TextView) rowView.findViewById(R.id.requestTypeView3_2);
        TextView leave = (TextView) rowView.findViewById(R.id.leaveView3_2);
//        TextView unitReduce = (TextView) rowView.findViewById(R.id.unitReduceView);
        TextView notes = (TextView) rowView.findViewById(R.id.notesView3_2);
//        TextView approvedDate = (TextView) rowView.findViewById(R.id.approvedDateView);

        decisionNumber.setText(leaveRequestList.get(position).getDecisionNumber());
        requestType.setText(leaveRequestList.get(position).getRequestType());
        leave.setText(dateFormat(leaveRequestList.get(position).getStartLeave()) + " - " + dateFormat(leaveRequestList.get(position).getEndLeave()) );
//        unitReduce.setText(leaveRequestList.get(position).getTotalUnit());
        notes.setText(leaveRequestList.get(position).getNotes());
//        approvedDate.setText(leaveRequestList.get(position).getApproveDate());

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
