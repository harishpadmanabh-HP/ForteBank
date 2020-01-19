package com.hp.fortebank;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.harishpadmanabh.apppreferences.AppPreferences;

public class Home extends AppCompatActivity {

    private TextView Name;
    private TextView Accnum;
    private TextView Phnnum;
    private TextView AccBal;
    private CardView withdrawCard;
    private CardView transactionsCard;
    private CardView benificarycard;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        setDetails();

    }

    private void setDetails() {
        Name.setText("Welcome "+appPreferences.getData("uname"));
        Accnum.setText("Acc No. "+appPreferences.getData("uaccno"));
        Phnnum.setText("Phone No. "+appPreferences.getData("uphone"));
        AccBal.setText("Account Balance : "+appPreferences.getData("ubal"+" Rs"));


    }

    private void initView() {
        Name = (TextView) findViewById(R.id.Name);
        Accnum = (TextView) findViewById(R.id.Accnum);
        Phnnum = (TextView) findViewById(R.id.Phnnum);
        AccBal = (TextView) findViewById(R.id.AccBal);
        withdrawCard = (CardView) findViewById(R.id.withdrawCard);
        transactionsCard = (CardView) findViewById(R.id.transactionsCard);
        benificarycard = (CardView) findViewById(R.id.benificarycard);
    }
}
