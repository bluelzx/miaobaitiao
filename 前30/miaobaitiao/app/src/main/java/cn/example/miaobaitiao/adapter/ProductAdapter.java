package com.example.miaobaitiao.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.miaobaitiao.R;
import com.example.miaobaitiao.model.Product;

import java.util.List;

/**
 * Created by apple on 2017/4/11.
 */

public class ProductAdapter extends BaseQuickAdapter<Product.PrdListBean,BaseViewHolder> {

    public ProductAdapter( List<Product.PrdListBean> data) {
        super(R.layout.product_item, data);
    }
    private  String str = "http://www.shoujiweidai.com";
    @Override
    protected void convert(BaseViewHolder helper, Product.PrdListBean item) {
                helper.setText(R.id.tv_ProductName,item.getName());

        Glide.with(mContext).load(str+item.getLogo()).skipMemoryCache(true).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.head));
    }
}
