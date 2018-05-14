package com.example.sino.foodyv1.TimKiem_Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sino.foodyv1.R;

/**
 * Created by SINO on 4/10/2017.
 */

public class TimKiem_Fragment extends Fragment implements View.OnClickListener {

    public static TimKiem_Fragment newInstance() {
        TimKiem_Fragment fragment = new TimKiem_Fragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.timkiem_layout, container, false);
    }

    private EditText edtNhapTuKhoa;
    private TextView txtDiaDiem, txtThanhPho;
    private Button btnDaTim,btnXemGanDay;
    private String mauDoDam = "#EE0000";
    private String mauChu = "#666666";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNhapTuKhoa = (EditText) view.findViewById(R.id.edtNhapTuKhoa);
        txtDiaDiem = (TextView)view.findViewById(R.id.txtDiaDiem);
        txtThanhPho = (TextView)view.findViewById(R.id.txtThanhPho);
        btnXemGanDay = (Button)view.findViewById(R.id.btnTKXemGanDay);
        btnDaTim = (Button)view.findViewById(R.id.btnTKDaTim);

        txtDiaDiem.setPaintFlags(txtDiaDiem.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtThanhPho.setPaintFlags(txtThanhPho.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        edtNhapTuKhoa.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edtNhapTuKhoa, InputMethodManager.SHOW_IMPLICIT);
        //imm.hideSoftInputFromWindow(edtNhapTuKhoa.getWindowToken(), 0);

        btnXemGanDay.setTextColor(Color.parseColor(mauDoDam));

        btnDaTim.setOnClickListener(this);
        btnXemGanDay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnTKDaTim){
            btnDaTim.setTextColor(Color.parseColor(mauDoDam));
            btnXemGanDay.setTextColor(Color.parseColor(mauChu));

            return;
        }

        if(id == R.id.btnTKXemGanDay){
            btnDaTim.setTextColor(Color.parseColor(mauChu));
            btnXemGanDay.setTextColor(Color.parseColor(mauDoDam));

            return;
        }
    }
}

