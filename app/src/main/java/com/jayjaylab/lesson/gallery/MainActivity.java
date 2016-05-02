package com.jayjaylab.lesson.gallery;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static int msn = 2;


    FragmentManager fragmentManager;
    Fragment1 fragment_first;
    Fragment2 fragment_second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getFragmentManager();
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
                    fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout, fragment_second);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    msn++;

                }else if(msn == 2){
                    fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout, fragment_first);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    msn--;
                }


            }


        });
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
