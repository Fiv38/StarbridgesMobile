package id.co.indocyber.android.starbridges.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.co.indocyber.android.starbridges.R;
import id.co.indocyber.android.starbridges.model.Authentication;
import id.co.indocyber.android.starbridges.model.AuthenticationError.AuthenticationError;
import id.co.indocyber.android.starbridges.model.MessageReturn.MessageReturn;
import id.co.indocyber.android.starbridges.model.OPost;
import id.co.indocyber.android.starbridges.model.versioning.Versioning;
import id.co.indocyber.android.starbridges.network.APIClient;
import id.co.indocyber.android.starbridges.network.APIInterfaceRest;
import id.co.indocyber.android.starbridges.reminder.alarmManager.AlarmManagerMasuk;
import id.co.indocyber.android.starbridges.reminder.alarmManager.AlarmManagerPulang;
import id.co.indocyber.android.starbridges.utility.GlobalVar;
import id.co.indocyber.android.starbridges.utility.SessionManagement;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;
import java.text.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1000;
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private String IMEI;
    public Boolean versionEqual;
    //Location references
    protected static final String TAG = "LocationOnOff";
    final static int REQUEST_LOCATION = 199;
    private GoogleApiClient googleApiClient;
    SessionManagement session;
    SharedPreferences pref;
    TextView txtFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setFinishOnTouchOutside(true);
        // Set up the login form.
        mUsernameView = (EditText) findViewById(R.id.txt_username);
        mPasswordView = (EditText) findViewById(R.id.txt_password);
        txtFooter = (TextView) findViewById(R.id.txtFooter);

        Date date = new Date();

        DateFormat df = new SimpleDateFormat("yyyy");
        String year = "";
        try {
            year = df.format(date);

        } catch (Exception e) {
            year = "2017";
        }

//        txtFooter.setText("Copyright " + year + " PT. Indocyber Global Teknologi\nAll Right Reserved");

