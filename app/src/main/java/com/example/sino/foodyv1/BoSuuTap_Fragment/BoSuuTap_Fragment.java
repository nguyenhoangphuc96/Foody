package com.example.sino.foodyv1.BoSuuTap_Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.TabAdapter;

/**
 * Created by SINO on 4/10/2017.
 */

public class BoSuuTap_Fragment extends Fragment {

    public static BoSuuTap_Fragment newInstance (){
        BoSuuTap_Fragment fragment = new BoSuuTap_Fragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bosuutap_layout,container,false);
    }

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager)view.findViewById(R.id.container);
        tabLayout = (TabLayout)view.findViewById(R.id.tabTieuDeChinh);

        viewPager.setAdapter(new TabAdapter(getFragmentManager(),"Bộ sưu tập địa điểm","Bộ sưu tập ảnh",new TabDiaDiem(),new TabAnh()));
        tabLayout.setupWithViewPager(viewPager);
    }

}

