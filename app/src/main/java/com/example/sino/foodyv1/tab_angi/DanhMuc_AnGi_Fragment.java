package com.example.sino.foodyv1.tab_angi;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.ListHasImageItemAdapter_DanhMucAnGi;
import com.example.sino.foodyv1.model.ItemImageList;
import com.example.sino.foodyv1.tab_odau.Tab_ODau;

import java.util.ArrayList;

/**
 * Created by SINO on 4/7/2017.
 */

public class DanhMuc_AnGi_Fragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.danhmuc_angi_fragment,container,false);
    }
    //Khai báo các biến

    private ArrayList<ItemImageList> _dsDanhMuc = new ArrayList<>();
    private ListHasImageItemAdapter_DanhMucAnGi _adapter;
    private ListView _lvDanhMucAnGi ;
    private Button btnHuyDanhMuc;
    public static int MaDanhMucDuocChonAnGi=-1;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Ánh xạ
        _lvDanhMucAnGi= (ListView) getView().findViewById(R.id.lvDanhMucAnGi);

        _adapter = new ListHasImageItemAdapter_DanhMucAnGi(getContext(),R.layout.item_imagelist,_dsDanhMuc);
        _lvDanhMucAnGi.setAdapter(_adapter);
        btnHuyDanhMuc = (Button) getView().findViewById(R.id.btnHuy_Danhmuc_AnGi);

        //đặt sự kiện click cho button hủy
        btnHuyDanhMuc.setOnClickListener(this);
        xuLyLayDuLieuDanhMucTuDatabase();

    }
    //Hàm lấy dữ kiệu từ database
    private void xuLyLayDuLieuDanhMucTuDatabase() {
        try {
            _dsDanhMuc.clear();
            Cursor cursor = MainActivity.layDuLieuTuBang("select * from tbDanhMuc where rowid >14 UNION ALL " +
                    "select * from tbDanhMuc where rowid <14", null);
            while (cursor.moveToNext()) {
                _dsDanhMuc.add(new ItemImageList(cursor.getInt(0), cursor.getBlob(1), cursor.getString(2)));
            }
            cursor.close();
            _adapter.notifyDataSetChanged();
        } catch (Exception ex) {
            Toast.makeText(getContext(), "" + ex, Toast.LENGTH_SHORT).show();
        }
    }

    //Hàm xử lí sự kiện click trên button hủy
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHuy_Danhmuc_AnGi:
                Tab_AnGi.mTabHostAnGi.setCurrentTab(0);//đặt lại tab home ở đâu
                Tab_AnGi.mTabHostAnGi.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor(Tab_ODau.mauTrang));
                //Hiện lại thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);
                break;
        }
    }

}
