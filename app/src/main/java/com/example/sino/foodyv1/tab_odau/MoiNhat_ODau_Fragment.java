package com.example.sino.foodyv1.tab_odau;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.ListHasIconItemAdapter_MoiNhatODau;
import com.example.sino.foodyv1.model.ItemIconList;

import java.util.ArrayList;

/**
 * Created by SINO on 4/3/2017.
 */

public class MoiNhat_ODau_Fragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.moinhat_odau_fragment,container,false);
    }
    //Khai báo các biến
    private ArrayList<ItemIconList> _dsMoiNhat;
    private ListHasIconItemAdapter_MoiNhatODau _adapter;
    private ListView _lvMoiNhatODau;
    private Button btnHuyMoiNhat;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Ánh xạ
       _lvMoiNhatODau = (ListView) getView().findViewById(R.id.lvMoiNhatODau);
        _dsMoiNhat = new ArrayList<>();
        _adapter = new ListHasIconItemAdapter_MoiNhatODau(getContext(),R.layout.item_iconlist,_dsMoiNhat);
        _lvMoiNhatODau.setAdapter(_adapter);
        btnHuyMoiNhat=(Button) getView().findViewById(R.id.btnHuy_Moinhat_ODau);
        //đặt sự kiện click cho button
        btnHuyMoiNhat.setOnClickListener(this);
        taoDuLieuChoListViewMoiNhat();

    }
    //Hàm tạo dữ liệu cho listview
    private void taoDuLieuChoListViewMoiNhat() {
        _dsMoiNhat.clear();
        _dsMoiNhat.add(new ItemIconList("mn01","Mới nhất"));
        _dsMoiNhat.add(new ItemIconList("mn02","Gần tôi"));
        _dsMoiNhat.add(new ItemIconList("mn03","Phổ biến"));
        _dsMoiNhat.add(new ItemIconList("mn04","Du khách"));
        _dsMoiNhat.add(new ItemIconList("mn05","Ưu đãi E-card"));
        _dsMoiNhat.add(new ItemIconList("mn06","Đặt chỗ"));
        _dsMoiNhat.add(new ItemIconList("mn07","Ưu đãi thẻ"));
        _dsMoiNhat.add(new ItemIconList("mncuoi","Đặt giao hàng"));
    }

    //hàm xử lí sự kiện click button hủy
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHuy_Moinhat_ODau:
                Tab_ODau.mTabHostODau.setCurrentTab(0);//đặt lại tab home ở đâu
                Tab_ODau.mTabHostODau.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(Tab_ODau.mauTrang));
                //Hiện lại thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);
                break;

        }
    }




}
