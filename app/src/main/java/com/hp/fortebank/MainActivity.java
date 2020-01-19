package com.hp.fortebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.harishpadmanabh.apppreferences.AppPreferences;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int Delay=3000;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();

                // Start MainActivity.class

                if(appPreferences.getDataBoolean("isloggedin")) {
                    Intent myIntent = new Intent(getApplicationContext(),
                            Login.class);
                    startActivity(myIntent);
                }else{
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }
}
