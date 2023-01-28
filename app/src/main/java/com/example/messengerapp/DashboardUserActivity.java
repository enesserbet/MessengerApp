package com.example.messengerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.messengerapp.databinding.ActivityDashboardUserBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardUserActivity extends AppCompatActivity {

    private ActivityDashboardUserBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private FirebaseUser currentUser;


    private ArrayList<ModelMessage> messageArrayList;
    private ArrayList<ModelMessage2> message2ArrayList;
    private ArrayList<ModelUsers> userArrayList;

    private AdapterMessage adapterMessage;

    private AdapterMessage2 adapterMessage2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        checkUser();
        loadMessages();


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUser();
            }
        });

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }


    private void loadMessages() {
        message2ArrayList = new ArrayList<>();
        messageArrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                /*messageArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    ModelMessage modelMessage = ds.getValue(ModelMessage.class);

                    messageArrayList.add(modelMessage);
                }
                adapterMessage = new AdapterMessage(DashboardUserActivity.this, messageArrayList);
                binding.categoryiesRv.setAdapter(adapterMessage);

                 */

                /*message2ArrayList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
                    ModelMessage2 modelMessage2 = ds.getValue(ModelMessage2.class);

                    message2ArrayList.add(modelMessage2);
                }
                adapterMessage2 = new AdapterMessage2(DashboardUserActivity.this,message2ArrayList);
                binding.categoryiesRv.setAdapter(adapterMessage2);

                 */

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String senderuid = firebaseUser.getUid();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");

                messageArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    ModelMessage modelMessage = ds.getValue(ModelMessage.class);
                    ModelMessage2 modelMessage2 = ds.getValue(ModelMessage2.class);
                    if (modelMessage.getUid().equals(senderuid)){
                        messageArrayList.add(modelMessage);
                        adapterMessage = new AdapterMessage(DashboardUserActivity.this,messageArrayList);
                        binding.categoryiesRv.setAdapter(adapterMessage);
                    }else {
                        message2ArrayList.add(modelMessage2);
                        adapterMessage2 = new AdapterMessage2(DashboardUserActivity.this,message2ArrayList);
                        binding.categoryiesRv.setAdapter(adapterMessage2);
                    }
                }

                /*String uid = "" + snapshot.child("uid").getValue();
                System.out.println(uid);
                System.out.println("Burası");
                if (uid.equals(senderuid)) {
                    System.out.println(uid);
                    System.out.println("Burası2");
                    messageArrayList.clear();
                    for (DataSnapshot ds: snapshot.getChildren()){
                        ModelMessage modelMessage = ds.getValue(ModelMessage.class);

                        messageArrayList.add(modelMessage);
                    }
                    adapterMessage = new AdapterMessage(DashboardUserActivity.this,messageArrayList);
                    binding.categoryiesRv.setAdapter(adapterMessage);


                } else {
                    message2ArrayList.clear();
                    for (DataSnapshot ds: snapshot.getChildren()){
                        ModelMessage2 modelMessage2 = ds.getValue(ModelMessage2.class);

                        message2ArrayList.add(modelMessage2);
                    }
                    adapterMessage2 = new AdapterMessage2(DashboardUserActivity.this,message2ArrayList);
                    binding.categoryiesRv.setAdapter(adapterMessage2);
                    //finish();
                }

                 */


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    /*messageArrayList.clear();
                for (DataSnapshot ds: snapshot.getChildren()){
        ModelMessage modelMessage = ds.getValue(ModelMessage.class);

        messageArrayList.add(modelMessage);
    }
    adapterMessage = new AdapterMessage(DashboardUserActivity.this,messageArrayList);
                binding.categoryiesRv.setAdapter(adapterMessage);

     */


    private String text = "";

    private void validateData() {
        text = binding.textEt.getText().toString().trim();

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "Texting Please...", Toast.LENGTH_SHORT).show();
        } else {
            uploadMessage();
        }
    }


    private void uploadMessage() {
        progressDialog.setMessage("Sendind your message...");
        progressDialog.show();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String senderuid = firebaseUser.getUid();


        long timestamp = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", "" + firebaseAuth.getUid());
        hashMap.put("message", "" + text);
        hashMap.put("uid", "" + firebaseAuth.getUid());
        hashMap.put("senderUid", "" + senderuid);
        hashMap.put("timestamp", "" + timestamp);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Messages");
        ref.child("" + timestamp)
                .setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(DashboardUserActivity.this, "Gitti knk", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(DashboardUserActivity.this, "gitmedi knk" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            String email = firebaseUser.getEmail();
            binding.subTitleTV.setText(email);
            System.out.println(email);

        }
    }
}