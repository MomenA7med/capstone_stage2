package com.example.momen.capstone_stage2.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.api.TriviaRetrofit;
import com.example.momen.capstone_stage2.api.TriviaService;
import com.example.momen.capstone_stage2.model.Help;
import com.example.momen.capstone_stage2.model.Response;
import com.example.momen.capstone_stage2.model.ResultsItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class QuestionActivity extends AppCompatActivity {

    private List<String> answers;
    private String correctAnswer,question;
    private TextView question_txt;
    private Button answer1,answer2,answer3,answer4;
    private Drawable btnDefault;
    private int size,position=0,numCorrect=0;
    private List<ResultsItem> resultsItems;
    private TextView noQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        noQuestion = findViewById(R.id.noQuestion);
        question_txt = findViewById(R.id.quesion);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        btnDefault = answer1.getBackground();


        TriviaService triviaService = TriviaRetrofit.getRetrofit().create(TriviaService.class);
        Call<Response> call = triviaService.getQuestions("10",getIntent().getStringExtra("categoryNum"),
                getIntent().getStringExtra("level"),"multiple");
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                resultsItems = response.body().getResults();
                size = resultsItems.size();
                if (resultsItems.size()!=0) {
                    question_txt.setText(resultsItems.get(position).getQuestion());

                    List<String> strings = resultsItems.get(position).getIncorrectAnswers();
                    strings.add(resultsItems.get(position).getCorrectAnswer());
                    Collections.shuffle(strings);
                    Collections.shuffle(strings);
                    answer1.setText(strings.get(0));
                    answer2.setText(strings.get(1));
                    answer3.setText(strings.get(2));
                    answer4.setText(strings.get(3));
                    correctAnswer = resultsItems.get(position).getCorrectAnswer();
                }else
                {
                    question_txt.setVisibility(View.GONE);
                    answer1.setVisibility(View.GONE);
                    answer2.setVisibility(View.GONE);
                    answer3.setVisibility(View.GONE);
                    answer4.setVisibility(View.GONE);
                    noQuestion.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(QuestionActivity.this, "Error in retriving data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkTrue(View view) {
        switch (view.getId()){
            case R.id.answer1:
                if (answer1.getText().equals(correctAnswer)){
                    answer1.setBackgroundColor(Color.GREEN);
                    numCorrect+=1;
                }else {
                    answer1.setBackgroundColor(Color.RED);
                    if (answer2.getText().equals(correctAnswer))
                        answer2.setBackgroundColor(Color.GREEN);
                    else if (answer3.getText().equals(correctAnswer))
                        answer3.setBackgroundColor(Color.GREEN);
                    else if (answer4.getText().equals(correctAnswer))
                        answer4.setBackgroundColor(Color.GREEN);
                }
                break;
            case R.id.answer2:
                if (answer2.getText().equals(correctAnswer)){
                    answer2.setBackgroundColor(Color.GREEN);
                    numCorrect+=1;
                }else {
                    answer2.setBackgroundColor(Color.RED);
                    if (answer1.getText().equals(correctAnswer))
                        answer1.setBackgroundColor(Color.GREEN);
                    else if (answer3.getText().equals(correctAnswer))
                        answer3.setBackgroundColor(Color.GREEN);
                    else if (answer4.getText().equals(correctAnswer))
                        answer4.setBackgroundColor(Color.GREEN);
                }
                break;
            case R.id.answer3:
                if (answer3.getText().equals(correctAnswer)){
                    answer3.setBackgroundColor(Color.GREEN);
                    numCorrect+=1;
                }else {
                    answer3.setBackgroundColor(Color.RED);
                    if (answer2.getText().equals(correctAnswer))
                        answer2.setBackgroundColor(Color.GREEN);
                    else if (answer1.getText().equals(correctAnswer))
                        answer1.setBackgroundColor(Color.GREEN);
                    else if (answer4.getText().equals(correctAnswer))
                        answer4.setBackgroundColor(Color.GREEN);
                }
                break;
            case R.id.answer4:
                if (answer4.getText().equals(correctAnswer)){
                    answer4.setBackgroundColor(Color.GREEN);
                    numCorrect+=1;
                }else {
                    answer4.setBackgroundColor(Color.RED);
                    if (answer2.getText().equals(correctAnswer))
                        answer2.setBackgroundColor(Color.GREEN);
                    else if (answer3.getText().equals(correctAnswer))
                        answer3.setBackgroundColor(Color.GREEN);
                    else if (answer1.getText().equals(correctAnswer))
                        answer1.setBackgroundColor(Color.GREEN);
                }
                break;
            default:break;
        }
        position+=1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    answer1.setBackground(btnDefault);
                    answer2.setBackground(btnDefault);
                    answer3.setBackground(btnDefault);
                    answer4.setBackground(btnDefault);
                    if (position<size){
                        question_txt.setText(resultsItems.get(position).getQuestion());
                        List<String> strings = resultsItems.get(position).getIncorrectAnswers();
                        strings.add(resultsItems.get(position).getCorrectAnswer());
                        Collections.shuffle(strings);
                        Collections.shuffle(strings);
                        answer1.setText(strings.get(0));
                        answer2.setText(strings.get(1));
                        answer3.setText(strings.get(2));
                        answer4.setText(strings.get(3));
                        correctAnswer = resultsItems.get(position).getCorrectAnswer();
                    }else {
                        Intent intent = new Intent(QuestionActivity.this,DetailQuizActivity.class);
                        intent.putExtra("size",size);
                        intent.putExtra("numCorrect",numCorrect);
                        startActivity(intent);
                        finish();
                    }
                }


            }
        },1500);

    }
}
