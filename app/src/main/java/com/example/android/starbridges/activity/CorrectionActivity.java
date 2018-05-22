package com.example.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.CorrectionAdapter;
import com.example.android.starbridges.model.ListAttendanceCorrection.ListAttendanceCorrection;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CorrectionActivity extends AppCompatActivity {

    TextView txtNameCorrection, txtNIKCorrection;

    private RecyclerView recyclerView;

    private ProgressDialog progressDialog;
    private CorrectionAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction);
        setTitle("Attendance Correction");
        txtNameCorrection=(TextView)findViewById(R.id.txtNameCorrection);
        txtNIKCorrection=(TextView)findViewById(R.id.txtNIKCorrection);
        recyclerView=(RecyclerView)findViewById(R.id.rcyListCorrection);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        txtNameCorrection.setText(GlobalVar.getFullname());
        txtNIKCorrection.setText(GlobalVar.getNIK());

        Calendar aCalendar = Calendar.getInstance();
        // add -1 month to current month
        aCalendar.add(Calendar.MONTH, -1);
        // set DATE to 1, so first date of previous month
        aCalendar.set(Calendar.DATE, 1);

        Date firstDateOfPreviousMonth = aCalendar.getTime();

        // set actual maximum date of previous month
        aCalendar.set(Calendar.DATE,     aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //read it
        Date lastDateOfPreviousMonth = aCalendar.getTime();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        getAttendaceCorrectionLog(sdf.format(firstDateOfPreviousMonth), sdf.format(lastDateOfPreviousMonth));
    }


    public void getAttendaceCorrectionLog(String dateFrom, String dateTo) {

        progressDialog = new ProgressDialog(CorrectionActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getListAttendanceCorrection(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<ListAttendanceCorrection> call3 = apiInterface.getListAttendanceCorrection(dateFrom, dateTo);
        call3.enqueue(new Callback<ListAttendanceCorrection>() {
            @Override
            public void onResponse(Call<ListAttendanceCorrection> call, Response<ListAttendanceCorrection> response) {
                progressDialog.dismiss();
                ListAttendanceCorrection data = response.body();
                if (data != null && data.isIsSucceed()) {
                    viewAdapter = new CorrectionAdapter(CorrectionActivity.this, data.getReturnValue());
                    recyclerView.setAdapter(viewAdapter);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CorrectionActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(CorrectionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListAttendanceCorrection> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_correction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_item_one)
        {
            Intent intent = new Intent(CorrectionActivity.this, DraftCorrectionListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
