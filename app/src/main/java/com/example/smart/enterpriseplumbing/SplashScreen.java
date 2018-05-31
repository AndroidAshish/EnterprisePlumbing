package com.example.smart.enterpriseplumbing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.smart.enterpriseplumbing.utils.Constants;


/**
 * Created by kmidev on 26/12/15.
 */
public class SplashScreen extends CommonBaseActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if((prefs.getBooleanValueForTag(Constants.KEY_REMEMBER))){

                    Intent i=new Intent(SplashScreen.this,PendingWorkActivity.class);
                    startActivity(i);

                }
                else
                {
                    Intent i=new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(i);

                }

            }
        }, SPLASH_TIME_OUT);
    }
}
