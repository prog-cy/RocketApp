package com.example.rocketapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import com.example.rocketapp.R;
import com.example.rocketapp.adapter.MyAdapter;
import com.example.rocketapp.model.RocketInfo;
import com.example.rocketapp.service.APIInterface;
import com.example.rocketapp.service.RetrofitInstance;
import com.example.rocketapp.viewmodel.MainActivityViewModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Toolbar toolbar;

    MainActivityViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Space-X Rocket");
        toolbar.setNavigationIcon(R.drawable.baseline_navigate_before_24);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        //Instantiating MyViewModel to use live data.
        myViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        //Method loads data from api.
        loadAllRockets();

        //LiveData which takes mutable live data from MyViewModel class
        LiveData<List<RocketInfo>> liveData = myViewModel.getMutableLiveData();

        //This will make persistence change in data when orientation of phone is changed
        liveData.observe(this, new Observer<List<RocketInfo>>() {
            @Override
            public void onChanged(List<RocketInfo> rocketInfos) {
                MyAdapter adapter = new MyAdapter(MainActivity.this, rocketInfos);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void loadAllRockets() {

        List<RocketInfo> rocketInfoList = new ArrayList<>();
        APIInterface apiInterface = RetrofitInstance.getClient().create(APIInterface.class);
        Call<JsonArray> call = apiInterface.getAllRocket();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray jsonArray = response.body();

                for(int i = 0; i<jsonArray.size(); i++){

                    JsonElement jsonElement = jsonArray.get(i);
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    String image = jsonObject.get("flickr_images").getAsJsonArray().get(0).getAsString();
                    int engine = jsonObject.get("engines").getAsJsonObject().get("number").getAsInt();
                    rocketInfoList.add(
                            new RocketInfo(jsonObject.get("country").getAsString(),
                                    jsonObject.get("rocket_name").getAsString(), image, engine,
                                    jsonObject.get("rocket_id").getAsString()));
                }

                //Setting data into live data.
                myViewModel.setMutableLiveData(rocketInfoList);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });




    }
}