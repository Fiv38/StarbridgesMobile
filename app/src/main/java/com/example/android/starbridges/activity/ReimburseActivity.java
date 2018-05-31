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
import com.example.android.starbridges.adapter.LeaveCancelationAdapter;
import com.example.android.starbridges.adapter.ReimburseAdapter;
import com.example.android.starbridges.model.ListLeaveCancelation.ListLeaveCancelation;
import com.example.android.starbridges.model.Reimbursement.Reimbursement;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;
import com.example.android.starbridges.utility.SessionManagement;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReimburseActivity extends AppCompatActivity {

    SessionManagement session;
    private ProgressDialog progressDialog;
    ReimburseAdapter viewAdapter;
    ListView lstReimburse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse);
        setTitle("Reimbursement");


        lstReimburse=(ListView)findViewById(R.id.lstReimburse);

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

        getReimbursementLog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // add new medical claim
        if(id == R.id.action_item_one ){
            Intent intent = new Intent(ReimburseActivity.this, ReimburseDetailActivity.class);
            startActivity(intent);
            return true;
        }

        // save to draft
        if(id == R.id.action_item_two ){
            Intent intent = new Intent(ReimburseActivity.this, ListDraftReimburseActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getReimbursementLog() {

        progressDialog = new ProgressDialog(ReimburseActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getReimburse(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<Reimbursement> call3 = apiInterface.getReimburse();
        call3.enqueue(new Callback<Reimbursement>() {
            @Override
            public void onResponse(Call<Reimbursement> call, Response<Reimbursement> response) {
                progressDialog.dismiss();
                Reimbursement data = response.body();
                if (data != null && data.isIsSucceed()) {
                    viewAdapter = new ReimburseAdapter(ReimburseActivity.this, R.layout.list_reimburse, data.getReturnValue());
                    lstReimburse.setAdapter(viewAdapter);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ReimburseActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ReimburseActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Reimbursement> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
