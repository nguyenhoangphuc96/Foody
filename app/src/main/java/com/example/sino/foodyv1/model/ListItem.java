package com.example.sino.foodyv1.model;

import java.util.ArrayList;

/**
 * Created by SINO on 4/9/2017.
 */

public class ListItem {
    private String mName;
    private int mImageView;

    public ListItem(String name, int ImageView) {
        mName = name;
        mImageView = ImageView;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getmImageView() {
        return mImageView;
    }

    public void setImageView(int mImageView) {
        this.mImageView = mImageView;
    }

    public static ArrayList<ListItem> createListItem(String[] danhsach, int dodai, int[] hinh) {
        ArrayList<ListItem> temp = new ArrayList<ListItem>();

        for (int i = 0; i < dodai; i++) {
            temp.add(new ListItem(danhsach[i], hinh[i]));
        }

        return temp;
    }
}