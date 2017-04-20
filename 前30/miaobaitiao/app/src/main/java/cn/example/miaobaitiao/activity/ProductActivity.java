package com.example.miaobaitiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.miaobaitiao.R;
import com.example.miaobaitiao.model.Product;
import com.itheima.roundedimageview.RoundedImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity {


    @Bind(R.id.desc_head)
    RoundedImageView descHead;
    @Bind(R.id.tv_demand1)
    TextView tvDemand1;
    @Bind(R.id.tv_demand2)
    TextView tvDemand2;
    @Bind(R.id.tv_tips1)
    TextView tvTips1;
    @Bind(R.id.tv_tips2)
    TextView tvTips2;
    @Bind(R.id.apply)
    Button apply;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private static String str = "http://www.shoujiweidai.com";
    @Bind(R.id.lines)
    TextView lines;
    @Bind(R.id.timeLimit)
    TextView timeLimit;
    @Bind(R.id.cost)
    TextView cost;
    @Bind(R.id.rate)
    TextView rate;
    @Bind(R.id.level)
    TextView level;
    @Bind(R.id.difficulty)
    TextView difficulty;

    private Product.PrdListBean product = new Product.PrdListBean();

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ProductActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        getDate();
    }

    private void getDate() {

        product = (Product.PrdListBean) getIntent().getSerializableExtra("product");
        Log.e("product", product.toString() + "-----------");
        if(product!=null){
            Glide.with(this).load(str + product.getLogo()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(descHead);
            lines.setText(product.getLines());
            timeLimit.setText(product.getTimeLimit());
            rate.setText(product.getRate());
            difficulty.setText(product.getDifficulty());
            tvDemand1.setText("1、"+product.getDemand1());
            tvDemand2.setText("2、"+product.getDemand2());
            tvTips1.setText("1、"+product.getTips1());
            tvTips2.setText("2、"+product.getTips2());
        }

    }
    @OnClick({R.id.iv_back, R.id.apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.apply:
                startActivity(new Intent(this, HtmlActivity.class).putExtra("html", product.getLink()));
                break;
        }
    }
}
