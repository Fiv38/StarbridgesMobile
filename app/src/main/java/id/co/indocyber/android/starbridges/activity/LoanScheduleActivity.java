package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.LoanMainHistoryAdapter;
import id.co.indocyber.android.starbridges.adapter.LoanScheduleAdapter;
import id.co.indocyber.android.starbridges.model.ListLoanSchedule.ListLoanSchedule;
import id.co.indocyber.android.starbridges.model.ListTransactionInformation.ListTransactionInformation;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.network.StringConverter;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanScheduleActivity extends AppCompatActivity {

    TextView txtPolicyNameScheduleLoan, txtRemainingLoanScheduleLoan;
    ListView lstScheduleLoan;

    LoanScheduleAdapter viewAdapter;

    APIInterfaceRest apiInterface;

    String loanBalanceID;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_schedule);
        setTitle("Loan History Schedule");

        txtPolicyNameScheduleLoan=(TextView)findViewById(R.id.txtPolicyNameScheduleLoan);
        txtRemainingLoanScheduleLoan=(TextView)findViewById(R.id.txtRemainingLoanScheduleLoan);

        lstScheduleLoan=(ListView)findViewById(R.id.lstScheduleLoan);

        txtPolicyNameScheduleLoan.setText(getIntent().getStringExtra("PolicyName"));
        txtRemainingLoanScheduleLoan.setText(new StringConverter().numberFormat(getIntent().getStringExtra("RemainingLoan")));

        loanBalanceID= getIntent().getStringExtra("LoanBalanceId");

        getListTransactionInformation();

    }

    public void getListTransactionInformation()
    {
        progressDialog= new ProgressDialog(LoanScheduleActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListLoanSchedule(loanBalanceID).enqueue(new Callback<ListLoanSchedule>() {
            @Override
            public void onResponse(Call<ListLoanSchedule> call, Response<ListLoanSchedule> response) {

                if (response.body().isIsSucceed()) {
                    viewAdapter = new LoanScheduleAdapter(LoanScheduleActivity.this, response.body().getReturnValue());
                    lstScheduleLoan.setAdapter(viewAdapter);

                } else {
                    Toast.makeText(LoanScheduleActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListLoanSchedule> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanScheduleActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
