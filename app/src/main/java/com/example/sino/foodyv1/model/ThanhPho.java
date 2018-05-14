package com.example.sino.foodyv1.model;

/**
 * Created by SINO on 4/24/2017.
 */

public class ThanhPho {
    public ThanhPho(int maTP, String tenTP) {
        MaTP = maTP;
        TenTP = tenTP;
    }

    public int MaTP;
    public String TenTP;
    public int getMaTP() {
        return MaTP;
    }

    public void setMaTP(int maTP) {
        MaTP = maTP;
    }

    public String getTenTP() {
        return TenTP;
    }

    public void setTenTP(String tenTP) {
        TenTP = tenTP;
    }
}
