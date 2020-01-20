package com.hp.fortebank;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.Retro.Retro;
import com.hp.fortebank.models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private TextInputLayout phone;
    private TextInputEditText phnEditText;
    private TextInputLayout pin;
    private TextInputEditText pinEditText;
    private MaterialButton loginButton;
    String uid;
    AppPreferences appPreferences;
    Boolean isloggedin=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
         appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

    }

    public void signinClick(View view) {
        if(phnEditText.getText().toString().equals("")||
           pinEditText.getText().toString().equals(""))
        {
            showError();
            Toast.makeText(Login.this, "Fill all fields.", Toast.LENGTH_SHORT).show();
        }else{
            new Retro().getApi().LOGIN_MODEL_CALL(
                    pinEditText.getText().toString(),
                    phnEditText.getText().toString()
                                                  ).enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    LoginModel loginModel=response.body();
                    if(loginModel.getStatus().equalsIgnoreCase("success"))
                    {
                        isloggedin=true;
                         uid=loginModel.getUser_data().getId();
                         appPreferences.saveData("uid",uid);
                         appPreferences.saveData("uname",loginModel.getUser_data().getName());
                         appPreferences.saveData("uphone",loginModel.getUser_data().getPhone());
                         appPreferences.saveData("upin",loginModel.getUser_data().getPin());
                         appPreferences.saveData("ubal",loginModel.getUser_data().getBalance());
                         appPreferences.saveData("uaccno",loginModel.getUser_data().getAccount_num());

                         appPreferences.saveDataBoolean("isloggedin",isloggedin);

                         Log.e("ACC BAL",appPreferences.getData("ubal"));
                         Toast.makeText(Login.this, "Login success", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(Login.this,Home.class));

                    }else
                    {
                        Toast.makeText(Login.this, "Login Failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {

                    Toast.makeText(Login.this, "API Call Failure "+t, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    private void showError() {


    }

    private void initView() {
        phone = (TextInputLayout) findViewById(R.id.phone);
        phnEditText = (TextInputEditText) findViewById(R.id.phn_edit_text);
        pin = (TextInputLayout) findViewById(R.id.pin);
        pinEditText = (TextInputEditText) findViewById(R.id.pin_edit_text);
        loginButton = (MaterialButton) findViewById(R.id.login_button);
    }
}
