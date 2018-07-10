package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.util.HashMap;

import id.co.indocyber.android.starbridges.adapter.ReimburseAdapter;
import id.co.indocyber.android.starbridges.model.Reimbursement.Reimbursement;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReimburseActivity extends AppCompatActivity {

    SessionManagement session;
    private ProgressDialog progressDialog;
    ReimburseAdapter viewAdapter;
    ListView lstReimburse;

    FloatingActionButton fabAddReimburse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse);
        setTitle("Reimbursement");


        lstReimburse=(ListView)findViewById(R.id.lstReimburse);

        fabAddReimburse=(FloatingActionButton)findViewById(R.id.fabAddReimburse);

        fabAddReimburse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ReimburseActivity.this, ReimburseDetailActivity.class);
                startActivity(intent);
            }
        });

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
        getMenuInflater().inflate(R.menu.menu_draft, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // add new reimburse claim
        if(id == R.id.action_item_one ){
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
