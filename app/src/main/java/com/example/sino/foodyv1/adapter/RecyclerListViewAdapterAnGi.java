package com.example.sino.foodyv1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.tab_angi.NoiDungListAnGi;

import java.util.List;

/**
 * Created by SINO on 4/11/2017.
 */

public class RecyclerListViewAdapterAnGi extends RecyclerView.Adapter< RecyclerView.ViewHolder>{

    private static final int TYPE_HEADER =0;
    private static final int TYPE_ITEM= 1;

    List<NoiDungListAnGi> noiDungListAnGiList;
    private Context context;

    private int[] HinhAnh;
    private String[] TenTheoHinh;

    public RecyclerListViewAdapterAnGi(Context context, List<NoiDungListAnGi> noiDungListAnGiList, String[] TenTheoHinh,
                                       int[] HinhAnh  ) {
        this.noiDungListAnGiList = noiDungListAnGiList;
        this.context=context;
        this.HinhAnh=HinhAnh;
        this.TenTheoHinh=TenTheoHinh;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_odau, parent, false);

            viewHolder = new ODauHead(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_angi_noidung, parent, false);
            viewHolder = new ODauItem(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ODauHead) {
            ODauHead mHolder = (ODauHead)holder;
            mHolder.txt_tieude_noidung.setText(TenTheoHinh[position]);
            mHolder.img_tieude_noidung.setImageResource(HinhAnh[position]);
        } else {
            ODauItem mHolder = (ODauItem)holder;
            NoiDungListAnGi item = noiDungListAnGiList.get(position-TenTheoHinh.length);
            mHolder.txtDiaDiem.setText(item.diaChi);
            mHolder.txtTenQuan.setText(item.tenQuan);
            Bitmap bmHinhDaiDien =
                    BitmapFactory.decodeByteArray(item.hinhAnh,0,item.hinhAnh.length);
            mHolder.imgNoiDungODau.setImageBitmap(bmHinhDaiDien);

            mHolder.txtTenMonAn.setText(item.TenMonAn);

        }

    }

    @Override
    public int getItemCount() {

        //return noiDungListODauList == null ? 0:noiDungListODauList.size();
        return noiDungListAnGiList.size()+TenTheoHinh.length;
    }
    @Override
    public int getItemViewType(int position) {
        if (position<10)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }


    public class ODauItem extends RecyclerView.ViewHolder {
        private TextView txtTenQuan;
        private TextView txtTenMonAn;
        private TextView txtDiaDiem;
        private ImageView imgNoiDungODau;
        public ODauItem(View itemView) {
            super(itemView);
            txtDiaDiem = (TextView) itemView.findViewById(R.id.txtDiaDiem);
            txtTenMonAn = (TextView) itemView.findViewById(R.id.txtTenMonAn);
            txtTenQuan = (TextView) itemView.findViewById(R.id.txtTenQuan);
            imgNoiDungODau = (ImageView) itemView.findViewById(R.id.img_NoiDung_ODau);
        }
    }
    public class ODauHead extends RecyclerView.ViewHolder {

        private TextView txt_tieude_noidung;
        private ImageView img_tieude_noidung;
        public ODauHead(View itemView) {
            super(itemView);

            txt_tieude_noidung = (TextView) itemView.findViewById(R.id.txt_tieude_odau);
            img_tieude_noidung = (ImageView) itemView.findViewById(R.id.img_tieude_odau);
        }
    }


}
