package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.model.ModelODau;

import java.util.List;

/**
 * Created by SINO on 4/24/2017.
 */

public class QuanAnAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CATEGORY = 1;
    private static final int TYPE_DATA = 2;

    private Context context;
    private int imgHeader;
    private int[] imgCategory;
    private String[] tvCategory;
    //private List<QuanAn> listQuanAn;
    private List<ModelODau> listODau;

    public QuanAnAdapter(Context context, int imgHeader, int[] imgCategory, String[] tvCategory, List<ModelODau> listODau) {
        this.context=context;
        this.imgHeader=imgHeader;
        this.imgCategory=imgCategory;
        this.tvCategory=tvCategory;
        //this.listQuanAn=listQuanAn;
        this.listODau=listODau;
    }

    public class HeaderItem extends RecyclerView.ViewHolder {

        private ImageView img_quangcao;
        public HeaderItem(View itemView) {
            super(itemView);

            img_quangcao = (ImageView) itemView.findViewById(R.id.imgHeader);
        }
    }

    public class CategoryItem extends RecyclerView.ViewHolder {

        private ImageView img_category;
        private TextView tv_category;
        public CategoryItem(View itemView) {
            super(itemView);

            img_category = (ImageView) itemView.findViewById(R.id.img_tieude_odau);
            tv_category = (TextView) itemView.findViewById(R.id.txt_tieude_odau);
        }
    }

    public class DataItem extends RecyclerView.ViewHolder {
        private TextView tvDiem;
        private TextView tvTen;
        private TextView tvDiaDiem;
        private ImageView imgHinh;
        public DataItem(View itemView) {
            super(itemView);
            tvDiem = (TextView) itemView.findViewById(R.id.txtDiem);
            tvTen = (TextView) itemView.findViewById(R.id.txtTenQuan);
            tvDiaDiem = (TextView) itemView.findViewById(R.id.txtDiaDiem);
            imgHinh = (ImageView) itemView.findViewById(R.id.img_NoiDung_ODau);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_main_header, parent, false);

            viewHolder = new HeaderItem(view);
        } else if (viewType == TYPE_CATEGORY){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_odau, parent, false);
            viewHolder = new CategoryItem(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_odau, parent, false);
            viewHolder = new DataItem(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderItem) {
            HeaderItem mHolder = (HeaderItem)holder;
            mHolder.img_quangcao.setImageResource(imgHeader);
        } else if (holder instanceof CategoryItem){
            CategoryItem mHolder = (CategoryItem)holder;
            mHolder.img_category.setImageResource(imgCategory[position - 1]);
            mHolder.tv_category.setText(tvCategory[position - 1]);
        } else {
            DataItem mHolder = (DataItem) holder;
            ModelODau item = listODau.get(position - tvCategory.length - 1);
            mHolder.tvDiem.setText(String.valueOf(item.getDiem()));
            mHolder.tvTen.setText(item.getTenQuan());
            mHolder.tvDiaDiem.setText(item.getDiaChi() + ", " + item.getTenDuong()+ ", " + item.getTenQuanHuyen()+ ", " + item.getTenThanhPho());
            mHolder.imgHinh.setImageBitmap(item.getHinhAnh());
        }
    }

    @Override
    public int getItemCount() {
        return 1 + tvCategory.length + listODau.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else if (position <= 10)
            return TYPE_CATEGORY;
        else
            return TYPE_DATA;
    }
}