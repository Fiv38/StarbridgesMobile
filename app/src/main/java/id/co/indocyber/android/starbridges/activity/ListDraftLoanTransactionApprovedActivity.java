package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.adapter.ListDraftLoanTransactionApprovedAdapter;
import id.co.indocyber.android.starbridges.adapter.LoanMainTransactionAdapter;
import id.co.indocyber.android.starbridges.model.ListDraftTransactionLoanApproved.ListDraftTransactionLoanApproved;
import id.co.indocyber.android.starbridges.model.ListTransactionInformation.ListTransactionInformation;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftLoanTransactionApprovedActivity extends AppCompatActivity {

    ListView lstDraftLoanTransactionMain;

    ListDraftLoanTransactionApprovedAdapter viewAdapter;

    APIInterfaceRest apiInterface;

    ProgressDialog progressDialog;

    ListDraftTransactionLoanApproved data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_transaction_approved_loan);
        setTitle("Draft Loan Transaction");

        lstDraftLoanTransactionMain=(ListView)findViewById(R.id.lstDraftLoanTransactionMain);

        getDraft();
    }

    public void getDraft()
    {
        progressDialog= new ProgressDialog(ListDraftLoanTransactionApprovedActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.editDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        apiInterface.getListDraftLoanTransactionApproved().enqueue(new Callback<ListDraftTransactionLoanApproved>() {
            @Override
            public void onResponse(Call<ListDraftTransactionLoanApproved> call, Response<ListDraftTransactionLoanApproved> response) {

                if (response.body().getIsSucceed()) {
                    data=response.body();
                    viewAdapter = new ListDraftLoanTransactionApprovedAdapter(ListDraftLoanTransactionApprovedActivity.this, response.body().getReturnValue());
                    lstDraftLoanTransactionMain.setAdapter(viewAdapter);

//                    lstDraftLoanTransactionMain.setOnItemClickListener(ListDraftLoanTransactionApprovedActivity.this);

                } else {
                    Toast.makeText(ListDraftLoanTransactionApprovedActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ListDraftTransactionLoanApproved> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListDraftLoanTransactionApprovedActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
