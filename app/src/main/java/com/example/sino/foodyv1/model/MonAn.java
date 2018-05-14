package com.example.sino.foodyv1.model;

/**
 * Created by SINO on 4/10/2017.
 */

public class MonAn {
    String _id;
    String _tieuDe;
    String _tenQuan;
    String _diaChi;
    String _maTK;

    public MonAn(String _id, String _tieuDe, String _tenQuan, String _diaChi, String _maTK) {
        this._id = _id;
        this._tieuDe = _tieuDe;
        this._tenQuan = _tenQuan;
        this._diaChi = _diaChi;
        this._maTK = _maTK;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_tieuDe() {
        return _tieuDe;
    }

    public void set_tieuDe(String _tieuDe) {
        this._tieuDe = _tieuDe;
    }

    public String get_tenQuan() {
        return _tenQuan;
    }

    public void set_tenQuan(String _tenQuan) {
        this._tenQuan = _tenQuan;
    }

    public String get_diaChi() {
        return _diaChi;
    }

    public void set_diaChi(String _diaChi) {
        this._diaChi = _diaChi;
    }

    public String get_maTK() {
        return _maTK;
    }

    public void set_maTK(String _maTK) {
        this._maTK = _maTK;
    }

}
