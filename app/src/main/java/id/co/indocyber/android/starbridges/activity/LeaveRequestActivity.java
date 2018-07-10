package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.indocyber.android.starbridges.R;

import org.json.JSONObject;

import id.co.indocyber.android.starbridges.adapter.LeaveRequestAdapter;
import id.co.indocyber.android.starbridges.model.leaverequest.LeaveRequest;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
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

    public void addLeaveRequest(View view) {
        Intent intent = new Intent(LeaveRequestActivity.this, LeaveRequestDetailActivity.class);
        startActivity(intent);
    }
}
