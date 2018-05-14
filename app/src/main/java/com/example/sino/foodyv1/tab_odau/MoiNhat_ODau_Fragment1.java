package com.example.sino.foodyv1.tab_odau;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.MoiNhatAdapter;

import static com.example.sino.foodyv1.adapter.MoiNhatAdapter.IsPressFirstItem_MoiNhat;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.flMN;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.mTabHostODau;
import static com.example.sino.foodyv1.tab_odau.Tab_ODau.mauTrang;

/**
 * Created by SINO on 4/20/2017.
 */

public class MoiNhat_ODau_Fragment1 extends Fragment {

    private ListView lvItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.moinhat_odau_fragment, container, false);

        final String[] tv_custom = {"Mới nhất", "Gần tôi", "Phổ biến", "Du khách", "Ưu đãi E-card", "Đặt chỗ", "Ưu đãi thẻ", "Đặt giao hàng"};
        int[] img_custom={ R.drawable.mn01, R.drawable.mn02, R.drawable.mn03, R.drawable.mn04,
                R.drawable.mn05, R.drawable.mn06, R.drawable.mn07, R.drawable.mncuoi};

        lvItems = (ListView) v.findViewById(R.id.lvMoiNhatODau);
        lvItems.setAdapter(new MoiNhatAdapter(getContext(), img_custom,tv_custom, mTabHostODau));

        lvItems.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (IsPressFirstItem_MoiNhat == true) {
                    mTabHostODau.setCurrentTab(0);
                    flMN = false;
                    //Đặt màu nền cho tab

                    mTabHostODau.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(mauTrang));

                    //hiện thanh bottombar
                    MainActivity.setVisibilityLayoutBottonBar(true);
                }
                if(((ListView)parent).isItemChecked(position)){
                    TextView temp = (TextView) mTabHostODau.getTabWidget().getChildAt(1).findViewById(R.id.tabname);//Lấy những text muôn thay đổi
                    temp.setText(tv_custom[position]);
                    temp.setTextColor(Color.RED);
                }

            }
        });
        if(IsPressFirstItem_MoiNhat == false) {
            lvItems.performItemClick(lvItems, 0, lvItems.getItemIdAtPosition(0));
        }

        Button btn=(Button) v.findViewById(R.id.btnHuy_Moinhat_ODau);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTabHostODau.setCurrentTab(0);
                //Đặt màu nền cho tab

                mTabHostODau.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor(mauTrang));

                //hiện thanh bottombar
                MainActivity.setVisibilityLayoutBottonBar(true);
            }
        });


        return v;
    }
}
