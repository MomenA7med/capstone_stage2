package com.example.momen.capstone_stage2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText userName,password,phone;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users");
        users = new ArrayList<>();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                users.add(user);
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

        userName = findViewById(R.id.userNameLogin);
        password = findViewById(R.id.passwordLogin);
        phone = findViewById(R.id.phone);
    }

    public void register(View view) {
        if (TextUtils.isEmpty(userName.getText().toString()) ||
                TextUtils.isEmpty(phone.getText().toString())|| TextUtils.isEmpty(password.getText().toString())) {
            if (TextUtils.isEmpty(userName.getText().toString())) {
                userName.setError("can not be empty");
            }
            if (TextUtils.isEmpty(password.getText().toString())) {
                password.setError("can not be empty");
            }
            if (TextUtils.isEmpty(phone.getText().toString())) {
                phone.setError("can not be empty");
            }
        }
        else {
            boolean flag = false;
            String user_name = userName.getText().toString();
            for (int i =0 ;i<users.size();i++){
                if (user_name.equals(users.get(i).getName())){
                    flag = true;
                    break;
                }
            }
            if (flag){
                userName.setError("this user name is exist");
            }
            else {
                databaseReference.child(user_name).setValue(new User(user_name,password.getText().toString(),phone.getText().toString(),null));
            }
        }
    }
}
