package com.getirhackathon.preselection.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.getirhackathon.preselection.R;
import com.getirhackathon.preselection.fragments.RetrofitFragment;
import com.getirhackathon.preselection.fragments.VolleyFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_quick_access);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            Fragment fragment = new RetrofitFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main_container, fragment, MainActivity.class.getCanonicalName())
                    .commit();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_retrofit:
                replaceFragment(new RetrofitFragment());
                break;
            case R.id.action_volley:
                replaceFragment(new VolleyFragment());
                break;

        }
        return true;
    }

    private void replaceFragment(Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, MainActivity.class.getCanonicalName())
                .commit();
    }

}
