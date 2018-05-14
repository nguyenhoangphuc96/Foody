package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sino.foodyv1.Client;
import com.example.sino.foodyv1.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SINO on 5/19/2017.
 */

public class LoginDangKi extends Activity {
    ProgressDialog prgDialog;
    EditText etxtTaiKhoanMailDangKy;
    EditText etxtMatKhauDangKy;
    EditText etxtXacNhanMatKhau;
    EditText etxtTenHienThi;
    Button btnDangKyTaiKhoan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdangki);

        ImageView imgThoatManHinhDangKy = (ImageView) findViewById(R.id.imgThoatManHinhDangKy);
        imgThoatManHinhDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etxtTaiKhoanMailDangKy = (EditText) findViewById(R.id.etxtTaiKhoanMailDangKy);
        etxtMatKhauDangKy = (EditText) findViewById(R.id.etxtMatKhauDangKy);
        etxtXacNhanMatKhau = (EditText) findViewById(R.id.etxtXacNhanMatKhau);
        etxtTenHienThi = (EditText) findViewById(R.id.etxtTenHienThi);
        btnDangKyTaiKhoan = (Button) findViewById(R.id.btnDangKyTaiKhoan);
        //xử lí sự kiện click nút đăng kí
        btnDangKyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = etxtTaiKhoanMailDangKy.getText().toString();
                String matkhau = etxtMatKhauDangKy.getText().toString();
                String xacnhanmatkhau = etxtXacNhanMatKhau.getText().toString();
                String tenhienthi = etxtTenHienThi.getText().toString();

                prgDialog = new ProgressDialog(LoginDangKi.this);
                prgDialog.setMessage("Vui lòng chờ ...");//hiện thông báo
                prgDialog.setCancelable(false);
                DangKyTaiKhoan(taikhoan, matkhau, xacnhanmatkhau, tenhienthi);//thực hiện hàm đăng kí
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void DangKyTaiKhoan(String Email, String Pass, String ConfirmPass, String Name){
        RequestParams params = new RequestParams();
        if(Check.isNotNull(Email) && Check.isNotNull(Pass) && Check.isNotNull(Name)&& Check.isNotNull(ConfirmPass)){
            if(Check.validate(Email)) { //email hợp lệ
                if(Check.validatePasslenght(Pass)) { // chiều dài pass hợp lệ
                    if(Check.validatePass(Pass, ConfirmPass)) { // pass trùng với confirmpass
                        prgDialog.show(); // hiện thông báo đợi
                        params.put("TaiKhoan", Email); // truyền chuỗi email vào tham số TaiKhoan
                        params.put("MatKhau", Pass);//truyền chuổi pass vào MatKhau
                        params.put("TenHienThi", Name);//truyền chuỗi Name vào TenHienThi
                        Client dangky = new Client();//kết nối client
                        dangky.register(getApplicationContext(), "/api/TaiKhoan/DangKyTaiKhoan", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                try {
                                    Boolean register = Boolean.valueOf(new String(responseBody,"UTF-8"));
                                    if (register) {
                                        //message = "Đăng Ký Thành Công";
                                        Toast.makeText(getApplicationContext(), "Đăng Ký Thành Công", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                    else {
                                        //message = "Đăng Ký Thất Bại";
                                        Toast.makeText(getApplicationContext(), "Đăng Ký Thất Bại", Toast.LENGTH_LONG).show();
                                    }
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                prgDialog.dismiss();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                //message = "Kết Nối Thất Bại";
                                Toast.makeText(getApplicationContext(), "Kết Nối Thất Bại", Toast.LENGTH_LONG).show();
                                prgDialog.dismiss();
                            }
                        });
                    }
                    else {
                        //message = "Xác nhận mật khẩu sai";
                        Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu sai", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    //message = "Mật khẩu phải có ít nhất 4 ký tự";
                    Toast.makeText(getApplicationContext(),  "Mật khẩu phải có ít nhất 4 ký tự", Toast.LENGTH_LONG).show();
                }
            }
            else {
                //message = "Định dạng Email sai";
                Toast.makeText(getApplicationContext(), "Định dạng Email sai", Toast.LENGTH_LONG).show();
            }
        }
        else {
            //message = "Vui lòng điền đầy đủ thông tin";
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }
    }
}
