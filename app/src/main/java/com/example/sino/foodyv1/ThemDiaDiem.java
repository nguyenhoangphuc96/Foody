package com.example.sino.foodyv1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sino.foodyv1.TaiKhoan_Fragment.Check;
import com.example.sino.foodyv1.model.DanhMuc;
import com.example.sino.foodyv1.model.DuongQuan;
import com.example.sino.foodyv1.model.QuanHuyen;
import com.example.sino.foodyv1.model.ThanhPho;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.extras.Base64;

import static com.example.sino.foodyv1.MainActivity.DATABASE_NAME;
import static com.example.sino.foodyv1.MainActivity.database;

/**
 * Created by SINO on 5/23/2017.
 */

public class ThemDiaDiem extends Activity implements View.OnClickListener {
    private static int REQUEST_LOAD_IMAGE = 1;
    private static int REQUEST_CAMERA = 2;
    private static Boolean ChangeImage = false;

    ProgressDialog prgDialog;
    ImageButton ibtnThoatManHinhThemDiaDiem;
    Button btnThemDiaDiem;
    TextView txtChonThanhPhoThemDiaDiem;
    TextView txtChonQuanThemDiaDiem;
    EditText edtNhapTenDiaDiem;
    TextView txtNhapLoaiHinhDiaDiem;
    TextView txtChonDuongQuanThemDiaDiem;
    EditText edtNhapDiaChiDiaDiem;
    LinearLayout loViTriBanDo;
    TextView txtHienThiToaDoDiaDiem;
    EditText edtNhapSDTDiaDiem;
    TextView txtChonGioBatDauMoCuaDiaDiem;
    TextView txtChonGioKetThucCuaDiaDiem;
    EditText edtNhapGiaThapNhatCuaDiaDiem;
    EditText edtNhapGiaCaoNhatCuaDiaDiem;
    EditText edtMoTaDiaDiem;
    ImageView imgHinhAnhThemDiaDiem;
    RecyclerView rcvHienThiHinhAnhDiaDiem;
    ImageButton ibtnChonAnhDiaDiemTuThuVien;
    ImageButton ibtnChupAnhDiaDiem;

    ArrayList<DanhMuc> listDanhMuc;
    ArrayList<ThanhPho> listThanhPho;
    ArrayList<QuanHuyen> listQuanHuyen;
    ArrayList<DuongQuan> listDuongQuan;
    int MaDanhMucThemDiaDiem = -1;
    int MaThanhPhoThemDiaDiem = 0;
    int MaQuanHuyenThemDiaDiem = -1;
    int MaDuongQuanThemDiaDiem = -1;
    String giobatdau = "09:00:00";
    String gioketthuc = "21:00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themdiadiem_main);

