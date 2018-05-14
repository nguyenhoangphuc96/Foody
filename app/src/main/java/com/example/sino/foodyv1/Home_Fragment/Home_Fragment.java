package com.example.sino.foodyv1.Home_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.TaiKhoan_Fragment.Login;
import com.example.sino.foodyv1.ThemDiaDiem;
import com.example.sino.foodyv1.adapter.ViewPagerAdapter;
import com.example.sino.foodyv1.tab_angi.Tab_AnGi;
import com.example.sino.foodyv1.tab_odau.Tab_ODau;

import static com.example.sino.foodyv1.MainActivity.flLogin;

/**
 * Created by SINO on 4/10/2017.
 */

public class Home_Fragment extends Fragment implements View.OnClickListener {

    TabLayout _tabHienThi;
    ViewPager _vpHienThi;
    ViewPagerAdapter _adapter;
    private ImageButton btnCong;

    public static Home_Fragment newInstance (){
        Home_Fragment fragment = new Home_Fragment();
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.activity_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Ánh xạ
        _tabHienThi = (TabLayout)view.findViewById(R.id.tabHienThi);
        _vpHienThi = (ViewPager) view.findViewById(R.id.vpHienThi);
        btnCong=(ImageButton) getView().findViewById(R.id.btnCong) ;
        //Đặt sự kiện click cho button
        btnCong.setOnClickListener(this);
        //Khai báo biến để hiện thị các fragment
        _adapter = new ViewPagerAdapter(getFragmentManager());

        _adapter.addTab(new Tab_ODau(),"Ở đâu");
        _adapter.addTab(new Tab_AnGi(),"Ăn gì");

        _vpHienThi.setAdapter(_adapter);
        _tabHienThi.setupWithViewPager(_vpHienThi);

    }

    @Override
    public void onClick(View v) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.getContext());
        View bottomSheetView = LayoutInflater.from(this.getContext()).inflate(R.layout.home_bottomsheet_layout,null);

        bottomSheetDialog.setContentView(bottomSheetView);
        TextView txtThemDiaDiem = (TextView)bottomSheetView.findViewById(R.id.txtThemDiaDiem);
        txtThemDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flLogin) {
                    bottomSheetDialog.dismiss();
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                    //Login = true;
                }
                else {
                    Toast.makeText(getContext(), "Đã Login r", Toast.LENGTH_LONG).show();
                    bottomSheetDialog.dismiss();
                    Intent intent = new Intent(getActivity(), ThemDiaDiem.class);
                    startActivity(intent);
                }
            }
        });
        bottomSheetDialog.show();
    }
}
