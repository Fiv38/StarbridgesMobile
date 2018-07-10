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

import com.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.util.HashMap;

import id.co.indocyber.android.starbridges.adapter.LeaveCancelationAdapter;
import id.co.indocyber.android.starbridges.model.ListLeaveCancelation.ListLeaveCancelation;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeaveCancelationActivity extends AppCompatActivity {

    SessionManagement session;
    private ProgressDialog progressDialog;
    LeaveCancelationAdapter viewAdapter;
    ListView lstCancelation;
    FloatingActionButton fabAddLeaveCancelation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_cancelation);
        setTitle("Leave Cancelation");

        lstCancelation=(ListView)findViewById(R.id.lstCancelation);
        fabAddLeaveCancelation=(FloatingActionButton)findViewById(R.id.fabAddLeaveCancelation);

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

        getLeaveCancelationLog();

        fabAddLeaveCancelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LeaveCancelationActivity.this, LeaveCancelationDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_draft, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // save to draft
        if(id == R.id.action_item_one ){
            Intent intent = new Intent(LeaveCancelationActivity.this, ListDraftLeaveCancelationActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void getLeaveCancelationLog() {

        progressDialog = new ProgressDialog(LeaveCancelationActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<ListLeaveCancelation> call3 = apiInterface.getLeaveCancelation();
        call3.enqueue(new Callback<ListLeaveCancelation>() {
            @Override
            public void onResponse(Call<ListLeaveCancelation> call, Response<ListLeaveCancelation> response) {
                progressDialog.dismiss();
                ListLeaveCancelation data = response.body();
                if (data != null && data.isIsSucceed()) {
                    viewAdapter = new LeaveCancelationAdapter(LeaveCancelationActivity.this, R.layout.lst_cancelation2, data.getReturnValue());
                    lstCancelation.setAdapter(viewAdapter);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LeaveCancelationActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LeaveCancelationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListLeaveCancelation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

}
