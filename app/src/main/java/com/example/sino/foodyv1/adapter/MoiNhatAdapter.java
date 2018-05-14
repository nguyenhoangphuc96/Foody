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

/**
 * Created by SINO on 4/20/2017.
 */

public class MoiNhatAdapter extends BaseAdapter {

    public static boolean IsPressFirstItem_MoiNhat = false;

    Context context;
    int[] img_custom;
    String[] tv_custom;
    FragmentTabHost mTabHost;

    public MoiNhatAdapter(Context context, int[] img_custom, String[] tv_custom, FragmentTabHost mTabHost) {
        this.context = context;
        this.img_custom = img_custom;
        this.tv_custom = tv_custom;
        this.mTabHost = mTabHost;
    }

    @Override
    public int getCount() {
        return tv_custom.length ;
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
        View rowView = inflater.inflate(R.layout.item_iconlist, null);

        TextView tv=(TextView) rowView.findViewById(R.id.txtTieuDeBasicList);
        ImageView img=(ImageView) rowView.findViewById(R.id.imgIconBasicList);
        ImageView img_stick=(ImageView) rowView.findViewById(R.id.img_stick_moinhat);

        tv.setText(tv_custom[position]);
        img.setImageResource(img_custom[position]);
        if (((ListView)parent).isItemChecked(position)) {
            IsPressFirstItem_MoiNhat = true;
            tv.setTextColor(Color.RED);
            img_stick.setImageResource(R.drawable.stick_icon);
        }




        return rowView;
    }
}
