package com.example.sino.foodyv1.model;

/**
 * Created by SINO on 4/24/2017.
 */

public class DuongQuan{
    public DuongQuan(int maDuong, String tenDuong) {
        MaDuong = maDuong;
        TenDuong = tenDuong;
    }

    public int MaDuong;
    public String TenDuong;
    public int getMaDuong() {
        return MaDuong;
    }

    public void setMaDuong(int maDuong) {
        MaDuong = maDuong;
    }

    public String getTenDuong() {
        return TenDuong;
    }

    public void setTenDuong(String tenDuong) {
        TenDuong = tenDuong;
    }
}
