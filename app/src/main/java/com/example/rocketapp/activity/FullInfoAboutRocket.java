package com.example.rocketapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rocketapp.R;
import com.example.rocketapp.adapter.DetailAdapter;
import com.example.rocketapp.model.DetailRocketInfo;
import com.example.rocketapp.service.APIInterface;
import com.example.rocketapp.service.RetrofitInstance;
import com.example.rocketapp.viewmodel.DetailActivityViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FullInfoAboutRocket extends AppCompatActivity {

    RecyclerView rec1;

    TextView activeStatus, costPerLaunch, height, diameter, successRate, description;

    AppCompatButton viewMore;

    Toolbar toolbar;

    String rocketId;


    DetailActivityViewModel viewModel;

    HorizontalScrollView h_scroll;

    LinearLayout linearLayout, linearLayout2;

    String link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_info_layout);

        rocketId = getIntent().getStringExtra("rocketId");

        viewModel = new ViewModelProvider(this).get(DetailActivityViewModel.class);

        LiveData<DetailRocketInfo> liveData = viewModel.getMutableLiveData();

        liveData.observe(this, new Observer<DetailRocketInfo>() {
            @Override
            public void onChanged(DetailRocketInfo detailRocketInfo) {

                toolbar.setTitle(detailRocketInfo.getRocketName());
                linearLayout2.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);

                if(detailRocketInfo.isStatus()){
                    activeStatus.setTextColor(getResources().getColor(R.color.white));
                    activeStatus.setBackground(getDrawable(R.color.deep_blue));
                    activeStatus.setText("Active");
                }else{
                    activeStatus.setTextColor(getResources().getColor(R.color.white));
                    activeStatus.setBackground(getDrawable(R.color.red));
                    activeStatus.setText("Not Active");
                }


                costPerLaunch.setText(detailRocketInfo.getCostPerLaunch()+"$");
                successRate.setText(detailRocketInfo.getSuccessPct()+"%");
                height.setText(detailRocketInfo.getHeight_meter()+"meter, "+detailRocketInfo.getHeight_feet()+"feet");
                diameter.setText(detailRocketInfo.getDiameter_meter()+"meter, "+detailRocketInfo.getDiameter_feet()+"feet");
                description.setText(detailRocketInfo.getDescription());

                h_scroll.setVisibility(View.GONE);
                rec1.setVisibility(View.VISIBLE);

                rec1.setLayoutManager(new LinearLayoutManager(FullInfoAboutRocket.this, LinearLayoutManager.HORIZONTAL, false));
                DetailAdapter detailAdapter = new DetailAdapter(FullInfoAboutRocket.this, detailRocketInfo.getImgUrl());
                rec1.setAdapter(detailAdapter);
            }
        });

        init();


        toolbar.setNavigationIcon(R.drawable.baseline_navigate_before_24);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });


        viewMore.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(intent);
        });

    }

    public void init(){

        toolbar = findViewById(R.id.toolbar);
        rec1 = findViewById(R.id.rec1);
        rec1.setVisibility(View.GONE);
        activeStatus = findViewById(R.id.activeStatus);
        costPerLaunch = findViewById(R.id.cost);
        height = findViewById(R.id.height);
        diameter = findViewById(R.id.diameter);
        successRate = findViewById(R.id.success);
        description = findViewById(R.id.description);
        viewMore = findViewById(R.id.link);
        h_scroll = findViewById(R.id.h_scroll);
        h_scroll.setVisibility(View.VISIBLE);
        linearLayout = findViewById(R.id.li1);
        linearLayout.setVisibility(View.GONE);
        linearLayout2 = findViewById(R.id.li2);
        linearLayout2.setVisibility(View.VISIBLE);
        loadSpecificRocket(rocketId);
    }

    private void loadSpecificRocket(String rocketId) {

        APIInterface apiInterface = RetrofitInstance.getClient().create(APIInterface.class);
        Call<JsonObject> call = apiInterface.getSpecificRocket(rocketId);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject jsonObject = response.body();

                String rocketName = jsonObject.get("rocket_name").getAsString();
                boolean status = jsonObject.get("active").getAsBoolean();
                long cost = jsonObject.get("cost_per_launch").getAsInt();
                double successPct = jsonObject.get("success_rate_pct").getAsDouble();
                double h_meter = jsonObject.get("height").getAsJsonObject().get("meters").getAsDouble();
                double h_feet = jsonObject.get("height").getAsJsonObject().get("feet").getAsDouble();
                double d_meter = jsonObject.get("diameter").getAsJsonObject().get("meters").getAsDouble();
                double d_feet = jsonObject.get("diameter").getAsJsonObject().get("feet").getAsDouble();
                String descrip = jsonObject.get("description").getAsString();
                link = jsonObject.get("wikipedia").getAsString();
                JsonArray jsonArray = jsonObject.get("flickr_images").getAsJsonArray();
                String[] imgUrls = new String[jsonArray.size()];
                for(int i = 0; i<jsonArray.size(); i++){

                    imgUrls[i] = jsonArray.get(i).getAsString();
                }

                viewModel.setMutableLiveData( new DetailRocketInfo(rocketName, status, cost, successPct, h_meter, h_feet, d_meter,
                        d_feet, descrip, imgUrls));



            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
