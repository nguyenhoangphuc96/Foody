package com.example.sino.foodyv1.tab_odau;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sino.foodyv1.Database;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.RecyclerListViewAdapterODau;

import java.util.ArrayList;

import static com.example.sino.foodyv1.tab_odau.DanhMuc_ODau_Fragment2.DanhMucDuocChonODau;


/**
 * Created by SINO on 4/3/2017.
 */

public class Home_ODau_Fragment extends Fragment {

    final String DATABASE_NAME="QuanLyFoodyDB.sqlite";
    SQLiteDatabase database ;
    public static boolean NhanNutBenDanhMuc =false;
    private RecyclerView listView;
    private RecyclerListViewAdapterODau listViewAdapterODau;
    private ArrayList<NoiDungListODau> noiDungListODaus;
    private int[] HinhAnh ={R.drawable.ic_nearby,R.drawable.ic_ecoupon,R.drawable.ic_sttnotification_tablenow,
            R.drawable.ic_sttnotification_deli,R.drawable.ic_ecard,R.drawable.ic_game,
            R.drawable.ic_icon_binhluanmoi,R.drawable.ic_reporttraffic,R.drawable.ic_icon_topthanhvien,
            R.drawable.ic_video};
    private String[] TenTheoHinh ={"Gần tôi","Coupon","Đặt chỗ ưu đãi","Đặt giao hàng","E-card","Game & Fun","Bình luận",
            "Blogs","Top Thành Viên","Video"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_odau_fragment,container,false);



        listView = (RecyclerView) v.findViewById(R.id.rcvHienThiCacQuanAn);
        setDummyData();

        listView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position<10 ? 1 : 2;
            }
        });

        listView.setLayoutManager(layoutManager);
        listViewAdapterODau = new RecyclerListViewAdapterODau(getContext(), noiDungListODaus,TenTheoHinh,HinhAnh);
        listView.setAdapter(listViewAdapterODau);



        listViewAdapterODau.notifyDataSetChanged();

        return v;
    }

    private void setDummyData() {

        database = Database.initDatabase(getActivity(),DATABASE_NAME);// gọi database
        // Tạo câu jquery để gọi những thành phần cần loand lên listview
        String codequery="";
        if( DanhMucDuocChonODau =="Danh mục")
        {
            codequery="SELECT MaQuanAn,TenQuanAn,DiaChi,HinhAnh,SoDIenThoai,TenDuong,TenQuan,TenTP" +
                    " FROM  (select MaQuanAn,TenQuanAn,DiaChi,HinhAnh,SoDIenThoai,TenDuong,TenQuan,MaTP " +
                    "from (select MaQuanAn,TenQuanAn,DiaChi,HinhAnh,SoDienThoai,MaQuan,DuongQuan.TenDuong " +
                    "from QuanAn inner join DuongQuan on DuongQuan.MaDuong==QuanAn.MaDuong) as t1 " +
                    "inner join QuanHuyen on t1.MaQuan = QuanHuyen.MaQuan) as t2 " +
                    "inner join ThanhPho on t2.MaTP = ThanhPho.MaTP";
        }
        else
        {

            codequery = "SELECT MaQuanAn,TenQuanAn,DiaChi,HinhAnh,SoDIenThoai,TenDuong,TenQuan,TenTP  " +
                    "FROM  (select MaQuanAn,TenQuanAn,DiaChi,HinhAnh,SoDIenThoai,TenDuong,TenQuan,MaTP " +
                    " from (select MaQuanAn,TenQuanAn,DiaChi,HinhAnh,SoDienThoai,MaQuan,DuongQuan.TenDuong " +
                    " from QuanAn inner join DuongQuan on DuongQuan.MaDuong==QuanAn.MaDuong where TenDanhMuc="+DanhMucDuocChonODau+") " +
                    "as t1 inner join QuanHuyen on t1.MaQuan = QuanHuyen.MaQuan) " +
                    "as t2 inner join ThanhPho on t2.MaTP = ThanhPho.MaTP";

        }


        Cursor cursor = database.rawQuery(codequery,null);
        noiDungListODaus = new ArrayList<>();
        for(int i=0;i<cursor.getCount();i++) {
            cursor.moveToPosition(i);
            int MaQuanAn = cursor.getInt(0);
            String TenQuanAn = cursor.getString(1);
            String DiaChi = cursor.getString(2)+","+cursor.getString(5)+","+cursor.getString(6)+","+cursor.getString(7);
            byte[] anh = cursor.getBlob(3);

            noiDungListODaus.add(new NoiDungListODau(MaQuanAn,TenQuanAn,DiaChi,anh,"80"));
        }

    }
}


