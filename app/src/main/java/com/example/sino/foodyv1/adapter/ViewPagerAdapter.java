package com.example.sino.foodyv1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by SINO on 4/3/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter  {

    ArrayList<Fragment> _dsFragment = new ArrayList<>();
    ArrayList<String> _dsTieuDe = new ArrayList<>();

    public void addTab (Fragment fragment , String tieuDe){
        _dsFragment.add(fragment);
        _dsTieuDe.add(tieuDe);
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return _dsFragment.get(position);
    }

    @Override
    public int getCount() {
        return _dsTieuDe.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return _dsTieuDe.get(position);
    }
}
