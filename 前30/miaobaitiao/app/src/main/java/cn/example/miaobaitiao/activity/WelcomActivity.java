package com.example.miaobaitiao.activity;

import android.net.ConnectivityManager;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.miaobaitiao.MyApplication;
import com.example.miaobaitiao.task.GetImageBean;
import com.example.miaobaitiao.task.GetProduct;
import com.example.miaobaitiao.util.SPUtils;
import com.example.miaobaitiao.util.SharedPreferencesUtil;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class WelcomActivity extends Activity {

	private SwitchHandler mHandler = new SwitchHandler(this);
	private static  final String URL="http://www.shoujiweidai.com/android/app1006.html";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		boolean url1 = SPUtils.contains(this, "url");
		if(!url1){
			setUrl();
		}else {
			TextNet();
			boolean flag = SPUtils.contains(this, "flag");
			if(flag){
				mHandler.sendEmptyMessageDelayed(2, 1000);
			}else {
				mHandler.sendEmptyMessageDelayed(3, 1000);
			}
		}
	}


	private void TextNet() {
		ConnectivityManager con= (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
		if(wifi|internet){
			//执行相关操作
			new GetImageBean(this).execute();
			new GetProduct(this).execute();
		}else{
			Toast.makeText(this,
					"亲，网络连接失败咯！", Toast.LENGTH_LONG)
					.show();
		}
	}
	private void setUrl() {

		StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				new GetImageBean(WelcomActivity.this).execute();
				new GetProduct(WelcomActivity.this).execute();
				SPUtils.put(WelcomActivity.this,"url",URL);
				setWelcome();

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				mHandler.sendEmptyMessageDelayed(1, 1500);


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
			mHandler.sendEmptyMessageDelayed(2, 1500);
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
				switch (msg.what){
					case 1:
						MainActivity.launch(activity);
						activity.finish();
						break;
					case 2:
						HomeActivity.launch(activity);
						activity.finish();
						break;
					case 3:
						Login2Activity.launch(activity);
						activity.finish();
						break;

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
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
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
