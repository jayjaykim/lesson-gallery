package com.jayjaylab.lesson.gallery;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Homin on 2016-04-29.
 */
public class GlideModules implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {


        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (2.0 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (2.0 * defaultBitmapPoolSize);

        builder.setMemoryCache( new LruResourceCache( customMemoryCacheSize ));
        builder.setBitmapPool( new LruBitmapPool( customBitmapPoolSize ));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
