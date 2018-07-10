package id.co.indocyber.android.starbridges.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import id.co.indocyber.android.starbridges.R;

import java.util.List;

import id.co.indocyber.android.starbridges.model.ListOvertime.ReturnValue;


public class ListOvertimeAdapter extends ArrayAdapter<ReturnValue> {
//public class ListOvertimeAdapter {
    private final Context context;
    private final List<ReturnValue> OvertimeList;

    public ListOvertimeAdapter(Context context, List<ReturnValue> OvertimeList ){
        super(context, R.layout.list_overtime, OvertimeList);

        this.context = context;
        this.OvertimeList = OvertimeList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // get rowview from inflater
        View rowView = inflater.inflate(R.layout.list_overtime2, parent, false);

        // get the text view from the rowView
        TextView decisionNumber = (TextView) rowView.findViewById(R.id.decisionNumberView2);
        TextView date = (TextView) rowView.findViewById(R.id.DateView2);
        TextView startOv = (TextView) rowView.findViewById(R.id.StartView2);
        TextView endOv = (TextView) rowView.findViewById(R.id.EndView2);
//        TextView approvedDate = (TextView) rowView.findViewById(R.id.approvedDateView);


        decisionNumber.setText( OvertimeList.get(position).getDecisionNumber());
        date.setText(OvertimeList.get(position).getOvertimeDate());
        startOv.setText(OvertimeList.get(position).getOvertimeStart());
        endOv.setText(OvertimeList.get(position).getOvertimeEnd());
//        approvedDate.setText(OvertimeList.get(position).getApprovedDate());

        // return rowView
        return rowView;
    }
}
