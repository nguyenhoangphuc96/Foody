package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sino.foodyv1.R;

import static com.example.sino.foodyv1.MainActivity.flLogin;

/**
 * Created by SINO on 5/19/2017.
 */

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdangnhap);
        ImageView BackLogin=(ImageView)findViewById(R.id.back_login);
        //sự kiện click dấu quay lại
        BackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flLogin=false;
                finish();
            }
        });

        Button btnDangKyTaiKhoan = (Button) findViewById(R.id.btnDangKyTaiKhoan);
        //sự kiện click nút đăng kí
        btnDangKyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LoginDangKi.class);
                startActivity(intent);
            }
        });
        Button btnDangNhapVoiEmail = (Button) findViewById(R.id.btnDangNhapVoiEmail);
        //Sự kiện click nút đăng nhập bằng email
        btnDangNhapVoiEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LoginEmail.class);
                startActivity(intent);
            }
        });
        //Sự kiện click nút đang nhập bằng sdt
        Button btnDangNhapVoiSDT = (Button) findViewById(R.id.btnDangNhapVoiSDT);
        btnDangNhapVoiSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LoginSDT.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        flLogin=false;//trở về tắt cờ login
        finish();
    }
}