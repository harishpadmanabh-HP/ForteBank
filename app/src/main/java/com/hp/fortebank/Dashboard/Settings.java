package com.hp.fortebank.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.MainActivity;
import com.hp.fortebank.R;

public class Settings extends AppCompatActivity {

    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

    }

    public void lockappClick(View view) {
    }

    public void logoutClick(View view) {

        AlertDialog.Builder builder=new AlertDialog.Builder(Settings.this);
        builder.setTitle("LOGOUT ?");
        builder.setMessage("Do you want to log out ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                appPreferences.saveDataBoolean("isloggedin",false);
                startActivity(new Intent(Settings.this, MainActivity.class));
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
}
