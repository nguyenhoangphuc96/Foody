package com.example.sino.foodyv1.ThongBao_Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sino.foodyv1.R;

/**
 * Created by SINO on 4/10/2017.
 */

public class ThongBao_Fragment extends Fragment implements View.OnClickListener {

    public static ThongBao_Fragment newInstance (){
        ThongBao_Fragment fragment = new ThongBao_Fragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thongbao_layout,container,false);
    }

    private Button btnDichVu,btnCuaToi,btnTinMoi;
    private String mauDoDam = "#EE0000";
    private String mauChu = "#666666";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnDichVu = (Button)view.findViewById(R.id.btnTBDichVu);
        btnCuaToi = (Button)view.findViewById(R.id.btnTBCuaToi);
        btnTinMoi = (Button)view.findViewById(R.id.btnTBTinMoi);

        btnTinMoi.setTextColor(Color.parseColor(mauDoDam));

        btnTinMoi.setOnClickListener(this);
        btnCuaToi.setOnClickListener(this);
        btnDichVu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnTBDichVu){
            btnDichVu.setTextColor(Color.parseColor(mauDoDam));
            btnCuaToi.setTextColor(Color.parseColor(mauChu));
            btnTinMoi.setTextColor(Color.parseColor(mauChu));

            return;
        }

        if(id == R.id.btnTBCuaToi){
            btnDichVu.setTextColor(Color.parseColor(mauChu));
            btnCuaToi.setTextColor(Color.parseColor(mauDoDam));
            btnTinMoi.setTextColor(Color.parseColor(mauChu));

            return;
        }

        if(id == R.id.btnTBTinMoi){
            btnDichVu.setTextColor(Color.parseColor(mauChu));
            btnCuaToi.setTextColor(Color.parseColor(mauChu));
            btnTinMoi.setTextColor(Color.parseColor(mauDoDam));

            return;
        }
    }
}
