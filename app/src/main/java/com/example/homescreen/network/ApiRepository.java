package com.example.homescreen.network;

import android.annotation.SuppressLint;
import android.content.Context;

import retrofit2.Retrofit;

public class ApiRepository {

    private static final String TAG = ApiRepository.class.getSimpleName();
    private static Api apiInterface;
    private static Retrofit mRetrofit_base;
    Retrofit mRetrofit_base1;
    Context mContext;
    String deviceInfo;
    Api apiService;

    @SuppressLint("StaticFieldLeak")
    private static ApiRepository apiRepository;


    public static ApiRepository getInstance() {
        if (apiRepository == null) {
            apiRepository = new ApiRepository();
        }

        mRetrofit_base = ApiCleint.getClient();
        apiInterface = mRetrofit_base.create(Api.class);
        return apiRepository;
    }



}