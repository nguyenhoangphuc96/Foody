package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ItemIconList;

import java.util.List;

/**
 * Created by SINO on 4/3/2017.
 */

public class ListHasOnlyTextItemAdapter extends BaseAdapter {

    Context _context;
    int _resource;
    List<ItemIconList> _objects;
    LayoutInflater _inflater;

    public ListHasOnlyTextItemAdapter(Context context, int resource, List<ItemIconList> objects) {
        this._context = context;
        this._resource = resource;
        this._objects =objects;
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

        TextView txtItemTextList = (TextView) row.findViewById(R.id.txtItemTextList);

        ItemIconList item = _objects.get(position);

        txtItemTextList.setText(item.get_tieuDe());

        return row;
    }
}
