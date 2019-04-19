package com.example.momen.capstone_stage2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.model.Help;
import com.squareup.picasso.Picasso;

public class LevelActivity extends AppCompatActivity {

    RadioGroup rGroup;
    RadioButton radioButton;
    Button startQuiz;
    String userName,nameS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        ImageView img = findViewById(R.id.catagoryImgLevel);
        final TextView name = findViewById(R.id.catagoryNameLevel);
        userName = getIntent().getStringExtra(getString(R.string.userName));
        final String categoryNum = getIntent().getStringExtra(getString(R.string.categoryNum));
        Picasso.with(this).load(getIntent().getStringExtra(getString(R.string.image))).into(img);
        nameS = getIntent().getStringExtra(getString(R.string.name));
        name.setText(nameS);
        rGroup = findViewById(R.id.radioGroup);
        startQuiz = findViewById(R.id.startQuiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rGroup.getCheckedRadioButtonId();
                radioButton = findViewById(id);
                if (radioButton!=null) {
                    Intent intent = new Intent(LevelActivity.this, QuestionActivity.class);
                    intent.putExtra(getResources().getString(R.string.categoryNum), categoryNum);
                    intent.putExtra(getString(R.string.name),nameS);
                    intent.putExtra(getString(R.string.userName),userName);
                    intent.putExtra(getString(R.string.level), radioButton.getText());
                    startActivity(intent);
                    finish();
                }else
                    Toast.makeText(LevelActivity.this, R.string.choose_your_level, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
