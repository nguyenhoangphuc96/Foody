package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ThanhPho;

import java.util.ArrayList;

/**
 * Created by SINO on 4/24/2017.
 */

public class ThanhPhoAdapter extends BaseAdapter {
    Context context;
    ArrayList<ThanhPho> thanhPhos;
    public static boolean NutNhanTrcChonThanhPho=false;
    public ThanhPhoAdapter(Context context, ArrayList<ThanhPho> thanhPhos) {
        this.thanhPhos = thanhPhos;
        this.context = context;

    }
    @Override
    public int getCount() {
        return thanhPhos.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_chonthanhpho, null);

        ImageView imgChonThanhPho = (ImageView) row.findViewById(R.id.imgThanhPhoDaChon);
        TextView txtTenThanhPho = (TextView) row.findViewById(R.id.txtThanhPhoDuocChon);
        final String lv = thanhPhos.get(position).TenTP;
        if(((ListView)parent).isItemChecked(position)){
            NutNhanTrcChonThanhPho=true;

            txtTenThanhPho.setText(lv);
            txtTenThanhPho.setTextColor(Color.BLUE);

            imgChonThanhPho.setImageResource(R.drawable.stick_icon);

        }else {


            txtTenThanhPho.setText(lv);

        }


        return row;
    }
}
