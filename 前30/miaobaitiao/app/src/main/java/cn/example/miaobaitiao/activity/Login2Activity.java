package com.example.miaobaitiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.miaobaitiao.R;
import com.example.miaobaitiao.model.Message;
import com.example.miaobaitiao.model.MessageBean;
import com.example.miaobaitiao.util.CaptchaTimeCount;
import com.example.miaobaitiao.util.Constants;
import com.example.miaobaitiao.util.SPUtils;
import com.example.miaobaitiao.util.ToastUtils;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.umeng.analytics.MobclickAgent;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login2Activity extends AppCompatActivity {

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.bt_getCode)
    Button btGetCode;

    private CaptchaTimeCount captchaTimeCount;
    private String MessageCode = null;


    public static void launch(Context context) {
        context.startActivity(new Intent(context, Login2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        captchaTimeCount = new CaptchaTimeCount(Constants.Times.MILLIS_IN_TOTAL, Constants.Times.COUNT_DOWN_INTERVAL, btGetCode, this);

    }

    @OnClick({R.id.bt_getCode, R.id.bt_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_getCode:
                if (!etPhone.getText().toString().isEmpty() && etPhone.length() == 11) {
                    getCodeMessage();
                } else {
                    ToastUtils.showToast(this, "请输入正确的手机号码 ");
                }
                break;
            case R.id.bt_Login:
                if (!etPhone.getText().toString().isEmpty() && !etCode.getText().toString().isEmpty() && etCode.getText().toString().equals(MessageCode)) {
                    setLogin();
                } else {
                    ToastUtils.showToast(this, "验证码或手机号不正确 ");
                    break;
                }
        }
    }
    /**
     * 获取短信验证码
     */
    private void getCodeMessage() {
        captchaTimeCount.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String URL = "http://www.shoujiweidai.com/Service/WSForAPP3.asmx";
                String nameSpace = "http://chachaxy.com/";
                String method_Name = "QuickLgnMsg";
                String SOAP_ACTION = nameSpace + method_Name;
                SoapObject rpc = new SoapObject(nameSpace, method_Name);
                rpc.addProperty("strCellNumber", etPhone.getText().toString());
                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.debug = true;
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.bodyOut = rpc;
                envelope.dotNet = true;
                envelope.setOutputSoapObject(rpc);
                try {
                    transport.call(SOAP_ACTION, envelope);
                    SoapObject object = (SoapObject) envelope.bodyIn;
                    String str = object.getProperty("QuickLgnMsgResult").toString();
                    if (str != null) {
                        Log.e("main", "---" + str.toString());
                        if (str.length() > 1) {
                            String[] split = str.split(",");
                            Message bean = new Message(split[0], split[1]);
                            if ("0".equals(bean.getMeaage())) {
                                MessageCode = bean.getCode();
                            }
                        } else {
                            Login2Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToast(Login2Activity.this,"网络错误 ");

                                }
                            });
                        }
                    } else {
                        Login2Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(Login2Activity.this,"网络错误 ");


                            }
                        });
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();

    }

    /**
     * 登陆
     */
    private KProgressHUD hud;

    private void setLogin() {
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait.....")
                .setCancellable(true)
                .show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String URL = "http://www.shoujiweidai.com/Service/WSForAPP3.asmx";
                String nameSpace = "http://chachaxy.com/";
                String method_Name = "QuickLgn";
                String SOAP_ACTION = nameSpace + method_Name;
                SoapObject rpc = new SoapObject(nameSpace, method_Name);

                MessageBean message = new MessageBean();
                MessageBean.GerenBean gerenBean = new MessageBean.GerenBean();
                gerenBean.setUsername(etPhone.getText().toString());
                gerenBean.setChannel("安卓");
                gerenBean.setPassword("");
                gerenBean.setQudao("");
                gerenBean.setUserid("");
                message.setGeren(gerenBean);
                Gson gson = new Gson();
                String json = gson.toJson(message);
                rpc.addProperty("strJson", json);

                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.debug = true;
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.bodyOut = rpc;
                envelope.dotNet = true;
                envelope.setOutputSoapObject(rpc);
                try {
                    transport.call(SOAP_ACTION, envelope);
                    SoapObject object = (SoapObject) envelope.bodyIn;
                    String str = object.getProperty("QuickLgnResult").toString();
                    if (str != null) {
                        if (!str.startsWith("1")) {
                            Login2Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SPUtils.put(Login2Activity.this, "flag", "Ok");
                                    HomeActivity.launch(Login2Activity.this);
                                    finish();
                                }
                            });
                        } else {
                            Login2Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToast(Login2Activity.this,"网络错误 ");

                                }
                            });
                        }


                    } else {
                        Login2Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(Login2Activity.this,"网络错误 ");

                            }
                        });
                    }
                    hud.dismiss();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }).start();
    }

    private long mLastBackTime = 0;

    @Override
    public void onBackPressed() {
        // finish while click back key 2 times during 1s.
        if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
            finish();
        } else {
            mLastBackTime = System.currentTimeMillis();
            ToastUtils.showToast(this,"再点一次 ");
        }
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
