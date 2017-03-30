package com.example.apple.xianjinxia.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.apple.xianjinxia.R;
import com.example.apple.xianjinxia.fragment.FristFragment;
import com.example.apple.xianjinxia.utils.DoubleClickExit;
import com.umeng.analytics.MobclickAgent;

public class Main2Activity extends AppCompatActivity {
    private FragmentManager mFragmentManager;

    private Fragment mCurrentFragment;
    public static void launch(Context context) {
        context.startActivity(new Intent(context, Main2Activity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initWeb();

    }


    private void initWeb() {
        mCurrentFragment=new FristFragment();
        mFragmentManager=getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.app_item, mCurrentFragment).commit();

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
