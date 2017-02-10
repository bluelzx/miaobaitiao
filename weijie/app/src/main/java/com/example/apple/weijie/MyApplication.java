package com.example.apple.weijie;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by apple on 17/1/8.
 */

public class MyApplication extends Application {
    public static RequestQueue requestQueue;
    public static RequestQueue getVolleyRequestQueue(){
        return requestQueue;
    }
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        app = (MyApplication) getApplicationContext();
    }
}
