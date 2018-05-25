package com.example.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.LeaveRequestAdapter;
import com.example.android.starbridges.model.leaverequest.LeaveRequest;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.android.starbridges.model.LeaveRequestTbl;
//import com.raizlabs.android.dbflow.sql.language.SQLite;

public class LeaveRequestActivity extends AppCompatActivity {

    // declare komp listView
    ListView listView;

    // declare adapter
    LeaveRequestAdapter adapter;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);
        setTitle("Leave Request");

        // pass context and data to the custom adapter
        //adapter = new LeaveRequestAdapter(LeaveRequestActivity.this, generateData());

        // get listView from activity
        //listView = (ListView) findViewById(R.id.listLeaveRequest);

        // set listadapter
        //listView.setAdapter(adapter);

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
            Intent intent = new Intent(LeaveRequestActivity.this, LeaveRequestDetailActivity.class);
            startActivity(intent);
            return true;
        }

        // save to draft
        if(id == R.id.action_item_two ){
            Intent intent = new Intent(LeaveRequestActivity.this, ListDraftLeaveRequestActivity.class);
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
        apiInterface = APIClient.getListLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(LeaveRequestActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest();
        call3.enqueue(new Callback<LeaveRequest>() {
            @Override
            public void onResponse(Call<LeaveRequest> call, Response<LeaveRequest> response) {
                progressDialog.dismiss();
                LeaveRequest data = response.body();
                if (data != null) {

                    // pass context and data to the custom adapter
                    adapter = new LeaveRequestAdapter(LeaveRequestActivity.this, data.getReturnValue());

                    // get listView from activity
                    listView = (ListView) findViewById(R.id.listLeaveRequest);

                    // set listadapter
                    listView.setAdapter(adapter);

                    //viewAdapter = new HistoryAdapter(HistoriesActivity.this, data.getReturnValue());
                    //recyclerView.setAdapter(viewAdapter);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LeaveRequestActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LeaveRequestActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<LeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
