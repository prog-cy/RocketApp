package com.example.rocketapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.rocketapp.activity.FullInfoAboutRocket;
import com.example.rocketapp.R;
import com.example.rocketapp.model.RocketInfo;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter{

    Context context;

    List<RocketInfo> list;

    public MyAdapter(Context context, List<RocketInfo> list){

        this.context = context;
        this.list = list;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country, rocket, engine;

        AppCompatImageView imageView;

        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.country = itemView.findViewById(R.id.country);
            this.rocket = itemView.findViewById(R.id.rocketName);
            this.imageView = itemView.findViewById(R.id.rocketImg);
            this.card = itemView.findViewById(R.id.card);
            this.engine = itemView.findViewById(R.id.engine);
        }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



            View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

            return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


            MyViewHolder holder1 = (MyViewHolder) holder;
            holder1.rocket.setText("Rocket Name: "+list.get(position).getRocketName());
            holder1.country.setText("Country: "+list.get(position).getCountryName());
            holder1.engine.setTextColor(context.getResources().getColor(R.color.deep_blue));
            holder1.engine.setText("Total Engines: "+list.get(position).getEngine()+"");
            setImage(holder1, list.get(position).getImgUrl());

            holder1.card.setOnClickListener(v -> {
                Intent intent = new Intent(context, FullInfoAboutRocket.class);
                intent.putExtra("rocketId", list.get(position).getRocketId());
                context.startActivity(intent);
            });

    }

    private void setImage(MyViewHolder holder, String imgUrl) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();


        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(holder.imageView);

//            Picasso.get()
//                    .load(imgUrl)
//                    .resize(50, 50)
//                    .centerCrop()
//                    .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

