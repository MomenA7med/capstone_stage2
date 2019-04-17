package com.example.momen.capstone_stage2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.model.Category;

import java.util.List;

/**
 * Created by Momen on 4/14/2019.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreVH> {
    Context context;
    List<Category> categories;


    public ScoreAdapter(List<Category> categories){
        this.categories = categories;
    }
    @NonNull
    @Override
    public ScoreAdapter.ScoreVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item,parent,false);
        return new ScoreVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ScoreVH holder, int position) {
       holder.rankScore.setText(String.valueOf(categories.get(position).getMaxDegree()));
       holder.scoreCategory.setText(categories.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ScoreVH extends RecyclerView.ViewHolder {
        TextView scoreCategory,rankScore;
        public ScoreVH(View itemView) {
            super(itemView);
            rankScore = itemView.findViewById(R.id.rankScore);
            scoreCategory = itemView.findViewById(R.id.scoreCategory);
        }
    }
}
