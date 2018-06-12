package com.example.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.adapter.ListDraftShiftExchangeAdapter;
import com.example.android.starbridges.model.ListDraftShiftExchange.ListDraftShiftExchange;
import com.example.android.starbridges.model.ListDraftShiftExchange.ReturnValue;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftShiftExchangeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView list;
    private ProgressDialog progressDialog;
    ImageView imgDeleteCDrafts;
    ListDraftShiftExchangeAdapter viewAdapter;
    ListDraftShiftExchange listData;
    private String idSelected="";
    List<String> lstIdSelected=new ArrayList<>();
    FloatingActionButton fabAddDraftShiftExchange;


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ReturnValue data=listData.getReturnValue().get(position);
//        listData.getReturnValue().remove(data);
//        viewAdapter=new ListDraftCorrectionAdapter(DraftCorrectionListActivity.this,R.layout.lst_correction_draft2, listData.getReturnValue());
//        list.setAdapter(viewAdapter);

        Intent intent=new Intent(this, ShiftExchangeDetailActivity.class);
        intent.putExtra("id", data.getID());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_shift_exchange);
        setTitle("Shift Exchange Draft");

        list =(ListView) findViewById(R.id.lstDraftShiftExchange);
        fabAddDraftShiftExchange=(FloatingActionButton)findViewById(R.id.fabAddDraftShiftExchange);

        fabAddDraftShiftExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListDraftShiftExchangeActivity.this, ShiftExchangeDetailActivity.class);
                startActivity(intent);
            }
        });
        //imgDeleteCDrafts=(ImageView)findViewById(R.id.imgDeleteCDrafts);


//        getListDraftCorrection();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(Color.WHITE);
            }
        });
    }

    public void getListDraftShiftEx() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        final APIInterfaceRest apiInterface = APIClient.getListDraftShiftExchange(GlobalVar.getToken()).create(APIInterfaceRest.class);
        Call<ListDraftShiftExchange> call3 = apiInterface.getListDraftShiftExchange();
        call3.enqueue(new Callback<ListDraftShiftExchange>() {
            @Override
            public void onResponse(Call<ListDraftShiftExchange> call, Response<ListDraftShiftExchange> response) {
                progressDialog.dismiss();
                listData = response.body();
                if (listData != null && listData.getIsSucceed()) {

                    setListViewDraftShiftEx();



                    list.setOnItemClickListener(ListDraftShiftExchangeActivity.this);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftShiftExchangeActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftShiftExchangeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDraftShiftExchange> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void setListViewDraftShiftEx()
    {
        viewAdapter = new ListDraftShiftExchangeAdapter(ListDraftShiftExchangeActivity.this, R.layout.list_draft_shift_exchange, listData.getReturnValue());
        list.setAdapter(viewAdapter);

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        list.setItemsCanFocus(true);
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                actionMode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                viewAdapter.toggleSelection(i);


            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater=actionMode.getMenuInflater();
                inflater.inflate(R.menu.menu_draft_correction, menu);
                return true;

            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;

            }

            @Override
            public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.deleteDraft:
                        AlertDialog.Builder alert = new AlertDialog.Builder(ListDraftShiftExchangeActivity.this);
                        alert.setTitle("Confirmation");
                        alert.setTitle("This information will be deleted");

                        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                // Calls getSelectedIds method from ListViewAdapter Class
                                final SparseBooleanArray selected = viewAdapter
                                        .getSelectedIds();
//                                idSelected= SharedPreferenceUtils.getSetting(DraftCorrectionListActivity.this, "lstIdSelected", "");
                                lstIdSelected=new ArrayList<>();
                                // Captures all selected ids with a loop
                                for (int i2 = (selected.size() - 1); i2 >= 0; i2--) {
                                    if (selected.valueAt(i2)) {
                                        ReturnValue selecteditem = viewAdapter
                                                .getItem(selected.keyAt(i2));
                                        // Remove selected items following the ids
                                        lstIdSelected.add(selecteditem.getID());
                                        viewAdapter.remove(selecteditem);
                                    }
                                }
                                idSelected=lstIdSelected.toString();
                                Log.d("lstIdSelected",idSelected);
                                deleteDraftShiftEx();
                                // Close CAB
                                actionMode.finish();

                            }
                        });

                        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        alert.show();



                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

                viewAdapter.removeSelection();

            }
        });
    }

    public void deleteDraftShiftEx() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        getListDraftShiftEx();
    }
}
