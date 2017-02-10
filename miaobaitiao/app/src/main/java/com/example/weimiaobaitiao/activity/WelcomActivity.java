package com.example.weimiaobaitiao.activity;

import android.os.Bundle;

import java.lang.ref.WeakReference;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.weimiaobaitiao.MyApplication;
import com.example.weimiaobaitiao.util.SPUtils;
import com.example.weimiaobaitiao.util.SharedPreferencesUtil;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class WelcomActivity extends Activity {

	private SwitchHandler mHandler = new SwitchHandler(this);
	private static  final String URL="http://www.shoujiweidai.com/android/app12.html";
	private static boolean TAG=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		boolean url1 = SPUtils.contains(this, "url");
		if(!url1){
			setUrl();
			setWelcome();
		}else {
			TAG=true;
			mHandler.sendEmptyMessageDelayed(1, 1000);
		}
	}
	private void setUrl() {

		StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				SPUtils.put(WelcomActivity.this,"url",URL);
				TAG=true;
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
			}
		});
		MyApplication.getVolleyRequestQueue().add(request);
	}
	private void setWelcome(){
		boolean isFirstOpen = SharedPreferencesUtil.getBoolean(WelcomActivity.this, SharedPreferencesUtil.FIRST_OPEN, true);
		if (isFirstOpen) {
			Intent intent = new Intent(WelcomActivity.this, GuideActivity.class);
			startActivity(intent);
			finish();
			return;
		}else {
			mHandler.sendEmptyMessageDelayed(1, 1500);
		}
	}
	private static class SwitchHandler extends Handler {
		private WeakReference<WelcomActivity> mWeakReference;
		SwitchHandler(WelcomActivity activity) {
			mWeakReference = new WeakReference<WelcomActivity>(activity);
		}
		@Override
		public void handleMessage(Message msg) {
			WelcomActivity activity = mWeakReference.get();
			if (activity != null) {
				if(TAG){
					Main2Activity.launch(activity);
					activity.finish();
				}else {
					LoginActivity.launch(activity);
					activity.finish();
				}
			}

		}
	}
	private int backPressCount = 0;
	@Override
	public void onBackPressed() {

		backPressCount++;
		if (2 == backPressCount) {
			this.finish();
		} else {
			Toast.makeText(this, "再按一次退出程序", 0).show();
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
