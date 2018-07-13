package id.co.indocyber.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import id.co.indocyber.android.starbridges.adapter.ListDraftOvertimeAdapter;
import id.co.indocyber.android.starbridges.adapter.ListDraftOvertimeAdapter2;
import id.co.indocyber.android.starbridges.model.ListDraftOvertime.ListDraftOvertime;
import id.co.indocyber.android.starbridges.model.ListDraftOvertime.ReturnValue;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 19/06/2018 12:01
public class ListDraftOvertimeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    // declare komp listView
    ListView listView;

    // declare adapter
    ListDraftOvertimeAdapter adapter;
    // 19/06/2018 12:01
    ListDraftOvertimeAdapter2 adapter2;
    ListDraftOvertime data;
    APIInterfaceRest apiInterface;
    ProgressDialog progressDialog;
    private String selectedId;
    List<String> listSelectedId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_draft_overtime);
        setTitle("Draft Overtime");
        getListDraftOvertime();

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
//        if (id == R.id.action_item_one) {
//            Intent intent = new Intent(ListDraftOvertimeActivity.this, OvertimeDetailActivity.class);
//            startActivity(intent);
//            return true;
//        }
//
//        // delete
//        if (id == R.id.action_item_two) {
//            // get list data from checkbox
//            String listid = ListDraftOvertimeAdapter.listID.toString();
//
//            //Toast.makeText(ListDraftLeaveRequestActivity.this, "List : " + listid, Toast.LENGTH_SHORT).show();
//            deleteDraftOvertime(listid);
//
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    public void getListDraftOvertime() {
        apiInterface = APIClient.getListDraftOvertime(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftOvertimeActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        //Call<LeaveRequest> call3 = apiInterface.getListLeaveRequest("");
        Call<ListDraftOvertime> call3 = apiInterface.getListDraftOvertime();
        call3.enqueue(new Callback<ListDraftOvertime>() {
            @Override
            public void onResponse(Call<ListDraftOvertime> call, Response<ListDraftOvertime> response) {
                progressDialog.dismiss();
                data = response.body();

                if (data != null) {

                    // pass context and data to the custor adapter
//                    adapter = new ListDraftOvertimeAdapter(ListDraftOvertimeActivity.this, data.getReturnValue());
                    // 19/06/2018 12:07
                    adapter2 = new ListDraftOvertimeAdapter2(ListDraftOvertimeActivity.this, R.layout.list_draft_overtime2, data.getReturnValue());
                    // get listView from activity
                    listView = (ListView) findViewById(R.id.listDraftOvertime);

                    // set listadapter
                    listView.setAdapter(adapter2);
                    listView.setOnItemClickListener(ListDraftOvertimeActivity.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                    listView.setItemsCanFocus(true);
                    listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                        @Override
                        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                            final int checkedCount = listView.getCheckedItemCount();
                            // Set the CAB title according to total checked items
                            mode.setTitle(checkedCount + " Selected");
                            // Calls toggleSelection method from ListViewAdapter Class
                            adapter2.toggleSelection(position);
                        }

                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            MenuInflater inflater = mode.getMenuInflater();
                            inflater.inflate(R.menu.menu_draft_correction, menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            return false;
                        }

                        @Override
                        public boolean onActionItemClicked(final ActionMode actionMode, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.deleteDraft:
                                    AlertDialog.Builder alert = new AlertDialog.Builder(ListDraftOvertimeActivity.this);
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
                                            deleteDraftOvertime(selectedId);
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
                        public void onDestroyActionMode(ActionMode mode) {
                            adapter2.removeSelection();
                        }
                    });


                } else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //Toast.makeText(ListDraftLeaveRequestActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        //Toast.makeText(ListDraftLeaveRequestActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListDraftOvertime> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void deleteDraftOvertime(String listid) {
        apiInterface = APIClient.deleteDraftCancelation(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(ListDraftOvertimeActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), listid);
        Call<MessageReturn> call3 = apiInterface.deleteDraftOvertime(body);
        call3.enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                progressDialog.dismiss();
                MessageReturn data = response.body();

                if (data != null && data.isIsSucceed()) {
                    Toast.makeText(ListDraftOvertimeActivity.this, data.getMessage(), Toast.LENGTH_LONG).show();

                    // delete list id on checkbox
                    ListDraftLeaveCancelationAdapter.listID.clear();

                    // call list draft
                    getListDraftOvertime();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ListDraftOvertimeActivity.this, jObjError.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ListDraftOvertimeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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

    public void addOvertime(View view) {
        Intent intent = new Intent(ListDraftOvertimeActivity.this, OvertimeDetailActivity.class);
        startActivity(intent);
    }

    // 19/06/2018 12:01
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReturnValue singleData = data.getReturnValue().get(position);
        Intent intent = new Intent(ListDraftOvertimeActivity.this, OvertimeDetailActivity.class);
        intent.putExtra("id", data.getReturnValue().get(position).getID());
        startActivity(intent);
    }
}
