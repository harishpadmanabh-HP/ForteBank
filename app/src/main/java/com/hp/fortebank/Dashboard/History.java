package com.hp.fortebank.Dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.Adapter.HistoryAdapter;
import com.hp.fortebank.Benlist;
import com.hp.fortebank.R;
import com.hp.fortebank.Retro.Retro;
import com.hp.fortebank.models.HistoryModel;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {

    private TextView usernameheading;
    private RecyclerView lv;
    private TextView balance;
    private AppPreferences appPreferences;
    AlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_spare);
        initView();
        pd = new SpotsDialog(this,R.style.CustomAlert);
        pd.show();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        usernameheading.setText(appPreferences.getData("uname"));
        balance.setText("Current balance : "+appPreferences.getData("ubal")+" Rs");

        new Retro().getApi().HISTORY_MODEL_CALL(appPreferences.getData("uid")).enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                HistoryModel historyModel=response.body();
                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(History.this, RecyclerView.VERTICAL, false);
                lv.setLayoutManager(verticalLayoutmanager);
                lv.setAdapter(new HistoryAdapter(historyModel));
                pd.dismiss();


            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(History.this, "API FAILURE "+t, Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void initView() {
        usernameheading = (TextView) findViewById(R.id.usernameheading);
        lv = (RecyclerView) findViewById(R.id.lv);
        balance = (TextView) findViewById(R.id.balance);
    }
}
