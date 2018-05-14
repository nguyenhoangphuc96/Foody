package com.example.sino.foodyv1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sino.foodyv1.BoSuuTap_Fragment.BoSuuTap_Fragment;
import com.example.sino.foodyv1.Home_Fragment.Home_Fragment;
import com.example.sino.foodyv1.TaiKhoan_Fragment.TaiKhoan_Fragment;
import com.example.sino.foodyv1.ThongBao_Fragment.ThongBao_Fragment;
import com.example.sino.foodyv1.TimKiem_Fragment.TimKiem_Fragment;
import com.example.sino.foodyv1.model.TaiKhoan;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    final public static String DATABASE_NAME = "QuanLyFoodyDB.sqlite";//Khai báo tên database trên sqlite

    String DB_PATH_SUFFIX = "/databases/";//Khai báo đường dẫn đến database
    static String DATA_PATH ;//Khai báo biến lưu đường dẫn database
    public static SQLiteDatabase database;
    static Activity _activity;
    public static boolean flLogin = false; //cờ kiểm tra trạng thái đang nhập
    public static TaiKhoan tk= null; // biến lưu thông tin tài khoản

    private static LinearLayout bottomlayout;

    private BottomNavigationViewEx bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bottomlayout = (LinearLayout) findViewById(R.id.bottombar_layout);

        bottomView = (BottomNavigationViewEx) findViewById(R.id.bottomBar);
        bottomView.enableAnimation(false);
        bottomView.enableItemShiftingMode(false);
        bottomView.enableShiftingMode(false);

        FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
        tran.replace(R.id.bottomBar_fragment_content, Home_Fragment.newInstance());
        tran.commit();
        bottomView.setCurrentItem(0);
        bottomView.setOnNavigationItemSelectedListener(this);

        //Đường dẫn của database
        DATA_PATH =  getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
        _activity = this;

        xuLySaoChepCSDLVaoHeThong();
    }
    //Hàm sử lý sao chép CSDL vào hệ thống
    private void xuLySaoChepCSDLVaoHeThong() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
            try {
                saoChepCSDMTuAsset();//Hàm sao chép CSDL từ assets vào hệ thống
                Toast.makeText(this, "Sao chép CSDL thành công!", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Hàm sao chép CSDL từ assets vào hệ thống
    private void saoChepCSDMTuAsset() {
        try {
            //Mở file database trong thư mục assets
            InputStream myInput = getAssets().open(DATABASE_NAME);
            //Lấy đường dẫn cần lưu trữ
            String outputFileName = layDuongDanLuuTru();


            //Kiểm tra xem file có tồn tại chưa, chưa thì tạo mới
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }

            OutputStream myOutput = new FileOutputStream(outputFileName);

            //Đọc dữ liệu bằng byte và ghi vào file
            byte [] buffer = new byte[1024];
            int mLenght;
            while ((mLenght = myInput.read(buffer))>0){
                myOutput.write(buffer,0,mLenght);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (Exception ex){

        }
    }

    //Hàm lấy đường dẫn lưu trữ
    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    //Hàm lấy dữ liệu từ database
    public static Cursor layDuLieuTuBang(String query, String[] selArgs){
        try {
            Cursor cursor;
            database = SQLiteDatabase.openDatabase(DATA_PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
            cursor = database.rawQuery(query,selArgs);
            return cursor;
        }
        catch (Exception ex){
            Toast.makeText(_activity, "Lỗi truy vấn!! "+ex, Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.action_Nha:
                selectedFragment = new Home_Fragment();
                break;
            case R.id.action_BoSuuTap:
                selectedFragment = new BoSuuTap_Fragment();
                break;
            case R.id.action_TimKiem:
                selectedFragment =  new TimKiem_Fragment();
                break;
            case R.id.action_ThongBao:
                selectedFragment = new ThongBao_Fragment();
                break;
            case R.id.action_TaiKhoan:
                selectedFragment = new TaiKhoan_Fragment();
                break;
        }

        FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
        tran.replace(R.id.bottomBar_fragment_content, selectedFragment);
        tran.commit();
        return true;
    }
//hàm ẩn/ hiện thanh bottom
    public static void setVisibilityLayoutBottonBar(boolean instance){
        if(!instance){
            bottomlayout.setVisibility(View.GONE);
        }else bottomlayout.setVisibility(View.VISIBLE);
    }
}
