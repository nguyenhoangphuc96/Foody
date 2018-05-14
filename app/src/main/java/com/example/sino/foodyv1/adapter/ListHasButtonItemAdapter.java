package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ItemIconList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SINO on 4/3/2017.
 */

public class ListHasButtonItemAdapter extends BaseAdapter {

    Context _context;
    int _resource;
    List<ItemIconList> _objects = new ArrayList<>();
    LayoutInflater _inflater;
    public ListHasButtonItemAdapter(Context context, int resource, List<ItemIconList> objects) {
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
        return _objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = _inflater.inflate(this._resource,parent,false);
        //ánh xạ
        TextView txtItemButtonList = (TextView) row.findViewById(R.id.txtItemButtonList);
        Button btnItemButtonList = (Button) row.findViewById(R.id.btnItemButtonList);
        final ListView lvItemButtonList = (ListView) row.findViewById(R.id.lvItemButtonList);
        lvItemButtonList.setVisibility(View.GONE);
        //Biến iten để lấy thông tin từng object
        ItemIconList item = _objects.get(position);
        //khai báo mảng dsDuongPho để lưu trữ các Đường phố
        ArrayList<ItemIconList> dsDuongPho = new ArrayList<>();
        ListHasOnlyTextItemAdapter adapter = new ListHasOnlyTextItemAdapter(_context,R.layout.item_textlist,dsDuongPho);
        lvItemButtonList.setAdapter(adapter);

        //Gán tiêu đề cho các item
        txtItemButtonList.setText(item.get_tieuDe());
        //Lấy dử liệu từ database gán cho dsDuongPho
        try {
            dsDuongPho.clear();
            Cursor cursor = MainActivity.layDuLieuTuBang("select * from tbDuongPho where idQuanHuyen = ?",new String[]{item.get_idhinhAnh()});
            while (cursor.moveToNext()){
                dsDuongPho.add(new ItemIconList(String.valueOf(cursor.getInt(0)),cursor.getString(1)));
            }
            cursor.close();
            adapter.notifyDataSetChanged();
        }catch (Exception ex){
            Toast.makeText(_context, ""+ex, Toast.LENGTH_SHORT).show();
        }

        ViewGroup.LayoutParams params = lvItemButtonList.getLayoutParams();
        params.height = 110 * dsDuongPho.size();
        lvItemButtonList.setLayoutParams(params);
        //Khi click item của listview lớn
        btnItemButtonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvItemButtonList.setVisibility(lvItemButtonList.isShown() ? View.GONE : View.VISIBLE);
            }
        });
        return row;
    }
}
