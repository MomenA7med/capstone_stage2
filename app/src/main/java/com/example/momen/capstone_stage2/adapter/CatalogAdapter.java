package com.example.momen.capstone_stage2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.momen.capstone_stage2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Momen on 4/12/2019.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogVH> {

    private List<String>names,images;
    private Context context;
    private CatagoryClick catagoryClick;

    public interface CatagoryClick{
         void catagoryOnClick(int postion);
    }
    public CatalogAdapter(Context context,List<String>names,List<String>images,CatagoryClick catagoryClick){
        this.context = context;
        this.names = names;
        this.images = images;
        this.catagoryClick = catagoryClick;
    }
    @NonNull
    @Override
    public CatalogAdapter.CatalogVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_item,parent,false);
        return new CatalogAdapter.CatalogVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.CatalogVH holder, int position) {
        holder.textView.setText(names.get(position));
        Picasso.with(context).load(images.get(position)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class CatalogVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView textView;
        public CatalogVH(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.catagoryImg);
            textView = itemView.findViewById(R.id.catagoryName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            catagoryClick.catagoryOnClick(getAdapterPosition());
        }
    }
}
