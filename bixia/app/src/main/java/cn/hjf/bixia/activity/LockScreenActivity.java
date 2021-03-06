package cn.hjf.bixia.activity;

import cn.hjf.bixia.Constants;
import cn.hjf.bixia.R;
import cn.hjf.bixia.fragment.AboutFragment;
import cn.hjf.bixia.fragment.CommonHeaderFragment;
import cn.hjf.bixia.fragment.CommonHeaderFragment.HEAD_TYPE;
import cn.hjf.bixia.fragment.FristFragment;
import cn.hjf.bixia.util.DoubleClickExit;
import cn.hjf.bixia.util.Md5;
import cn.hjf.bixia.util.SPUtils;
import cn.hjf.bixia.util.SharedPreferencesUtil;
import cn.hjf.bixia.view.LockView;
import cn.hjf.bixia.view.ToastUtil;
import cn.hjf.bixia.view.LockView.OnInputListener;
import cn.hjf.bixia.view.LockView.Position;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LockScreenActivity extends BaseActivity implements CommonHeaderFragment.ICallback {
    
    public static final String PAGE_TYPE = "page_type";
    public static final String LAUNCH_TYPE = "launch_type";
    public static final String PASSWORD = "password";
    
    private CommonHeaderFragment mTitleFragment; //顶部标题栏
    
    private boolean mIsFirstLogin; //第一次打开，还没设置过密码
    private LockView mLockView; //九宫格
    private PageType mPageType; //页面工作类型
    private LaunchType mLaunchType; //页面启动类型
    private TextView mForgetText; //忘记密码
    
    private boolean mNeedFinishAnimate = false; //是否需要页面离开动画
    
    private String mPassword;
    
    private Position[] mPositions;

    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private BottomNavigationView mNavigationView;

    public enum PageType {
        SET_PWD, //首次使用，或者重置密码，第一次设置密码界面
        SET_PWD_REPEAT, //第二次设置密码界面
        INPUT_PWD //登录，输入密码界面
    }
    
    public enum LaunchType {
        APP_START, //应用启动
        FORGET_PWD, //应用启动，忘记密码
        RESET_PWD //进入设置界面，重置密码
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, LockScreenActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean utl = SPUtils.contains(this, "url");
        if(utl){
            setContentView(R.layout.main);
            initWeb();
        }else {
            setContentView(R.layout.activity_lock_screen);
            getWindow().setBackgroundDrawable(null);
            typeIdentify();
            initTitle();
            initView();
            initValue();
            initEvent();
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

    @Override
    protected void onResume() {
        super.onResume();
    }
    
    /**
     * 页面工作模式和启动模式识别
     */
    private void typeIdentify() {
        //应用刚启动
        if (getIntent().getSerializableExtra(PAGE_TYPE) == null) {
            mPassword = SharedPreferencesUtil.getSharedPreferences(this).getString(Constants.PASSWORD, null);
            mIsFirstLogin = mPassword == null ? true : false;
            //还没有密码,页面状态为 SET_PWD
            if (mIsFirstLogin) {
                mPageType = PageType.SET_PWD;
            }
            //已经有密码,页面状态为 INPUT_PWD
            else {
                mPageType = PageType.INPUT_PWD;
            }
        } else {
            mPageType = (PageType) getIntent().getSerializableExtra(PAGE_TYPE);
        }
        
        //页面启动模式，如果为空，就是App启动
        mLaunchType = (LaunchType) getIntent().getSerializableExtra(LAUNCH_TYPE);
        if (mLaunchType == null) {
            mLaunchType = LaunchType.APP_START;
        }
        Log.i("O_O", "mPageType : " + mPageType);
        Log.i("O_O", "mLaunchType : " + mLaunchType);
    }
    
    /**
     * 初始化顶部导航栏
     */
    @Override
    public void initTitle() {
        mTitleFragment = (CommonHeaderFragment) mFragmentManager.findFragmentById(R.id.title_lock_screen);
        mTitleFragment.setHeadBtnType(HEAD_TYPE.LEFT_BACK_TEXT,HEAD_TYPE.RIGHT_NULL);
        switch (mPageType) {
        case SET_PWD:
            mTitleFragment.setHeadText(R.string.title_back, R.string.title_set_password, null);
            break;
        case SET_PWD_REPEAT:
            mTitleFragment.setHeadText(R.string.title_back, R.string.title_set_password_repeat, null);
            break;
        case INPUT_PWD:
            mTitleFragment.setHeadText(R.string.title_back, R.string.title_input_password, null);
            break;
        default:
            break;
        }
        mTitleFragment.setCallback(this);
    }

    @Override
    protected void initView() {
        mLockView = (LockView) findViewById(R.id.v_lock);
        mForgetText = (TextView) findViewById(R.id.tv_forget_pwd);
    }

    @Override
    protected void initValue() {
        switch (mPageType) {
        case SET_PWD:
            mPassword = null;
            break;
        case SET_PWD_REPEAT:
            mPassword = getIntent().getStringExtra(PASSWORD);
            break;
        case INPUT_PWD:
            mPassword = SharedPreferencesUtil.getSharedPreferences(this).getString("password", null);
            break;
        default:
            break;
        }
        
        if (mPageType.equals(PageType.INPUT_PWD)) {
            mForgetText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initEvent() {
        mForgetText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LockScreenActivity.this, LockScreenActivity.class);
                intent.putExtra(PAGE_TYPE, PageType.SET_PWD);
                intent.putExtra(LAUNCH_TYPE, LaunchType.FORGET_PWD);
                startActivity(intent);
                finish();
            }
        });
        
        mLockView.setOnInputListener(new OnInputListener() {
            @Override
            public void OnInputCompleted(Position[] inputResult) {
                mPositions = inputResult;
                
                if (mPageType.equals(PageType.SET_PWD)) {
                    mPassword = getPassword(inputResult);
                    Intent intent = new Intent(LockScreenActivity.this, LockScreenActivity.class);
                    intent.putExtra(PAGE_TYPE, PageType.SET_PWD_REPEAT);
                    intent.putExtra(PASSWORD, mPassword);
                    intent.putExtra(LAUNCH_TYPE, mLaunchType);
                    startActivity(intent);
                    finish();
                    return;
                }
                if (mPageType.equals(PageType.SET_PWD_REPEAT)) {
                    if (mPassword.equals(getPassword(inputResult))) {
                        SharedPreferencesUtil.getSharedPreferences(LockScreenActivity.this).edit().putString(Constants.PASSWORD, mPassword).commit();
                        ToastUtil.showToast(getApplicationContext(), getString(R.string.tip_set_password_success), Toast.LENGTH_LONG);
                        switch (mLaunchType) {
                        case APP_START:
                            Intent intent = new Intent(LockScreenActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        case FORGET_PWD:
                            Intent intent1 = new Intent(LockScreenActivity.this, LockScreenActivity.class);
                            intent1.putExtra(PAGE_TYPE, PageType.INPUT_PWD);
                            startActivity(intent1);
                            finish();
                            break;
                        case RESET_PWD:
                            mNeedFinishAnimate = true;
                            finish();
                            break;
                        default:
                            break;
                        }
                        

                        
                        
                        
                    } else {
                        ToastUtil.showToast(getApplicationContext(), getString(R.string.tip_password_conflict), Toast.LENGTH_SHORT);
                        mLockView.resetDelayed(1000);
                    }
                    return;
                }
                if (mPageType.equals(PageType.INPUT_PWD)) {
                    if (mPassword.equals(getPassword(inputResult))) {
                        startActivity(new Intent(LockScreenActivity.this, MainActivity.class));
                        finish();
                    } else {
                        ToastUtil.showToast(getApplicationContext(), getString(R.string.tip_password_error), Toast.LENGTH_SHORT);
                        mLockView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mLockView.drawCircles(mPositions, Color.RED);
                            }
                        }, 50);
                        mLockView.resetDelayed(2500);
                    }
                    return;
                }
                
            }
        });
    }
    
    /**
     * 根据九宫格数据，获得MD5加密的数据
     * @param inputResult
     * @return
     */
    private String getPassword(Position[] inputResult) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mPositions.length; i++) {
            sb.append(mPositions[i].row);
            sb.append(mPositions[i].column);
        }
        return Md5.md5(sb.toString());
    }

    
    @Override
    protected boolean needFinishAnimate() {
        return mNeedFinishAnimate;
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {
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
