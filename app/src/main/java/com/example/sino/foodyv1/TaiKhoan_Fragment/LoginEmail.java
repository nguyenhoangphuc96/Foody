package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sino.foodyv1.Client;
import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.TaiKhoan;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.example.sino.foodyv1.MainActivity.flLogin;
import static com.example.sino.foodyv1.MainActivity.tk;

/**
 * Created by SINO on 5/19/2017.
 */

public class LoginEmail extends AppCompatActivity {

    ProgressDialog prgDialog;
    ImageView Back;
    EditText EdtEmail;
    EditText EdtPass;
    Button btnLogin;
    Button btnDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhapemail);
        Back=(ImageView)findViewById(R.id.imgThoatManHinhDangNhapVoiEmail);
        EdtEmail=(EditText)findViewById(R.id.etxtTaiKhoanMail);
        EdtPass=(EditText)findViewById(R.id.etxtMatKhauMail);
        btnLogin=(Button)findViewById(R.id.btnDangNhapBangMail);
        btnDK=(Button)findViewById(R.id.btnDangKyTaiKhoan);
        //Click button Back
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //CLick Button Đăng Nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email= EdtEmail.getText().toString();
                String Pass=EdtPass.getText().toString();
                // Instantiate Progress Dialog object
                prgDialog = new ProgressDialog(LoginEmail.this);
                // Set Progress Dialog Text
                prgDialog.setMessage("Please wait...");
                // Set Cancelable as False
                prgDialog.setCancelable(false);
                //
                Login(Email,Pass);

            }
        });
        //Click button Đăng Ký
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToRegister();
                finish();
            }
        });
    }
    public void Login(String Email, String Pass){
        if(Check.isNotNull(Email) && Check.isNotNull(Pass)){
            prgDialog.show();
            RequestParams params = new RequestParams();
            params.put("TaiKhoan", Email);
            params.put("MatKhau", Pass);
            Client dangnhap = new Client();
            dangnhap.login(getApplicationContext(), "api/TaiKhoan/DangNhapTaiKhoan", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    tk = new TaiKhoan(response);
                    Log.d("Success", String.valueOf(statusCode));
                    if (!(tk == null)) {
                        flLogin = true;
                        Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                        prgDialog.dismiss();
                        //Về trang chủ
                        Intent intent = new Intent(LoginEmail.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Đăng Nhập Thất Bại", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                    Log.d("Failure", String.valueOf(statusCode));
                    Toast.makeText(getApplicationContext(), "Kết Nối Thất Bại", Toast.LENGTH_LONG).show();
                    prgDialog.dismiss();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("Failure", String.valueOf(statusCode));
                    Toast.makeText(getApplicationContext(), "Kết Nối Thất Bại", Toast.LENGTH_LONG).show();
                    prgDialog.dismiss();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
        }


    }

    public void ToMain(){
        Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

    public void ToRegister(){
        Intent loginIntent = new Intent(getApplicationContext(),LoginDangKi.class);
        // Clears History of Activity
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

}


//        extends Activity {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dangnhapemail);
//
//        ImageView imgThoatManHinhDangNhapVoiEmail = (ImageView) findViewById(R.id.imgThoatManHinhDangNhapVoiEmail);
//        imgThoatManHinhDangNhapVoiEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void onBackPressed() {
//        finish();
//    }
//}
