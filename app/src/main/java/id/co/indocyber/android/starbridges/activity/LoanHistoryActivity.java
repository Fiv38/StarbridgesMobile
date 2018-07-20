package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.LoanHistoryAdapter;
import id.co.indocyber.android.starbridges.adapter.LoanScheduleAdapter;
import id.co.indocyber.android.starbridges.model.ListLoanHistory.ListLoanHistory;
import id.co.indocyber.android.starbridges.model.ListLoanSchedule.ListLoanSchedule;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.network.StringConverter;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanHistoryActivity extends AppCompatActivity {

    TextView txtPolicyNameHistoryLoan, txtRemainingLoanHistoryLoan;
    ListView lstHistoryLoan;


    APIInterfaceRest apiInterface;

    String loanBalanceID;

    ProgressDialog progressDialog;

    LoanHistoryAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_history);

        txtPolicyNameHistoryLoan=(TextView)findViewById(R.id.txtPolicyNameHistoryLoan);
        txtRemainingLoanHistoryLoan=(TextView)findViewById(R.id.txtRemainingLoanHistoryLoan);

        lstHistoryLoan=(ListView)findViewById(R.id.lstHistoryLoan);

        txtPolicyNameHistoryLoan.setText(getIntent().getStringExtra("PolicyName"));
        txtRemainingLoanHistoryLoan.setText(new StringConverter().numberFormat(getIntent().getStringExtra("RemainingLoan")) );

        loanBalanceID= getIntent().getStringExtra("LoanBalanceId");

        getListTransactionInformation();

    }

    public void getListTransactionInformation()
    {
        progressDialog= new ProgressDialog(LoanHistoryActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListLoanHistory(loanBalanceID).enqueue(new Callback<ListLoanHistory>() {
            @Override
            public void onResponse(Call<ListLoanHistory> call, Response<ListLoanHistory> response) {

                if (response.body().isIsSucceed()) {
                    viewAdapter = new LoanHistoryAdapter(LoanHistoryActivity.this, response.body().getReturnValue());
                    lstHistoryLoan.setAdapter(viewAdapter);

                } else {
                    Toast.makeText(LoanHistoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListLoanHistory> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanHistoryActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
