package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;

import org.json.JSONObject;

import id.co.indocyber.android.starbridges.adapter.DraftCorrectionAdapter;
import id.co.indocyber.android.starbridges.model.ListDraftCorrection.ListDraftCorrection;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SharedPreferenceUtils;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DraftCorrectionActivity extends AppCompatActivity {

    TextView txtNameCorrectionDraft, txtNIKCorrectionDraft;

    ImageView imgDeleteCDrafts;

    private RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    DraftCorrectionAdapter viewAdapter;

    ListDraftCorrection data;

    String idSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_correction_draft);

        txtNameCorrectionDraft=(TextView)findViewById(R.id.txtNameDraftCorrection);
        txtNIKCorrectionDraft=(TextView)findViewById(R.id.txtNIKDraftCorrection);
        recyclerView=(RecyclerView)findViewById(R.id.rcyListDraftCorrection);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        imgDeleteCDrafts=(ImageView)findViewById(R.id.imgDeleteCDrafts);

        txtNameCorrectionDraft.setText(GlobalVar.getFullname());
        txtNIKCorrectionDraft.setText(GlobalVar.getNIK());

        getListDraftCorrection();

        final Intent intent = getIntent();
//        String lstIdSelected = intent.getStringExtra("lstIdSelected");
//
//        if(lstIdSelected.length()<3)
//            imgDeleteCDrafts.setVisibility(View.GONE);
//        else
//            imgDeleteCDrafts.setVisibility(View.VISIBLE);
    }

    public void getListDraftCorrection() {

        progressDialog = new ProgressDialog(DraftCorrectionActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getListDraftCorrection(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<ListDraftCorrection> call3 = apiInterface.getListDraftCorrection();
        call3.enqueue(new Callback<ListDraftCorrection>() {
            @Override
            public void onResponse(Call<ListDraftCorrection> call, Response<ListDraftCorrection> response) {
                progressDialog.dismiss();
                data = response.body();

                if (data != null && data.isIsSucceed()) {
                    setListViewDraftCorrection();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(DraftCorrectionActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(DraftCorrectionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDraftCorrection> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void setListViewDraftCorrection()
    {
        if (data != null && data.isIsSucceed()) {
            viewAdapter = new DraftCorrectionAdapter(DraftCorrectionActivity.this, data.getReturnValue());
            recyclerView.setAdapter(viewAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_draft_correction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.deleteDraft)
        {
//            data.getReturnValue().remove(0);
//            viewAdapter.notifyDataSetChanged();
//            setListViewDraftCorrection();

            idSelected= SharedPreferenceUtils.getSetting(DraftCorrectionActivity.this, "lstIdSelected", "");
            if(idSelected.length()>3)
            {
                Toast.makeText(getApplicationContext(), idSelected, Toast.LENGTH_LONG).show();
                deleteDraftCorrection();
                getListDraftCorrection();
            }
            else
                Toast.makeText(getApplicationContext(), "cant delete "+ idSelected, Toast.LENGTH_LONG).show();


        }
        return super.onOptionsItemSelected(item);
    }


    public void deleteDraftCorrection() {

        progressDialog = new ProgressDialog(DraftCorrectionActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getListDraftCorrection(GlobalVar.getToken()).create(APIInterfaceRest.class);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),idSelected);
        Call<MessageReturn> call3 = apiInterface.deleteDraftCorrection(body);
        call3.enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                progressDialog.dismiss();
                MessageReturn result = response.body();
                if (result != null && result.isIsSucceed()) {
                    Toast.makeText(DraftCorrectionActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(DraftCorrectionActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(DraftCorrectionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_draft_correction, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        return super.onOptionsItemSelected(item);
//    }
}
