package com.example.miaobaitiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.miaobaitiao.MyApplication;
import com.example.miaobaitiao.R;
import com.example.miaobaitiao.adapter.ProductAdapter;
import com.example.miaobaitiao.model.ImagerBean;
import com.example.miaobaitiao.model.Product;
import com.example.miaobaitiao.util.TinyDB;
import com.example.miaobaitiao.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.banner_fresco_demo_content)
    BGABanner bannerFrescoDemoContent;
    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    private ProductAdapter adapter;
    private static String str = "http://www.shoujiweidai.com";
    public static void launch(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setListener();
        setRecycler();
    }


    private void setRecycler() {
        if(MyApplication.getProduct()==null){
            setProduct((Product) new TinyDB(this).getObject("Product",Product.class));
        }else {
            setProduct(MyApplication.getProduct());
        }

    }

    private void setProduct(final Product product) {

        if(product!=null){
            adapter=new ProductAdapter(product.getPrdList());
            GridLayoutManager layoutManager=new GridLayoutManager(this,3);
            recylerview.setLayoutManager(layoutManager);

            layoutManager.setOrientation(GridLayoutManager.VERTICAL);
            recylerview.setAdapter(adapter);
            recylerview.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("product",product.getPrdList().get(position));
                    startActivity(new Intent(HomeActivity.this,ProductActivity.class).putExtras(bundle));
                }
            });
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    private void setListener() {

        if(MyApplication.getUser()==null){
            setBunder((ImagerBean) new TinyDB(this).getObject("ImagerBean",ImagerBean.class));
        }else {
            setBunder(MyApplication.getUser());
        }
    }
    private void setBunder(ImagerBean bean) {
        if(bean!=null){
            final ArrayList<String> arr = new ArrayList<>();
            final List<ImagerBean.DaohangBean> daohang =bean.getDaohang();
            for (ImagerBean.DaohangBean s : daohang) {
                arr.add(str + s.getAdvpath());
            }
            bannerFrescoDemoContent.setDelegate(new BGABanner.Delegate<ImageView, String>() {
                @Override
                public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                    startActivity(new Intent(HomeActivity.this, HtmlActivity.class).putExtra("html", daohang.get(position).getLink()));
                }
            });
            bannerFrescoDemoContent.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                @Override
                public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                    Glide.with(HomeActivity.this)
                            .load(model)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .dontAnimate()
                            .into(itemView);
                }
            });
            bannerFrescoDemoContent.setData(arr, null);
        }

    }
    private int backPressCount = 0;
    @Override
    public void onBackPressed() {

        backPressCount++;
        if (2 == backPressCount) {
            this.finish();
        } else {
            ToastUtils.showToast(this, "再按一次退出程序");
        }

    }

}
