package com.example.momen.capstone_stage2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.model.Help;
import com.example.momen.capstone_stage2.model.User;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText useName,password;
    List<User> users;
    TextView forgetPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        useName = findViewById(R.id.userNameLogin);
        password = findViewById(R.id.passwordLogin);
        forgetPass = findViewById(R.id.forgetPass);

        MobileAds.initialize(this,"ca-app-pub-9851658657338441~1713506651");


        AdView mAdView = (AdView) findViewById(R.id.adViewLogin);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

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
                User user = dataSnapshot.getValue(User.class);
                users.add(user);
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

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = useName.getText().toString();
                int position = -1;
                boolean flag = false;
                for (int i = 0; i < users.size(); i++) {
                    if (user_name.equals(users.get(i).getName())) {
                        flag = true;
                        position = i;
                        break;
                    }
                }
                if (flag) {
                    String rand = getRandomNumberString();
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(users.get(position).getPhone(), "trivia_app", rand, null, null);
                    Toast.makeText(LoginActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                    intent.putExtra("rand",rand);
                    intent.putExtra("name",users.get(position).getName());
                    startActivity(intent);
                }
            }});
    }
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public void register(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void signin(View view) {
        int position = -1;
        String user_name = useName.getText().toString();
        String pass = password.getText().toString();
        boolean flag = false;
        for (int i =0 ;i<users.size();i++){
            if (user_name.equals(users.get(i).getName())&&pass.equals(users.get(i).getPassword())){
                flag = true;
                position = i;
                break;
            }
        }
        if (flag){
            Help.userName = users.get(position).getName();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else
            Toast.makeText(this, "userName or password invalid", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
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
                User user = dataSnapshot.getValue(User.class);
                users.add(user);
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
