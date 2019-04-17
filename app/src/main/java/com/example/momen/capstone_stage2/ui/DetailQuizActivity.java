package com.example.momen.capstone_stage2.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.model.Category;
import com.example.momen.capstone_stage2.model.Help;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailQuizActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_quiz);

        int correct = getIntent().getIntExtra("numCorrect",0);
        int size = getIntent().getIntExtra("size",-1);
        final float percent = (float) correct/size;
        final float percentage = percent * 100;

        Toast.makeText(this, Help.userName + " " + Help.categoryName, Toast.LENGTH_SHORT).show();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Users").child(Help.userName).child("category");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean flag = false;
                DataSnapshot dataChild = null;
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Log.i("ky",child.getKey());
                    if (child.getKey().equals(Help.categoryName)){
                        flag = true;
                        dataChild = child;
                        break;
                    }
                }
                if (flag){
                    Category category = dataChild.getValue(Category.class);
                    if (category != null){
                        if (category.getMaxDegree() < (int) percentage) {
                            Log.i("max",String.valueOf(category.getMaxDegree()));
                            Log.i("maxI",Help.categoryName);
                            databaseReference.child(Help.categoryName).child("maxDegree").setValue((int) percentage);
                        }
                    }
                }else{
                    databaseReference.child(Help.categoryName).setValue(new Category(Help.categoryName,(int)percentage));
                    Log.i("max","else");
                    Log.i("maxI",Help.categoryName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        PieChart pieChart=(PieChart)findViewById(R.id.chart);
        pieChart.setHoleRadius(50f);
        pieChart.setRotationEnabled(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText(String.valueOf(percentage)+"%");
        pieChart.setCenterTextSize(20);
        ArrayList<PieEntry> pieEntries =new ArrayList<>();
        pieEntries.add(new PieEntry(percentage));
        pieEntries.add(new PieEntry(100-percentage));
        PieDataSet pieDataSet =new PieDataSet (pieEntries,"Score");
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(2);
        PieData pieData =new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    public void goToCategory(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
