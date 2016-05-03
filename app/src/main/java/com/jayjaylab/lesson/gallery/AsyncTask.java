package com.jayjaylab.lesson.gallery;

import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.jayjaylab.lesson.gallery.fragment.Fragment2;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by Homin on 2016-04-29.
 */
public class AsyncTask extends android.os.AsyncTask<File[], Void, File> {

    private final Context context;

    public AsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "hahaha", Toast.LENGTH_LONG).show();
    }

    @Override
    protected File doInBackground(File[]... params) {


        File[] url = params[0];


        FutureTarget<File> future = Glide.with(context)
                .load(url)
                .downloadOnly(400, 400);
        try {
            File cacheFile = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;


    }

}