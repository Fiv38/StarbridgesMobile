package com.example.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.HistoryAdapter;
import com.example.android.starbridges.model.history.History;
import com.example.android.starbridges.model.history.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartEndDayActivity extends AppCompatActivity {

    private TextView mDateView, mTimeView;
    private Button mShowDetail;
    private RecyclerView recyclerView;
    private APIInterfaceRest apiInterface;
    private ProgressDialog progressDialog;
    private List<ReturnValue> value;
    private HistoryAdapter viewAdapter;
    private String dateString, dateString2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_end_day);
        this.setTitle("Attendance");
        long date = System.currentTimeMillis();
        mDateView = (TextView) findViewById(R.id.txt_date);
        mTimeView = (TextClock) findViewById(R.id.txt_time);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
        dateString = sdf.format(date);
        dateString2 = sdf2.format(date);
        mDateView.setText(dateString);

        recyclerView = (RecyclerView) findViewById(R.id.StartDayList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mShowDetail = (Button) findViewById(R.id.btn_show_detail);
        mShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail();
            }
        });

        //getAttendaceLog(dateString2, dateString2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAttendaceLog(dateString2, dateString2);
    }

    public void showDetail() {
        String dateValue;
        String timeValue;
        String logType;

        timeValue = mTimeView.getText().toString();
        dateValue = mDateView.getText().toString();
        logType = mShowDetail.getText().toString();

        Intent intent = new Intent(this, StartEndDayDetailActivity.class);

        intent.putExtra("time", timeValue);
        intent.putExtra("date", dateValue);
        intent.putExtra("logType", logType);
        startActivity(intent);
    }

    public void getAttendaceLog(String DateFrom, String DateTo) {
        apiInterface = APIClient.getHistory(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(StartEndDayActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        // khusus logType di hardcode -> LogType -> Start Day
        Call<History> call3 = apiInterface.getHistory(DateFrom, DateTo);
        call3.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                progressDialog.dismiss();
                History data = response.body();
                if (data != null && data.getIsSucceed()) {
                    if (data.getReturnValue().size() > 0) {
                        mShowDetail.setText("End Day");
                    }
                    viewAdapter = new HistoryAdapter(StartEndDayActivity.this, data.getReturnValue());
                    recyclerView.setAdapter(viewAdapter);
                } else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(StartEndDayActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(StartEndDayActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
