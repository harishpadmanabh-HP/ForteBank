package com.hp.fortebank.Dashboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.Benlist;
import com.hp.fortebank.R;
import com.hp.fortebank.Retro.Retro;
import com.hp.fortebank.models.BenificiaryModel;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Benificiary extends AppCompatActivity {

    private TextInputLayout name;
    private TextInputLayout bank;
    private TextInputLayout branch;
    private TextInputEditText pswdEdttxt;
    private TextInputLayout ifsc;
    private MaterialButton addButton;
    private AppPreferences appPreferences;
    private AlertDialog pd;
    private TextInputLayout phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benificiary);
        initView();
        pd = new SpotsDialog(this, R.style.CustomAlert);

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

    }

    public void addBenificiaryClicked(final View view) {

        if (name.getEditText().getText().toString().equals("") ||
                bank.getEditText().getText().toString().equals("") ||
                branch.getEditText().getText().toString().equals("") ||
                phone.getEditText().getText().toString().equals("") ||

                ifsc.getEditText().getText().toString().equals("")) {
            Snackbar.make(view, "Fill All Fields", BaseTransientBottomBar.LENGTH_SHORT).show();
        } else {
            pd.show();
            new Retro().getApi().BENIFICIARY_MODEL_CALL(appPreferences.getData("uid"),
                    name.getEditText().getText().toString(),
                    bank.getEditText().getText().toString(),
                    branch.getEditText().getText().toString(),
                    ifsc.getEditText().getText().toString(),
                    phone.getEditText().getText().toString()).enqueue(new Callback<BenificiaryModel>() {
                @Override
                public void onResponse(Call<BenificiaryModel> call, Response<BenificiaryModel> response) {
                    BenificiaryModel benificiaryModel = response.body();
                    if (benificiaryModel.getStatus().equalsIgnoreCase("success")) {
                        pd.dismiss();
                        Snackbar.make(view, name.getEditText().getText().toString() + " has been aded to your Benificiary.", BaseTransientBottomBar.LENGTH_SHORT).show();

                        startActivity(new Intent(Benificiary.this, Benlist.class));

                    } else {
                        pd.dismiss();
                        Snackbar.make(view, "Something Went wrong."+benificiaryModel.getStatus(), BaseTransientBottomBar.LENGTH_SHORT).show();


                    }
                }

                @Override
                public void onFailure(Call<BenificiaryModel> call, Throwable t) {
                    pd.dismiss();
                    Snackbar.make(view, "Api Failure " + t, BaseTransientBottomBar.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void initView() {
        name = (TextInputLayout) findViewById(R.id.name);
        bank = (TextInputLayout) findViewById(R.id.bank);
        branch = (TextInputLayout) findViewById(R.id.branch);
        pswdEdttxt = (TextInputEditText) findViewById(R.id.pswdEdttxt);
        ifsc = (TextInputLayout) findViewById(R.id.ifsc);
        addButton = (MaterialButton) findViewById(R.id.add_button);
        phone = findViewById(R.id.phone);
    }
}
