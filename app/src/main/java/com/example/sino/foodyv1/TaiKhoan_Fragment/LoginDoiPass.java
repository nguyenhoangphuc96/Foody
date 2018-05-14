package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sino.foodyv1.Client;
import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

import static com.example.sino.foodyv1.MainActivity.tk;

/**
 * Created by SINO on 5/23/2017.
 */

public class LoginDoiPass extends Activity {
    ProgressDialog prgDialog;
    ImageView back_doipass;
    EditText edt_oldpass;
    EditText edit_passnew;
    EditText edit_confirm;
    Button btn_luuthaydoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doipassword);

        back_doipass = (ImageView) findViewById(R.id.back_doipass);
        edt_oldpass = (EditText) findViewById(R.id.edt_oldpass);
        edit_passnew = (EditText) findViewById(R.id.edit_passnew);
        edit_confirm = (EditText) findViewById(R.id.edit_confirm);
        btn_luuthaydoi = (Button) findViewById(R.id.btn_luuthaydoi);

        back_doipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_luuthaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_password = edt_oldpass.getText().toString();
                String new_password = edit_passnew.getText().toString();
                String confirm_password = edit_confirm.getText().toString();

                prgDialog = new ProgressDialog(LoginDoiPass.this);
                prgDialog.setMessage("Vui lòng chờ ...");
                prgDialog.setCancelable(false);
                getDoiMatKhau(current_password, new_password, confirm_password);
            }
        });
    }
    public void getDoiMatKhau(String current_password, final String new_password, String confirm_password) {

        RequestParams params = new RequestParams();
        if(Check.isNotNull(current_password) && Check.isNotNull(new_password) && Check.isNotNull(confirm_password)){
            if(Check.validateString(tk.getMatKhau(), current_password)) {
                if (Check.validatePasslenght(new_password)) {
                    if (Check.validatePass(new_password, confirm_password)) {
                        prgDialog.show();
                        params.put("TaiKhoan", tk.getTaiKhoan());
                        params.put("MatKhau", current_password);
                        params.put("MatKhauMoi", new_password);
                        Client doimatkhau = new Client();
                        doimatkhau.changepassword(getApplicationContext(), "/api/TaiKhoan/LoginDoiPass", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                try {
                                    Boolean register= Boolean.valueOf(new String(responseBody,"UTF-8"));
                                    if (register) {
                                        Toast.makeText(getApplicationContext(), "Đổi Mật Khẩu Thành Công", Toast.LENGTH_LONG).show();
                                        //Cập nhât mật khẩu
                                        tk.setMatKhau(new_password);
                                        //Về trang chủ
                                        Intent intent = new Intent(LoginDoiPass.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Đổi Mật Khẩu Thất Bại", Toast.LENGTH_LONG).show();
                                    }
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                prgDialog.dismiss();
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(getApplicationContext(), "Kết Nối Thất Bại", Toast.LENGTH_LONG).show();
                                prgDialog.dismiss();
                            }
                        });
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu sai", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mật khẩu phải có ít nhất 4 ký tự", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Mật khẩu hiện tại không đúng", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }
    }
}
