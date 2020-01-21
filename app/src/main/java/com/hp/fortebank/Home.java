package com.hp.fortebank;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.Dashboard.Benificiary;
import com.hp.fortebank.Dashboard.History;
import com.hp.fortebank.Dashboard.Withdraw;

import dmax.dialog.SpotsDialog;

public class Home extends AppCompatActivity {

    private TextView Name;
    private TextView Accnum;
    private TextView Phnnum;
    private TextView AccBal;
    private CardView withdrawCard;
    private CardView transactionsCard;
    private CardView benificarycard;
    private AppPreferences appPreferences;
    private AlertDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        pd = new SpotsDialog(this,R.style.CustomAlert);
        pd.show();
        setDetails();


    }

    private void setDetails() {
        Name.setText("Welcome "+appPreferences.getData("uname"));
        Accnum.setText("Acc No. "+appPreferences.getData("uaccno"));
        Phnnum.setText("Phone No. "+appPreferences.getData("uphone"));
        AccBal.setText("Account Balance : "+appPreferences.getData("ubal")+" Rs");
        pd.dismiss();


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

    public void withDrawClicked(View view) {
        startActivity(new Intent(Home.this, Withdraw.class));
    }

    public void benificiaryClicked(View view) {
        startActivity(new Intent(Home.this, Benlist.class));

    }

    public void transactionClick(View view) {
        startActivity(new Intent(Home.this, History.class));

    }
}
