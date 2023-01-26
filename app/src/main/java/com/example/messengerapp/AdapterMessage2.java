package com.example.messengerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.messengerapp.databinding.RowMessageOpponentBinding;

import java.util.ArrayList;

public class AdapterMessage2 extends RecyclerView.Adapter<AdapterMessage2.HolderMessage2> {
    private DashboardUserActivity context;
    private ArrayList<ModelMessage> messageArrayList;
    private RowMessageOpponentBinding binding;

    public AdapterMessage2(DashboardUserActivity context, ArrayList<ModelMessage> messageArrayList) {
        this.context = context;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public HolderMessage2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowMessageOpponentBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderMessage2(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage2 holder, int position) {
        ModelMessage model = messageArrayList.get(position);
        String id = model.getId();
        String message = model.getMessage();
        String uid = model.getUid();
        String timestamp = model.getTimestamp();

        holder.messageopponent.setText(message);
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }


    class HolderMessage2 extends RecyclerView.ViewHolder {

        TextView messageopponent;
        public HolderMessage2(@NonNull View itemView) {
            super(itemView);
            messageopponent = binding.messageopponent;
        }
    }
}


