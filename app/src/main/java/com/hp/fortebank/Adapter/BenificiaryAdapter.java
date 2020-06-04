package com.hp.fortebank.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.fortebank.PayActivity.PayActivity;
import com.hp.fortebank.R;

import java.util.ArrayList;

public class BenificiaryAdapter extends RecyclerView.Adapter<BenificiaryAdapter.MyVH> {

    private AppPreferences appPreferences;



    public BenificiaryAdapter(ArrayList<String> namelist, ArrayList<String> numlist, ArrayList<String> benID, ArrayList<String> userID, Context context) {
        this.namelist = namelist;
        this.numlist = numlist;
        this.benID = benID;
        this.userID = userID;
        this.context = context;
    }

    ArrayList<String> namelist,numlist,benID,userID;
    Context context;



    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));


        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, final int position) {

        holder.name.setText(namelist.get(position));
        holder.acc.setText(numlist.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferences.saveData("recievers_name",namelist.get(position));
                appPreferences.saveData("senders_id",userID.get(position));
                appPreferences.saveData("recievers_id",benID.get(position));
                context.startActivity(new Intent(context, PayActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return namelist.size();
    }

    class MyVH extends RecyclerView.ViewHolder{
        TextView name,acc;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.listname);
            acc=itemView.findViewById(R.id.listaccountnum);
        }
    }
}
