package com.example.sino.foodyv1.model;

/**
 * Created by SINO on 4/24/2017.
 */

public class QuanHuyen {
    public QuanHuyen(int maQuan, String tenQuan) {
        MaQuan = maQuan;
        TenQuan = tenQuan;
    }

    public int MaQuan;
    public String TenQuan;
    public int getMaQuan() {
        return MaQuan;
    }

    public void setMaQuan(int maQuan) {
        MaQuan = maQuan;
    }

    public String getTenQuan() {
        return TenQuan;
    }

    public void setTenQuan(String tenQuan) {
        TenQuan = tenQuan;
    }
}