package com.jayjaylab.lesson.gallery.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.jayjaylab.lesson.gallery.R;
import com.jayjaylab.lesson.gallery.fragment.Fragment1;
import com.jayjaylab.lesson.gallery.fragment.Fragment2;

public class MainActivity extends AppCompatActivity {
    final String TAG = MainActivity.class.getSimpleName();
    private static int msn = 1;


    FragmentManager fragmentManager;
    Fragment1 fragment_first;
    Fragment2 fragment_second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "externalCacheDir : " + getExternalCacheDir()
                + ", internalCacheDir : " + getCacheDir());


        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment_first = new Fragment1();
        fragment_second = new Fragment2();
        fragmentTransaction.replace(R.id.main_layout, fragment_first);
        fragmentTransaction.commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(msn == 1){
                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout, fragment_second);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    msn++;

                }else if(msn == 2){
                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.main_layout, fragment_first);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    msn--;
                }


            }


        });


        Cursor = getApplicationContext().getContentResolver().query();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
