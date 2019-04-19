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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        ImageView img = findViewById(R.id.catagoryImgLevel);
        TextView name = findViewById(R.id.catagoryNameLevel);
        final String categoryNum = getIntent().getStringExtra("categoryNum");
        Picasso.with(this).load(getIntent().getStringExtra("image")).into(img);
        name.setText(getIntent().getStringExtra("name"));
        rGroup = findViewById(R.id.radioGroup);
        startQuiz = findViewById(R.id.startQuiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rGroup.getCheckedRadioButtonId();
                radioButton = findViewById(id);
                if (radioButton!=null) {
                    Intent intent = new Intent(LevelActivity.this, QuestionActivity.class);
                    intent.putExtra("categoryNum", categoryNum);
                    intent.putExtra("level", radioButton.getText());
                    startActivity(intent);
                    //finish();
                }else
                    Toast.makeText(LevelActivity.this, "choose your level", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
