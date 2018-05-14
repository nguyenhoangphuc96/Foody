package com.example.sino.foodyv1.model;

import android.graphics.Bitmap;

/**
 * Created by SINO on 4/24/2017.
 */

public class MonAn1 {

    private Bitmap imgHinhMonAn;
    private String tvTenMonAn;
    private String tvTenQuanMonAn;
    private String tvDiaDiemQuanMonAn;
    private String tvTenDuong;

    public MonAn1(Bitmap imgHinhMonAn, String tvTenMonAn, String tvTenQuanMonAn, String tvDiaDiemQuanMonAn, String tvTenDuong) {
        this.imgHinhMonAn = imgHinhMonAn;
        this.tvTenMonAn = tvTenMonAn;
        this.tvTenQuanMonAn = tvTenQuanMonAn;
        this.tvDiaDiemQuanMonAn = tvDiaDiemQuanMonAn;
        this.tvTenDuong = tvTenDuong;
    }

    public Bitmap getImgHinhMonAn() {
        return imgHinhMonAn;
    }

    public String getTvTenMonAn() {
        return tvTenMonAn;
    }

    public String getTvTenQuanMonAn() {
        return tvTenQuanMonAn;
    }

    public String getTvDiaDiemQuanMonAn() {
        return tvDiaDiemQuanMonAn;
    }

    public String getTvTenDuong() {
        return tvTenDuong;
    }

    public void setImgHinhMonAn(Bitmap imgHinhMonAn) {
        this.imgHinhMonAn = imgHinhMonAn;
    }

    public void setTvTenMonAn(String tvTenMonAn) {
        this.tvTenMonAn = tvTenMonAn;
    }

    public void setTvTenQuanMonAn(String tvTenQuanMonAn) {
        this.tvTenQuanMonAn = tvTenQuanMonAn;
    }

    public void setTvDiaDiemQuanMonAn(String tvDiaDiemQuanMonAn) {
        this.tvDiaDiemQuanMonAn = tvDiaDiemQuanMonAn;
    }

    public void setTvTenDuong(String tvTenDuong) {
        this.tvTenDuong = tvTenDuong;
    }
}
