package vn.edu.stu.nodejs_api.Model;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Key;
import java.util.ArrayList;

import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.Xuly.XulyKhoHang;
import vn.edu.stu.nodejs_api.Xuly.XulySanPham;
import vn.edu.stu.nodejs_api.adapter.adapter_khohang;
import vn.edu.stu.nodejs_api.adapter.adapter_sanpham;
import vn.edu.stu.nodejs_api.control.KhachHang;
import vn.edu.stu.nodejs_api.control.KhoHang;
import vn.edu.stu.nodejs_api.control.SanPham;

public class Activity_KhoHang extends AppCompatActivity {

    ListView lsKhoHang;
    FloatingActionButton fab;
    adapter_khohang apdaterWH;
    ArrayList<KhoHang>dsKhoHang=new ArrayList<>();
    VolleyUtils volleyUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kho_hang);
        volleyUtils=new VolleyUtils(Activity_KhoHang.this,"http://192.168.110.15:3000/api/");
        addControl();
        addEvent();
        getKhoHang();
    }
    private void addControl()
    {
        lsKhoHang=findViewById(R.id.lsMC);
        fab=findViewById(R.id.btnfab);
    }
    private void addEvent()
    {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Activity_KhoHang.this, XulyKhoHang.class), 1);
            }
        });
        lsKhoHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Activity_KhoHang.this, XulyKhoHang.class);
                KhoHang up=apdaterWH.getItem(position);
                intent.putExtra("UPDATE",up);
                startActivityForResult(intent,2);
            }
        });
        lsKhoHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               KhoHang khoHang=dsKhoHang.get(position);
                deleteWH(khoHang.getSokho()+"");
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {

            KhoHang khoHang = (KhoHang) data.getSerializableExtra("KHOHANG");
            insertWH(khoHang);
        } else if(requestCode==2&&resultCode==RESULT_OK)
        {

            KhoHang khoHang=(KhoHang)data.getSerializableExtra("KHOHANG");
            updateWH(khoHang);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getKhoHang() {

        String token = getIntent().getStringExtra("token");
        volleyUtils.makeGetRequestWithToken("getallWH/", token,new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    dsKhoHang.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String id = jsonObject.getString("sokho");
                        int tong = jsonObject.getInt("tongsoluong");
                        int slN = jsonObject.getInt("soluongnhap");
                        int slX = jsonObject.getInt("soluongxuat");


                        dsKhoHang.add(new KhoHang(id, tong,slN,slX));
                    }

                    apdaterWH=new adapter_khohang(Activity_KhoHang.this,R.layout.apdater_khohang,dsKhoHang);
                    lsKhoHang.setAdapter(apdaterWH);
                    apdaterWH.notifyDataSetChanged();


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_KhoHang.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onError(String errorMessage) {

            }
        });


    }
    private void insertWH(KhoHang wh)
    {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sokho\":\"" + wh.getSokho() + "\"," +
                "\"tongsoluong\":\"" + wh.getTong() + "\"," +
                "\"soluongnhap\":\"" + wh.getNhap() + "\"," +
                "\"soluongxuat\":\"" + wh.getXuat() + "\"" +
                "}";

        // Make the POST request using VolleyUtils
        volleyUtils.makePostRequestWithToken("insertWH", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_KhoHang.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                getKhoHang();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void updateWH(KhoHang wh) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sokho\":\"" + wh.getSokho() + "\"," +
                "\"tongsoluong\":\"" + wh.getTong() + "\"," +
                "\"soluongnhap\":\"" + wh.getNhap() + "\"," +
                "\"soluongxuat\":\"" + wh.getXuat() + "\"" +
                "}";



        volleyUtils.makePostRequestWithToken("updateWH/"+wh.getSokho()+"", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_KhoHang.this, "update thành công", Toast.LENGTH_SHORT).show();
                getKhoHang();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void deleteWH  (String id) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sokho\":\"" + id + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("deleteWH/" + id, jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_KhoHang.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getKhoHang();
            }

            @Override
            public void onError(String errorMessage) {
                if (errorMessage.contains("Ràng buộc ngoại khóa")) {
                    Log.d("VolleyError", errorMessage);

                    Toast.makeText(Activity_KhoHang.this, "Đã xảy ra lỗi khi xóa KhoHang", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(Activity_KhoHang.this, "Không thể xóa vì có ràng buộc ngoại khóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}