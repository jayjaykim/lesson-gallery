package com.jayjaylab.lesson.gallery.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jayjaylab.lesson.gallery.*;
import com.jayjaylab.lesson.gallery.adapter.ImageAdapter;
import com.jayjaylab.lesson.gallery.adapter.MyAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Homin on 2016-04-07.
 */
public class Fragment2 extends Fragment {

    static File[] imageFiles;
    static Context mContext;

    private RecyclerView recyclerViewMenu;
    private RecyclerView recyclerViewGallery;
    private RecyclerView.Adapter adapterMenu, adapterGallery;
    private ArrayList<MyData> myDataset;
    private File mGalleryFolder;
    private String GALLERY_LOCATION = ".thumbnails";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        createImageGallery();

        View rootView = inflater.inflate(R.layout.manage_layout, container, false);
        recyclerViewMenu = (RecyclerView) rootView.findViewById(R.id.my_recycler_view1);
        recyclerViewGallery = (RecyclerView) rootView.findViewById(R.id.image_layout);

        return rootView;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myDataset = new ArrayList<>();

        myDataset.add(new MyData("icon1", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon2", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon3", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon4", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon5", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon6", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon7", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon8", R.mipmap.ic_launcher));

        mContext = getActivity();

        imageFiles = mGalleryFolder.listFiles();

        recyclerViewMenu.setHasFixedSize(true);
        recyclerViewGallery.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);

        recyclerViewMenu.setLayoutManager(linearLayoutManager);
        recyclerViewGallery.setLayoutManager(gridLayoutManager);
        recyclerViewGallery.setItemViewCacheSize(30);

        adapterMenu = new MyAdapter(myDataset);
        adapterGallery = new ImageAdapter(this, imageFiles);

        recyclerViewMenu.setAdapter(adapterMenu);
        recyclerViewGallery.setAdapter(adapterGallery);
    }

//폴더생성
    private void createImageGallery() {
        File storageDirectory = Environment.getExternalStoragePublicDirectory("/DCIM");
        mGalleryFolder = new File(storageDirectory, GALLERY_LOCATION);
        if (!mGalleryFolder.exists()) {
            mGalleryFolder.mkdirs();
        }
    }
}
