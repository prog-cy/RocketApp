package com.example.rocketapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rocketapp.R;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    Context context;

    String[] url;

    public DetailAdapter(Context context, String[] url){

        this.context = context;
        this.url = url;
    }



    public class DetailViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView appCompatImageView;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            this.appCompatImageView = itemView.findViewById(R.id.flicker_img);
        }
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);

        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {

        String image = url[position];
        Glide.with(context).load(image).into(holder.appCompatImageView);
    }

    @Override
    public int getItemCount() {
        return url.length;
    }
}
