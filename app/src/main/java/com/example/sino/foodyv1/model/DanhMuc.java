package com.example.sino.foodyv1.model;

import android.graphics.Bitmap;

/**
 * Created by SINO on 4/19/2017.
 */

public class DanhMuc {

    private Bitmap imgDanhMuc;
    private String tvDanhMuc;
    private int MaDanhMuc;
    private int KieuDanhMuc;

    public DanhMuc(Bitmap imgDanhMuc, String tvDanhMuc, int maDanhMuc, int kieuDanhMuc) {
        this.imgDanhMuc = imgDanhMuc;
        this.tvDanhMuc = tvDanhMuc;
        MaDanhMuc = maDanhMuc;
        KieuDanhMuc = kieuDanhMuc;
    }

    public DanhMuc(Bitmap imgDanhMuc, String tvDanhMuc) {
        this.imgDanhMuc = imgDanhMuc;
        this.tvDanhMuc = tvDanhMuc;
    }

    public Bitmap getImgDanhMuc() {
        return imgDanhMuc;
    }

    public void setImgDanhMuc(Bitmap imgDanhMuc) {
        this.imgDanhMuc = imgDanhMuc;
    }

    public String getTvDanhMuc() {
        return tvDanhMuc;
    }

    public void setTvDanhMuc(String tvDanhMuc) {
        this.tvDanhMuc = tvDanhMuc;
    }

    public int getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        MaDanhMuc = maDanhMuc;
    }

    public int getKieuDanhMuc() {
        return KieuDanhMuc;
    }

    public void setKieuDanhMuc(int kieuDanhMuc) {
        KieuDanhMuc = kieuDanhMuc;
    }
}
