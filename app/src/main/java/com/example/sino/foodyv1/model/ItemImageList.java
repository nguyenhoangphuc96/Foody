package com.example.sino.foodyv1.model;

import java.io.Serializable;

/**
 * Created by SINO on 4/3/2017.
 */

public class ItemImageList implements Serializable {
    private int _id;
    private byte[] _hinhAnh;
    private String _tieuDe;

    public ItemImageList(int _id, byte[] _hinhAnh, String _tieuDe) {
        this._id = _id;
        this._hinhAnh = _hinhAnh;
        this._tieuDe = _tieuDe;
    }

    public ItemImageList() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public byte[] get_hinhAnh() {
        return _hinhAnh;
    }

    public void set_hinhAnh(byte[] _hinhAnh) {
        this._hinhAnh = _hinhAnh;
    }

    public String get_tieuDe() {
        return _tieuDe;
    }

    public void set_tieuDe(String _tieuDe) {
        this._tieuDe = _tieuDe;
    }
}
