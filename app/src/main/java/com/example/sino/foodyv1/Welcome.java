package com.example.sino.foodyv1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;



/**
 * Created by SINO on 5/9/2017.
 */

public class Welcome  extends AppCompatActivity{
    public ProgressDialog myDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhwelcome);

        new Handler().postDelayed(new Runnable() {

            public void run() {

                myDialog = ProgressDialog.show(Welcome.this,"", "Loading", true);

                Intent intent=new Intent(Welcome.this,MainActivity.class);
                Welcome.this.startActivity(intent);
                myDialog.dismiss();
                Welcome.this.finish();
            }

        }, 3000);// 3 Seconds
    }






}
