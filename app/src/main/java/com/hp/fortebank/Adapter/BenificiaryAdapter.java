package com.hp.fortebank.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hp.fortebank.R;

import java.util.ArrayList;

public class BenificiaryAdapter extends RecyclerView.Adapter<BenificiaryAdapter.MyVH> {

    public BenificiaryAdapter(ArrayList<String> namelist, ArrayList<String> numlist) {
        this.namelist = namelist;
        this.numlist = numlist;
    }

    ArrayList<String> namelist,numlist;


    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);

        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {

        holder.name.setText(namelist.get(position));
        holder.acc.setText(numlist.get(position));

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
