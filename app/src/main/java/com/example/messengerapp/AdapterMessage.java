package com.example.messengerapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.messengerapp.databinding.RowMessageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.HolderMessage> {

    private DashboardUserActivity context;
    private ArrayList<ModelMessage> messageArrayList;


    private RowMessageBinding binding;

    public AdapterMessage(DashboardUserActivity context, ArrayList<ModelMessage> messageArrayList) {
        this.context = context;
        this.messageArrayList = messageArrayList;
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowMessageBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderMessage(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        ModelMessage model = messageArrayList.get(position);
        String id = model.getId();
        String message = model.getMessage();
        String uid = model.getUid();
        String timestamp = model.getTimestamp();


        holder.categroyTv.setText(message);

    }


    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    class HolderMessage extends RecyclerView.ViewHolder {

        TextView categroyTv;
        //TextView categroyTv2;

        public HolderMessage(@NonNull View itemView) {
            super(itemView);


            categroyTv = binding.categoryTv;
            //categroyTv2 = binding.categoryTv2;

        }
    }
}
