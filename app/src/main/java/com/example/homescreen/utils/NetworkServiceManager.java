package com.example.homescreen.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkServiceManager {

    Context context;

    private static NetworkServiceManager networkServiceManager;

    public NetworkServiceManager(Context base) {
        context = base;
    }

    public static final NetworkServiceManager getInstance(Context context) {
        if (networkServiceManager == null) {
            networkServiceManager = new NetworkServiceManager(context);
        }
        return networkServiceManager;
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        assert cm != null;
        if(cm.getActiveNetworkInfo()!= null){
            networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }else {
            return false;
        }
    }

}