package com.example.demodrawer.rest;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static Retrofit retrofit = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()))
                    .build();
        }
        return retrofit;
    }
}
