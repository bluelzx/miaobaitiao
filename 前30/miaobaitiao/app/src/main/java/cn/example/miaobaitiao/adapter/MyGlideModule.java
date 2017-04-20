package com.example.miaobaitiao.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by apple on 2017/4/12.
 */

public class MyGlideModule implements GlideModule {
    private static final int MEMORY_MAX_SPACE=(int) (Runtime.getRuntime().maxMemory()/8);

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {



        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        int cacheSize100MegaBytes = 104857600;
        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));
        String downloadDirectoryPath = context.getExternalCacheDir().getPath() + File.separator + "glide";
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath,cacheSize100MegaBytes));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
