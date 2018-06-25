package com.example.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.ListOvertimeAdapter;
import com.example.android.starbridges.model.ListOvertime.Overtime;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;
import com.example.android.starbridges.utility.SessionManagement;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OvertimeActivity extends AppCompatActivity {

    // declare komp listView
    ListView listView;

    // declare adapter
    ListOvertimeAdapter adapter;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtime);
        setTitle("Overtime");

        // pass context and data to the custom adapter
        //adapter = new LeaveRequestAdapter(LeaveRequestActivity.this, generateData());

        // get listView from activity
        //listView = (ListView) findViewById(R.id.listLeaveRequest);

        // set listadapter
        //listView.setAdapter(adapter);

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String token_sp = user.get(SessionManagement.KEY_TOKEN);
        String fullName_sp = user.get(SessionManagement.KEY_FULLNAME);
        String nik_sp = user.get(SessionManagement.KEY_NIK);
        String employee_sp=user.get(SessionManagement.KEY_EMPLOYEE_ID);
        GlobalVar.setToken(token_sp);
        GlobalVar.setFullname(fullName_sp);
        GlobalVar.setNIK(nik_sp);
        GlobalVar.setEmployeeId(employee_sp);

        getListLeaveRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // pass context and data to the custom adapter
        //adapter = new LeaveRequestAdapter(LeaveRequestActivity.this, generateData());

        // set listadapter
        //listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // add new leave request
        if(id == R.id.action_item_one ){
            Intent intent = new Intent(OvertimeActivity.this, OvertimeDetailActivity.class);
            startActivity(intent);
            return true;
        }

        // save to draft
        if(id == R.id.action_item_two ){
            Intent intent = new Intent(OvertimeActivity.this, ListDraftOvertimeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private List<LeaveRequestTbl> generateData(){
//        List<LeaveRequestTbl> items = SQLite.select().from(LeaveRequestTbl.class).queryList();
//
//        return items;
//    }

    public void getListLeaveRequest() {
        apiInterface = APIClient.getListOvertime(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(OvertimeActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<Overtime> call3 = apiInterface.getListOvertime();
        call3.enqueue(new Callback<Overtime>() {
            @Override
            public void onResponse(Call<Overtime> call, Response<Overtime> response) {
                progressDialog.dismiss();
                Overtime data = response.body();
                if (data != null) {

                    // pass context and data to the custom adapter
                    adapter = new ListOvertimeAdapter(OvertimeActivity.this, data.getReturnValue());

                    // get listView from activity
                    listView = (ListView) findViewById(R.id.listOvertime);

                    // set listadapter
                    listView.setAdapter(adapter);

                    //viewAdapter = new HistoryAdapter(HistoriesActivity.this, data.getReturnValue());
                    //recyclerView.setAdapter(viewAdapter);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(OvertimeActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(OvertimeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Overtime> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void addOvertime(View view) {
        Intent intent = new Intent(OvertimeActivity.this,OvertimeDetailActivity.class);
        startActivity(intent);
    }
}
