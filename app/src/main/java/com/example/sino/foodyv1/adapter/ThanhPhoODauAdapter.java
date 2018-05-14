package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.DuongQuan;
import com.example.sino.foodyv1.model.QuanHuyen;
import com.example.sino.foodyv1.tab_angi.Tab_AnGi;

import java.util.HashMap;
import java.util.List;

import static com.example.sino.foodyv1.tab_odau.Tab_ODau.diadiem_odau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.flTP;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.kieudiadiem_odau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.mTabHostODau;

/**
 * Created by SINO on 5/22/2017.
 */

public class ThanhPhoODauAdapter extends BaseExpandableListAdapter {
    private List<QuanHuyen> header_titles;
    private HashMap<String, List<DuongQuan>> child_titles;
    private Context ctx;

    public ThanhPhoODauAdapter(Context ctx, List<QuanHuyen> header_titles, HashMap<String, List<DuongQuan>> chiled_titles)
    {
        this.ctx = ctx;
        this.child_titles = chiled_titles;
        this.header_titles = header_titles;
    }
    @Override
    public int getGroupCount() {
        return header_titles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  child_titles.get(header_titles.get(groupPosition).getTenQuan()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return header_titles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_titles.get(header_titles.get(groupPosition).getTenQuan()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        QuanHuyen title = (QuanHuyen)this.getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.thanhpho_parent, parent, false);
        }
        final TextView textView = (TextView)convertView.findViewById(R.id.heading_item);
        textView.setText(title.getTenQuan());
        Button btnSoDuong = (Button)convertView.findViewById(R.id.btnSoDuong);
        btnSoDuong.setText(this.getChildrenCount(groupPosition) + " đường");
        btnSoDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpanded)
                    ((ExpandableListView) parent).collapseGroup(groupPosition);
                else
                    ((ExpandableListView) parent).expandGroup(groupPosition, true);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temp = (TextView) mTabHostODau.getTabWidget().getChildAt(3).findViewById(R.id.tabname);//Lấy những text muôn thay đổi
                temp.setText(textView.getText());
                temp.setTextColor(Color.RED);

                kieudiadiem_odau = "QuanHuyen";
                diadiem_odau = temp.getText().toString();
                mTabHostODau.setCurrentTab(0);
                flTP = false;
                MainActivity.setVisibilityLayoutBottonBar(true);
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        DuongQuan title = (DuongQuan)this.getChild(groupPosition, childPosition);
        if (convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.thanhpho_child, parent, false);
        }
        final TextView textView = (TextView)convertView.findViewById(R.id.child_item);
        textView.setText(title.getTenDuong());

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temp = (TextView) mTabHostODau.getTabWidget().getChildAt(3).findViewById(R.id.tabname);//Lấy những text muôn thay đổi
                temp.setText(textView.getText());
                temp.setTextColor(Color.RED);

                kieudiadiem_odau = "DuongPho";
                diadiem_odau = temp.getText().toString();
                mTabHostODau.setCurrentTab(0);
                flTP = false;
                Tab_AnGi.flTP = false;
                MainActivity.setVisibilityLayoutBottonBar(true);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
