package com.hp.fortebank.Dashboard;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.Home;
import com.hp.fortebank.R;
import com.hp.fortebank.Retro.Retro;
import com.hp.fortebank.models.OTPModel;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Withdraw extends AppCompatActivity {
    private int storagepermissioncode=1;
    private ZXingScannerView scannerView;//test git
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.CAMERA, Manifest.permission.SEND_SMS},storagepermissioncode);

        if((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)){
            Toast.makeText(getApplicationContext(), "ALREADY GRANTED  PERMISSION", Toast.LENGTH_SHORT).show();

            ScanQR();

        }
        else{
            requeststroagepermission();
        }
    }

    public void ScanQR() {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
                Toast.makeText(Withdraw.this, "Result :"+result, Toast.LENGTH_SHORT).show();

                Log.e("QRresult",result.getText());
                showAlert("proceed",result.getText());
            }
        });

        setContentView(scannerView);
        scannerView.startCamera();
    }





    private void requeststroagepermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            new AlertDialog.Builder(this)
                    .setTitle("PERMISSION NEEDED")
                    .setMessage("This permission is needed for scan QR")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Withdraw.this, new String[] {Manifest.permission.CAMERA},storagepermissioncode);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){
            new AlertDialog.Builder(this)
                    .setTitle("PERMISSION NEEDED")
                    .setMessage("This permission is needed for scan QR")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Withdraw.this, new String[] {Manifest.permission.CAMERA},storagepermissioncode);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA,Manifest.permission.SEND_SMS},storagepermissioncode);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==storagepermissioncode){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();

                ScanQR();
            }
            else
                Toast.makeText(this, "Scan QR", Toast.LENGTH_SHORT).show();
        }
    }
    public  void showAlert(String message, final String result)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(message);
        builder.setPositiveButton("GET OTP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // Toast.makeText(Withdraw.this, "Get otp", Toast.LENGTH_SHORT).show();
                new Retro().getApi().OTP_MODEL_CALL(result,appPreferences.getData("uid")).enqueue(new Callback<OTPModel>() {
                    @Override
                    public void onResponse(Call<OTPModel> call, Response<OTPModel> response) {
                        OTPModel  otpModel=response.body();
                        if(otpModel.getStatus().equalsIgnoreCase("OTP Updated"))
                        {
                            SmsManager smsManager = SmsManager.getDefault();

                            String phones=appPreferences.getData("uphone");
                            smsManager.sendTextMessage(phones, null, "Your otp for cardlessATM is "+otpModel.getOTP(), null, null);

                            startActivity(new Intent(Withdraw.this, Home.class ));
                            Toast.makeText(Withdraw.this, "OTP will be sent to registered phone number.", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(Withdraw.this, "QR Scan error. Try again after some time", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OTPModel> call, Throwable t) {

                        Toast.makeText(Withdraw.this, "API FAILURE"+t, Toast.LENGTH_SHORT).show();

                    }
                });


            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(Withdraw.this, "Transaction cancelled", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_withdraw);

            }
        }).create().show();
          }

}
