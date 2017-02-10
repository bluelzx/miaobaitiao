package com.Michael;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.Michael.AccountBook.R;
import com.umeng.analytics.MobclickAgent;

import adapter.HomePagerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import fragment.InputFragment;
import fragment.OutPutFragment;

public class PuzzleActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @Bind(R.id.coord)
    CoordinatorLayout coord;
    @Bind(R.id.activity_puzzle)
    DrawerLayout activityPuzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
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
        HomePagerAdapter mHomePagerAdapter = new HomePagerAdapter(this.getSupportFragmentManager());
        mHomePagerAdapter.addTab(new InputFragment(), "收入饼图");
        mHomePagerAdapter.addTab(new OutPutFragment(), "支出饼图");
        viewPager.setAdapter(mHomePagerAdapter);
        tabLayout.setupWithViewPager(viewPager, false);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
