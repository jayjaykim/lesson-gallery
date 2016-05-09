package com.jayjaylab.lesson.gallery.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jayjaylab.lesson.gallery.R;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by nigelhenshaw on 25/06/2015.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    final String TAG = ImageAdapter.class.getSimpleName();
    Fragment fragment;
    private File[] imagesFile;

    public ImageAdapter(Fragment fragment, File[] folderFile) {
        this.fragment = fragment;
        imagesFile = folderFile;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File imageFile = imagesFile[position];
        Log.d(TAG, "position : " + position +", imageFile: " + imageFile);

        if(imageFile != null) {
            Glide.with(fragment)
                    .load(imageFile)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .thumbnail(0.1f)
                    .listener(requestListener)
                    .crossFade()
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return imagesFile.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.item_img);
        }
    }

    RequestListener<File, GlideDrawable> requestListener = new RequestListener<File, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, File file, Target<GlideDrawable> target,
                                   boolean isFirstResource) {
            Log.d(TAG, ", file : " + file +
                    ", target : " + target + ", isFirstResource : " + isFirstResource);
            if(e != null) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable glideDrawable, File file,
                                       Target<GlideDrawable> target, boolean isFromMemoryCache,
                                       boolean isFirstResource) {
            Log.d(TAG, "file : " + file.getAbsolutePath() +
                    ", target : " + target +
                    ", isFromMemoryCache : " + isFromMemoryCache +
                    ", isFirstResource : " + isFirstResource);
            return false;
        }
    };
}
