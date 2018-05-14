package com.example.sino.foodyv1.model;

import java.io.Serializable;

/**
 * Created by SINO on 4/3/2017.
 */

public class ItemIconList implements Serializable {
    private String _idhinhAnh;
    private String _tieuDe;

    public ItemIconList() {
    }

    public ItemIconList(String _idhinhAnh, String _tieuDe) {
        this._idhinhAnh = _idhinhAnh;
        this._tieuDe = _tieuDe;
    }

    public String get_idhinhAnh() {
        return _idhinhAnh;
    }

    public void set_idhinhAnh(String _idhinhAnh) {
        this._idhinhAnh = _idhinhAnh;
    }

    public String get_tieuDe() {
        return _tieuDe;
    }

    public void set_tieuDe(String _tieuDe) {
        this._tieuDe = _tieuDe;
    }
}
