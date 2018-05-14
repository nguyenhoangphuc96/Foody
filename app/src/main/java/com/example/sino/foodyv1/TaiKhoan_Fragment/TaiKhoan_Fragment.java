package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;

import static com.example.sino.foodyv1.MainActivity.flLogin;
import static com.example.sino.foodyv1.MainActivity.tk;

/**
 * Created by SINO on 4/10/2017.
 */

public class TaiKhoan_Fragment extends Fragment {


    TextView tvDangNhap;
    ImageView imgDaiDien;
    TextView tvThietLapTK;
    LinearLayout loDangXuat;

    public static TaiKhoan_Fragment newInstance() {
        TaiKhoan_Fragment fragment = new TaiKhoan_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_fragment, container, false);
        tvDangNhap = (TextView) rootView.findViewById(R.id.txtDangNhap);
        imgDaiDien = (ImageView) rootView.findViewById(R.id.imgDaiDien);
        tvThietLapTK = (TextView) rootView.findViewById(R.id.tv_ThietLapTK);
        loDangXuat = (LinearLayout) rootView.findViewById(R.id.loDangXuat);

        if (flLogin) {//trường hợp đã đăng nhập
            if (tk.getHinhDaiDien() != null) { // trường hợp đã có hình đại diện
                tvDangNhap.setText(tk.getTenHienThi());
                imgDaiDien.setImageBitmap(tk.getHinhDaiDien());
            } else { //trường hợp chưa có hình đại diện thì lấy hình mặc định
                tvDangNhap.setText(tk.getTenHienThi());
                imgDaiDien.setImageResource(R.drawable.ic_user);
            }
            tvThietLapTK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ThietLapTK.class);
                    startActivity(intent);
                }
            });
            loDangXuat.setVisibility(View.VISIBLE);
            loDangXuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CreateDialogLogOut dialogLogOut = new CreateDialogLogOut();
                    dialogLogOut.show(getFragmentManager(), "LogOut");
                }
            });
        }
        else
        {
        {tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
            loDangXuat.setVisibility(View.GONE);//ẩn dòng đăng xuất
        }

        }


        return rootView;

    }

    public static class CreateDialogLogOut extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Bạn có muốn đăng xuất ?")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            flLogin = false;
                            tk = null;
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}



