package id.co.indocyber.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.co.indocyber.android.starbridges.adapter.ListDraftLeaveRequestAdapter;
import id.co.indocyber.android.starbridges.adapter.ListDraftLeaveRequestAdapter2;
import id.co.indocyber.android.starbridges.model.deleteleaverequest.DeleteLeaveRequest;
import id.co.indocyber.android.starbridges.model.listdraftleaverequest.ListDraftLeaveRequest;
import id.co.indocyber.android.starbridges.model.listdraftleaverequest.ReturnValue;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDraftLeaveRequestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // declare komp listView
    ListView listView;

    // declare adapter
    ListDraftLeaveRequestAdapter adapter;
    ListDraftLeaveRequestAdapter2 adapter2;
    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;
    ListDraftLeaveRequest listdata;
    private String selectedId;
    List<String> listSelectedId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_leave_request);
        setTitle("Draft Leave Request");
//        getListDraftLeaveRequest();
        FloatingActionButton tambahDraft = (FloatingActionButton) findViewById(R.id.fabAddListDraftLeaveRequest);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.leaverequest, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        // add new leave request
//        if(id == R.id.action_item_one ){
//            Intent intent = new Intent(ListDraftLeaveRequestActivity.this, LeaveRequestDetailActivity.class);
//            startActivity(intent);
//            return true;
//        }
//
//        // delete
//        if(id == R.id.action_item_two ){
//            // get list data from checkbox
//            String listid = ListDraftLeaveRequestAdapter.listID.toString();
//
//            //Toast.makeText(ListDraftLeaveRequestActivity.this, "List : " + listid, Toast.LENGTH_SHORT).show();
//            deleteCheckedDraft(listid);
//
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void deleteCheckedDraft(String listid) {
        apiInterface = APIClient.deleteLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftLeaveRequestActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), listid);
        Call<DeleteLeaveRequest> call3 = apiInterface.deleteLeaveRequst(body);
        call3.enqueue(new Callback<DeleteLeaveRequest>() {
            @Override
            public void onResponse(Call<DeleteLeaveRequest> call, Response<DeleteLeaveRequest> response) {
                progressDialog.dismiss();
                DeleteLeaveRequest data = response.body();

                if (data != null && data.getIsSucceed()) {
                    Toast.makeText(ListDraftLeaveRequestActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                    // delete list id on checkbox
                    ListDraftLeaveRequestAdapter.listID.clear();

                    // call list draft
                    getListDraftLeaveRequest();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveRequestActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveRequestActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<DeleteLeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void getListDraftLeaveRequest() {
        apiInterface = APIClient.getListDraftLeaveRequest(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftLeaveRequestActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<ListDraftLeaveRequest> call3 = apiInterface.getListDraftLeaveRequest();
        call3.enqueue(new Callback<ListDraftLeaveRequest>() {
            @Override
            public void onResponse(Call<ListDraftLeaveRequest> call, Response<ListDraftLeaveRequest> response) {
                progressDialog.dismiss();
                listdata = response.body();

                if (listdata != null) {

                    // pass context and data to the custor adapter
//                    adapter = new ListDraftLeaveRequestAdapter(ListDraftLeaveRequestActivity.this, data.getReturnValue());
                    //13/06/18
                    adapter2 = new ListDraftLeaveRequestAdapter2(ListDraftLeaveRequestActivity.this, R.layout.list_draft_leave_request2, listdata.getReturnValue());
                    // get listView from activity
                    listView = (ListView) findViewById(R.id.listDraftLeaveRequest);

                    // set listadapter
//                    listView.setAdapter(adapter);
                    // 13/06/18
                    listView.setAdapter(adapter2);
                    listView.setOnItemClickListener(ListDraftLeaveRequestActivity.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                    listView.setItemsCanFocus(true);
                    listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                        @Override
                        public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

                            // Capture total checked items
                            final int checkedCount = listView.getCheckedItemCount();
                            // Set the CAB title according to total checked items
                            actionMode.setTitle(checkedCount + " Selected");
                            // Calls toggleSelection method from ListViewAdapter Class
                            adapter2.toggleSelection(i);


                        }

                        @Override
                        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                            MenuInflater inflater = actionMode.getMenuInflater();
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
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ListDraftLeaveRequestActivity.this);
                                    alert.setTitle("Confirmation");
                                    alert.setTitle("This information will be deleted");

                                    alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            // Calls getSelectedIds method from ListViewAdapter Class
                                            final SparseBooleanArray selected = adapter2
                                                    .getSelectedIds();
//                                idSelected= SharedPreferenceUtils.getSetting(DraftCorrectionListActivity.this, "lstIdSelected", "");
                                            listSelectedId = new ArrayList<>();
                                            // Captures all selected ids with a loop
                                            for (int i2 = (selected.size() - 1); i2 >= 0; i2--) {
                                                if (selected.valueAt(i2)) {
                                                    ReturnValue selecteditem = adapter2
                                                            .getItem(selected.keyAt(i2));
                                                    // Remove selected items following the ids
                                                    listSelectedId.add(selecteditem.getID());
                                                    adapter2.remove(selecteditem);
                                                }
                                            }
                                            selectedId = listSelectedId.toString();
                                            Log.d("lstIdSelected", selectedId);
                                            deleteCheckedDraft(selectedId);
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

                            adapter2.removeSelection();

                        }
                    });
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftLeaveRequestActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftLeaveRequestActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListDraftLeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReturnValue data = listdata.getReturnValue().get(position);
        Intent intent = new Intent(ListDraftLeaveRequestActivity.this, LeaveRequestDetailActivity.class);
        intent.putExtra("ID", data.getID());
        startActivity(intent);
    }

    public void addListDraftLeaveRequest(View view) {
        Intent intent = new Intent(ListDraftLeaveRequestActivity.this, LeaveRequestDetailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListDraftLeaveRequest();
    }
}

