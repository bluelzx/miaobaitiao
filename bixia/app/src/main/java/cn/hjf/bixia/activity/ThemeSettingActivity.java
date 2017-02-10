package cn.hjf.bixia.activity;

import java.util.Arrays;
import java.util.List;

import cn.hjf.bixia.R;
import cn.hjf.bixia.adapter.SettingSelectMenuAdapter;
import cn.hjf.bixia.fragment.CommonHeaderFragment;
import cn.hjf.bixia.fragment.CommonHeaderFragment.HEAD_TYPE;

import android.os.Bundle;
import android.widget.ListView;

/**
 * 主题设置界面
 * 
 * @author huangjinfu
 * 
 */
public class ThemeSettingActivity extends BaseActivity implements CommonHeaderFragment.ICallback{
    
    private CommonHeaderFragment mTitleFragment; //顶部标题栏
    private ListView mMenuList; //菜单列表
    private SettingSelectMenuAdapter mSettingSelectMenuAdapter; //菜单显示适配器
    private List<String> mMenuString; //菜单列表

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_setting);
        getWindow().setBackgroundDrawable(null);
        
        initTitle();
        initView();
        initValue();
        initEvent();
    }
    
    /**
     * 初始化顶部导航栏
     */
    @Override
    public void initTitle() {
        mTitleFragment = (CommonHeaderFragment) mFragmentManager.findFragmentById(R.id.title_theme_setting);
        mTitleFragment.setHeadBtnType(HEAD_TYPE.LEFT_BACK_TEXT,HEAD_TYPE.RIGHT_NULL);
        mTitleFragment.setHeadText(R.string.title_back, R.string.title_theme_setting, null);
        mTitleFragment.setCallback(this);
    }

    /**
     * 初始化各控件
     */
    @Override
    protected void initView() {
        mMenuList = (ListView) findViewById(R.id.lv_theme_select);
    }

    /**
     * 初始化各控件的值
     */
    @Override
    protected void initValue() {
        mMenuString = Arrays.asList(getResources().getStringArray(R.array.setting_select_menu));
        mSettingSelectMenuAdapter = new SettingSelectMenuAdapter(this, mMenuString);
        mMenuList.setAdapter(mSettingSelectMenuAdapter);
    }

    /**
     * 初始化各控件的事件
     */
    @Override
    protected void initEvent() {

    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {
    }

}
