package com.example.messengerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messengerapp.databinding.RowCategoryBinding;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.HolderMessage> {

    private DashboardUserActivity context;
    private ArrayList<ModelMessage> messageArrayList;

    private RowCategoryBinding binding;

    public AdapterMessage(DashboardUserActivity context, ArrayList<ModelMessage> messageArrayList) {
        this.context = context;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowCategoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderMessage(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        ModelMessage model = messageArrayList.get(position);
        String id = model.getId();
        String message = model.getMessage();
        String uid = model.getUid();
        long timestamp = model.getTimestamp();

        holder.categroyTv.setText(message);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    class HolderMessage extends RecyclerView.ViewHolder {

        TextView categroyTv;
        ImageButton deleteBtn;
        public HolderMessage(@NonNull View itemView) {
            super(itemView);

            categroyTv = binding.categoryTv;
            deleteBtn = binding.deleteBtn;
        }
    }
}
