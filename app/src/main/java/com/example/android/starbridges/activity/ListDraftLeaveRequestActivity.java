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
import com.example.android.starbridges.adapter.ListDraftLeaveRequestAdapter;
import com.example.android.starbridges.model.deleteleaverequest.DeleteLeaveRequest;
import com.example.android.starbridges.model.listdraftleaverequest.ListDraftLeaveRequest;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftLeaveRequestActivity extends AppCompatActivity {

    // declare komp listView
    ListView listView;

    // declare adapter
    ListDraftLeaveRequestAdapter adapter;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_leave_request);
        setTitle("Draft Leave Request");
        getListDraftLeaveRequest();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.leaverequest, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // add new leave request
        if(id == R.id.action_item_one ){
            Intent intent = new Intent(ListDraftLeaveRequestActivity.this, LeaveRequestDetailActivity.class);
            startActivity(intent);
            return true;
        }

        // delete
        if(id == R.id.action_item_two ){
            // get list data from checkbox
            String listid = ListDraftLeaveRequestAdapter.listID.toString();

            //Toast.makeText(ListDraftLeaveRequestActivity.this, "List : " + listid, Toast.LENGTH_SHORT).show();
            deleteCheckedDraft(listid);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteCheckedDraft(String listid){
        apiInterface = APIClient.deleteLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftLeaveRequestActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), listid);
        Call<DeleteLeaveRequest> call3 = apiInterface.deleteLeaveRequst(body);
        call3.enqueue(new Callback<DeleteLeaveRequest>() {
            @Override
            public void onResponse(Call<DeleteLeaveRequest> call, Response<DeleteLeaveRequest> response) {
                progressDialog.dismiss();
                DeleteLeaveRequest data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(ListDraftLeaveRequestActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                    // delete list id on checkbox
                    ListDraftLeaveRequestAdapter.listID.clear();

                    // call list draft
                    getListDraftLeaveRequest();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveRequestActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveRequestActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<DeleteLeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void getListDraftLeaveRequest(){
        apiInterface = APIClient.getListDraftLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftLeaveRequestActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<ListDraftLeaveRequest> call3 = apiInterface.getListDraftLeaveRequest();
        call3.enqueue(new Callback<ListDraftLeaveRequest>() {
            @Override
            public void onResponse(Call<ListDraftLeaveRequest> call, Response<ListDraftLeaveRequest> response) {
                progressDialog.dismiss();
                ListDraftLeaveRequest data = response.body();

                if (data != null) {

                    // pass context and data to the custor adapter
                    adapter = new ListDraftLeaveRequestAdapter(ListDraftLeaveRequestActivity.this, data.getReturnValue());

                    // get listView from activity
                    listView = (ListView) findViewById(R.id.listDraftLeaveRequest);

                    // set listadapter
                    listView.setAdapter(adapter);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveRequestActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveRequestActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDraftLeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}

