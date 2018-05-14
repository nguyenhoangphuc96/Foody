package com.example.sino.foodyv1.tab_odau;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.sino.foodyv1.Database;
import com.example.sino.foodyv1.R;
import com.example.sino.foodyv1.adapter.ThanhPhoAdapter;
import com.example.sino.foodyv1.model.ThanhPho;

import java.util.ArrayList;

/**
 * Created by SINO on 4/24/2017.
 */

public class ChonThanhPho_Fragment extends Activity {

    final String DATABASE_NAME="QuanLyFoodyDB.sqlite";
    SQLiteDatabase database ;

    Button btnXong;
    ImageButton btnQuayLai;
    ListView listView;
    public static int MaThanhPho=0;
    public static String TenThanhPho="TP.HCM";
    ArrayList<ThanhPho> list;
    ThanhPhoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chontp_fragment);

        btnXong = (Button) findViewById(R.id.btnXongThanhPho);
        btnQuayLai = (ImageButton)  findViewById(R.id.btnQuayLaiThanhPho);

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.listChonThanhPho);
        list = new ArrayList<ThanhPho>();
        readData();
        adapter = new ThanhPhoAdapter(this,list);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        // set sự kiện cho item của listview
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                MaThanhPho = list.get(position).getMaTP();
                TenThanhPho = list.get(position).getTenTP();

                listView.smoothScrollToPosition(position-1);
                adapter.notifyDataSetChanged();

            }
        });
        //Click theo MaThanhPho(cong 1 vi id trong SQLite bat dau tu 0)
        listView.performItemClick(listView, MaThanhPho+1, listView.getItemIdAtPosition(MaThanhPho+1));
    }
    private void readData()
    {
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from ThanhPho",null);
        list.clear();
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            list.add(new ThanhPho(id,ten));
        }

    }
    // 2.0 and above
    @Override
    public void onBackPressed() {

        finish();
    }

}
