package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sino.foodyv1.R;

/**
 * Created by SINO on 5/23/2017.
 */

public class ThietLapTK extends Activity {
    ImageView back_thietlap;
    LinearLayout loAnhDaiDien;
    LinearLayout loMatKhau;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thietlaptaikhoan);

        back_thietlap = (ImageView) findViewById(R.id.back_thietlap);
        loAnhDaiDien = (LinearLayout) findViewById(R.id.loAnhDaiDien);
        loMatKhau = (LinearLayout) findViewById(R.id.loMatKhau);

        back_thietlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loAnhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThietLapTK.this, LoginDoiAvartar.class);
                startActivity(intent);
            }
        });
        loMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThietLapTK.this, LoginDoiPass.class);
                startActivity(intent);
            }
        });
    }
}