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
    private ArrayList<ModelMessage2> message2ArrayList;
    private RowMessageOpponentBinding binding;

    public AdapterMessage2(DashboardUserActivity context, ArrayList<ModelMessage2> message2ArrayList) {
        this.context = context;
        this.message2ArrayList = message2ArrayList;
    }

    @NonNull
    @Override
    public HolderMessage2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowMessageOpponentBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderMessage2(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage2 holder, int position) {
        ModelMessage2 model = message2ArrayList.get(position);
        String id = model.getId();
        String message = model.getMessage();
        String uid = model.getUid();
        String timestamp = model.getTimestamp();
        String userType = model.getUserType();

        holder.messageopponent.setText(message);
    }

    @Override
    public int getItemCount() {
        return message2ArrayList.size();
    }


    class HolderMessage2 extends RecyclerView.ViewHolder {

        TextView messageopponent;
        public HolderMessage2(@NonNull View itemView) {
            super(itemView);
            messageopponent = binding.messageopponent;
        }
    }
}


