package com.jayjaylab.lesson.gallery;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Homin on 2016-04-07.
 */
public class Fragment2 extends Fragment {

    static File[] imageFiles;
    static Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView1;
    private RecyclerView.Adapter mAdapter, mAdapter1;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager1;
    private ArrayList<MyData> myDataset;
    private File mGalleryFolder;
    private String GALLERY_LOCATION = "Camera";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        createImageGallery();

        View rootView = inflater.inflate(R.layout.manage_layout, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view1);
        mRecyclerView1 = (RecyclerView) rootView.findViewById(R.id.image_layout);

        return rootView;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        mContext = getActivity();

        imageFiles = mGalleryFolder.listFiles();

        AsyncTask AT = new AsyncTask(getActivity());
        AT.execute(imageFiles);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView1.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager1 = new GridLayoutManager(getActivity(), 4);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView1.setLayoutManager(mLayoutManager1);

        mAdapter = new MyAdapter(myDataset);
        mAdapter1 = new ImageAdapter(imageFiles);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView1.setAdapter(mAdapter1);



        myDataset = new ArrayList<>();

        myDataset.add(new MyData("icon1", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon2", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon3", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon4", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon5", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon6", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon7", R.mipmap.ic_launcher));
        myDataset.add(new MyData("icon8", R.mipmap.ic_launcher));
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
