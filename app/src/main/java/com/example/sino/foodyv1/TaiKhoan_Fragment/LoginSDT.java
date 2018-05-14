package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.app.Activity;
import android.os.Bundle;

import com.example.sino.foodyv1.R;

/**
 * Created by SINO on 5/19/2017.
 */

public class LoginSDT extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhapsdt);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