//        alarmManager.start(getApplicationContext());
//        checkAppVersion();

        session = new SessionManagement(getApplicationContext());

        mUsernameView.setText("");
        mPasswordView.setText("");
        checkIMEIPermission();


        Button mSignInButton = (Button) findViewById(R.id.btn_sign_in);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });

        TextView mRegisterButton = (TextView) findViewById(R.id.lbl_register_imei);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                registerIMEI();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) LoginActivity.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(LoginActivity.this)) {
//            Toast.makeText(LoginActivity.this, "Gps already enabled", Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if (!hasGPSDevice(LoginActivity.this)) {
            Toast.makeText(LoginActivity.this, "Gps not Supported", Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(LoginActivity.this)) {
            Log.e("Starbridges", "Gps already enabled");
            Toast.makeText(LoginActivity.this, "Gps not enabled", Toast.LENGTH_SHORT).show();
            enableLoc();
        } else {
            Log.e("Starbridges", "Gps already enabled");
            //Toast.makeText(LoginActivity.this, "Gps already enabled", Toast.LENGTH_SHORT).show();
        }

        setAlarmMasukPulang(getApplicationContext());
    }

    private boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }

    private void setAlarmMasukPulang(Context context) {
        boolean isAlarmMasuk = (PendingIntent.getBroadcast(context, 0,
                new Intent("id.co.indocyber.android.starbridges.ACTION_NOTIFY_MASUK"),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (isAlarmMasuk) {
            Log.d("myTag", "Alarm Masuk is already active");
//            Toast.makeText(context, "Alarm Masuk is already active", Toast.LENGTH_SHORT).show();
        } else {
            AlarmManagerMasuk.start(context);
            Log.d("myTag", "AlarmMasuk is Created");
//            Toast.makeText(context, "AlarmMasuk is Created", Toast.LENGTH_SHORT).show();
        }

        boolean isAlarmKeluar = (PendingIntent.getBroadcast(context, 1,
                new Intent("id.co.indocyber.android.starbridges.ACTION_NOTIFY_PULANG"),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (isAlarmKeluar) {
            Log.d("myTag", "AlarmPulang is already active");
//            Toast.makeText(context, "Alarm Pulang is already active", Toast.LENGTH_SHORT).show();
        } else {
            AlarmManagerPulang.start(context);
            Log.d("myTag", "AlarmPulang is Created");
//            Toast.makeText(context, "AlarmPulang is Created", Toast.LENGTH_SHORT).show();
        }

    }

    private void enableLoc() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            googleApiClient.connect();
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d("Location error", "Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(LoginActivity.this, REQUEST_LOCATION);

                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                    }
                }
            });
        }
    }

    private void registerIMEI() {
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            //checkIMEIPermission();
            APIInterfaceRest regService = APIClient.getClient().create(APIInterfaceRest.class);
            Call<OPost> call = regService.postRegisterImei(username, password, IMEI);
            call.enqueue(new Callback<OPost>() {
                @Override
                public void onResponse(Call<OPost> call, Response<OPost> response) {

                    if (response.body().getIsSucceed()) {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Register succesful. Now you can login", Toast.LENGTH_LONG).show();
                    } else {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<OPost> call, Throwable t) {
                    showProgress(false);
                    Toast.makeText(LoginActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            //checkIMEIPermission();
            APIInterfaceRest loginService = APIClient.getClient().create(APIInterfaceRest.class);
            Call<Authentication> call = loginService.getAuthentication("password", username, password, "ngAuthApp", IMEI);
            call.enqueue(new Callback<Authentication>() {
                @Override
                public void onResponse(Call<Authentication> call, Response<Authentication> response) {

                    if (response.message().equals("OK")) {
                        showProgress(false);
                        Intent home = new Intent(LoginActivity.this, HomeActivity.class);

                        String loginName_sp = response.body().getLoginName();
                        String fullName_sp = response.body().getFullName();
                        String token_sp = response.body().getTokenType() + " " + response.body().getAccessToken();
                        String expires_sp = response.body().getExpires();
                        String nik_sp = response.body().getNik();
                        String employee_id = response.body().getEmployeeID();
                        String locationId = response.body().getLocationID();
                        String locationName = response.body().getLocation();
                        String attendancePrivilege = response.body().getAttendancePrivilege();
                        //String employee_id_sp =

                        session.createLoginSession(loginName_sp, fullName_sp, token_sp, expires_sp, nik_sp, employee_id, locationName, locationId, attendancePrivilege);

                        GlobalVar.setToken(token_sp);
                        GlobalVar.setLoginName(loginName_sp);
                        GlobalVar.setFullname(fullName_sp);
                        GlobalVar.setNIK(nik_sp);
                        GlobalVar.setLocation(locationName);
                        GlobalVar.setLocationId(locationId);
                        GlobalVar.setAttendancePrivilege(attendancePrivilege);

                        startActivity(home);
                        finish();
                    }
                    else  if (response.errorBody() != null ) {
                        showProgress(false);

                        try{
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Gson gson=new Gson();

                            JsonParser parser = new JsonParser();
                            JsonElement mJson =  parser.parse(jObjError+"");

                            AuthenticationError authenticationError=gson.fromJson(mJson, AuthenticationError.class);
                            Toast.makeText(LoginActivity.this, authenticationError.getErrorDescription(), Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }



                    }
                    else
                    {
                        showProgress(false);

                        Toast.makeText(LoginActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Authentication> call, Throwable t) {
                    showProgress(false);
                    Toast.makeText(LoginActivity.this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void validateLogin() {
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else {
            if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                mPasswordView.setError(getString(R.string.error_invalid_password));
                focusView = mPasswordView;
                cancel = true;
            }
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            //checkIMEIPermission();
            APIInterfaceRest loginService = APIClient.getClient().create(APIInterfaceRest.class);
            Call<MessageReturn> call = loginService.getValidation(username, password, IMEI);
            call.enqueue(new Callback<MessageReturn>() {
                @Override
                public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {

                    if (response.body().isIsSucceed()) {
                        showProgress(false);
                        attemptLogin();
                    } else {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<MessageReturn> call, Throwable t) {
                    showProgress(false);
                    Toast.makeText(LoginActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isPasswordValid(String password) {

        if (password.length() < 4) {
            return false;
        }
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @SuppressLint("MissingPermission")
    public void getIMEI(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        IMEI = telephonyManager.getDeviceId();
//        IMEI="352875087316146";// maryuri
//        IMEI="865684032897881";
//        IMEI="863263034362087"; // Dhaba
//        IMEI="8680420031440079";// Dhaba new
//        IMEI="866941024390260";// Pak rio
//        IMEI="861558031284990"; //Bang rizal

    }

    public void checkIMEIPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            getIMEI(this);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getIMEI(this);
            } else {
                Toast.makeText(LoginActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private void checkAppVersion() {
//        String versionName = "";
//        String versionCode = "";
////        Boolean versionEqual = false;
//        try {
//            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
//            versionName = packageInfo.versionName;
//            versionCode = String.valueOf(packageInfo.versionCode);
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        final APIInterfaceRest apiInterface = APIClient.getClient().create(APIInterfaceRest.class);
//        final Call<Versioning> call3 = apiInterface.checkAppVerion();
//        final String finalVersionCode = versionCode;
//        final String finalVersionName = versionName;
//        call3.enqueue(new Callback<Versioning>() {
//            @Override
//            public void onResponse(Call<Versioning> call, Response<Versioning> response) {
////                Versioning data = response.body();
//                if(response.isSuccessful())
//                {
//                    String versionCodeAPi = response.body().getReturnValue().getVersionCode().toString();
//                    String versionNameApi = response.body().getReturnValue().getVersionName().toString();
//                    if (versionCodeAPi.equalsIgnoreCase(String.valueOf(finalVersionCode)) &&
//                            versionNameApi.equalsIgnoreCase(finalVersionName)) {
//                        versionEqual = true;
//                    } else {
//                        versionEqual = false;
//                    }
//
//                    if (versionEqual != false) {
//                        if (session.isLoggedIn()) {
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                            finish();
//                        }
//                    } else {
//                        alertNotif("", "your version is out of date please update ");
//                    }
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Versioning> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Something went wrong...Please try again!", Toast.LENGTH_LONG).show();
//                call.cancel();
//            }
//        });
//    }
//
//    private void alertNotif(String title, String message) {
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle(title);
//        alert.setMessage(message);
//        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Uri starBridges = Uri.parse("https://play.google.com/store/apps/details?id=id.co.indocyber.android.starbridges");
//                Intent intent = new Intent(Intent.ACTION_VIEW, starBridges);
//                startActivity(intent);
//            }
//        });
//        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                finish();
//            }
//        });
//        alert.show();
//    }
}

