package com.example.sino.foodyv1.model;

import android.graphics.Bitmap;

/**
 * Created by SINO on 4/10/2017.
 */

public class QuanAn {

    private String tvDiem;
    private String tvTen;
    private String tvDiaDiem;
    private String tvThanhPho;
    private Bitmap imgHinh;

    public QuanAn(String tvDiem, String tvTen, String tvDiaDiem, String tvThanhPho, Bitmap imgHinh) {
        this.tvDiem = tvDiem;
        this.tvTen = tvTen;
        this.tvDiaDiem = tvDiaDiem;
        this.tvThanhPho = tvThanhPho;
        this.imgHinh = imgHinh;
    }

    public String getTvDiem() {
        return tvDiem;
    }

    public String getTvTen() {
        return tvTen;
    }

    public String getTvDiaDiem() {
        return tvDiaDiem;
    }

    public Bitmap getImgHinh() {
        return imgHinh;
    }

    public void setTvDiem(String tvDiem) {
        this.tvDiem = tvDiem;
    }

    public void setTvTen(String tvTen) {
        this.tvTen = tvTen;
    }

    public void setTvDiaDiem(String tvDiaDiem) {
        this.tvDiaDiem = tvDiaDiem;
    }

    public void setImgHinh(Bitmap imgHinh) {
        this.imgHinh = imgHinh;
    }

    public String getTvThanhPho() {
        return tvThanhPho;
    }

    public void setTvThanhPho(String tvThanhPho) {
        this.tvThanhPho = tvThanhPho;
    }
}
