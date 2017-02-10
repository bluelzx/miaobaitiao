package com.example.apple.xianjinxia.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.apple.xianjinxia.R;
import com.example.apple.xianjinxia.utils.SPUtils;
import com.example.apple.xianjinxia.utils.SharedPreferencesUtil;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

public class GuildActivity extends AppCompatActivity {
    private SwitchHandler mHandler = new SwitchHandler(this);

    private static  final String URL="http://www.shoujiweidai.com/android/app28.html";
    private static  boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean url1 = SPUtils.contains(this, "url");
        if(!url1){
            setUrl();
           // setWelcome();
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }else {
            flag=true;
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    }
    private void setUrl() {

        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                SPUtils.put(GuildActivity.this,"url",URL);
                flag=true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApp.getVolleyRequestQueue().add(request);
    }

    private static class SwitchHandler extends Handler {
        private WeakReference<GuildActivity> mWeakReference;

        SwitchHandler(GuildActivity activity) {
            mWeakReference = new WeakReference<GuildActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            GuildActivity activity = mWeakReference.get();
            if (activity != null) {
                if(flag){
                    Main2Activity.launch(activity);
                }else {
                    MainActivity.launch(activity);
                }
                activity.finish();
            }
        }
    }
    private void setWelcome(){
        boolean isFirstOpen = SharedPreferencesUtil.getBoolean(GuildActivity.this, SharedPreferencesUtil.FIRST_OPEN, true);
        if (isFirstOpen) {
            Intent intent = new Intent(GuildActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }else {
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
