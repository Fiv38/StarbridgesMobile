package id.co.indocyber.android.starbridges.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;

import java.net.Inet4Address;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.indocyber.android.starbridges.model.getimage.GetImage;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private String username,fullname, token;
    ProgressDialog progressDialog;
    APIInterfaceRest apiInterface;
    CircleImageView imageView;
    private TextView mUsernameView;
    private String attendancePrivilege="";
    SessionManagement session;
    Button btnSignOut;
    Button AttendanceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = (CircleImageView)findViewById(R.id.profile_image);
        session = new SessionManagement(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();
        String token_sp = user.get(SessionManagement.KEY_TOKEN);
        String loginName_sp = user.get(SessionManagement.KEY_LOGINNAME);
        String fullName_sp = user.get(SessionManagement.KEY_FULLNAME);
        String tokenExpiredDate=user.get(SessionManagement.KEY_EXPIRES);
        String location=user.get(SessionManagement.KEY_LOCATION);
        String locationId=user.get(SessionManagement.KEY_LOCATION_ID);
        String employeeId=user.get(SessionManagement.KEY_EMPLOYEE_ID);
        attendancePrivilege=user.get(SessionManagement.KEY_ATTENDANCE_PRIVILEGE);

                //Thu, 31 May 2018 01:34:37 GMT
        DateFormat df = new SimpleDateFormat("EEE, dd MMMM yyyy kk:mm:ss z", Locale.ENGLISH);
        Date tokenExpired;
        try{
            tokenExpired =  df.parse(tokenExpiredDate);
            Date checkDate = new Date();
            if(checkDate.after(tokenExpired))
            {
                session.logoutUser();
                Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e)
        {
            Log.d("error parse", "error parse");
        }

        GlobalVar.setEmployeeId(employeeId);
        GlobalVar.setToken(token_sp);
        GlobalVar.setLoginName(loginName_sp);
        GlobalVar.setFullname(fullName_sp);
        GlobalVar.setLocation(location);
        GlobalVar.setLocationId(locationId);
        GlobalVar.setAttendancePrivilege(attendancePrivilege);
        username = GlobalVar.loginName();
        fullname = GlobalVar.getFullname();
        mUsernameView=(TextView) findViewById(R.id.lbl_username);
        mUsernameView.setText("Hello,\n"+fullname);

        loadingImage();

    }

    void loadingImage(){


        apiInterface = APIClient.getImage(GlobalVar.getToken()).create(APIInterfaceRest.class);
        progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        // khusus logType di hardcode -> LogType -> Start Day
        Call<GetImage> call3 = apiInterface.getImage();
        call3.enqueue(new Callback<GetImage>() {

            @Override
            public void onResponse(Call<GetImage> call, Response<GetImage> response) {

                GetImage data = response.body();

                if (data != null && data.getIsSucceed()) {
                    //Toast.makeText(HomeActivity.this, "Image terambil", Toast.LENGTH_LONG).show();
                    //finish();
                    byte[] decodedString = Base64.decode(data.getReturnValue(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imageView.setImageBitmap(bitmap);

                } else {
                    try {
                        //JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(HomeActivity.this, "Failed to get image", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
//                    session.logoutUser();
//                    finish();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetImage> call, Throwable t) {
                Toast.makeText(HomeActivity.this, getString(R.string.error_connection), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
//                session.logoutUser();
//                finish();
            }
        });
        }

    public void showStartEndDate(View view) {
        if (attendancePrivilege.equals("False")&&attendancePrivilege!=null){
            AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
            alert.setTitle("Alert");
            alert.setTitle("You do not have privilege to access this menu");
            alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alert.show();
        } else {
            Intent startEndDay = new Intent(this, StartEndDayActivity.class);
            startActivity(startEndDay);
        }
    }

    public void showCheckInOut(View view) {
        if (attendancePrivilege.equals("False")&&attendancePrivilege!=null){
            AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
            alert.setTitle("Alert");
            alert.setTitle("You do not have privilege to access this menu");
            alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alert.show();
        } else {
            Intent checkInOut = new Intent(this, CheckInOutActivity.class);
            startActivity(checkInOut);
        }
    }

    public void showHistory(View view) {
        Intent histories = new Intent(this, HistoryFilterActivity.class);
        startActivity(histories);
    }

    public void showCorrection(View view){
        Intent correction = new Intent(this, CorrectionFilterActivity.class);
        startActivity(correction);
    }
    public void signOut(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
        alert.setTitle("Confirmation");
        alert.setTitle("Are You Sure to Sign Out?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                session.logoutUser();
                finish();
            }
        });

        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();

    }

    public void showOvertime(View view){
        Intent overtime = new Intent(this, OvertimeActivity.class);
        startActivity(overtime);

    }

    public void showCancelation(View view){
        Intent cancelation = new Intent(this, LeaveCancelationActivity.class);
        startActivity(cancelation);
    }

    public void showLeaveRequest(View view) {
        Intent leaveRequest = new Intent(this, LeaveRequestActivity.class);
        startActivity(leaveRequest);
    }

    public void showMedicalClaim(View view){
        Intent medicalClaim = new Intent(this, MedicalClaimActivity.class);
        startActivity(medicalClaim);
    }

    public void showReimburse(View view){
        Intent reimburse = new Intent(this, ReimburseActivity.class);
        startActivity(reimburse);
    }

    public void showShiftExchange(View view){
        Intent shiftExchange = new Intent(this, ShiftExchangeActivity.class);
        startActivity(shiftExchange);
    }

    public void showLoan(View view){
        Intent shiftExchange = new Intent(this, LoanSelectorActivity.class);
        startActivity(shiftExchange);
    }

}
