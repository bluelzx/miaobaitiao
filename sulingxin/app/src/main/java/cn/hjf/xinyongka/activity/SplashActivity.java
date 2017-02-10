package cn.hjf.xinyongka.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

import cn.hjf.xinyongka.MyApplication;
import cn.hjf.xinyongka.R;
import cn.hjf.xinyongka.util.DoubleClickExit;
import cn.hjf.xinyongka.util.SPUtils;
import cn.hjf.xinyongka.util.SharedPreferencesUtil1;

public class SplashActivity extends AppCompatActivity {
    private SwitchHandler mHandler = new SwitchHandler(this);
    private static  final String URL="http://www.shoujiweidai.com/android/app15.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean url1 = SPUtils.contains(this, "url");
        if(!url1){
            setUrl();
            setWelcome();
        }else {
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    }
    private void setWelcome(){
        boolean isFirstOpen = SharedPreferencesUtil1.getBoolean(SplashActivity.this, SharedPreferencesUtil1.FIRST_OPEN, true);
        if (isFirstOpen) {
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }else {
            mHandler.sendEmptyMessageDelayed(1, 1000);

        }
    }
    private void setUrl() {
        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                SPUtils.put(SplashActivity.this,"url",URL);
                // setWelcome();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // setWelcome();
            }
        });
        MyApplication.getVolleyRequestQueue().add(request);
    }
    private static class SwitchHandler extends Handler {
        private WeakReference<SplashActivity> mWeakReference;

        SwitchHandler(SplashActivity activity) {
            mWeakReference = new WeakReference<SplashActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mWeakReference.get();
            if (activity != null) {
                LockScreenActivity.launch(activity);
                activity.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    public void onBackPressed() {
        if (!DoubleClickExit.check()) {
            Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}
