package cn.hjf.bixia.activity;

import java.util.Calendar;

import cn.hjf.bixia.R;
import cn.hjf.bixia.businessmodel.ConsumeType;
import cn.hjf.bixia.businessmodel.QueryInfo;
import cn.hjf.bixia.fragment.CommonHeaderFragment;
import cn.hjf.bixia.fragment.CommonHeaderFragment.HEAD_TYPE;
import cn.hjf.bixia.util.NumberUtil;
import cn.hjf.bixia.util.TimeUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

/**
 * 查询条件输入界面
 * @author xfujohn
 *
 */
public class QueryActivity extends BaseActivity implements CommonHeaderFragment.ICallback {
    
    private static final int REQ_CODE_SELECT_TYPE = 0; //请求选择消费类型的请求码
    public static final String QUERY_INFO = "query_info"; //从Intent中获取QueryInfo的key
    
    private CommonHeaderFragment mTitleFragment; //顶部标题栏
    
    private EditText mConsumeName; //消费记录名称
    private TextView mConsumeType; //消费记录类型
    private RelativeLayout mConsumeTypeLayout; //消费记录类型布局
    private TextView mStartTime; //开始时间
    private TextView mEndTime; //结束时间
    private Button mQueryButton; //查询按钮
    
    private DatePickerDialog mDatePickerDialog; // 消费日期选择对话框
    
    private QueryInfo mQueryInfo; //要返回的查询信息
    
    public QueryActivity() {
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        getWindow().setBackgroundDrawable(null);
        
        Intent intent = getIntent();
        if (intent != null) {
            mQueryInfo = intent.getParcelableExtra(QUERY_INFO);
            if (mQueryInfo == null) {
                finish();
                return;
            }
        }
        
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
        mTitleFragment = (CommonHeaderFragment) mFragmentManager.findFragmentById(R.id.title_query_record);
        mTitleFragment.setHeadBtnType(HEAD_TYPE.LEFT_BACK_TEXT,HEAD_TYPE.RIGHT_NULL);
        mTitleFragment.setHeadText(R.string.title_back, R.string.title_query_record, null);
        mTitleFragment.setCallback(this);
    }

    @Override
    protected void initView() {
        mConsumeName = (EditText) findViewById(R.id.et_record_name);
        mConsumeType = (TextView) findViewById(R.id.tv_record_type);
        mConsumeTypeLayout = (RelativeLayout) findViewById(R.id.rl_record_type);
        mStartTime = (TextView) findViewById(R.id.tv_record_date_start);
        mEndTime = (TextView) findViewById(R.id.tv_record_time_end);
        mQueryButton = (Button) findViewById(R.id.btn_query);
    }

    @Override
    protected void initValue() {
        mConsumeName.setText(mQueryInfo.getName());
        mConsumeType.setText(mQueryInfo.getType() == null ? null : mQueryInfo.getType().getName());
        mStartTime.setText(mQueryInfo.getStartTime() == null ? null : TimeUtil.getDateString(mQueryInfo.getStartTime()));
        mEndTime.setText(mQueryInfo.getEndTime() == null ? null : TimeUtil.getDateString(mQueryInfo.getEndTime()));
    }

    @Override
    protected void initEvent() {
        mQueryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buildQueryInfo();
                Intent intent = new Intent();
                intent.putExtra(QUERY_INFO, mQueryInfo);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        mConsumeTypeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryActivity.this, TypeSelectActivity.class);
                intent.putExtra(TypeSelectActivity.PAGE_TYPE, TypeSelectActivity.PageType.STATISTIC);
                QueryActivity.this.startActivityForResult(intent, REQ_CODE_SELECT_TYPE);
            }
        });
        
        mStartTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQueryInfo.getStartTime() == null) {
                    mQueryInfo.setStartTime(Calendar.getInstance());
                }
                mDatePickerDialog = new DatePickerDialog(QueryActivity.this,
                        AlertDialog.THEME_HOLO_LIGHT,
                        new OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                    int monthOfYear, int dayOfMonth) {
                                mStartTime.setText(year + " - "
                                        + NumberUtil.formatTwoInt(monthOfYear + 1) + " - " + NumberUtil.formatTwoInt(dayOfMonth));
                                mQueryInfo.getStartTime().set(Calendar.YEAR, year);
                                mQueryInfo.getStartTime().set(Calendar.MONTH, monthOfYear);
                                mQueryInfo.getStartTime().set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                mQueryInfo.getStartTime().set(Calendar.HOUR_OF_DAY, 0);
                                mQueryInfo.getStartTime().set(Calendar.MINUTE, 0);
                                mQueryInfo.getStartTime().set(Calendar.SECOND, 0);
                            }
                        }, mQueryInfo.getStartTime().get(Calendar.YEAR),
                        mQueryInfo.getStartTime().get(Calendar.MONTH),
                        mQueryInfo.getStartTime().get(Calendar.DAY_OF_MONTH));
//                mDatePickerDialog.getWindow().setWindowAnimations(R.style.tip_dialog_window_anim);
                mDatePickerDialog.show();
            }
        });
        mEndTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQueryInfo.getEndTime() == null) {
                    mQueryInfo.setEndTime(Calendar.getInstance());
                }
                mDatePickerDialog = new DatePickerDialog(QueryActivity.this,
                        AlertDialog.THEME_HOLO_LIGHT,
                        new OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                    int monthOfYear, int dayOfMonth) {
                                mEndTime.setText(year + " - "
                                        + NumberUtil.formatTwoInt(monthOfYear + 1) + " - " + NumberUtil.formatTwoInt(dayOfMonth));
                                mQueryInfo.getEndTime().set(Calendar.YEAR, year);
                                mQueryInfo.getEndTime().set(Calendar.MONTH, monthOfYear);
                                mQueryInfo.getEndTime().set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                mQueryInfo.getEndTime().set(Calendar.HOUR_OF_DAY, 23);
                                mQueryInfo.getEndTime().set(Calendar.MINUTE, 59);
                                mQueryInfo.getEndTime().set(Calendar.SECOND, 59);
                            }
                        }, mQueryInfo.getEndTime().get(Calendar.YEAR),
                        mQueryInfo.getEndTime().get(Calendar.MONTH),
                        mQueryInfo.getEndTime().get(Calendar.DAY_OF_MONTH));
//                mDatePickerDialog.getWindow().setWindowAnimations(R.style.tip_dialog_window_anim);
                mDatePickerDialog.show();
            }
        });
    }
    
    /**
     * 构建查询信息
     */
    private void buildQueryInfo() {
        mQueryInfo.setName(mConsumeName.getText().toString());
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_CODE_SELECT_TYPE) {
                ConsumeType type = data.getParcelableExtra(TypeSelectActivity.CONSUME_TYPE);
                if (type != null) {
                    mConsumeType.setText(type.getName());
                    mQueryInfo.setType(type);
                }
            }
        }
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {
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
