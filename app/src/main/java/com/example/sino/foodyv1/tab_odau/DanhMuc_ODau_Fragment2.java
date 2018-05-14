package com.example.sino.foodyv1.tab_odau;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sino.foodyv1.Database;
import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.DanhMucAdapter;
import com.example.sino.foodyv1.model.DanhMuc;

import java.util.ArrayList;

import static com.example.sino.foodyv1.MainActivity.DATABASE_NAME;
import static com.example.sino.foodyv1.MainActivity.database;
import static com.example.sino.foodyv1.adapter.DanhMucAdapter.IsPressFirstItem_DanhMuc;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.danhmuc_odau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.flDM;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.mTabHostODau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.mauTrang;

/**
 * Created by SINO on 4/19/2017.
 */

public class DanhMuc_ODau_Fragment2 extends Fragment {

    private ListView lvItems;
    private ArrayList<DanhMuc> listDanhMuc;

    public static String DanhMucDuocChonODau="Danh mục";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.danhmuc_odau_fragment, container, false);

        lvItems = (ListView) v.findViewById(R.id.lvDanhMucODau);
        fillData();
        final DanhMucAdapter adapter = new DanhMucAdapter(getContext(), "Danh mục", listDanhMuc, mTabHostODau);
        lvItems.setAdapter(adapter);

        fillData();

        lvItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (IsPressFirstItem_DanhMuc == true) {
                    String a = listDanhMuc.get(position).getTvDanhMuc();

                    DanhMucDuocChonODau=a; //gán tên danh mục được chọn để lọc dữ liệu
                    mTabHostODau.setCurrentTab(0);
                    flDM = false;
                    //Đặt màu nền cho tab

                    mTabHostODau.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor(mauTrang));

                    //hiện thanh bottombar
                    MainActivity.setVisibilityLayoutBottonBar(true);
                }
                if(((ListView)parent).isItemChecked(position)){
                    if (adapter.getItemViewType(position) == 0) {
                        TextView temp = (TextView) mTabHostODau.getTabWidget().getChildAt(2).findViewById(R.id.tabname);//Lấy những text muôn thay đổi
                        temp.setText("Danh mục");
                        temp.setTextColor(Color.BLACK);
                        danhmuc_odau=temp.getText().toString();
                    }
                    else {
                        TextView temp = (TextView) mTabHostODau.getTabWidget().getChildAt(2).findViewById(R.id.tabname);//Lấy những text muôn thay đổi
                        temp.setText(listDanhMuc.get(position - 1).getTvDanhMuc());
                        temp.setTextColor(Color.RED);
                        danhmuc_odau=temp.getText().toString();
                    }
                }

            }
        });
        if(IsPressFirstItem_DanhMuc == false) {
            lvItems.performItemClick(lvItems, 0, lvItems.getItemIdAtPosition(0));
        }

        Button btn=(Button) v.findViewById(R.id.btnHuy_Danhmuc_ODau);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabHostODau.setCurrentTab(0);
                //Đặt màu nền cho tab

                mTabHostODau.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor(mauTrang));

                //hiện thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);
            }
        });


        return v;
    }
    private void fillData() {
        listDanhMuc = new ArrayList<>();

        database = Database.initDatabase(getActivity(), DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT HinhDanhMuc, TenDanhMuc\n" +
                "FROM DanhMuc\n" +
                "ORDER BY KieuDanhMuc", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            byte[] hinhDanhMuc = cursor.getBlob(0);
            String tenDanhMuc = cursor.getString(1);

            //Xử lý hình ảnh
            Bitmap bmp_hinhDanhMuc = BitmapFactory.decodeByteArray(hinhDanhMuc, 0, hinhDanhMuc.length);

            listDanhMuc.add(new DanhMuc(bmp_hinhDanhMuc, tenDanhMuc));
        }

        database.close();
    }
}
