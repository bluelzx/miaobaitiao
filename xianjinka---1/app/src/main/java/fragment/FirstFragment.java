package fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Michael.xianjinka.R;
import com.Michael.MySQLiteOpenHelper;
import com.Michael.PuzzleActivity;
import com.Michael.xianjinka.SPUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ericssonlabs.BarCodeTestActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private static final String TAG = FirstFragment.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.spinner1)
    Spinner spinner1;
    @Bind(R.id.radioButton_main_income)
    RadioButton radioButtonMainIncome;
    @Bind(R.id.radioButton_main_expend)
    RadioButton radioButtonMainExpend;
    @Bind(R.id.button_confirm)
    Button buttonConfirm;
    @Bind(R.id.text_main_date)
    TextView textMainDate;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.Layout)
    LinearLayout Layout;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.editText_mian_input)
    EditText editTextMianInput;
    @Bind(R.id.listView_main_bill)
    ListView listViewMainBill;
    private MySQLiteOpenHelper dbHelper;
    private List<Map<String, Object>> totalList;
    private SimpleAdapter adapter;
    //收入、支出状态
    private String state;
    //收入、支出的数组
    private String[] arr;
    //第一个下拉框
    private String item1;
    private SharedPreferences sp;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        // toolbar.setNavigationIcon(R.drawable.back);
        String url = (String) SPUtils.get(getActivity(), "url", "");
        if (url.length()>0) {
            webView.setVisibility(View.VISIBLE);
            Log.e(TAG, url.toString() + "111111111111");
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCachePath(getActivity().getCacheDir().getPath());
            TextNet();
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);

                    return true;
                }
            });
            webView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if(keyEvent.getAction()==keyEvent.ACTION_DOWN){

                        if (i == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                            webView.goBack();
                            return true;
                        }
                    }
                    return false;
                }
            });
        } else {
            Log.e(TAG, "++++++++");
            Layout.setVisibility(View.VISIBLE);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });

            toolbar.setTitle("现金卡");
            toolbar.inflateMenu(R.menu.menu_main);
            init(view);
            setListener();
        }
        return view;
    }

    /**
     * 检测网络是否可用
     */
    private void TextNet() {
        ConnectivityManager con=(ConnectivityManager)getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if(wifi|internet){
            //执行相关操作
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        }else{
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            Toast.makeText(getActivity(),
                    "亲，网络连接失败咯！", Toast.LENGTH_LONG)
                    .show();
        }
    }
    private void setListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_saoma:
                        Intent intent2 = new Intent();
                        intent2.setClass(getActivity(), BarCodeTestActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.menu_pingtu:
                        startActivity(new Intent(getActivity(), PuzzleActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    public void init(View view) {

        dbHelper = new MySQLiteOpenHelper(getActivity());
        totalList = new ArrayList<Map<String, Object>>();

        //listView添加适配器
        adapter = new SimpleAdapter(getActivity(), totalList, R.layout.layout_listview_itme, new String[]{"state", "money", "bigKind", "smallKind", "date"},
                new int[]{R.id.textView_item_state, R.id.textView_item_money, R.id.textView_item_bigKind, R.id.textView_item_smallKind, R.id.textView_item_date});

        listViewMainBill.setAdapter(adapter);
        reloadListView();

        //收入类型
        arr = new String[]{"工资", "外快"};
        changeSpinner();
    }

    public void changeSpinner() {

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, android.R.id.text1, arr);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item1 = adapter1.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
/*
    public void clickButton(View view) {
        switch (view.getId()) {
            //选择日期
            case R.id.text_main_date:

                break;

            //确定记账
            case R.id.button_confirm:

        }
    }

    public void changeSpinner(View view) {
        switch (view.getId()) {
            case R.id.radioButton_main_expend:
                arr = new String[]{"吃", "穿", "住", "行"};
                changeSpinner();
                break;
            case R.id.radioButton_main_income:
                arr = new String[]{"工资", "外快"};
                changeSpinner();
                break;
        }
    }*/

    public void reloadListView() {
        totalList.clear();
        List<Map<String, Object>> currentList = dbHelper.selectList(
                "select state,money,bigKind,smallKind,date from tb_myAccount order by id desc", null);
        totalList.addAll(currentList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webView.destroy();
        webView.removeAllViews();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.radioButton_main_income, R.id.radioButton_main_expend, R.id.button_confirm, R.id.text_main_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButton_main_income:
                arr = new String[]{"工资", "外快"};
                changeSpinner();
                break;
            case R.id.radioButton_main_expend:
                arr = new String[]{"吃", "穿", "住", "行"};
                changeSpinner();
                break;
            case R.id.button_confirm:
                //输入的金额
                String money = editTextMianInput.getText().toString();
                //输入时选择的日期
                String date = textMainDate.getText().toString();

                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton_main_expend) {
                    state = "支出";

                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton_main_income) {
                    state = "收入";
                }

                if (TextUtils.isEmpty(money) || date.equals("选择日期")) {
                    Toast.makeText(getActivity(), "请检查金额和日期是否填写正确！", Toast.LENGTH_SHORT).show();
                } else {
                    String insertSql = "insert into tb_myAccount (state,money,bigKind,smallKind,date) values (?,?,?,?,?)";

                    boolean flag = dbHelper.execData(insertSql,
                            new String[]{state, money, item1, null, date});
                    if (flag) {
                        reloadListView();
                    }
                }
                break;
            case R.id.text_main_date:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int monthOfYear = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String dateString = year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth;
                                textMainDate.setText(dateString);
                            }
                        }, year, monthOfYear, dayOfMonth);
                dDialog.show();
                break;
        }
    }


}
