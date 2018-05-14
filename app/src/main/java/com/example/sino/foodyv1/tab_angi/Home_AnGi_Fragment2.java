package com.example.sino.foodyv1.tab_angi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sino.foodyv1.Client;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.MonAnAdapter1;
import com.example.sino.foodyv1.model.MoDelAnGi;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;


import static com.example.sino.foodyv1.tab_angi.Tab_AnGi.danhmuc_angi;
import static com.example.sino.foodyv1.tab_angi.Tab_AnGi.diadiem_angi;
import static com.example.sino.foodyv1.tab_angi.Tab_AnGi.kieudiadiem_angi;

/**
 * Created by SINO on 5/17/2017.
 */

public class Home_AnGi_Fragment2 extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView listView;
    private MonAnAdapter1 monAnAdapter;
    private int imgHeader = R.drawable.quangcao1;
    private int[] imgCategory ={R.drawable.ic_nearby,R.drawable.ic_ecoupon,R.drawable.ic_sttnotification_tablenow,
            R.drawable.ic_sttnotification_deli,R.drawable.ic_ecard,R.drawable.ic_game,
            R.drawable.ic_icon_binhluanmoi,R.drawable.ic_reporttraffic,R.drawable.ic_icon_topthanhvien,
            R.drawable.ic_video};
    private String[] tvCategory ={"Gần tôi","Coupon","Đặt chỗ ưu đãi","Đặt giao hàng","E-card","Game & Fun","Bình luận",
            "Blogs","Top Thành Viên","Video"};
    //private ArrayList<MonAn> listMonAn;
    private ArrayList<MoDelAnGi> listAnGi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_angi_fragment, container, false);

        listView = (RecyclerView)v.findViewById(R.id.rcvHienThiCacMonAn);

        listView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position == 0 ? 2 : position <= 10 ? 1 : 1;
            }
        });
        listView.setLayoutManager(layoutManager);

        listAnGi = new ArrayList<MoDelAnGi>();
        monAnAdapter = new MonAnAdapter1(getContext(), imgHeader, imgCategory, tvCategory, listAnGi);
        listView.setAdapter(monAnAdapter);
        monAnAdapter.notifyDataSetChanged();
        listView.scrollToPosition(0);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefreshAnGi);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getAnGi();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }
    ProgressDialog progressDialog;
    private void getAnGi(){
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RequestParams params = new RequestParams();
        params.put("TenDanhMuc", danhmuc_angi);
        params.put("KieuDiaDiem", kieudiadiem_angi);
        params.put("TenDiaDiem", diadiem_angi);

        Client angi = new Client();
        angi.get(getContext(), "api/MonAn/GetMonAnList", headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        progressDialog = new ProgressDialog(getContext(), R.style.MyTheme);
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        listAnGi = new ArrayList<MoDelAnGi>();
                        //monAnAdapter = new MonAnAdapter(getContext(), imgHeader, imgCategory, tvCategory, listAnGi);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                listAnGi.add(new MoDelAnGi(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        monAnAdapter = new MonAnAdapter1(getContext(), imgHeader, imgCategory, tvCategory, listAnGi);

                        listView.setAdapter(monAnAdapter);
                        monAnAdapter.notifyDataSetChanged();

                        listView.scrollToPosition(0);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(getActivity(), responseString, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinish() {
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getAnGi();
    }
}
