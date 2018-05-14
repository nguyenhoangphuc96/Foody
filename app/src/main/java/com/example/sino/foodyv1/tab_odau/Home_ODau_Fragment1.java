package com.example.sino.foodyv1.tab_odau;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sino.foodyv1.Client;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.QuanAnAdapter;
import com.example.sino.foodyv1.model.ModelODau;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;


import static com.example.sino.foodyv1.tab_odau.Tab_ODau.danhmuc_odau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.diadiem_odau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.kieudiadiem_odau;

/**
 * Created by SINO on 4/24/2017.
 */

public class Home_ODau_Fragment1 extends Fragment {


    private RecyclerView listView;
    private QuanAnAdapter quanAnAdapter;
    private int imgHeader = R.drawable.quangcao1;
    private int[] imgCategory ={R.drawable.ic_nearby,R.drawable.ic_ecoupon,R.drawable.ic_sttnotification_tablenow,
            R.drawable.ic_sttnotification_deli,R.drawable.ic_ecard,R.drawable.ic_game,
            R.drawable.ic_icon_binhluanmoi,R.drawable.ic_reporttraffic,R.drawable.ic_icon_topthanhvien,
            R.drawable.ic_video};
    private String[] tvCategory ={"Gần tôi","Coupon","Đặt chỗ ưu đãi","Đặt giao hàng","E-card","Game & Fun","Bình luận",
            "Blogs","Top Thành Viên","Video"};

    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<ModelODau> listODau;//mảng list để lưu thông tin quan ăn


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_odau_fragment, container, false);

        listView = (RecyclerView)v.findViewById(R.id.rcvHienThiCacQuanAn);




        listView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position == 0 ? 2 : position <= 10 ? 1 : 2;
            }
        });
        listView.setLayoutManager(layoutManager);


        quanAnAdapter = new QuanAnAdapter(getContext(), imgHeader, imgCategory, tvCategory, listODau);
        listView.setAdapter(quanAnAdapter);//dặt adapter quan ăn cho rycecle view
        quanAnAdapter.notifyDataSetChanged();
        listView.scrollToPosition(0);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);//trượt xuống để refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getQuanAn();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;


    }
    ProgressDialog progressDialog;
    private void getQuanAn(){ // Hàm lấy danh sách quán ăn từ server
        List<Header> headers = new ArrayList<Header>();//khai báo arraylist header
        headers.add(new BasicHeader("Accept", "application/json"));
        RequestParams params = new RequestParams();
        params.put("TenDanhMuc", danhmuc_odau);//truyền chuỗi tên danh mục vào TenDanhMuc trên web service
        params.put("KieuDiaDiem", kieudiadiem_odau);//truyền chuổi kiểu địa đieểm vào KieuDiaDiem trên web service
        params.put("TenDiaDiem", diadiem_odau);//truyền tên địa điểm

        Client odau = new Client();
        odau.get(getContext(), "api/QuanAn/GetQuanAnList", headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() { // Load dữ liệu
                        progressDialog = new ProgressDialog(getContext(), R.style.MyTheme);
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) { //Nếu load thành công
                        listODau = new ArrayList<ModelODau>();
                        for (int i = response.length(); i>0; i--) { //đưa từng phần tử trong JSONArray response vào list ODau
                            try {
                                listODau.add(new ModelODau(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        quanAnAdapter = new QuanAnAdapter(getContext(), imgHeader, imgCategory, tvCategory, listODau);

                        listView.setAdapter(quanAnAdapter);
                        quanAnAdapter.notifyDataSetChanged();

                        listView.scrollToPosition(0);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(getActivity(), responseString, Toast.LENGTH_LONG).show();//Nếu thất bại thì hiện thông báo
                    }

                    @Override
                    public void onFinish() {
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    getQuanAn();
    }
}
//    private void fillData() {
//        listQuanAn.clear();
//
//        database = Database.initDatabase(getActivity(), DATABASE_NAME);
//        String query = "SELECT TenQuanAn, DiaChi, TenDuong, TenQuan, TenTP, DiemQuan, HinhAnh\n" +
//                "FROM QuanAn, DanhMuc, DuongQuan, QuanHuyen, ThanhPho\n" +
//                "WHERE QuanAn.MaDanhMuc=DanhMuc.MaDanhMuc\n" +
//                "and QuanAn.MaDuong=DuongQuan.MaDuong\n" +
//                "and DuongQuan.MaQuan=QuanHuyen.MaQuan\n" +
//                "and QuanHuyen.MaTP=ThanhPho.MaTP\n";
//        TextView temp = (TextView) mTabHostODau.getTabWidget().getChildAt(2).findViewById(R.id.tabname);
//        danhmuc_odau = temp.getText().toString();
//        if (danhmuc_odau != "Danh mục"){
//            query = query + "and DanhMuc.TenDanhMuc='" +danhmuc_odau+ "'";
//        }
//        Cursor cursor = database.rawQuery(query, null);
//        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToPosition(i);
//
//            String tenQuan = cursor.getString(0);
//            String diachiQuan = cursor.getString(1);
//            String tenDuongQuan = cursor.getString(2);
//            String tenQuanHuyen = cursor.getString(3);
//            String tenThanhPho = cursor.getString(4);
//            String diemQuan = cursor.getString(5);
//            byte[] hinhQuan = cursor.getBlob(6);
//            //Xử lý hình ảnh
//            Bitmap bmp_hinhQuan = BitmapFactory.decodeByteArray(hinhQuan, 0, hinhQuan.length);
//            //Xử lý nối địa chỉ
//            String diachiChiTiet = tenDuongQuan + ", " + tenQuanHuyen + ", " + tenThanhPho;
//
//            listQuanAn.add(new QuanAn(diemQuan, tenQuan, diachiQuan, diachiChiTiet, bmp_hinhQuan));
//        }
//
//        database.close();
//    }

//    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
//        private int space;
//
//        public SpacesItemDecoration(int space) {
//            this.space = space;
//        }
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view,
//                                   RecyclerView parent, RecyclerView.State state) {
//            outRect.left = space;
//            outRect.right = space;
//            outRect.bottom = space / 2;
//
//            // Add top margin only for the first item to avoid double space between items
//            if (parent.getChildLayoutPosition(view) == 0) {
//                outRect.top = space / 2;
//            } else {
//                outRect.top = 0;
//            }
//        }
//    }

