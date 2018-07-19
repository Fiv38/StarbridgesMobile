package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import id.co.indocyber.android.starbridges.adapter.CorrectionListAdapter;
import id.co.indocyber.android.starbridges.model.ListAttendanceCorrection.ListAttendanceCorrection;
import id.co.indocyber.android.starbridges.model.ListAttendanceCorrection.ReturnValue;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CorrectionListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    TextView txtNameCorrection, txtNIKCorrection;

    private ListView list;
    ListAttendanceCorrection listData;

    private ProgressDialog progressDialog;
    private CorrectionListAdapter viewAdapter;
    SessionManagement session;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ReturnValue data=listData.getReturnValue().get(position);
//        listData.getReturnValue().remove(data);
//        viewAdapter=new ListDraftCorrectionAdapter(DraftCorrectionListActivity.this,R.layout.lst_correction_draft2, listData.getReturnValue());
//        list.setAdapter(viewAdapter);

        Intent intent=new Intent(CorrectionListActivity.this, CorrectionDetailActivity.class);
        intent.putExtra("uid", data.getUID());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction_list);
        setTitle("Attendance Correction");
        txtNameCorrection=(TextView)findViewById(R.id.txtNameCorrection);
        txtNIKCorrection=(TextView)findViewById(R.id.txtNIKCorrection);
        list =(ListView) findViewById(R.id.lstCorrection);

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String token_sp = user.get(SessionManagement.KEY_TOKEN);
        String fullName_sp = user.get(SessionManagement.KEY_FULLNAME);
        String nik_sp = user.get(SessionManagement.KEY_NIK);
        GlobalVar.setToken(token_sp);
        GlobalVar.setFullname(fullName_sp);
        GlobalVar.setNIK(nik_sp);

        txtNameCorrection.setText(GlobalVar.getFullname());
        txtNIKCorrection.setText(GlobalVar.getNIK());

        Calendar aCalendar = Calendar.getInstance();
        // add -1 month to current month
//        aCalendar.add(Calendar.MONTH, -1);
        aCalendar.add(Calendar.MONTH, -aCalendar.getTime().getMonth());
        // set DATE to 1, so first date of previous month
        aCalendar.set(Calendar.DATE, 1);

        Date firstDateOfThisYear=aCalendar.getTime();
//        Date firstDateOfPreviousMonth = aCalendar.getTime();

        // set actual maximum date of previous month
        aCalendar.set(Calendar.DATE,     aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //read it
        Date lastDateOfPreviousMonth = aCalendar.getTime();

        Date today= new Date();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        Intent intent = getIntent();
        String sDateFrom = intent.getStringExtra("from");
        String sDateTo = intent.getStringExtra("to");

        if(sDateFrom==null||sDateFrom=="")
        {
            sDateFrom= SharedPreferenceUtils.getSetting(getApplicationContext(), "fromDate", "");
        }
        else
            SharedPreferenceUtils.setSetting(getApplicationContext(),"fromDate", sDateFrom);


        if(sDateTo==null||sDateTo=="")
        {
            sDateTo= SharedPreferenceUtils.getSetting(getApplicationContext(), "toDate", "");
        }
        else
            SharedPreferenceUtils.setSetting(getApplicationContext(),"toDate", sDateTo);

        try{
            DateFormat df= new SimpleDateFormat("MM/dd/yyyy");
            Date dateFrom = df.parse(sDateFrom);
            Date dateTo= df.parse(sDateTo);

            sDateFrom=sdf.format(dateFrom);
            sDateTo=sdf.format(dateTo);

        }catch (Exception e)
        {

        }

        getAttendaceCorrectionLog(sDateFrom, sDateTo);
//        getAttendaceCorrectionLog(sdf.format(firstDateOfThisYear), sdf.format(today));
    }


    public void getAttendaceCorrectionLog(String dateFrom, String dateTo) {

        progressDialog = new ProgressDialog(CorrectionListActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getListAttendanceCorrection(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<ListAttendanceCorrection> call3 = apiInterface.getListAttendanceCorrection(dateFrom, dateTo);
        call3.enqueue(new Callback<ListAttendanceCorrection>() {
            @Override
            public void onResponse(Call<ListAttendanceCorrection> call, Response<ListAttendanceCorrection> response) {
                progressDialog.dismiss();
                listData = response.body();
                if (listData != null && listData.isIsSucceed()) {
                    viewAdapter = new CorrectionListAdapter(CorrectionListActivity.this, R.layout.lst_correction2, listData.getReturnValue());
                    list.setAdapter(viewAdapter);
                    list.setOnItemClickListener(CorrectionListActivity.this);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CorrectionListActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(CorrectionListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListAttendanceCorrection> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_correction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_item_one)
        {
            Intent intent = new Intent(CorrectionListActivity.this, DraftCorrectionListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
