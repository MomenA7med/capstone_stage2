package com.example.momen.capstone_stage2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.adapter.ScoreAdapter;
import com.example.momen.capstone_stage2.model.Category;
import com.example.momen.capstone_stage2.model.Help;
import com.example.momen.capstone_stage2.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    TextView yourName,yourPhone;
    RecyclerView scoreRV;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Category> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        yourName = findViewById(R.id.your_name);
        yourPhone = findViewById(R.id.your_phone);
        scoreRV = findViewById(R.id.scoreRV);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");
        categories = new ArrayList<>();
        scoreRV.setHasFixedSize(false);
        scoreRV.setLayoutManager(new LinearLayoutManager(this));
        final ScoreAdapter scoreAdapter = new ScoreAdapter(categories);
        scoreRV.setAdapter(scoreAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    if (user.getName().equals(Help.userName)) {
                        yourName.setText(user.getName());
                        yourPhone.setText(user.getPhone());
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child(Help.userName).child("category").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category category = dataSnapshot.getValue(Category.class);
                categories.add(category);
                scoreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
