package com.Michael;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.Michael.xianjinka.R;
import com.Michael.xianjinka.SPUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import fragment.AboutFragment;
import fragment.FirstFragment;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.app_item)
    FrameLayout appItem;
    @Bind(R.id.tab)
    PagerBottomTabLayout tab;
    @Bind(R.id.activity_home)
    RelativeLayout activityHome;
    private Controller mController;
    private FragmentManager mFragmentManager;

    private Fragment mCurrentFragment;


    /**
     * Called when the activity is first created.
     */


    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();

    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private void initView() {
        boolean url1 = SPUtils.contains(this, "url");
        if(url1){
            tab.setVisibility(View.VISIBLE);
            tab.builder().build().setBackgroundColor(getResources().getColor(R.color.windows_color));
            mController=tab.builder()
                    .addTabItem(R.drawable.home, "首页")
                    .addTabItem(R.drawable.bug,"帮助")
                    .build();
            mController.addTabItemClickListener(listener);
        }
        mCurrentFragment=new FirstFragment();
        mFragmentManager=getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.app_item, mCurrentFragment).commit();
    }

    OnTabItemSelectListener listener=new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {
            switchMenu(getFragmentName(index+1));
        }

        @Override
        public void onRepeatClick(int index, Object tag) {

        }
    };

    private String getFragmentName(int menuId) {
        switch (menuId) {
            case 1:
                return FirstFragment.class.getName();
            case 2:
                return AboutFragment.class.getName();
            default:
                return null;
        }
    }
    private void switchMenu(String fragmentName) {
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);
        if (fragment != null) {
            if (fragment == mCurrentFragment) return;

            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            fragment = Fragment.instantiate(this, fragmentName);
            mFragmentManager.beginTransaction().add(R.id.app_item, fragment, fragmentName).commit();
        }

        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }
        mCurrentFragment = fragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    private long mLastBackTime = 0;
    @Override
    public void onBackPressed() {
        // finish while click back key 2 times during 1s.
        if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
            finish();
        } else {
            mLastBackTime = System.currentTimeMillis();
            Toast.makeText(this, "请再确认一次", Toast.LENGTH_SHORT).show();
        }

    }
}

