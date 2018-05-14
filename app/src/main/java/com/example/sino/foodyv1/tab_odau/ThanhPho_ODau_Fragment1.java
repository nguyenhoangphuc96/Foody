package com.example.sino.foodyv1.tab_odau;

import android.content.Intent;
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
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.sino.foodyv1.Database;
import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.ThanhPhoODauAdapter;
import com.example.sino.foodyv1.model.DuongQuan;
import com.example.sino.foodyv1.model.QuanHuyen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.sino.foodyv1.tab_odau.ChonThanhPho_Fragment.MaThanhPho;
import static com.example.sino.foodyv1.tab_odau.ChonThanhPho_Fragment.TenThanhPho;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.diadiem_odau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.flTP;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.kieudiadiem_odau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.mTabHostODau;

/**
 * Created by SINO on 4/10/2017.
 */

public class ThanhPho_ODau_Fragment1 extends Fragment {

    String DATABASE_NAME = "QuanLyFoodyDB.sqlite";
    SQLiteDatabase database;
    TextView tvThanhPho;
    ExpandableListView expandableListView;
    ThanhPhoODauAdapter adapter;
    //ExpandableListAdapter listAdapter;
    List<QuanHuyen> Headings;
    HashMap<String, List<DuongQuan>> ChildList;
    Button btnDoiTinhThanh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_thanhpho, container, false);

        tvThanhPho = (TextView) v.findViewById(R.id.tvDiaDiem);
        tvThanhPho.setText(TenThanhPho);
        tvThanhPho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kieudiadiem_odau = "ThanhPho";
                diadiem_odau = tvThanhPho.getText().toString();
                tvThanhPho.setTextColor(Color.RED);
                TextView temp = (TextView) mTabHostODau.getTabWidget().getChildAt(3).findViewById(R.id.tabname);//Lấy những text muôn thay đổi
                temp.setText(tvThanhPho.getText().toString());
                temp.setTextColor(Color.RED);
                mTabHostODau.setCurrentTab(0);
                flTP = false;
                MainActivity.setVisibilityLayoutBottonBar(true);
            }
        });
        expandableListView =(ExpandableListView)v.findViewById(R.id.expand_listview);

        fillData();
        adapter = new ThanhPhoODauAdapter(getContext(), Headings, ChildList);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        //btnDoiTinhThanh
        btnDoiTinhThanh =(Button)v.findViewById(R.id.btnDoiTinhThanh);
        btnDoiTinhThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChonThanhPho_Fragment.class);
                startActivity(intent);
            }
        });

        Button btn=(Button) v.findViewById(R.id.btnHuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabHostODau.setCurrentTab(0);
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
        String query = "";
        if(MaThanhPho <= 0) {
            query = "SELECT MaQuan, TenQuan FROM QuanHuyen WHERE MaTP=0";
        }
        else {
            query = "SELECT MaQuan, TenQuan FROM QuanHuyen WHERE MaTP=" +MaThanhPho+ "";
        }
        Cursor cursorHeading = database.rawQuery(query, null);
        for (int i = 0; i < cursorHeading.getCount(); i++) {
            cursorHeading.moveToPosition(i);
            int maQH = cursorHeading.getInt(0);
            String tenQH = cursorHeading.getString(1);
            Headings.add(new QuanHuyen(maQH,tenQH));
            //add ChildList
            List<DuongQuan> temp = new ArrayList<>();
            Cursor cursorChildList = database.rawQuery("SELECT MaDuong,TenDuong FROM DuongQuan WHERE MaQuan='" + maQH + "'", null);
            for (int j = 0; j < cursorChildList.getCount(); j++) {
                cursorChildList.moveToPosition(j);
                int maDuong = cursorHeading.getInt(0);
                String tenDuong = cursorChildList.getString(1);
                temp.add(new DuongQuan(maDuong,tenDuong));
            }
            ChildList.put(tenQH, temp);
            //end add ChildList

        }

        database.close();
    }
}
