package com.example.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.MedicalAdapter;
import com.example.android.starbridges.model.getmedicalsupport.GetMedicalSupport;
import com.example.android.starbridges.model.listmedicalclaim.ListMedicalClaim;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalClaimActivity extends AppCompatActivity {

    // declare komp listView
    ListView listView;

    // declare adapter
    MedicalAdapter adapter;

    private String medicalSupportID = "";
    private String medicalSupportName = "";

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_claim);
        setTitle("Medical Claim");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddMedical);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicalClaimActivity.this, MedicalClaimDetailActivity.class);
                startActivity(intent);
            }
        });
        // run api Medical Support
        //initMedicalSupport();

        // run api Get List Medical
        getListMedical();
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
            Intent intent = new Intent(MedicalClaimActivity.this, MedicalClaimDetailActivity.class);
            //intent.putExtra("MEDICAL_GRADE", medicalSupportName);
            //intent.putExtra("MEDICAL_SUPPORT_ID", medicalSupportID);
            startActivity(intent);
            return true;
        }

        // save to draft
        if(id == R.id.action_item_two ){
            Intent intent = new Intent(MedicalClaimActivity.this, ListDraftMedicalActivity.class);
            //intent.putExtra("MEDICAL_GRADE", medicalSupportName);
            //intent.putExtra("MEDICAL_SUPPORT_ID", medicalSupportID);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getListMedical(){
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(MedicalClaimActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<ListMedicalClaim> call3 = apiInterface.getListMedicalClaim();
        call3.enqueue(new Callback<ListMedicalClaim>() {
            @Override
            public void onResponse(Call<ListMedicalClaim> call, Response<ListMedicalClaim> response) {
                progressDialog.dismiss();
                ListMedicalClaim data = response.body();
                if (data != null) {

                    // pass context and data to the custom adapter
                    adapter = new MedicalAdapter(MedicalClaimActivity.this, data.getReturnValue());

                    // get listView from activity
                    listView = (ListView) findViewById(R.id.listMedical);

                    // set listadapter
                    listView.setAdapter(adapter);

                    //viewAdapter = new HistoryAdapter(HistoriesActivity.this, data.getReturnValue());
                    //recyclerView.setAdapter(viewAdapter);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MedicalClaimActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MedicalClaimActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListMedicalClaim> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void initMedicalSupport(){
        // get token
        apiInterface = APIClient.getClient(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(MedicalClaimActivity.this);
        progressDialog.setTitle("Loading");
        //progressDialog.show();

        Call<GetMedicalSupport> call3 = apiInterface.getMedicalSupport();
        call3.enqueue(new Callback<GetMedicalSupport>() {

            @Override
            public void onResponse(Call<GetMedicalSupport> call, Response<GetMedicalSupport> response) {
                progressDialog.dismiss();
                GetMedicalSupport data = response.body();

                if (data != null && data.getIsSucceed()) {
                    medicalSupportID = response.body().getReturnValue().getMedicalSupportID().toString();
                    medicalSupportName = response.body().getReturnValue().getMedicalSupportName();

                    // set text medical grade
                    //medicalGrade.setText(medicalSupportName);

                    // init spinner medical policy
                    //initSpinnerMedicalPolicy();

                } else {
                    Toast.makeText(MedicalClaimActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GetMedicalSupport> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MedicalClaimActivity.this, "Something went wrong...Please try again!", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }
}
