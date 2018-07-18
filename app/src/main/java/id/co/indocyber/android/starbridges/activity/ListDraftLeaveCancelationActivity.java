package id.co.indocyber.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
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
import android.widget.ListView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.co.indocyber.android.starbridges.adapter.ListDraftLeaveCancelationAdapter;
import id.co.indocyber.android.starbridges.model.ListDraftLeaveCancelation.ListDraftLeaveCancelation;
import id.co.indocyber.android.starbridges.model.ListDraftLeaveCancelation.ReturnValue;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftLeaveCancelationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    // declare komp lstDraftLeaveCancelation
    ListView lstDraftLeaveCancelation;

    // declare adapter
    ListDraftLeaveCancelationAdapter adapter;

    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;

    private String idSelected="";

    List<String> lstIdSelected=new ArrayList<>();

    ListDraftLeaveCancelation data;

    FloatingActionButton fabAddDraftLeaveCancelation;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ReturnValue data1=data.getReturnValue().get(position);
//        listData.getReturnValue().remove(data);
//        viewAdapter=new ListDraftCorrectionAdapter(DraftCorrectionListActivity.this,R.layout.lst_correction_draft2, listData.getReturnValue());
//        list.setAdapter(viewAdapter);

        Intent intent=new Intent(ListDraftLeaveCancelationActivity.this, LeaveCancelationDetailActivity.class);
        intent.putExtra("id", data1.getID());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_leave_cancelation);

        fabAddDraftLeaveCancelation=(FloatingActionButton)findViewById(R.id.fabAddDraftLeaveCancelation);

        setTitle("Draft Leave Cancelation");
        getListDraftLeaveCancelation();


        fabAddDraftLeaveCancelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListDraftLeaveCancelationActivity.this, LeaveCancelationDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void deleteCheckedDraft(){
        apiInterface = APIClient.deleteDraftCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftLeaveCancelationActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), lstIdSelected+"");
        Call<MessageReturn> call3 = apiInterface.deleteDraftCancelation(body);
        call3.enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                progressDialog.dismiss();
                MessageReturn data = response.body();

                if (data != null && data.isIsSucceed()) {
                    Toast.makeText(ListDraftLeaveCancelationActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                    // delete list id on checkbox
                    ListDraftLeaveCancelationAdapter.listID.clear();

                    // call list draft
                    getListDraftLeaveCancelation();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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

    public void getListDraftLeaveCancelation(){
        progressDialog = new ProgressDialog(ListDraftLeaveCancelationActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        apiInterface = APIClient.getListDraftLeaveCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<ListDraftLeaveCancelation> call3 = apiInterface.getListDraftLeaveCancelation();
        call3.enqueue(new Callback<ListDraftLeaveCancelation>() {
            @Override
            public void onResponse(Call<ListDraftLeaveCancelation> call, Response<ListDraftLeaveCancelation> response) {
                progressDialog.dismiss();
                data = response.body();

                if (data != null) {

                    // pass context and data to the custor adapter
                    adapter = new ListDraftLeaveCancelationAdapter(ListDraftLeaveCancelationActivity.this, data.getReturnValue());

                    // get lstDraftLeaveCancelation from activity
                    lstDraftLeaveCancelation = (ListView) findViewById(R.id.lstDraftLeaveCancelation);

                    // set listadapter
                    lstDraftLeaveCancelation.setAdapter(adapter);
                    lstDraftLeaveCancelation.setOnItemClickListener(ListDraftLeaveCancelationActivity.this);

                    lstDraftLeaveCancelation.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                    lstDraftLeaveCancelation.setItemsCanFocus(true);
                    lstDraftLeaveCancelation.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                        @Override
                        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

                            // Capture total checked items
                            final int checkedCount = lstDraftLeaveCancelation.getCheckedItemCount();
                            // Set the CAB title according to total checked items
                            actionMode.setTitle(checkedCount + " Selected");
                            // Calls toggleSelection method from ListViewAdapter Class
                            adapter.toggleSelection(i);


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
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ListDraftLeaveCancelationActivity.this);
                                    alert.setTitle("Confirmation");
                                    alert.setTitle("This information will be deleted");

                                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            // Calls getSelectedIds method from ListViewAdapter Class
                                            final SparseBooleanArray selected = adapter
                                                    .getSelectedIds();
//                                idSelected= SharedPreferenceUtils.getSetting(ListDraftLeaveCancelationActivity.this, "lstIdSelected", "");
                                            lstIdSelected=new ArrayList<>();
                                            // Captures all selected ids with a loop
                                            for (int i2 = (selected.size() - 1); i2 >= 0; i2--) {
                                                if (selected.valueAt(i2)) {
                                                    ReturnValue selecteditem = adapter
                                                            .getItem(selected.keyAt(i2));
                                                    // Remove selected items following the ids
                                                    lstIdSelected.add(selecteditem.getID());
                                                    adapter.remove(selecteditem);
                                                }
                                            }
                                            idSelected=lstIdSelected.toString();
                                            Log.d("lstIdSelected",idSelected);
                                            deleteCheckedDraft();
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

                            adapter.removeSelection();

                        }
                    });

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveCancelationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDraftLeaveCancelation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
