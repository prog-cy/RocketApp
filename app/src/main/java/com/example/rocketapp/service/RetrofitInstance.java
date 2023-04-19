package com.example.rocketapp.service;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit retrofit = null;

//    static int cacheSize = 10 * 1024 * 1024; // 10 MB
//     Cache cache = new Cache(Context.getCacheDir(), cacheSize);

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.MINUTES)
//                .cache(cache)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES).addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

}
