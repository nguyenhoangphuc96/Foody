package com.example.sino.foodyv1.tab_angi;

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
import com.example.sino.foodyv1.adapter.RecyclerListViewAdapterAnGi;

import java.util.ArrayList;

import static com.example.sino.foodyv1.tab_angi.DanhMuc_AnGi_Fragment.MaDanhMucDuocChonAnGi;

/**
 * Created by SINO on 4/7/2017.
 */

public class Home_AnGi_Fragment extends Fragment{
    final String DATABASE_NAME="QuanLyFoodyDB.sqlite";
    SQLiteDatabase database ;
    private RecyclerView listView;
    private RecyclerListViewAdapterAnGi listViewAdapterAnGi;
    private ArrayList<NoiDungListAnGi> noiDungListAnGis;
    private int[] HinhAnh ={R.drawable.ic_nearby,R.drawable.ic_ecoupon,R.drawable.ic_sttnotification_tablenow,
            R.drawable.ic_sttnotification_deli,R.drawable.ic_ecard,R.drawable.ic_game,
            R.drawable.ic_icon_binhluanmoi,R.drawable.ic_reporttraffic,R.drawable.ic_icon_topthanhvien,
            R.drawable.ic_video};
    private String[] TenTheoHinh ={"Gần tôi","Coupon","Đặt chỗ ưu đãi","Đặt giao hàng","E-card","Game & Fun","Bình luận",
            "Blogs","Top Thành Viên","Video"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_angi_fragment,container,false);
        listView = (RecyclerView) v.findViewById(R.id.rcvHienThiCacMonAn);

        setDummyData();
        listView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        listView.setLayoutManager(layoutManager);
        listViewAdapterAnGi = new RecyclerListViewAdapterAnGi(getContext(), noiDungListAnGis,TenTheoHinh,HinhAnh);
        listView.setAdapter(listViewAdapterAnGi);

        listViewAdapterAnGi.notifyDataSetChanged();

        return v;
    }
    private void setDummyData() {


        database = Database.initDatabase(getActivity(),DATABASE_NAME);// gọi database
        // Tạo câu jquery để gọi những thành phần cần loand lên listview
        String codequery="";
        //MaDanhMucDuocChonAnGi <=0 thì sẽ xuất hiện mặc định lúc ban đầu
        //MaDanhMucDuocChonAnGi >0 thì nó đã dduocj chọn 1 danh mục nhất định, và nó sẽ xuất ra danh sách theo danh mục đó
        if(MaDanhMucDuocChonAnGi <= 0)
        {
            codequery="select TenQuanAn,DiaChi,TenDuong,TenQuan,TenTP,TenMonAn,MaMonAn,MonAn.HinhAnh\n" +
                    "from (SELECT MaQuanAn,TenQuanAn,DiaChi,TenDuong,TenQuan,TenTP " +
                    "FROM  (select MaQuanAn,TenQuanAn,DiaChi,TenDuong,TenQuan,MaTP" +
                    " from (select MaQuanAn,TenQuanAn,DiaChi,MaQuan,DuongQuan.TenDuong " +
                    "from QuanAn inner join DuongQuan on DuongQuan.MaDuong==QuanAn.MaDuong) as t1" +
                    " inner join QuanHuyen on t1.MaQuan = QuanHuyen.MaQuan where MaTP=0) as t2 " +
                    "inner join ThanhPho on t2.MaTP = ThanhPho.MaTP) as t3 " +
                    "inner join MonAn on t3.MaQuanAn = MonAn.MaQuanAn order by TenMonAn";
        }
        else
        {

            codequery = "select TenQuanAn,DiaChi,TenDuong,TenQuan,TenTP,TenMonAn,MaMonAn,MonAn.HinhAnh\n" +
                    "from (SELECT MaQuanAn,TenQuanAn,DiaChi,TenDuong,TenQuan,TenTP " +
                    "FROM  (select MaQuanAn,TenQuanAn,DiaChi,TenDuong,TenQuan,MaTP" +
                    " from (select MaQuanAn,TenQuanAn,DiaChi,MaQuan,DuongQuan.TenDuong " +
                    "from QuanAn inner join DuongQuan on DuongQuan.MaDuong==QuanAn.MaDuong where MaDanhMuc="+MaDanhMucDuocChonAnGi+") as t1" +
                    " inner join QuanHuyen on t1.MaQuan = QuanHuyen.MaQuan where MaTP=0) as t2 " +
                    "inner join ThanhPho on t2.MaTP = ThanhPho.MaTP) as t3 " +
                    "inner join MonAn on t3.MaQuanAn = MonAn.MaQuanAn order by TenMonAn";

        }



        Cursor cursor = database.rawQuery(codequery,null);
        noiDungListAnGis = new ArrayList<>();
        for(int i=0;i<cursor.getCount();i++) {
            cursor.moveToPosition(i);
            int MaMonAn = cursor.getInt(6);
            String TenMonAn = cursor.getString(5);
            String TenQuanAn = cursor.getString(0);
            String DiaChi = cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4);
            byte[] anh = cursor.getBlob(7);

            noiDungListAnGis.add(new NoiDungListAnGi(MaMonAn,TenMonAn,TenQuanAn,DiaChi,anh));
        }

    }
}
