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
import com.example.android.starbridges.adapter.ListDraftLeaveRequestAdapter;
import com.example.android.starbridges.adapter.ListDraftMedicalAdapter;
import com.example.android.starbridges.model.deleteleaverequest.DeleteLeaveRequest;
import com.example.android.starbridges.model.deletemedical.DeleteMedical;
import com.example.android.starbridges.model.listdraftleaverequest.ListDraftLeaveRequest;
import com.example.android.starbridges.model.listdraftmedical.ListDraftMedical;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftMedicalActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1000) {
            if (resultCode == 1100) {
                getListDraftMedical();
            }
        }
    }

    // decalre comp listview
    ListView listView;

    //private String medicalSupportID = "";
    //private String medicalSupportName = "";

    // declare adapter
    ListDraftMedicalAdapter adapter;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_medical);

        // Get intent from MedicalClaimActivity
        /*Intent intent2 = getIntent();
        if(intent2.getStringExtra("MEDICAL_GRADE") != null) {
            medicalSupportName = intent2.getStringExtra("MEDICAL_GRADE");
        }

        if(intent2.getStringExtra("MEDICAL_SUPPORT_ID") != null) {
            medicalSupportID = intent2.getStringExtra("MEDICAL_SUPPORT_ID");
        }*/

        getListDraftMedical();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.leaverequest, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // add new medical
        if(id == R.id.action_item_one ){
            Intent intent = new Intent(ListDraftMedicalActivity.this, MedicalClaimDetailActivity.class);
            startActivity(intent);
            return true;
        }


        // delete medical
        if(id == R.id.action_item_two ){
            // get list data from checkbox
            String listid = ListDraftMedicalAdapter.listID.toString();

            //Toast.makeText(ListDraftLeaveRequestActivity.this, "List : " + listid, Toast.LENGTH_SHORT).show();
            deleteCheckedDraft(listid);

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void deleteCheckedDraft(String listid){
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftMedicalActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), listid);
        Call<DeleteMedical> call3 = apiInterface.deleteMedical(body);
        call3.enqueue(new Callback<DeleteMedical>() {
            @Override
            public void onResponse(Call<DeleteMedical> call, Response<DeleteMedical> response) {
                progressDialog.dismiss();
                DeleteMedical data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(ListDraftMedicalActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                    // delete list id on checkbox
                    ListDraftMedicalAdapter.listID.clear();

                    // call list draft
                    getListDraftMedical();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftMedicalActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftMedicalActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<DeleteMedical> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void getListDraftMedical(){
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftMedicalActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<ListDraftMedical> call3 = apiInterface.getListDraftMedical();
        call3.enqueue(new Callback<ListDraftMedical>() {
            @Override
            public void onResponse(Call<ListDraftMedical> call, Response<ListDraftMedical> response) {
                progressDialog.dismiss();
                ListDraftMedical data = response.body();

                if (data != null) {

                    // pass context and data to the custor adapter
                    adapter = new ListDraftMedicalAdapter(ListDraftMedicalActivity.this, data.getReturnValue());

                    // get listView from activity
                    listView = (ListView) findViewById(R.id.listDraftMedical);

                    // set listadapter
                    listView.setAdapter(adapter);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftMedicalActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftMedicalActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDraftMedical> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
