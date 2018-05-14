package com.example.sino.foodyv1.tab_angi;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sino.foodyv1.Database;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.MonAnAdapter;
import com.example.sino.foodyv1.model.MonAn1;

import java.util.ArrayList;

import static com.example.sino.foodyv1.MainActivity.DATABASE_NAME;
import static com.example.sino.foodyv1.MainActivity.database;

import static com.example.sino.foodyv1.tab_angi.Tab_AnGi.danhmuc_angi;
import static com.example.sino.foodyv1.tab_angi.Tab_AnGi.mTabHostAnGi;

/**
 * Created by SINO on 4/24/2017.
 */

public class Home_AnGi_Fragment1 extends Fragment {

    private RecyclerView listView;
    private MonAnAdapter mainAdapter;
    private int imgHeader = R.drawable.quangcao1;
    private int[] imgCategory ={R.drawable.ic_nearby,R.drawable.ic_ecoupon,R.drawable.ic_sttnotification_tablenow,
            R.drawable.ic_sttnotification_deli,R.drawable.ic_ecard,R.drawable.ic_game,
            R.drawable.ic_icon_binhluanmoi,R.drawable.ic_reporttraffic,R.drawable.ic_icon_topthanhvien,
            R.drawable.ic_video};
    private String[] tvCategory ={"Gần tôi","Coupon","Đặt chỗ ưu đãi","Đặt giao hàng","E-card","Game & Fun","Bình luận",
            "Blogs","Top Thành Viên","Video"};
    private ArrayList<MonAn1> listMonAn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_angi_fragment, container, false);

        listView = (RecyclerView)v.findViewById(R.id.rcvHienThiCacMonAn);
        listView.addItemDecoration(new SpacesItemDecoration(5));

        fillData();

        listView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position == 0 ? 2 : position <= 10 ? 1 : 1;
            }
        });
        listView.setLayoutManager(layoutManager);

        mainAdapter = new MonAnAdapter(getContext(), imgHeader, imgCategory, tvCategory, listMonAn);
        listView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();

        listView.scrollToPosition(0);

        return v;
    }

    private void fillData() {
        listMonAn = new ArrayList<>();

        database = Database.initDatabase(getActivity(), DATABASE_NAME);
        String query = "SELECT TenMonAn, TenQuanAn, DiaChi, TenDuong, TenQuan, TenTP, MonAn.HinhAnh\n" +
                "FROM MonAn, QuanAn, DanhMuc, DuongQuan, QuanHuyen, ThanhPho\n" +
                "WHERE  MonAn.MaQuanAn=QuanAn.MaQuanAn\n" +
                "and QuanAn.MaDanhMuc=DanhMuc.MaDanhMuc\n" +
                "and QuanAn.MaDuong=DuongQuan.MaDuong\n" +
                "and DuongQuan.MaQuan=QuanHuyen.MaQuan\n" +
                "and QuanHuyen.MaTP=ThanhPho.MaTP\n";
        TextView temp = (TextView) mTabHostAnGi.getTabWidget().getChildAt(2).findViewById(R.id.tabname);
        danhmuc_angi = temp.getText().toString();
        if (danhmuc_angi != "Danh mục"){
            query = query + "and DanhMuc.TenDanhMuc='" +danhmuc_angi+ "'";
        }
        Cursor cursor = database.rawQuery(query, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);

            String tenMonAn = cursor.getString(0);
            String tenQuanMonAn = cursor.getString(1);
            String diachiQuanMonAn = cursor.getString(2);
            String tenduongQuanMonAn = cursor.getString(3);
            String tenquanQuanMonAn = cursor.getString(4);
            String tenthanhphQuanMonAn = cursor.getString(5);
            byte[] hinhMonAn = cursor.getBlob(6);
            //Xử lý hình ảnh
            Bitmap bmp_hinhMonAn = BitmapFactory.decodeByteArray(hinhMonAn, 0, hinhMonAn.length);
            //Xử lý nối địa chỉ
            String diachiChiTiet = tenduongQuanMonAn + ", " + tenquanQuanMonAn + ", " + tenthanhphQuanMonAn;

            listMonAn.add(new MonAn1(bmp_hinhMonAn, tenMonAn, tenQuanMonAn, diachiQuanMonAn, diachiChiTiet));
        }

        database.close();
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space / 2;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space / 2;
            } else {
                outRect.top = 0;
            }
        }
    }
}
