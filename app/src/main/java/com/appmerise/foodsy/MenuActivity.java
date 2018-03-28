package com.appmerise.foodsy;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.appmerise.foodsy.Fragments.DessertFragment;
import com.appmerise.foodsy.Fragments.DrinksFragment;
import com.appmerise.foodsy.Fragments.MainCourseFragment;
import com.appmerise.foodsy.Fragments.StarterFragment;
import com.appmerise.foodsy.Model.ViewPagerAdapter;

public class MenuActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    TabLayout tabLayout;


    private static final int PAGE_STARTER = 0;
    private static final int PAGE_MAINCOURSE = 1;

    private static final int PAGE_DESSERT = 2;
    private static final int PAGE_DRINKS = 3;

    //ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout = findViewById(R.id.htab_tabs);
        mViewPager = findViewById(R.id.htab_viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.Addfragment(new StarterFragment(), "Starters");
        adapter.Addfragment(new MainCourseFragment(), "Main Course");
        adapter.Addfragment(new DessertFragment(), "Dessert");
        adapter.Addfragment(new DrinksFragment(), "Drinks");

        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
