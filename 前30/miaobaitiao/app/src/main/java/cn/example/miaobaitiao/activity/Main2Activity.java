package com.example.miaobaitiao.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.miaobaitiao.R;
import com.example.miaobaitiao.fragment.FristFragment;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    @Bind(R.id.app_item)
    FrameLayout appItem;
    private FragmentManager mFragmentManager;

    private Fragment mCurrentFragment;



    public static void launch(Context context) {
        context.startActivity(new Intent(context, Main2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
}
