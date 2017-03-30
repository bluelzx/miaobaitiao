package com.example.apple.xianjinxia.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.xianjinxia.R;
import com.example.apple.xianjinxia.bean.Message;
import com.example.apple.xianjinxia.bean.MessageBean;
import com.example.apple.xianjinxia.utils.CaptchaTimeCount;
import com.example.apple.xianjinxia.utils.Constants;
import com.example.apple.xianjinxia.utils.SPUtils;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sdsmdg.tastytoast.TastyToast;
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
import es.dmoral.toasty.Toasty;

public class Login2Activity extends AppCompatActivity {

    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
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
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bt_getCode:
                if (!etPhone.getText().toString().isEmpty() && etPhone.length() == 11) {
                    getCodeMessage();
                } else {
                    TastyToast.makeText(getApplicationContext(), "请输入正确的手机号码 ", TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR);                }
                break;
            case R.id.bt_Login:
                if (!etPhone.getText().toString().isEmpty() && !etCode.getText().toString().isEmpty() && etCode.getText().toString().equals(MessageCode)) {
                    setLogin();
                } else {
                    TastyToast.makeText(getApplicationContext(), "验证码或手机号不正确 ", TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR);                }
                break;
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
                                    TastyToast.makeText(getApplicationContext(), "网络错误 ", TastyToast.LENGTH_SHORT,
                                            TastyToast.CONFUSING);
                            }
                            });
                        }
                    } else {
                        Login2Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TastyToast.makeText(getApplicationContext(), "网络错误 ", TastyToast.LENGTH_SHORT,
                                        TastyToast.CONFUSING);
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
                .setLabel("Please wait")
                .setCancellable(true)
                .show();
        Log.e("main", "---0000");
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
                gerenBean.setChannel("Android-现金侠");
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
                        Log.e("main", "---" + str.toString());

                        if (!str.startsWith("1")) {
                            Login2Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SPUtils.put(Login2Activity.this, "flag", "Ok");
                                    Main2Activity.launch(Login2Activity.this);
                                    finish();
                                }
                            });
                        } else {
                            Login2Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TastyToast.makeText(getApplicationContext(), "网络错误 ", TastyToast.LENGTH_SHORT,
                                            TastyToast.CONFUSING);
                                }
                            });
                        }


                    } else {
                        Login2Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TastyToast.makeText(getApplicationContext(), "网络错误 ", TastyToast.LENGTH_SHORT,
                                        TastyToast.CONFUSING);
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
            TastyToast.makeText(getApplicationContext(), "再来一次", TastyToast.LENGTH_SHORT,
                    TastyToast.WARNING);        }

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
