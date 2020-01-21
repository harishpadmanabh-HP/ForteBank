package com.hp.fortebank.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hp.fortebank.R;
import com.hp.fortebank.models.HistoryModel;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryVH> {
    HistoryModel historyModel;

    public HistoryAdapter(HistoryModel historyModel) {
        this.historyModel = historyModel;
    }

    @NonNull
    @Override
    public HistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sparelistitem, parent, false);

        return new HistoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {

        holder.date.setText(historyModel.getTransaction_History().get(position).getDebited_On());
        holder.amt.setText(historyModel.getTransaction_History().get(position).getDebited_Amount()+" Rs");

    }

    @Override
    public int getItemCount() {
        return historyModel.getTransaction_History().size();
    }

    class HistoryVH extends RecyclerView.ViewHolder{

        TextView date,amt;

        public HistoryVH(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.listdate);
            amt=itemView.findViewById(R.id.listamt);
        }
    }
}
