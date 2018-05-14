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
import android.widget.ListView;
import android.widget.TextView;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ItemImageList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SINO on 4/3/2017.
 */

public class ListHasImageItemAdapter_DanhMucODau extends BaseAdapter{

    public static String nameDM = "Sang trọng";
    Context _context;
    int _resource;
    List<ItemImageList> _objects = new ArrayList<>();
    LayoutInflater _inflater;
    private static final int TYPE_HEARDER = 0;
    private static final int TYPE_ITEM = 1;
    String HeaderDanhMuc;
    public static boolean IsPressFirstItem_DanhMuc = false;



    public ListHasImageItemAdapter_DanhMucODau(Context context, String headerDanhMuc, int resource, List<ItemImageList> objects) {
        this._context = context;
        this.HeaderDanhMuc= headerDanhMuc;
        this._resource = resource;
        this._objects = objects;
        this._inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//        View row = _inflater.inflate(this._resource,parent,false);
//        //Ánh xạ
//        ImageView imgImageBasicList = (ImageView) row.findViewById(R.id.imgImageBasicList);
//        TextView txtTieuDeImageBasicList = (TextView) row.findViewById(R.id.txtTieuDeImageBasicList);
//        //Khai báo biến item để lấy thông tin từng object
//        final ItemImageList item = _objects.get(position);
//        //Gắn hình và tiêu đề cho từng item của listview
//        Bitmap bitmap = BitmapFactory.decodeByteArray(item.get_hinhAnh(),0,item.get_hinhAnh().length);
//        imgImageBasicList.setImageBitmap(bitmap);
//        txtTieuDeImageBasicList.setText(item.get_tieuDe());
//        //Lấy tên của danh mục duoc95 click đưa lên tab
//        row.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                nameDM = item.get_tieuDe();//lấy tên danh mục
//                TextView txt= (TextView) Tab_ODau.mTabHost.getTabWidget().getChildAt(2).findViewById(R.id.tabname);
//                txt.setText(nameDM);
//                txt.setTextColor(Color.RED);
//                Tab_ODau.mTabHost.setCurrentTab(0);
//                Tab_ODau.flDM=false;
//                //Hiện lại thanh bottombar
//                MainActivity.setVisibilityLayoutBottonBar(true);
//
//            }
//    });
        LayoutInflater inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            //Ánh xạ
            ImageView imgImageBasicList = (ImageView) rowView.findViewById(R.id.imgImageBasicList);
            TextView txtTieuDeImageBasicList = (TextView) rowView.findViewById(R.id.txtTieuDeImageBasicList);
            ImageView img_stick=(ImageView) rowView.findViewById(R.id.img_stick);

            //img_item.setImageBitmap(listDanhMuc.get(position - 1).getImgDanhMuc());
            //tv_item.setText(listDanhMuc.get(position - 1).getTvDanhMuc());
            //Khai báo biến item để lấy thông tin từng object
            final ItemImageList item = _objects.get(position);
            //Gắn hình và tiêu đề cho từng item của listview
            Bitmap bitmap = BitmapFactory.decodeByteArray(item.get_hinhAnh(),0,item.get_hinhAnh().length);
            imgImageBasicList.setImageBitmap(bitmap);
            txtTieuDeImageBasicList.setText(item.get_tieuDe());

            if (((ListView)parent).isItemChecked(position)) {
                IsPressFirstItem_DanhMuc = true;
                txtTieuDeImageBasicList.setTextColor(Color.RED);
                img_stick.setImageResource(R.drawable.stick_icon);
            }

        }


        return rowView;
    }
}
