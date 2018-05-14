package com.example.sino.foodyv1.tab_angi;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;

/**
 * Created by SINO on 4/8/2017.
 */

public class Tab_AnGi extends Fragment {
    public static boolean flMN, flDM, flTP;//khai báo các cờ cho từng tab
    public static FragmentTabHost mTabHostAnGi;//khai báo biến mTabHostAnGi kiểu FragmentTabHost
    public static String mauXam = "#f5f2f2";
    public static String mauTrang = "#ffffff";
    public static String danhmuc_angi = "Danh mục";
    public static String kieudiadiem_angi = "ThanhPho";
    public static String diadiem_angi = "TP.HCM";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHostAnGi = new FragmentTabHost(getActivity());
        mTabHostAnGi.setup(getActivity(), getChildFragmentManager(), R.layout.tab_angi);
        //Thiết lập các tab
        setupTab(new TextView(getContext()), "Ăn Gì", Home_AnGi_Fragment2.class);
        setupTab(new TextView(getContext()), "Mới nhất", MoiNhat_AnGi_Fragment1.class);
        setupTab(new TextView(getContext()), danhmuc_angi, DanhMuc_AnGi_Fragment2.class);
        setupTab(new TextView(getContext()), diadiem_angi, ThanhPho_AnGi_Fragment.class);

        mTabHostAnGi.getTabWidget().getChildAt(0).setVisibility(View.GONE);
        Reselect();


        return mTabHostAnGi;
    }

    //Hàm thiết lập các tab
    private void setupTab(final View view, final String tag, Class cl) {
        View tabview = createTabView(mTabHostAnGi.getContext(), tag);
        TabHost.TabSpec setContent = mTabHostAnGi.newTabSpec(tag).setIndicator(tabview).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {return view;}
        });
        mTabHostAnGi.addTab(setContent, cl, Bundle.EMPTY);
    }
    //Hàm tạo ra tabview
    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabcustom, null);
        TextView tv = (TextView) view.findViewById(R.id.tabname);
        tv.setText(text);
        return view;
    }
    //Hàm xử lí sự kiện chọn các tab
    private void Reselect(){

        mTabHostAnGi.getTabWidget().getChildAt(1).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                if (!flMN){
                    flMN = true;//khi chọn tab mới nhất, bật cờ mới nhất lên
                    flDM = false;//các cờ còn lại hạ xuống
                    flTP = false;
                    mTabHostAnGi.setCurrentTab(1);//hiển thị nội dung của tab mới nhất
                    //Đặt màu nền cho tab
                    for (int i=1;i<4;i++)
                        mTabHostAnGi.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor(mauTrang));
                    mTabHostAnGi.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(mauXam));
                    //Ẩn thanh bottombar đi
                    MainActivity.setVisibilityLayoutBottonBar(false);
                }
                else {
                    //chọn lần 2 hạ cờ xuống
                    flMN = false;
                    mTabHostAnGi.setCurrentTab(0);//hiển thị lại nội dung home
                    //Đặt lại màu nền cho tab

                    mTabHostAnGi.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(mauTrang));
                    //Hiện lại thanh bottombar
                    MainActivity.setVisibilityLayoutBottonBar(true);
                }

            }
        });

        mTabHostAnGi.getTabWidget().getChildAt(2).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                if (!flDM){
                    flDM = true;//khi chọn tab danh mục, bật cờ danh mục lên
                    flMN = false;//các cờ còn lại hạ xuống
                    flTP = false;
                    mTabHostAnGi.setCurrentTab(2);//hiển thị nội dung của tab danh mục
                    //Đặt màu nền cho tab
                    for (int i=1;i<4;i++)
                        mTabHostAnGi.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor(mauTrang));
                    mTabHostAnGi.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor(mauXam));
                    //Ẩn thanh bottombar đi
                    MainActivity.setVisibilityLayoutBottonBar(false);
                }
                else {
                    //chọn lần 2 hạ cờ xuống
                    flDM = false;
                    mTabHostAnGi.setCurrentTab(0);//hiển thị lại nội dung home
                    //Đặt lại màu nền cho tab

                    mTabHostAnGi.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor(mauTrang));
                    //Hiện lại thanh bottombar
                    MainActivity.setVisibilityLayoutBottonBar(true);

                }

            }
        });

        mTabHostAnGi.getTabWidget().getChildAt(3).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                if (!flTP){
                    flTP = true;//khi chọn tab TP, bật cờ TP lên
                    flDM = false;//các cờ còn lại hạ xuống
                    flMN = false;
                    mTabHostAnGi.setCurrentTab(3);//hiển thị nội dung của tab TP
                    //Đặt màu nền cho tab
                    for (int i=1;i<4;i++)
                        mTabHostAnGi.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor(mauTrang));
                    mTabHostAnGi.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor(mauXam));
                    //Ẩn thanh bottombar đi
                    MainActivity.setVisibilityLayoutBottonBar(false);
                }
                else {
                    //chọn lần 2 hạ cờ xuống
                    flTP = false;
                    mTabHostAnGi.setCurrentTab(0);//hiển thị lại nội dung home
                    //Đặt lại màu nền cho tab
                    mTabHostAnGi.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor(mauTrang));
                    //Hiện lại thanh bottombar
                    MainActivity.setVisibilityLayoutBottonBar(true);
                }


            }
        });
    }
}
