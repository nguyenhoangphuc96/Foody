package com.example.sino.foodyv1.tab_angi;

/**
 * Created by SINO on 4/11/2017.
 */

public class NoiDungListAnGi {
    public int MaMonAn;
    public String TenMonAn;
    public String tenQuan;
    public String diaChi;
    public byte[] hinhAnh;


    public NoiDungListAnGi(int i,String TenMonAn, String s, String s1, byte[] ic_game) {
        MaMonAn =i;
        this.TenMonAn=TenMonAn;
        tenQuan=s;
        diaChi=s1;
        hinhAnh=ic_game;
    }
}
