package com.example.sino.foodyv1.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.extras.Base64;

/**
 * Created by SINO on 5/17/2017.
 */

public class ModelODau {
    int id;
    String TenQuan;
    String DiaChi;
    Bitmap HinhAnh;
    double Diem;
    String TenDuong;
    String TenQuanHuyen;
    String TenThanhPho;

    public ModelODau(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.TenQuan = object.getString("TenQuan");
            this.DiaChi = object.getString("DiaChi");

            this.Diem = object.getDouble("Diem");
            this.TenDuong = object.getString("TenDuong");
            this.TenQuanHuyen = object.getString("TenQuanHuyen");
            this.TenThanhPho = object.getString("TenThanhPho");
            //Xử lý hình ảnh
            byte[] byteArray =  Base64.decode(object.getString("HinhAnh"), Base64.DEFAULT) ;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            this.HinhAnh =  BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ModelODau(int id, String tenQuan, String diaChi, Bitmap hinhAnh, float diem, String tenDuong, String tenQuanHuyen, String tenThanhPho) {
        this.id = id;
        TenQuan = tenQuan;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
        Diem = diem;
        TenDuong = tenDuong;
        TenQuanHuyen = tenQuanHuyen;
        TenThanhPho = tenThanhPho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenQuan() {
        return TenQuan;
    }

    public void setTenQuan(String tenQuan) {
        TenQuan = tenQuan;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public Bitmap getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(Bitmap hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public double getDiem() {
        return Diem;
    }

    public void setDiem(double diem) {
        Diem = diem;
    }

    public String getTenDuong() {
        return TenDuong;
    }

    public void setTenDuong(String tenDuong) {
        TenDuong = tenDuong;
    }

    public String getTenQuanHuyen() {
        return TenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        TenQuanHuyen = tenQuanHuyen;
    }

    public String getTenThanhPho() {
        return TenThanhPho;
    }

    public void setTenThanhPho(String tenThanhPho) {
        TenThanhPho = tenThanhPho;
    }
}
