package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.DanhMuc;

import java.util.ArrayList;

/**
 * Created by SINO on 4/19/2017.
 */

public class DanhMucAdapter extends BaseAdapter {

    public static boolean IsPressFirstItem_DanhMuc = false;

    private static final int TYPE_HEARDER = 0;
    private static final int TYPE_ITEM = 1;

    Context context;
    String HeaderDanhMuc;
    ArrayList<DanhMuc> listDanhMuc;
    FragmentTabHost mTabHost;



    public DanhMucAdapter(Context context, String headerDanhMuc, ArrayList<DanhMuc> listDanhMuc, FragmentTabHost mTabHost) {
        this.context = context;
        this.HeaderDanhMuc = headerDanhMuc;
        this.listDanhMuc = listDanhMuc;
        this.mTabHost = mTabHost;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return TYPE_HEARDER;
        return TYPE_ITEM;
    }

    @Override
    public int getCount() {
        return listDanhMuc.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;

        int rowType = getItemViewType(position);
        if (rowType == TYPE_HEARDER){
            rowView = inflater.inflate(R.layout.normal_list, null);
            TextView tv_header = (TextView) rowView.findViewById(R.id.tv_normal);
            ImageView img_stick=(ImageView) rowView.findViewById(R.id.img_stick_normal);

            tv_header.setText(HeaderDanhMuc);

            if (((ListView)parent).isItemChecked(position)) {
                IsPressFirstItem_DanhMuc = true;
                tv_header.setTextColor(Color.RED);
                img_stick.setImageResource(R.drawable.stick_icon);
            }

        }
        else {
            rowView = inflater.inflate(R.layout.item_imagelist, null);
            ImageView img_item = (ImageView) rowView.findViewById(R.id.imgImageBasicList);
            TextView tv_item = (TextView) rowView.findViewById(R.id.txtTieuDeImageBasicList);
            ImageView img_stick=(ImageView) rowView.findViewById(R.id.img_stick);

            img_item.setImageBitmap(listDanhMuc.get(position - 1).getImgDanhMuc());
            tv_item.setText(listDanhMuc.get(position - 1).getTvDanhMuc());

            if (((ListView)parent).isItemChecked(position)) {
                IsPressFirstItem_DanhMuc = true;
                tv_item.setTextColor(Color.RED);
                img_stick.setImageResource(R.drawable.stick_icon);
            }

        }

        return rowView;
    }
}
