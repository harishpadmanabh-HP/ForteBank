package com.hp.fortebank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.Dashboard.Benificiary;
import com.hp.fortebank.Dashboard.History;
import com.hp.fortebank.Dashboard.Settings;
import com.hp.fortebank.Dashboard.Withdraw;
import com.hp.fortebank.Retro.Retro;
import com.hp.fortebank.models.LoginModel;
import com.hp.fortebank.models.UserDetailsModel;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    String balance;
    UserDetailsModel userDetailsModel;


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
        new Retro().getApi().USER_DETAILS_MODEL_CALL(appPreferences.getData("uid")).enqueue(new Callback<UserDetailsModel>() {
            @Override
            public void onResponse(Call<UserDetailsModel> call, Response<UserDetailsModel> response) {
                UserDetailsModel userDetailsModel=response.body();
               if(userDetailsModel!=null)
                AccBal.setText("Account Balance : "+userDetailsModel.getUser_Details().get(0).getBalance()+" Rs");
              else
                   Toast.makeText(Home.this, "no data found", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserDetailsModel> call, Throwable t) {

                Toast.makeText(Home.this, "Current Balance api failure "+t, Toast.LENGTH_SHORT).show();
                pd.dismiss();

            }
        });
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

    public void setttingsClick(View view) {
        startActivity(new Intent(Home.this, Settings.class));


    }

    @Override
    public void onBackPressed() {
        Context context;
        AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
        builder.setTitle("Exit")
                .setMessage("Do you want to exit ?")
                .setPositiveButton("  Exit  ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setCancelable(false).create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("OnResume","setdetails");
        setDetails();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart","setdetails");

        setDetails();
    }
}
