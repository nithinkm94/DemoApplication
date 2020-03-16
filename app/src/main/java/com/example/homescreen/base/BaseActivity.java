package com.example.homescreen.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.homescreen.R;
import com.google.android.material.snackbar.Snackbar;


public class BaseActivity extends AppCompatActivity {


    Handler handler;
    Runnable r;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public void showSnackBar(View snackBarView, String message) {
        Snackbar snackbar;
        snackbar = Snackbar.make(snackBarView, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }


    public void startFragment(int frame, Fragment fragment, String tag) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frame, fragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void startAnimatedFragment(int frame, Fragment fragment, String tag) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_right);
        fragmentTransaction.replace(frame, fragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


    public Dialog mLoadingDialog;

    public void showLoadingDialog(Context context) {
        try{
            hideaLoadingDialog();
            if (mLoadingDialog == null) {
                mLoadingDialog = new Dialog(context);
                mLoadingDialog.setCancelable(false);
                mLoadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mLoadingDialog.setContentView(R.layout.dialogue_loading_layout);
                mLoadingDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent);

                mLoadingDialog.show();
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    // This is for hiding the progress
    public void hideaLoadingDialog() {
        try{
            if (mLoadingDialog != null)
                if (mLoadingDialog.isShowing()) {
                    mLoadingDialog.cancel();
                    mLoadingDialog.getWindow().closeAllPanels();
                }
                else {
                    mLoadingDialog = null;
                }
        }catch (Exception e ){
            e.printStackTrace();}


    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message , Toast.LENGTH_SHORT).show();
    }

}
