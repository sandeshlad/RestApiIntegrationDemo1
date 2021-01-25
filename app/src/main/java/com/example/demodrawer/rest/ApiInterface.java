package com.example.demodrawer.rest;

import com.example.demodrawer.model.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET("albums")
    Call<List<Albums>> getAllAlbums();

    @GET("albums/{id}")
    Call<Albums> getAlbumById(@Path("id") int id);


}
