package com.example.momen.capstone_stage2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.momen.capstone_stage2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgetPasswordActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText code,chg_pass,confirm_pass;
    Button submit,save;
    String rand;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        code = findViewById(R.id.code);
        chg_pass = findViewById(R.id.chg_pass);
        confirm_pass = findViewById(R.id.confirm_pass);
        submit = findViewById(R.id.submit);
        save = findViewById(R.id.save);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(getResources().getString(R.string.Users));

        rand = getIntent().getStringExtra(getString(R.string.rand));
        name = getIntent().getStringExtra(getString(R.string.name));
    }

    public void submit(View view) {
        if (TextUtils.isEmpty(code.getText().toString()))
            code.setError(getString(R.string.can_not_be_empty));
        else if (rand.equals(code.getText().toString())){
            code.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
            chg_pass.setVisibility(View.VISIBLE);
            confirm_pass.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
        }
    }

    public void save(View view) {
        if (TextUtils.isEmpty(chg_pass.getText().toString()) || TextUtils.isEmpty(confirm_pass.getText().toString())){
            if (TextUtils.isEmpty(chg_pass.getText().toString()))
                chg_pass.setError(getString(R.string.can_not_be_empty));
            if (TextUtils.isEmpty(confirm_pass.getText().toString()))
                confirm_pass.setError(getString(R.string.can_not_be_empty));
        }else if (chg_pass.getText().toString().equals(confirm_pass.getText().toString())){
            databaseReference.child(name).child("password").setValue(chg_pass.getText().toString());
            finish();
        }else
            confirm_pass.setError(getString(R.string.do_not_match));
    }
}
