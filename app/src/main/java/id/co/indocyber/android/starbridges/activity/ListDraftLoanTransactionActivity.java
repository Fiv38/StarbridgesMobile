package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.ListDraftLoanTransactionAdapter;
import id.co.indocyber.android.starbridges.adapter.LoanTransactionAdapter;
import id.co.indocyber.android.starbridges.model.ListDraftTransactionLoan.ListDraftTransactionLoan;
import id.co.indocyber.android.starbridges.model.ListLoanTransaction.ListLoanTransaction;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.network.StringConverter;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftLoanTransactionActivity extends AppCompatActivity {

    TextView txtPolicyNameTransactionLoan, txtRemainingLoanTransactionLoan;
    ListView lstTransactionLoan;

    String remainingLoan,policyName,loanBalanceID;

    ListDraftLoanTransactionAdapter viewAdapter;

    ProgressDialog progressDialog;

    APIInterfaceRest apiInterface;

    FloatingActionButton fabAddLoanTransactionMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_loan_transaction);
        setTitle("Draft Loan Transaction");

        txtPolicyNameTransactionLoan=(TextView)findViewById(R.id.txtPolicyNameTransactionLoan);
        txtRemainingLoanTransactionLoan=(TextView)findViewById(R.id.txtRemainingLoanTransactionLoan);

        lstTransactionLoan=(ListView)findViewById(R.id.lstTransactionLoan);

        fabAddLoanTransactionMain=(FloatingActionButton)findViewById(R.id.fabAddLoanTransactionMain);

        policyName=getIntent().getStringExtra("PolicyName");
        txtPolicyNameTransactionLoan.setText(policyName);
        remainingLoan=getIntent().getStringExtra("RemainingLoan");
        txtRemainingLoanTransactionLoan.setText(new StringConverter().numberFormat(remainingLoan));

        loanBalanceID= getIntent().getStringExtra("LoanBalanceId");

        fabAddLoanTransactionMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListDraftLoanTransactionActivity.this, LoanDetailPostPoneActivity.class);
                intent.putExtra("LoanBalanceId", loanBalanceID);
                startActivity(intent);
            }
        });

        getListTransaction();

    }

    public void getListTransaction()
    {
        progressDialog= new ProgressDialog(ListDraftLoanTransactionActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListDraftLoanTransaction(loanBalanceID).enqueue(new Callback<ListDraftTransactionLoan>() {
            @Override
            public void onResponse(Call<ListDraftTransactionLoan> call, Response<ListDraftTransactionLoan> response) {

                if (response.body().getIsSucceed()) {
                    viewAdapter = new ListDraftLoanTransactionAdapter(ListDraftLoanTransactionActivity.this, response.body().getReturnValue());
                    lstTransactionLoan.setAdapter(viewAdapter);

                } else {
                    Toast.makeText(ListDraftLoanTransactionActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListDraftTransactionLoan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListDraftLoanTransactionActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
