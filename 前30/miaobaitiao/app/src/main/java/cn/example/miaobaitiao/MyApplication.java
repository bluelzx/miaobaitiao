package com.example.miaobaitiao;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.miaobaitiao.model.ImagerBean;
import com.example.miaobaitiao.model.Product;

/**
 * Created by apple on 17/1/6.
 */

public class MyApplication  extends Application{
    public static RequestQueue requestQueue;
    private static MyApplication sInstance;
    public static MyApplication getInstance() {
        return sInstance;
    }
    public static RequestQueue getVolleyRequestQueue(){
        return requestQueue;
    }
    private static ImagerBean user;
    private static Product product;

    public static Product getProduct() {
        return product;
    }

    public static void setProduct(Product product) {
        MyApplication.product = product;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        requestQueue= Volley.newRequestQueue(getApplicationContext());
    }

    public static ImagerBean getUser() {
        return user;
    }
    public static void setUser(ImagerBean user) {
        MyApplication.user = user;
    }

}
