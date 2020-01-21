package com.hp.fortebank;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.Adapter.BenificiaryAdapter;
import com.hp.fortebank.Dashboard.Benificiary;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

public class Benlist extends AppCompatActivity {

    private RecyclerView benlist;
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    ArrayList<String> name,accno;
    private AppPreferences appPreferences;
    String API="http://srishti-systems.info/projects/ForteBank/api/view_beneficiary.php?";
    AlertDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benlist);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        pd = new SpotsDialog(this,R.style.CustomAlert);
        initView();
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        name=new ArrayList<>();
        accno=new ArrayList<>();
        requestParams.put("id",appPreferences.getData("uid"));
        asyncHttpClient.get(API,requestParams,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                String status= null;
                try {
                    status = response.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status.equalsIgnoreCase("success"))
                {
                    try {
                        JSONArray details=response.getJSONArray("Beneficiary_Details");
                        for(int i=0;i<details.length();i++)
                        {
                            JSONObject object=details.getJSONObject(i);
                            name.add(object.getString("name"));
                            Log.e("name",object.getString("name"));
                            accno.add(object.getString("ifsc"));
                            Log.e("ifsc",object.getString("ifsc"));

                        }

                        LinearLayoutManager verticalLayoutmanager
                                = new LinearLayoutManager(Benlist.this, RecyclerView.VERTICAL, false);
                        benlist.setLayoutManager(verticalLayoutmanager);
                        benlist.setAdapter(new BenificiaryAdapter(name,accno));
                        pd.dismiss();
                    } catch (JSONException e) {
                        pd.dismiss();
                        Toast.makeText(Benlist.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }else
                {
                    pd.dismiss();
                    Snackbar.make(benlist,"No Benificiary Found . Add One?", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void initView() {
        benlist = (RecyclerView) findViewById(R.id.benlist);
    }

    public void fabclick(View view) {
        startActivity(new Intent(getApplicationContext(), Benificiary.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("HOME","ONRESUME ENTERED");
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        name=new ArrayList<>();
        accno=new ArrayList<>();
        requestParams.put("id",appPreferences.getData("uid"));
        asyncHttpClient.get(API,requestParams,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                String status= null;
                try {
                    status = response.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status.equalsIgnoreCase("success"))
                {
                    try {
                        JSONArray details=response.getJSONArray("Beneficiary_Details");
                        for(int i=0;i<details.length();i++)
                        {
                            JSONObject object=details.getJSONObject(i);
                            name.add(object.getString("name"));
                            Log.e("name",object.getString("name"));
                            accno.add(object.getString("ifsc"));
                            Log.e("ifsc",object.getString("ifsc"));

                        }

                        LinearLayoutManager verticalLayoutmanager
                                = new LinearLayoutManager(Benlist.this, RecyclerView.VERTICAL, false);
                        benlist.setLayoutManager(verticalLayoutmanager);
                        benlist.setAdapter(new BenificiaryAdapter(name,accno));
                        pd.dismiss();
                    } catch (JSONException e) {
                        pd.dismiss();
                        Toast.makeText(Benlist.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }else
                {
                    pd.dismiss();
                    Snackbar.make(benlist,"No Benificiary Found . Add One?", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });


    }
}
