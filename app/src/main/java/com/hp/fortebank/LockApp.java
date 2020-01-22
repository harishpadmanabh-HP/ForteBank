package com.hp.fortebank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.harishpadmanabh.apppreferences.AppPreferences;

public class LockApp extends AppCompatActivity {

    Switch lockswitch;
    private AppPreferences appPreferences;
    boolean islocked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_app);
        lockswitch=findViewById(R.id.lockSwitch);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        islocked=appPreferences.getDataBoolean("islocked");
        if(islocked)
          lockswitch.setChecked(true);
        else lockswitch.setChecked(false);
        lockswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    Context context;
                    AlertDialog.Builder builder=new AlertDialog.Builder(LockApp.this);
                    builder.setTitle("LOCK APP");
                    builder.setMessage("App will use the device's security settings to authenticate app.");
                    builder.setPositiveButton("LOCK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            appPreferences.saveDataBoolean("islocked",true);
                            startActivity(new Intent(LockApp.this,PatternActivity.class));

                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            appPreferences.saveDataBoolean("islocked",false);

                            lockswitch.setChecked(false);
                            dialogInterface.dismiss();

                        }
                    }).setCancelable(false).create().show();
                }else
                {
                    appPreferences.saveDataBoolean("islocked",false);

                }
            }
        });
    }
}
