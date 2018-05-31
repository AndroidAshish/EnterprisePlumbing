package com.example.smart.enterpriseplumbing.utils;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.multidex.MultiDex;


/**
 * Created by Developer on 5/26/2017.
 */

public class App extends Application {

    public static final String TAG = App.class.getSimpleName();
    private static App mInstance;

    public static synchronized App getInstance() {
        return mInstance;
    }
    private static double CURRENT_LATITUDE = 0.0;
    private static double CURRENT_LONGITUDE = 0.0;
    private SharedPreferences prefs;
    public String pushType = "";
    Uri selectedImage;
    public Uri getImageUri(){
        return selectedImage;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance = this;

    }
    public void setImageUri(Uri selectedImage_){
        selectedImage = selectedImage_;
    }

    public boolean isConnected() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;
        }

        return false;
    }

    public ProgressDialog getProgressbar(){
        ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        return progressDialog;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }


}