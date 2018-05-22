package com.example.android.starbridges.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.Authentication;
import com.example.android.starbridges.model.OPost;
import com.example.android.starbridges.model.RegIMEI;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;
import com.example.android.starbridges.utility.SessionManagement;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Call;
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
    //Location references
    protected static final String TAG = "LocationOnOff";
    final static int REQUEST_LOCATION = 199;
    private GoogleApiClient googleApiClient;
    SessionManagement session;
    SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setFinishOnTouchOutside(true);
        // Set up the login form.
        mUsernameView = (EditText) findViewById(R.id.txt_username);
        mPasswordView = (EditText) findViewById(R.id.txt_password);

        session = new SessionManagement(getApplicationContext());

        mUsernameView.setText("");
        mPasswordView.setText("");
        checkIMEIPermission();


        Button mSignInButton = (Button) findViewById(R.id.btn_sign_in);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.btn_register);
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
            Toast.makeText(LoginActivity.this, "Gps already enabled", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(LoginActivity.this, "Gps already enabled", Toast.LENGTH_SHORT).show();
        }

        if (session.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

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

                            Log.d("Location error","Location error " + connectionResult.getErrorCode());
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
        }}

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
                    Toast.makeText(LoginActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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
                        String token_sp = response.body().getTokenType()+" "+ response.body().getAccessToken();
                        String expires_sp = response.body().getExpires();
                        String nik_sp = response.body().getNik();
                        String employee_id_sp = response.body().getEmployeeID();

                        session.createLoginSession(loginName_sp,fullName_sp,token_sp,expires_sp, nik_sp, employee_id_sp);

                        GlobalVar.setToken(token_sp);
                        GlobalVar.setLoginName(loginName_sp);
                        GlobalVar.setFullname(fullName_sp);

                        startActivity(home);
                        finish();
                    } else {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Authentication> call, Throwable t) {
                    showProgress(false);
                    Toast.makeText(LoginActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isPasswordValid(String password) {

        if (password.length()<4){
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
    public void getIMEI (Activity activity){
        TelephonyManager telephonyManager = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        IMEI= telephonyManager.getDeviceId();
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



}

