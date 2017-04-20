package com.example.miaobaitiao.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.miaobaitiao.R;
import com.example.miaobaitiao.fragment.AccountFlagInfoFragment;
import com.example.miaobaitiao.fragment.AddAccountFlagFragment;
import com.umeng.analytics.MobclickAgent;

public class AccountFlagActivity extends Activity implements OnClickListener {
	Button btn_accountFlagInfo, btn_addAccountFlag;

	AccountFlagInfoFragment accountFlagInfoFragment;
	AddAccountFlagFragment addAccountFlagFragment;
	FragmentManager fragmentManager;
	FragmentTransaction transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accountflag);

		init();
		fragmentManager = this.getFragmentManager();
		// 获取碎片实例
		accountFlagInfoFragment = new AccountFlagInfoFragment();

		addAccountFlagFragment = new AddAccountFlagFragment();

		// 加载默认加载我的便签碎片
		btn_accountFlagInfo.setBackgroundColor(Color.parseColor("#AAAAAA"));
		transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.add_fragment_layout, accountFlagInfoFragment);
		transaction.commit();
	}

	private void init() {
		btn_accountFlagInfo = (Button) this
				.findViewById(R.id.btn_accountFlagInfo);
		btn_addAccountFlag = (Button) this
				.findViewById(R.id.btn_addAccountFlag);
		btn_accountFlagInfo.setOnClickListener(this);
		btn_addAccountFlag.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			// 我的便签
			case R.id.btn_accountFlagInfo:
				// 点击改变按钮颜色
				btn_accountFlagInfo.setBackgroundColor(Color.parseColor("#AAAAAA"));
				btn_addAccountFlag.setBackgroundColor(Color.parseColor("#DEDCE0"));
				// 加载碎片
				transaction = fragmentManager.beginTransaction();
				transaction.replace(R.id.add_fragment_layout,
						accountFlagInfoFragment);

				break;
			// 新增便签
			case R.id.btn_addAccountFlag:
				// 点击改变按钮颜色
				btn_addAccountFlag.setBackgroundColor(Color.parseColor("#AAAAAA"));
				btn_accountFlagInfo.setBackgroundColor(Color.parseColor("#DEDCE0"));
				// 加载碎片
				transaction = fragmentManager.beginTransaction();
				transaction.replace(R.id.add_fragment_layout,
						addAccountFlagFragment);
				break;

		}
		transaction.commit();

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
