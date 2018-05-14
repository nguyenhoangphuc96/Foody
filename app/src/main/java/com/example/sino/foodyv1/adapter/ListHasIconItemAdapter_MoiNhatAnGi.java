package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ItemIconList;
import com.example.sino.foodyv1.tab_angi.Tab_AnGi;

import java.util.ArrayList;

import static com.example.sino.foodyv1.tab_angi.Tab_AnGi.mTabHostAnGi;
import static com.example.sino.foodyv1.tab_angi.Tab_AnGi.mauTrang;

/**
 * Created by SINO on 4/19/2017.
 */

public class ListHasIconItemAdapter_MoiNhatAnGi extends BaseAdapter {
    public static String nameMN;
    Context _context;
    int _resource;
    ArrayList<ItemIconList> _objects =  new ArrayList<>() ;
    LayoutInflater _inflater;

    public ListHasIconItemAdapter_MoiNhatAnGi(Context context, int resource, ArrayList<ItemIconList> objects) {
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
        final TextView txtTieuDeBasicList = (TextView) row.findViewById(R.id.txtTieuDeBasicList);
        final ImageView img_stick=(ImageView) row.findViewById(R.id.img_stick_moinhat);
        //biến item để lấy thông tin từng object
        final ItemIconList item = this._objects.get(position);
        //gán tiêu đề cho từng item của listview
        imgIconBasicList.setImageResource(this._context.getResources().getIdentifier(item.get_idhinhAnh(),"drawable",_context.getPackageName()));
        txtTieuDeBasicList.setText(item.get_tieuDe());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameMN = item.get_tieuDe();//lấy tên item
                TextView txt= (TextView) mTabHostAnGi.getTabWidget().getChildAt(1).findViewById(R.id.tabname);
                txt.setText(nameMN);
                txt.setTextColor(Color.RED);
                mTabHostAnGi.setCurrentTab(0);
                Tab_AnGi.flMN=false;

                //Đặt màu nền cho tab

                mTabHostAnGi.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(mauTrang));

                //hiện thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);

            }
        });

        return row;
    }
}
