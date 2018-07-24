package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.LoanHistoryAdapter;
import id.co.indocyber.android.starbridges.adapter.LoanMainTransactionAdapter;
import id.co.indocyber.android.starbridges.adapter.LoanTransactionAdapter;
import id.co.indocyber.android.starbridges.model.ListLoanHistory.ListLoanHistory;
import id.co.indocyber.android.starbridges.model.ListLoanTransaction.ListLoanTransaction;
import id.co.indocyber.android.starbridges.model.ListTransactionInformation.ListTransactionInformation;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.network.StringConverter;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanTransactionActivity extends AppCompatActivity {

    TextView txtPolicyNameTransactionLoan, txtRemainingLoanTransactionLoan;
    ListView lstTransactionLoan;

    String remainingLoan,policyName,loanBalanceID;

    LoanTransactionAdapter viewAdapter;

    ProgressDialog progressDialog;

    APIInterfaceRest apiInterface;

    FloatingActionButton fabAddLoanTransactionMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_transaction);
        setTitle("Loan Transaction");

        txtPolicyNameTransactionLoan=(TextView)findViewById(R.id.txtPolicyNameTransactionLoan);
        txtRemainingLoanTransactionLoan=(TextView)findViewById(R.id.txtRemainingLoanTransactionLoan);
        fabAddLoanTransactionMain=(FloatingActionButton)findViewById(R.id.fabAddLoanTransactionMain);

        lstTransactionLoan=(ListView)findViewById(R.id.lstTransactionLoan);

        policyName=getIntent().getStringExtra("PolicyName");
        remainingLoan=getIntent().getStringExtra("RemainingLoan");
        loanBalanceID= getIntent().getStringExtra("LoanBalanceId");

        if(policyName==null||policyName=="")
        {
            policyName= SharedPreferenceUtils.getSetting(getApplicationContext(), "PolicyName", "");
        }
        else
            SharedPreferenceUtils.setSetting(getApplicationContext(),"PolicyName", policyName);

        if(remainingLoan==null||remainingLoan=="")
        {
            remainingLoan= SharedPreferenceUtils.getSetting(getApplicationContext(), "RemainingLoan", "");
        }
        else
            SharedPreferenceUtils.setSetting(getApplicationContext(),"RemainingLoan", remainingLoan);

        if(loanBalanceID==null||loanBalanceID=="")
        {
            loanBalanceID= SharedPreferenceUtils.getSetting(getApplicationContext(), "LoanBalanceId", "");
        }
        else
            SharedPreferenceUtils.setSetting(getApplicationContext(),"LoanBalanceId", loanBalanceID);

        txtPolicyNameTransactionLoan.setText(policyName);
        txtRemainingLoanTransactionLoan.setText(new StringConverter().numberFormat(remainingLoan));

        fabAddLoanTransactionMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoanTransactionActivity.this, LoanDetailPostPoneActivity.class);
                intent.putExtra("LoanBalanceId", loanBalanceID);
                intent.putExtra("PolicyName", policyName);
                intent.putExtra("RemainingLoan", remainingLoan);
                startActivity(intent);
            }
        });

        getListTransaction();

    }

    public void getListTransaction()
    {
        progressDialog= new ProgressDialog(LoanTransactionActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListLoanTransaction(loanBalanceID).enqueue(new Callback<ListLoanTransaction>() {
            @Override
            public void onResponse(Call<ListLoanTransaction> call, Response<ListLoanTransaction> response) {

                if (response.body().getIsSucceed()) {
                    viewAdapter = new LoanTransactionAdapter(LoanTransactionActivity.this, response.body().getReturnValue());
                    lstTransactionLoan.setAdapter(viewAdapter);

                } else {
                    Toast.makeText(LoanTransactionActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListLoanTransaction> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanTransactionActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

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
            Intent intent = new Intent(LoanTransactionActivity.this, ListDraftLoanTransactionActivity.class);
            intent.putExtra("LoanBalanceId", loanBalanceID);
            intent.putExtra("PolicyName", policyName);
            intent.putExtra("RemainingLoan", remainingLoan);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
