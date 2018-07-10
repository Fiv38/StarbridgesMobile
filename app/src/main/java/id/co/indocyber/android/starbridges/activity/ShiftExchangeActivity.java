package id.co.indocyber.android.starbridges.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.co.indocyber.android.starbridges.adapter.ListShiftExchangeAdapter;
import id.co.indocyber.android.starbridges.model.ListShiftExchange.ListShiftExchange;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShiftExchangeActivity extends AppCompatActivity {
    private ListView listSE;
    private ProgressDialog progressDialog;
    ImageView imgDeleteCDrafts;
    ListShiftExchangeAdapter viewAdapter;
    ListShiftExchange listData;
    private String idSelected = "";
    List<String> lstIdSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_exchange);
        setTitle("Shift Exchange");

        listSE = (ListView) findViewById(R.id.lstShiftExchange);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddShiftExchange);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShiftExchangeActivity.this, ShiftExchangeDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getListShiftEx() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getListShiftExchange(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<ListShiftExchange> call3 = apiInterface.getListShiftExchange();
        call3.enqueue(new Callback<ListShiftExchange>() {
            @Override
            public void onResponse(Call<ListShiftExchange> call, Response<ListShiftExchange> response) {
                progressDialog.dismiss();
                listData = response.body();
                if (listData != null && listData.getIsSucceed()) {

                    viewAdapter = new ListShiftExchangeAdapter(ShiftExchangeActivity.this, listData.getReturnValue());

                    // get listView from activity
                    //list = (ListView) findViewById(R.id.lstShiftExchange);

                    // set listadapter
                    listSE.setAdapter(viewAdapter);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ShiftExchangeActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ShiftExchangeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListShiftExchange> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void setListViewShiftEx() {
        //viewAdapter = new ListShiftExchangeAdapter(ShiftExchangeActivity.this, R.layout.list_shift_exchange, listData.getReturnValue());
        //listSE.setAdapter(viewAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        getListShiftEx();
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
        if (id == R.id.action_item_one) {
            Intent intent = new Intent(ShiftExchangeActivity.this, ListDraftShiftExchangeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
