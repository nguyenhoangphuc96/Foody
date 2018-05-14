package com.example.sino.foodyv1.tab_odau;

/**
 * Created by SINO on 4/11/2017.
 */

public class NoiDungListODau {
    public int MaQuanAn;
    public String tenQuanAn;
    public String diaChi;
    public byte[] hinhAnh;
    public String diem;


    public NoiDungListODau(int i, String s, String s1, byte[] ic_game, String s3) {
        MaQuanAn =i;
        tenQuanAn=s;
        diaChi=s1;
        hinhAnh=ic_game;
        diem=s3;
    }
}
