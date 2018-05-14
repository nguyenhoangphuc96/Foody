package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ItemImageList;
import com.example.sino.foodyv1.tab_angi.Tab_AnGi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SINO on 4/18/2017.
 */

public class ListHasImageItemAdapter_DanhMucAnGi extends BaseAdapter{
    public static String nameDM = "Sang trọng";
    Context _context;
    int _resource;
    List<ItemImageList> _objects = new ArrayList<>();
    LayoutInflater _inflater;


    public ListHasImageItemAdapter_DanhMucAnGi(Context context, int resource, List<ItemImageList> objects) {
        this._context = context;
        this._resource = resource;
        this._objects = objects;
        this._inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return _objects.size();
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
        View row = _inflater.inflate(this._resource,parent,false);
        //Ánh xạ
        ImageView imgImageBasicList = (ImageView) row.findViewById(R.id.imgImageBasicList);
        TextView txtTieuDeImageBasicList = (TextView) row.findViewById(R.id.txtTieuDeImageBasicList);
        //Khai báo biến item để lấy thông tin từng object
        final ItemImageList item = _objects.get(position);
        //Gắn hình và tiêu đề cho từng item của listview
        Bitmap bitmap = BitmapFactory.decodeByteArray(item.get_hinhAnh(),0,item.get_hinhAnh().length);
        imgImageBasicList.setImageBitmap(bitmap);
        txtTieuDeImageBasicList.setText(item.get_tieuDe());
        //lấy tên của danh mục được click đưa lên tab
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameDM = item.get_tieuDe();//lấy tên danh mục
                TextView txt= (TextView) Tab_AnGi.mTabHostAnGi.getTabWidget().getChildAt(2).findViewById(R.id.tabname);
                txt.setText(nameDM);
                txt.setTextColor(Color.RED);
                Tab_AnGi.mTabHostAnGi.setCurrentTab(0);
                Tab_AnGi.flDM=false;
                //Hiện lại thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);

            }
        });


        return row;
    }
}
