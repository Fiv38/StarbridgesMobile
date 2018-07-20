package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanTransactionActivity extends AppCompatActivity {

    TextView txtPolicyNameTransactionLoan, txtRemainingLoanTransactionLoan;
    ListView lstTransactionLoan;

    String loanBalanceID;

    LoanTransactionAdapter viewAdapter;

    ProgressDialog progressDialog;

    APIInterfaceRest apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_transaction);

        txtPolicyNameTransactionLoan=(TextView)findViewById(R.id.txtPolicyNameTransactionLoan);
        txtRemainingLoanTransactionLoan=(TextView)findViewById(R.id.txtRemainingLoanTransactionLoan);

        lstTransactionLoan=(ListView)findViewById(R.id.lstTransactionLoan);

        txtPolicyNameTransactionLoan.setText(getIntent().getStringExtra("PolicyName"));
        txtRemainingLoanTransactionLoan.setText(new StringConverter().numberFormat(getIntent().getStringExtra("RemainingLoan")));

        loanBalanceID= getIntent().getStringExtra("LoanBalanceId");

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


}
