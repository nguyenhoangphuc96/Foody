package com.example.sino.foodyv1.BoSuuTap_Fragment;

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

public class TabDiaDiem extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_diadiem_layout,container,false);
    }

    private Button btnCuaBan,btnXemNhieu,btnNoiBat,btnDaLuu;
    private String mauDoDam = "#EE0000";
    private String mauChu = "#666666";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCuaBan = (Button)view.findViewById(R.id.btnDDCuaBan);
        btnXemNhieu = (Button)view.findViewById(R.id.btnDDXemNhieu);
        btnNoiBat = (Button)view.findViewById(R.id.btnDDNoiBat);
        btnDaLuu = (Button)view.findViewById(R.id.btnDDDaLuu);
        btnXemNhieu.setTextColor(Color.parseColor(mauDoDam));

        btnDaLuu.setOnClickListener(this);
        btnNoiBat.setOnClickListener(this);
        btnXemNhieu.setOnClickListener(this);
        btnCuaBan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btnDDCuaBan){
            btnCuaBan.setTextColor(Color.parseColor(mauDoDam));
            btnNoiBat.setTextColor(Color.parseColor(mauChu));
            btnXemNhieu.setTextColor(Color.parseColor(mauChu));
            btnDaLuu.setTextColor(Color.parseColor(mauChu));

            return;
        }

        if(id == R.id.btnDDNoiBat){
            btnCuaBan.setTextColor(Color.parseColor(mauChu));
            btnNoiBat.setTextColor(Color.parseColor(mauDoDam));
            btnXemNhieu.setTextColor(Color.parseColor(mauChu));
            btnDaLuu.setTextColor(Color.parseColor(mauChu));

            return;
        }

        if(id == R.id.btnDDXemNhieu){
            btnCuaBan.setTextColor(Color.parseColor(mauChu));
            btnNoiBat.setTextColor(Color.parseColor(mauChu));
            btnXemNhieu.setTextColor(Color.parseColor(mauDoDam));
            btnDaLuu.setTextColor(Color.parseColor(mauChu));

            return;
        }

        if(id == R.id.btnDDDaLuu){
            btnCuaBan.setTextColor(Color.parseColor(mauChu));
            btnNoiBat.setTextColor(Color.parseColor(mauChu));
            btnXemNhieu.setTextColor(Color.parseColor(mauChu));
            btnDaLuu.setTextColor(Color.parseColor(mauDoDam));

            return;
        }
    }
}