        initControl();
        setEvent();

    }
    public void initControl() {
        ibtnThoatManHinhThemDiaDiem = (ImageButton) findViewById(R.id.ibtnThoatManHinhThemDiaDiem);
        btnThemDiaDiem = (Button) findViewById(R.id.btnThemDiaDiem);
        txtChonThanhPhoThemDiaDiem = (TextView) findViewById(R.id.txtChonThanhPhoThemDiaDiem);
        txtChonQuanThemDiaDiem = (TextView) findViewById(R.id.txtChonQuanThemDiaDiem);
        edtNhapTenDiaDiem = (EditText) findViewById(R.id.edtNhapTenDiaDiem);
        txtNhapLoaiHinhDiaDiem = (TextView) findViewById(R.id.txtNhapLoaiHinhDiaDiem);
        txtChonDuongQuanThemDiaDiem = (TextView) findViewById(R.id.txtChonDuongQuanThemDiaDiem);
        edtNhapDiaChiDiaDiem = (EditText) findViewById(R.id.edtNhapDiaChiDiaDiem);
        loViTriBanDo = (LinearLayout) findViewById(R.id.loViTriBanDo);
        txtHienThiToaDoDiaDiem = (TextView) findViewById(R.id.txtHienThiToaDoDiaDiem);
        edtNhapSDTDiaDiem = (EditText) findViewById(R.id.edtNhapSDTDiaDiem);
        txtChonGioBatDauMoCuaDiaDiem = (TextView) findViewById(R.id.txtChonGioBatDauMoCuaDiaDiem);
        txtChonGioKetThucCuaDiaDiem = (TextView) findViewById(R.id.txtChonGioKetThucCuaDiaDiem);
        edtNhapGiaThapNhatCuaDiaDiem = (EditText) findViewById(R.id.edtNhapGiaThapNhatCuaDiaDiem);
        edtNhapGiaCaoNhatCuaDiaDiem = (EditText) findViewById(R.id.edtNhapGiaCaoNhatCuaDiaDiem);
        edtMoTaDiaDiem = (EditText) findViewById(R.id.edtMoTaDiaDiem);
        imgHinhAnhThemDiaDiem = (ImageView) findViewById(R.id.imgHinhAnhThemDiaDiem);
        rcvHienThiHinhAnhDiaDiem = (RecyclerView) findViewById(R.id.rcvHienThiHinhAnhDiaDiem);
        ibtnChonAnhDiaDiemTuThuVien = (ImageButton) findViewById(R.id.ibtnChonAnhDiaDiemTuThuVien);
        ibtnChupAnhDiaDiem = (ImageButton) findViewById(R.id.ibtnChupAnhDiaDiem);
    }

    public void setEvent() {
        ibtnThoatManHinhThemDiaDiem.setOnClickListener(this);
        btnThemDiaDiem.setOnClickListener(this);
        txtChonThanhPhoThemDiaDiem.setOnClickListener(this);
        txtChonQuanThemDiaDiem.setOnClickListener(this);
        txtNhapLoaiHinhDiaDiem.setOnClickListener(this);
        txtChonDuongQuanThemDiaDiem.setOnClickListener(this);
        loViTriBanDo.setOnClickListener(this);
        txtChonGioBatDauMoCuaDiaDiem.setOnClickListener(this);
        txtChonGioKetThucCuaDiaDiem.setOnClickListener(this);
        ibtnChonAnhDiaDiemTuThuVien.setOnClickListener(this);
        ibtnChupAnhDiaDiem.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnThoatManHinhThemDiaDiem:
                finish();
                break;
            case R.id.btnThemDiaDiem:
                if (ChangeImage) {
                    String TenQuan = edtNhapTenDiaDiem.getText().toString();
                    String DiaChi = edtNhapDiaChiDiaDiem.getText().toString();
                    String HinhAnh = getByteArrayFromImageView(imgHinhAnhThemDiaDiem);
                    String SDT = edtNhapSDTDiaDiem.getText().toString();
                    int MaDuong = MaDuongQuanThemDiaDiem;
                    int MaDanhMuc = MaDanhMucThemDiaDiem;
                    double Diem = 5;
                    double Lat = 9.6;
                    double Long = 6.9;
                    String GioMoCua = giobatdau;
                    String GioDongCua = gioketthuc;
                    double GiaThapNhat = Double.parseDouble(edtNhapGiaThapNhatCuaDiaDiem.getText().toString());
                    double GiaCaoNhat = Double.parseDouble(edtNhapGiaCaoNhatCuaDiaDiem.getText().toString());
                    String MoTa = edtMoTaDiaDiem.getText().toString();
                    Calendar c = Calendar.getInstance();
                    String ThoiGianThem = String.valueOf(c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DATE)+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
                    prgDialog = new ProgressDialog(ThemDiaDiem.this);
                    prgDialog.setMessage("Vui lòng chờ ...");
                    prgDialog.setCancelable(false);
                    getThemDiaDiem(TenQuan, DiaChi, HinhAnh, SDT, MaDuong, MaDanhMuc, Diem, Lat, Long, GioMoCua, GioDongCua, GiaThapNhat, GiaCaoNhat, MoTa, ThoiGianThem);
                }
                break;
            case R.id.txtChonThanhPhoThemDiaDiem:
                fillDataThanhPho();
                PopupMenuThanhPho(v, listThanhPho);
                break;
            case R.id.txtChonQuanThemDiaDiem:
                if (!(MaThanhPhoThemDiaDiem == -1)) {  //bằng giá trị khởi tạo
                    fillDataQuanHuyen();
                    PopupMenuQuanHuyen(v, listQuanHuyen);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn thành phố", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtNhapLoaiHinhDiaDiem:
                fillDataDanhMuc();
                PopupMenuDanhMuc(v, listDanhMuc);
                break;
            case R.id.txtChonDuongQuanThemDiaDiem:
                if (!(MaQuanHuyenThemDiaDiem == -1)) {  //bằng giá trị khởi tạo
                    fillDataDuongQuan();
                    PopupMenuDuongQuan(v, listDuongQuan);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn quận huyện", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.loViTriBanDo:
                break;
            case R.id.txtChonGioBatDauMoCuaDiaDiem:
                TimePickerDialog(txtChonGioBatDauMoCuaDiaDiem, 9, 0, false);
                break;
            case R.id.txtChonGioKetThucCuaDiaDiem:
                TimePickerDialog(txtChonGioKetThucCuaDiaDiem, 21, 0, false);
                break;
            case R.id.ibtnChonAnhDiaDiemTuThuVien:
                loadImagefromGallery();
                break;
            case R.id.ibtnChupAnhDiaDiem:
                loadImagefromCamera();
                break;
            default:
                break;
        }
    }
    public void getThemDiaDiem(String TenQuan, String DiaChi, String HinhAnh, String SDT, int MaDuong, int MaDanhMuc,
                               double Diem, double Lat, double Long, String GioMoCua, String GioDongCua, double GiaThapNhat, double GiaCaoNhat, String MoTa, String ThoiGianThem) {
        try {
            if (MaDuongQuanThemDiaDiem != -1 && MaDanhMucThemDiaDiem != -1) {
                if (Check.isNotNull(TenQuan) && Check.isNotNull(DiaChi) && Check.isNotNull(HinhAnh) && Check.isNotNull(SDT)
                        && Check.isNotNull(GioMoCua) && Check.isNotNull(GioDongCua) && Check.isNotNull(MoTa) && Check.isNotNull(ThoiGianThem)) {
                    prgDialog.show();
                    StringEntity entity = null;
                    try {
                        JSONObject object = new JSONObject();
                        object.put("TenQuan", TenQuan);
                        object.put("DiaChi", DiaChi);
                        object.put("HinhAnh", HinhAnh);
                        object.put("SDT", SDT);
                        object.put("MaDuong", MaDuong);
                        object.put("MaDanhMuc", MaDanhMuc);
                        object.put("Diem", Diem);
                        object.put("Lat", Lat);
                        object.put("Long", Long);
                        object.put("GioBatDau", GioMoCua);
                        object.put("GioKetThuc", GioDongCua);
                        object.put("GiaThapNhat", GiaThapNhat);
                        object.put("GiaCaoNhat", GiaCaoNhat);
                        object.put("MoTa", MoTa);
                        object.put("ThoiGianThem", ThoiGianThem);
                        entity = new StringEntity(object.toString(), "utf-8");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Client quanan = new Client();
                    quanan.themdiadiem(getApplicationContext(), "api/DiaDiem/ThemDiaDiem", entity, "application/json; charset=utf-8", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {
                                Boolean result = Boolean.valueOf(new String(responseBody,"UTF-8"));
                                if (result) {
                                    Toast.makeText(getApplicationContext(), "Thành Công", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ThemDiaDiem.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Thất Bại", Toast.LENGTH_LONG).show();
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            prgDialog.dismiss();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            //message = "Kết Nối Thất Bại";
                            Log.d("Failure", String.valueOf(statusCode));
                            Toast.makeText(getApplicationContext(), "Kết Nối Thất Bại", Toast.LENGTH_LONG).show();
                            prgDialog.dismiss();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Vui lòng chọn Loại Hình Địa Điểm và Tên Đường", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra thông tin đã nhập", Toast.LENGTH_LONG).show();
        }


    }
    private void fillDataDanhMuc() {
        listDanhMuc = new ArrayList<DanhMuc>();
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * From DanhMuc",null);
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            int kieu = cursor.getInt(3);
            listDanhMuc.add(new DanhMuc(null, ten,id, kieu));
        }

    }
    private void fillDataThanhPho() {
        listThanhPho = new ArrayList<ThanhPho>();
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * From ThanhPho",null);
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            listThanhPho.add(new ThanhPho(id, ten));
        }

    }
    private void fillDataQuanHuyen() {
        listQuanHuyen = new ArrayList<QuanHuyen>();
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * From QuanHuyen Where MaTP=" +MaThanhPhoThemDiaDiem+ "",null);
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            listQuanHuyen.add(new QuanHuyen(id, ten));
        }

    }
    private void fillDataDuongQuan() {
        listDuongQuan = new ArrayList<DuongQuan>();
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * From DuongQuan Where MaQuan=" +MaQuanHuyenThemDiaDiem+ "",null);
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            listDuongQuan.add(new DuongQuan(id, ten));
        }

    }
    public void PopupMenuDanhMuc(View v, ArrayList<DanhMuc> arrayList) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        for (int i=0;i<arrayList.size();i++) {
            popupMenu.getMenu().add(1, arrayList.get(i).getMaDanhMuc(), 1, arrayList.get(i).getTvDanhMuc());
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                MaDanhMucThemDiaDiem = item.getItemId();
                txtNhapLoaiHinhDiaDiem.setText(item.getTitle());
                return true;
            }
        });
        popupMenu.show();

    }
    public void PopupMenuThanhPho(View v, ArrayList<ThanhPho> arrayList) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        for (int i=0;i<arrayList.size();i++) {
            popupMenu.getMenu().add(1, arrayList.get(i).getMaTP(), 1, arrayList.get(i).getTenTP());
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                MaThanhPhoThemDiaDiem = item.getItemId();
                txtChonThanhPhoThemDiaDiem.setText(item.getTitle());
                MaQuanHuyenThemDiaDiem = -1;
                txtChonQuanThemDiaDiem.setText("Chọn quận");
                return true;
            }
        });
        popupMenu.show();

    }
    public void PopupMenuQuanHuyen(View v, ArrayList<QuanHuyen> arrayList) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        for (int i=0;i<arrayList.size();i++) {
            popupMenu.getMenu().add(1, arrayList.get(i).getMaQuan(), 1, arrayList.get(i).getTenQuan());
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                MaQuanHuyenThemDiaDiem = item.getItemId();
                txtChonQuanThemDiaDiem.setText(item.getTitle());
                MaDuongQuanThemDiaDiem = -1;
                txtChonDuongQuanThemDiaDiem.setText("Chọn đường");
                return true;
            }
        });
        popupMenu.show();

    }
    public void PopupMenuDuongQuan(View v, ArrayList<DuongQuan> arrayList) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        for (int i=0;i<arrayList.size();i++) {
            popupMenu.getMenu().add(1, arrayList.get(i).getMaDuong(), 1, arrayList.get(i).getTenDuong());
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                MaDuongQuanThemDiaDiem = item.getItemId();
                txtChonDuongQuanThemDiaDiem.setText(item.getTitle());
                return true;
            }
        });
        popupMenu.show();

    }
    public void TimePickerDialog(final TextView time, int hour, int minute, boolean is24hour) {
        TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.setText(convert(hourOfDay, minute));
                if (time.getId() == R.id.txtChonGioBatDauMoCuaDiaDiem)
                    giobatdau = pad(hourOfDay)+":"+pad(minute)+":00";
                else
                    gioketthuc = pad(hourOfDay)+":"+pad(minute)+":00";
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timePickerListener, hour, minute, is24hour);

        timePickerDialog.show();
    }
    public String convert(int h, int m) {
        if (h > 12) {
            return pad(h - 12)+":"+pad(m)+" PM";
        }
        else {
            return pad(h)+":"+pad(m)+" AM";
        }
    }
    public String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void loadImagefromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, REQUEST_LOAD_IMAGE);
    }
    private void loadImagefromCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_LOAD_IMAGE && null != data) {
                    // Get the Image from data

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();

                    // Set the Image in ImageView after decoding the String
                    imgHinhAnhThemDiaDiem.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    ChangeImage = true;
                }
                if (requestCode == REQUEST_CAMERA && null != data) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imgHinhAnhThemDiaDiem.setImageBitmap(photo);
                    ChangeImage = true;
                }

            } else {
                Toast.makeText(this, "Chưa chọn hình", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Yêu cầu thất bại", Toast.LENGTH_LONG).show();
        }
    }
    private String getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        String s = Base64.encodeToString(byteArray,Base64.DEFAULT);
        return s;
    }
}
