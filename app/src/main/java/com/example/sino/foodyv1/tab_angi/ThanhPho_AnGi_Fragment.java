package com.example.sino.foodyv1.tab_angi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.sino.foodyv1.Database;
import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.ExpandableListViewAdapter;
import com.example.sino.foodyv1.tab_odau.Tab_ODau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SINO on 4/11/2017.
 */

public class ThanhPho_AnGi_Fragment extends Fragment {

    String DATABASE_NAME = "QuanLyFoodyDB.sqlite";
    SQLiteDatabase database;

    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    List<String> Headings;
    HashMap<String, List<String>> ChildList;
    Button btnDoiTinhThanh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_thanhpho, container, false);

        expandableListView =(ExpandableListView)v.findViewById(R.id.expand_listview);

        fillData();
        listAdapter = new ExpandableListViewAdapter(getContext(), Headings, ChildList);
        expandableListView.setAdapter(listAdapter);

        //btnDoiTinhThanh
        btnDoiTinhThanh =(Button)v.findViewById(R.id.btnDoiTinhThanh);
        btnDoiTinhThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getContext(), DoiTinhThanh.class);
                //startActivity(intent);
            }
        });

        Button btn=(Button) v.findViewById(R.id.btnHuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //đặt lại tab home và đặt màu lại cho tab thành phố
                Tab_AnGi.mTabHostAnGi.setCurrentTab(0);
                Tab_AnGi.mTabHostAnGi.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor(Tab_ODau.mauTrang));

                //Hiện lại thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);
            }
        });

        return v;
    }
    public void fillData() {
        Headings = new ArrayList<>();
        ChildList = new HashMap<>();

        //add Heading
        database = Database.initDatabase(getActivity(), DATABASE_NAME);
        Cursor cursorHeading = database.rawQuery("SELECT TenQuan\n" +
                "FROM ThanhPho inner join QuanHuyen on ThanhPho.MaTP=QuanHuyen.MaTP\n" +
                "WHERE TenTP='TP.HCM'", null);
        for (int i = 0; i < cursorHeading.getCount(); i++) {
            cursorHeading.moveToPosition(i);
            String tenQH = cursorHeading.getString(0);
            //add ChildList
            List<String> temp = new ArrayList<>();
            Cursor cursorChildList = database.rawQuery("SELECT TenDuong\n" +
                    "FROM QuanHuyen inner join DuongQuan on QuanHuyen.MaQuan=DuongQuan.MaQuan\n" +
                    "WHERE TenQuan='" + tenQH + "'", null);
            for (int j = 0; j < cursorChildList.getCount(); j++) {
                cursorChildList.moveToPosition(j);
                String tenDuong = cursorChildList.getString(0);
                temp.add(tenDuong);
            }
            ChildList.put(tenQH, temp);
            //end add ChildList
            Headings.add(tenQH);
        }

        database.close();
    }
}
