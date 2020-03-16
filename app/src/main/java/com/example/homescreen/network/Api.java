package com.example.homescreen.network;

import com.example.homescreen.model.HeaderModel;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @GET("facts")
    Observable<HeaderModel> getItems();




}
