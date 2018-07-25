package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.LoanMainTransactionAdapter;
import id.co.indocyber.android.starbridges.model.ListTransactionInformation.ReturnValue;
import id.co.indocyber.android.starbridges.model.ListTransactionInformation.ListTransactionInformation;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanTransactionMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String sDateFrom, sDateTo;

    ListView lstLoanTransactionMain;

    LoanMainTransactionAdapter viewAdapter;

    APIInterfaceRest apiInterface;

    ProgressDialog progressDialog;

    ListTransactionInformation data;

    FloatingActionButton fabAddLoanTransactionMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_transaction_main);
        setTitle("Loan Transaction");

        lstLoanTransactionMain=(ListView)findViewById(R.id.lstLoanTransactionMain);
        fabAddLoanTransactionMain=(FloatingActionButton)findViewById(R.id.fabAddLoanTransactionMain);

        sDateFrom=getIntent().getStringExtra("from");
        sDateTo=getIntent().getStringExtra("to");

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

        getListTransactionInformation();

        fabAddLoanTransactionMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoanTransactionMainActivity.this, LoanRequestCreateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ReturnValue data1=data.getReturnValue().get(position);
//        listData.getReturnValue().remove(data);
//        viewAdapter=new ListDraftCorrectionAdapter(DraftCorrectionListActivity.this,R.layout.lst_correction_draft2, listData.getReturnValue());
//        list.setAdapter(viewAdapter);

        Intent intent=new Intent(LoanTransactionMainActivity.this, LoanTransactionActivity.class);
        intent.putExtra("LoanBalanceId",data1.getLoanBalanceID());
        intent.putExtra("PolicyName",data1.getPolicyName());
        intent.putExtra("RemainingLoan",data1.getRemainingLoan());
        startActivity(intent);
    }

    public void getListTransactionInformation()
    {

        progressDialog= new ProgressDialog(LoanTransactionMainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListTransactionInformation(sDateFrom, sDateTo).enqueue(new Callback<ListTransactionInformation>() {
            @Override
            public void onResponse(Call<ListTransactionInformation> call, Response<ListTransactionInformation> response) {

                if (response.body().isIsSucceed()) {
                    data=response.body();
                    viewAdapter = new LoanMainTransactionAdapter(LoanTransactionMainActivity.this, response.body().getReturnValue());
                    lstLoanTransactionMain.setAdapter(viewAdapter);

                    lstLoanTransactionMain.setOnItemClickListener(LoanTransactionMainActivity.this);

                } else {
                    Toast.makeText(LoanTransactionMainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListTransactionInformation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanTransactionMainActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

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
            Intent intent = new Intent(LoanTransactionMainActivity.this, ListDraftLoanTransactionApprovedActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
