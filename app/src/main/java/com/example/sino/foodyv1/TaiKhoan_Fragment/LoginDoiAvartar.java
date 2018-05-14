package com.example.sino.foodyv1.TaiKhoan_Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sino.foodyv1.Client;
import com.example.sino.foodyv1.MainActivity;
import com.example.sino.foodyv1.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.extras.Base64;

import static com.example.sino.foodyv1.MainActivity.tk;

/**
 * Created by SINO on 5/23/2017.
 */

public class LoginDoiAvartar extends Activity {
    private static int REQUEST_LOAD_IMAGE = 1;
    private static int REQUEST_CAMERA = 2;
    private static Boolean ChangeImage = false;

    ProgressDialog prgDialog;
    ImageView back_doianhdaidien;
    LinearLayout loHinhDaiDien;
    ImageView imgHinhDaiDien;
    LinearLayout loHinhBia;
    Button btn_luuthaydoi_hinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doiavartar);

        back_doianhdaidien = (ImageView) findViewById(R.id.back_doianhdaidien);
        loHinhDaiDien = (LinearLayout) findViewById(R.id.loHinhDaiDien);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
        loHinhBia = (LinearLayout) findViewById(R.id.loHinhBia);
        btn_luuthaydoi_hinh = (Button) findViewById(R.id.btn_luuthaydoi_hinh);

        back_doianhdaidien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loHinhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuImage(loHinhDaiDien);
            }
        });
        loHinhBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenuImage(loHinhBia);
            }
        });
        btn_luuthaydoi_hinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ChangeImage) {
                    String image = getByteArrayFromImageView(imgHinhDaiDien);
                    prgDialog = new ProgressDialog(LoginDoiAvartar.this);
                    prgDialog.setMessage("Vui lòng chờ ...");
                    prgDialog.setCancelable(false);
                    getChangeImage(tk.getTaiKhoan(), image);
                }
            }
        });
    }
    public void getChangeImage(String username, final String image) {
        prgDialog.show();
        StringEntity entity = null;
        try {
            JSONObject object = new JSONObject();
            object.put("TaiKhoan", username);
            object.put("HinhDaiDien", image);
            entity = new StringEntity(object.toString());
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Client doihinh = new Client();
        doihinh.changeimage(getApplicationContext(), "api/TaiKhoan/DoiHinhDaiDien", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Boolean result = Boolean.valueOf(new String(responseBody,"UTF-8"));
                    if (result) {
                        Toast.makeText(getApplicationContext(), "Thành Công", Toast.LENGTH_LONG).show();
                        //Cập nhật hình đại diện
                        byte[] byteArray =  Base64.decode(image, Base64.DEFAULT) ;
                        tk.setHinhDaiDien(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
                        //Về trang chủ
                        Intent intent = new Intent(LoginDoiAvartar.this, MainActivity.class);
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
    public void PopupMenuImage(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenu().add(1, 1, 1, "Chọn hình từ thư viện");
        popupMenu.getMenu().add(1, 2, 1, "Chụp ảnh");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == 1) {
                    Toast.makeText(getApplicationContext(), "Chọn ảnh từ thư viện", Toast.LENGTH_SHORT).show();
                    loadImagefromGallery();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Chụp ảnh", Toast.LENGTH_SHORT).show();
                    loadImagefromCamera();
                }
                return true;
            }
        });
        popupMenu.show();

    }
    public void loadImagefromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, REQUEST_LOAD_IMAGE);
    }
    private void loadImagefromCamera()
    {
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
                    imgHinhDaiDien.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    ChangeImage = true;
                }
                if (requestCode == REQUEST_CAMERA && null != data) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    imgHinhDaiDien.setImageBitmap(photo);
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
