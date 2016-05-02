package com.jayjaylab.lesson.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * Created by nigelhenshaw on 25/06/2015.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private File[] imagesFile;

    public ImageAdapter(File[] folderFile) {
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
        Glide.with(holder.getImageView().getContext())
                .load(imageFile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return imagesFile.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.item_img);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
