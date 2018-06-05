package com.example.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.ListDraftLeaveCancelationAdapter;
import com.example.android.starbridges.model.ListDraftLeaveCancelation.ListDraftLeaveCancelation;
import com.example.android.starbridges.model.MessageReturn.MessageReturn;
import com.example.android.starbridges.model.deleteleaverequest.DeleteLeaveRequest;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftLeaveCancelationActivity extends AppCompatActivity {

    // declare komp lstDraftLeaveCancelation
    ListView lstDraftLeaveCancelation;

    // declare adapter
    ListDraftLeaveCancelationAdapter adapter;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_leave_cancelation);
        setTitle("Draft Leave Cancelation");
        getListDraftLeaveCancelation();
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
            Intent intent = new Intent(ListDraftLeaveCancelationActivity.this, LeaveCancelationDetailActivity.class);
            startActivity(intent);
            return true;
        }

        // delete
        if(id == R.id.action_item_two ){
            // get list data from checkbox
            String listid = ListDraftLeaveCancelationAdapter.listID.toString();

            //Toast.makeText(ListDraftLeaveRCancelationctivity.this, "List : " + listid, Toast.LENGTH_SHORT).show();
            deleteCheckedDraft(listid);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteCheckedDraft(String listid){
        apiInterface = APIClient.deleteDraftCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftLeaveCancelationActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), listid);
        Call<MessageReturn> call3 = apiInterface.deleteDraftCancelation(body);
        call3.enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                progressDialog.dismiss();
                MessageReturn data = response.body();

                if (data != null && data.isIsSucceed()) {
                    Toast.makeText(ListDraftLeaveCancelationActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                    // delete list id on checkbox
                    ListDraftLeaveCancelationAdapter.listID.clear();

                    // call list draft
                    getListDraftLeaveCancelation();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something were wrong, please try again later", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void getListDraftLeaveCancelation(){
        progressDialog = new ProgressDialog(ListDraftLeaveCancelationActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.getListDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<ListDraftLeaveCancelation> call3 = apiInterface.getListDraftLeaveCancelation();
        call3.enqueue(new Callback<ListDraftLeaveCancelation>() {
            @Override
            public void onResponse(Call<ListDraftLeaveCancelation> call, Response<ListDraftLeaveCancelation> response) {
                progressDialog.dismiss();
                ListDraftLeaveCancelation data = response.body();

                if (data != null) {

                    // pass context and data to the custor adapter
                    adapter = new ListDraftLeaveCancelationAdapter(ListDraftLeaveCancelationActivity.this, data.getReturnValue());

                    // get lstDraftLeaveCancelation from activity
                    lstDraftLeaveCancelation = (ListView) findViewById(R.id.lstDraftLeaveCancelation);

                    // set listadapter
                    lstDraftLeaveCancelation.setAdapter(adapter);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDraftLeaveCancelation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }


}
