package id.co.indocyber.android.starbridges.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import id.co.indocyber.android.starbridges.R;

public class LoanSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_selector);

    }

    public void showLoanHistory(View view){
        Intent shiftExchange = new Intent(this, HistoryFilterActivity.class);
        shiftExchange.putExtra("destination", "LoanHistory");
        startActivity(shiftExchange);
        finish();
    }

    public void showLoanTransaction(View view){
        Intent shiftExchange = new Intent(this, HistoryFilterActivity.class);
        shiftExchange.putExtra("destination", "LoanTransaction");
        startActivity(shiftExchange);
        finish();
    }

}
