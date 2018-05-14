package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ItemIconList;

import java.util.ArrayList;

/**
 * Created by SINO on 4/3/2017.
 */

public class ListHasIconItemAdapter_MoiNhatODau extends BaseAdapter {
    public static String nameMN;
    Context _context;
    int _resource;
    ArrayList<ItemIconList> _objects =  new ArrayList<>() ;
    LayoutInflater _inflater;
    public static boolean IsPressFirstItem_MoiNhat = false;

    public ListHasIconItemAdapter_MoiNhatODau(Context context, int resource, ArrayList<ItemIconList> objects) {
        this._context=context;
        this._resource = resource;
        this._objects = objects;
        this._inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return _objects.size();
    }

    @Override
    public Object getItem(int position) {
        return _objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = _inflater.inflate(this._resource,parent,false);
        //ánh xạ
        ImageView imgIconBasicList = (ImageView) row.findViewById(R.id.imgIconBasicList);
        TextView txtTieuDeBasicList = (TextView) row.findViewById(R.id.txtTieuDeBasicList);
        ImageView img_stick=(ImageView) row.findViewById(R.id.img_stick_moinhat);
        //biến item để lấy thông tin từng object
        final ItemIconList item = this._objects.get(position);
        //gán tiêu đề cho từng item của listview
        imgIconBasicList.setImageResource(this._context.getResources().getIdentifier(item.get_idhinhAnh(),"drawable",_context.getPackageName()));
        txtTieuDeBasicList.setText(item.get_tieuDe());
        if (((ListView)parent).isItemChecked(position)) {
            IsPressFirstItem_MoiNhat = true;
            txtTieuDeBasicList.setTextColor(Color.RED);
            img_stick.setImageResource(R.drawable.stick_icon);
        }



        return row;
    }
}
