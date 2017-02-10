package com.example.apple.mashangdai.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.apple.mashangdai.R;
import com.example.apple.mashangdai.activity.activity.fragment.AboutFragment;
import com.example.apple.mashangdai.activity.activity.fragment.AddFragment;
import com.example.apple.mashangdai.activity.activity.fragment.DetailFragment;
import com.example.apple.mashangdai.activity.activity.fragment.FristFragment;
import com.example.apple.mashangdai.activity.activity.fragment.MineFragment;
import com.example.apple.mashangdai.activity.activity.util.DoubleClickExit;
import com.example.apple.mashangdai.activity.activity.util.SPUtils;
import com.umeng.analytics.MobclickAgent;
import com.wingsofts.byeburgernavigationview.ByeBurgerBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rgMain;
    private ViewPager vpMain;
    private MineFragment mineFragment;
    private RadioButton detail,my,add;
    private ByeBurgerBehavior mBehavior;
    private DetailFragment detailFragment;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private BottomNavigationView mNavigationView;
    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  sp = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        String  url = sp.getString("url", "");*/
        boolean utl = SPUtils.contains(this, "url");
        if(utl){
            setContentView(R.layout.activity_main);
            initWeb();
        }else {
            setContentView(R.layout.main);
            initDate();
            initView();
        }
    }

    private void initWeb() {
        fragmentList = new ArrayList<>();
        fragmentList.add(FristFragment.newInstance());
        fragmentList.add(AboutFragment.newInstance("me"));
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mNavigationView = (BottomNavigationView) findViewById(R.id.bye_burger);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override public int getCount() {
                return fragmentList.size();
            }
        });
       // mViewPager.setCurrentItem(0);
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if(item.getTitle().equals("home")){
                            mViewPager.setCurrentItem(0);
                        }else if(item.getTitle().equals("me")){
                            mViewPager.setCurrentItem(1);
                        }
                        return false;
                    }
                });
    }

    private void initDate() {
        fragmentList = new ArrayList<>();
        fragmentList.add(DetailFragment.newInstance());

        fragmentList.add(AddFragment.newInstance("发现"));

        fragmentList.add(MineFragment.newInstance("关于"));
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mNavigationView = (BottomNavigationView) findViewById(R.id.bye_burger);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override public int getCount() {
                return fragmentList.size();
            }
        });
        mViewPager.setCurrentItem(1);
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if(item.getTitle().equals("首页")){
                            mViewPager.setCurrentItem(0);
                            if(detailFragment != null){
                                detailFragment.refreshData();
                            }
                        }else if(item.getTitle().equals("发现")){
                            mViewPager.setCurrentItem(1);

                        }else if(item.getTitle().equals("关于")){
                            mViewPager.setCurrentItem(2);
                        }
                        return false;
                    }
                });
    }
/*
    private void initView(){
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        ((RadioButton)rgMain.getChildAt(1)).setChecked(true);
        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        vpMain.setCurrentItem(1);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_detail:
                        vpMain.setCurrentItem(0);
                        break;
                    case R.id.rb_add:
                        vpMain.setCurrentItem(1);
                        break;
                    case R.id.rb_mine:
                        vpMain.setCurrentItem(2);
                        break;
                }
            }
        });
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                ((RadioButton)rgMain.getChildAt(position)).setChecked(true);
                switch (position){
                    case 0:
                        if(detailFragment != null){
                            detailFragment.refreshData();
                        }
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                        }
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
                        }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //用于解决ViewPager 和 CoordinatorLayout之间存在的fitSystemWindows属性无效bug。
        ViewCompat.setOnApplyWindowInsetsListener(vpMain,
                new OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v,
                                                                  WindowInsetsCompat insets) {
                        insets = ViewCompat.onApplyWindowInsets(v, insets);
                        if (insets.isConsumed()) {
                            return insets;
                        }
                        boolean consumed = false;
                        for (int i = 0, count = vpMain.getChildCount(); i < count; i++) {
                            ViewCompat.dispatchApplyWindowInsets(vpMain.getChildAt(i), insets);
                            if (insets.isConsumed()) {
                                consumed = true;
                            }
                        }
                        return consumed ? insets.consumeSystemWindowInsets() : insets;
                    }
                });

    }
    class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    detailFragment = DetailFragment.newInstance();
                    return detailFragment;
                case 1:
                    return AddFragment.newInstance();
                case 2:
                    mineFragment = MineFragment.newInstance("", "");
                    return mineFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 202 && mineFragment != null){
            mineFragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
