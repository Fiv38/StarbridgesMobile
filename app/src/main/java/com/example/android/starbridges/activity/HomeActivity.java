package com.example.android.starbridges.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.starbridges.R;
import com.example.android.starbridges.utility.GlobalVar;

public class HomeActivity extends AppCompatActivity {
    private String username, token;
private TextView mUsernameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mUsernameView=(TextView) findViewById(R.id.lbl_username);
        username = GlobalVar.getFullName();
        mUsernameView.setText("Hello,\n"+username);


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
