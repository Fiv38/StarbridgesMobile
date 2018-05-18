package com.example.android.starbridges.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.starbridges.R;
import com.example.android.starbridges.model.getimage.GetImage;
import com.example.android.starbridges.network.APIClient;
import com.example.android.starbridges.network.APIInterfaceRest;
import com.example.android.starbridges.utility.GlobalVar;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private String username,fullname, token;
    ProgressDialog progressDialog;
    APIInterfaceRest apiInterface;
    CircleImageView imageView;
private TextView mUsernameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView = (CircleImageView)findViewById(R.id.profile_image);
        mUsernameView=(TextView) findViewById(R.id.lbl_username);
        username = GlobalVar.getUsername();
        fullname = GlobalVar.getFullname();
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
                progressDialog.dismiss();
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
                }
            }

            @Override
            public void onFailure(Call<GetImage> call, Throwable t) {

            }
        });
        }

    public void showStartEndDate(View view) {
        Intent startEndDay = new Intent(this, StartEndDayActivity.class);
        startActivity(startEndDay);
    }

    public void showCheckInOut(View view) {
        Intent checkInOut = new Intent(this, CheckInOutActivity.class);
        startActivity(checkInOut);
    }

    public void showHistory(View view) {
        Intent histories = new Intent(this, HistoryFilterActivity.class);
        startActivity(histories);
    }

    public void showCorrection(View view){
        Intent correction = new Intent(this, CorrectionActivity.class);
        startActivity(correction);
    }

}
