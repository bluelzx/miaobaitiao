package com.example.laijie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import fragment.AboutFragment;
import fragment.FristFragment;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;
import utlis.DoubleClickExit;

public class Main2Activity extends AppCompatActivity {
    @Bind(R.id.app_item)
    FrameLayout appItem;
    @Bind(R.id.tab)
    PagerBottomTabLayout tab;
    @Bind(R.id.activity_main2)
    RelativeLayout activityMain2;
    private Controller mController;
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

        tab.builder().build().setBackgroundColor(getResources().getColor(R.color.divider));
        mCurrentFragment=new FristFragment();
        mFragmentManager=getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.app_item, mCurrentFragment).commit();
        mController=tab.builder()
                .addTabItem(R.drawable.home1, "首页")
                .addTabItem(R.drawable.bug,"帮助")
                .build();
        mController.addTabItemClickListener(listener);
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
                return FristFragment.class.getName();
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
