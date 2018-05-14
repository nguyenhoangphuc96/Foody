package com.example.sino.foodyv1.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SINO on 5/23/2017.
 */

public class TaiKhoan {
    String TaiKhoan;
    String MatKhau;
    String TenHienThi;
    Bitmap HinhDaiDien;

    public TaiKhoan(JSONObject object) {
        try {
            TaiKhoan = object.getString("TaiKhoan1");
            MatKhau = object.getString("MatKhau");
            TenHienThi = object.getString("TenHienThi");
            //Xử lý hình ảnh
            byte[] byteArray =  cz.msebera.android.httpclient.extras.Base64.decode(object.getString("HinhDaiDien"), cz.msebera.android.httpclient.extras.Base64.DEFAULT) ;
            this.HinhDaiDien =  BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TaiKhoan(String taiKhoan, String matKhau, String tenHienThi) {
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        TenHienThi = tenHienThi;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getTenHienThi() {
        return TenHienThi;
    }

    public void setTenHienThi(String tenHienThi) {
        TenHienThi = tenHienThi;
    }

    public Bitmap getHinhDaiDien() {
        return HinhDaiDien;
    }

    public void setHinhDaiDien(Bitmap hinhDaiDien) {
        HinhDaiDien = hinhDaiDien;
    }
}
