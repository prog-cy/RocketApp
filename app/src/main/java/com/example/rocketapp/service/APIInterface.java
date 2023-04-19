package com.example.rocketapp.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("v3/rockets")
    Call<JsonArray> getAllRocket();


    @GET("v3/rockets/{rocket_id}")
    Call<JsonObject> getSpecificRocket(@Path("rocket_id") String rocketId);
}
