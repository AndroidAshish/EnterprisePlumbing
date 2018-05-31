package com.example.smart.enterpriseplumbing;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.smart.enterpriseplumbing.utils.AppPreference;


/**
 * Created by App_DEVELOPER on 7/4/2017.
 */

public class CommonBaseActivity extends AppCompatActivity {
    public AppPreference prefs;
    public boolean isErrorResponse;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new AppPreference(CommonBaseActivity.this.getApplicationContext());
        isErrorResponse = false;
    }


}
