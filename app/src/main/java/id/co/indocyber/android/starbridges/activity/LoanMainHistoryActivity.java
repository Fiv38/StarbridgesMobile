package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.ListDraftShiftExchangeAdapter;
import id.co.indocyber.android.starbridges.adapter.LoanMainHistoryAdapter;
import id.co.indocyber.android.starbridges.model.EditLeaveCancelation.EditLeaveCancelation;
import id.co.indocyber.android.starbridges.model.ListTransactionInformation.ListTransactionInformation;
import id.co.indocyber.android.starbridges.model.LoanSettingLimit.LoanSettingLimit;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanMainHistoryActivity extends AppCompatActivity {

    TextView txtLimitHistoryLoan;
    ProgressDialog progressDialog;
    APIInterfaceRest apiInterface;
    LoanMainHistoryAdapter viewAdapter;

    String sDateFrom, sDateTo;

    ListView lstLoanHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_main_history);
        setTitle("Loan History");

        txtLimitHistoryLoan=(TextView)findViewById(R.id.txtLimitHistoryLoan);
        lstLoanHistory=(ListView)findViewById(R.id.lstLoanHistory);

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

        getLimitLoan();

    }

    public void getLimitLoan()
    {
        progressDialog= new ProgressDialog(LoanMainHistoryActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getLoanSettingLimit().enqueue(new Callback<LoanSettingLimit>() {
            @Override
            public void onResponse(Call<LoanSettingLimit> call, Response<LoanSettingLimit> response) {


                if (response.body().isIsSucceed()) {
                    txtLimitHistoryLoan.setText(response.body().getReturnValue());
                    getListTransactionInformation();
                } else {
                    Toast.makeText(LoanMainHistoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoanSettingLimit> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanMainHistoryActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getListTransactionInformation()
    {
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListTransactionInformation(sDateFrom, sDateTo).enqueue(new Callback<ListTransactionInformation>() {
            @Override
            public void onResponse(Call<ListTransactionInformation> call, Response<ListTransactionInformation> response) {

                if (response.body().isIsSucceed()) {
                    viewAdapter = new LoanMainHistoryAdapter(LoanMainHistoryActivity.this, response.body().getReturnValue());
                    lstLoanHistory.setAdapter(viewAdapter);

                } else {
                    Toast.makeText(LoanMainHistoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListTransactionInformation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoanMainHistoryActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
