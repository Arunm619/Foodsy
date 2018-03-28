package com.appmerise.foodsy.Model;

/**
 * Created by root on 28/3/18.
 */

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/2/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List <Fragment> fragments = new ArrayList <>();
    private List <String> FragmentTitles = new ArrayList <>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentTitles.get(position);
    }

    public void Addfragment(Fragment fragment, String title) {
        fragments.add(fragment);
        FragmentTitles.add(title);
    }
}