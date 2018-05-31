package com.example.smart.enterpriseplumbing.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ANDROID SUBHENDU on 12/5/2016.
 */
public class VolleySingleton {
    private static VolleySingleton ourInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private VolleySingleton(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    public static VolleySingleton getInstance() {
        return ourInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new VolleySingleton(context);
        }
        return ourInstance;
    }

    public <T> void addToRequestque(Request<T> request) {
        requestQueue.add(request);
    }
}
