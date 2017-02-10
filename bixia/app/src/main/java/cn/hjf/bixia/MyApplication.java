package cn.hjf.bixia;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;

/**
 * 自定义的Application类
 * 
 * @author huangjinfu
 * 
 */
public class MyApplication extends Application {
	
	private String mBuglyAppID = "900002004"; //Bugly使用的AppID
	private String mBuglyAppKey = "WRHDP8yQOdXUINId"; //Bugly使用的AppKey
	public static RequestQueue requestQueue;
	public static RequestQueue getVolleyRequestQueue(){
		return requestQueue;
	}
	@Override
	public void onCreate() {
		super.onCreate();

		requestQueue= Volley.newRequestQueue(getApplicationContext());

//		SharedPreferencesUtil.getSharedPreferences(this).edit().putString("theme", "blue");
		//崩溃信息上传到Bugly
		CrashReport.initCrashReport(this.getApplicationContext(), mBuglyAppID, false);
		
	}
}
