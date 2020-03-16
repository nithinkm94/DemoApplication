package com.example.homescreen;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.facebook.stetho.Stetho;

//import android.databinding.DataBindingUtil;

public class MyApplication extends Application{

    private static MyApplication uniqInstance;
    private int activityReferences = 0;
    private boolean isActivityChangingConfigurations = false;
    private int isActivityCreate = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

//        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
    }

    public synchronized static MyApplication getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new MyApplication();
        }
        return uniqInstance;
    }

}
