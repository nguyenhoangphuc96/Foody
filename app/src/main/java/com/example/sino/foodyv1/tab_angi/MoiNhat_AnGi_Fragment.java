package com.example.sino.foodyv1.tab_angi;

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
import com.example.sino.foodyv1.adapter.ListHasIconItemAdapter_MoiNhatAnGi;
import com.example.sino.foodyv1.model.ItemIconList;

import java.util.ArrayList;

/**
 * Created by SINO on 4/7/2017.
 */

public class MoiNhat_AnGi_Fragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.moinhat_angi_fragment,container,false);
    }
    //Khai báo các biến
    private ArrayList<ItemIconList> _dsMoiNhat;
    private ListHasIconItemAdapter_MoiNhatAnGi _adapter;
    private ListView _lvMoiNhatAnGi;
    private Button btnHuyMoiNhat;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Ánh xạ
        _lvMoiNhatAnGi = (ListView) getView().findViewById(R.id.lvMoiNhatAnGi);
        _dsMoiNhat = new ArrayList<>();
        _adapter = new ListHasIconItemAdapter_MoiNhatAnGi(getContext(),R.layout.item_iconlist,_dsMoiNhat);
        _lvMoiNhatAnGi.setAdapter(_adapter);
        btnHuyMoiNhat=(Button) getView().findViewById(R.id.btnHuy_Moinhat_AnGi);
        //Dặt sự kiện click cho button hủy
        btnHuyMoiNhat.setOnClickListener(this);

        taoDuLieuChoListViewMoiNhat();
        //


    }
    //Hàm tạo dữ liệu cho listview
    private void taoDuLieuChoListViewMoiNhat() {
            _dsMoiNhat.clear();
            _dsMoiNhat.add(new ItemIconList("mn01", "Mới nhất"));
            _dsMoiNhat.add(new ItemIconList("mn02", "Gần tôi"));
            _dsMoiNhat.add(new ItemIconList("mn001", "Xem nhiều"));
            _dsMoiNhat.add(new ItemIconList("mn04", "Du khách"));


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHuy_Moinhat_AnGi:
                Tab_AnGi.mTabHostAnGi.setCurrentTab(0);//đặt lại tab home ở đâu
                Tab_AnGi.mTabHostAnGi.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(Tab_AnGi.mauTrang));
                //Hiện lại thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);
                break;
        }
    }
}
