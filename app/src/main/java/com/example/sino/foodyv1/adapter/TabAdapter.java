package com.example.sino.foodyv1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by SINO on 4/10/2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter {
    private String nt1 ="";
    private String nt2 = "";
    private Fragment f1 =null;
    private Fragment f2 = null;

    public TabAdapter(FragmentManager fm, String name1, String name2, Fragment a, Fragment b) {
        super(fm);
        this.nt1=name1;
        this.nt2=name2;
        this.f1=a;
        this.f2 = b;
    }


    //Tra lai Fragment theo vi tri duoc chon
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return f1;
            case 1:
                return f2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


    //Set ten cho 2 tab item
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return nt1;
            case 1:
                return nt2;
        }
        return null;
    }
}

