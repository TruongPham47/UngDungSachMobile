package vn.edu.stu.nodejs_api.Model;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.Xuly.XulyKhachHang;
import vn.edu.stu.nodejs_api.Xuly.XulyNhanVien;
import vn.edu.stu.nodejs_api.adapter.adapter_khachhang;
import vn.edu.stu.nodejs_api.adapter.adapter_nhanvien;
import vn.edu.stu.nodejs_api.control.KhachHang;
import vn.edu.stu.nodejs_api.control.NhanVien;

public class Activity_NhanVien extends AppCompatActivity {
ListView lsNhanVien;
FloatingActionButton fab;
adapter_nhanvien adapterNV;
ArrayList<NhanVien>dsNhanVien=new ArrayList<>();
VolleyUtils volleyUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        volleyUtils=new VolleyUtils(Activity_NhanVien.this,"http://192.168.110.15:3000/api/");

        addControl();
        addEvent();
        getNhanVien();
    }
    private void addControl()
    {
        lsNhanVien=findViewById(R.id.lsMC);
        fab=findViewById(R.id.btnfab);
    }
    private void addEvent()
    {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Activity_NhanVien.this, XulyNhanVien.class), 1);
            }
        });
        lsNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien del=dsNhanVien.get(position);
                deleteKH(del.getManv()+"");
                return false;
            }
        });
        lsNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Activity_NhanVien.this, XulyNhanVien.class);
                NhanVien up=adapterNV.getItem(position);
                intent.putExtra("UPDATE",up);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&resultCode==RESULT_OK)
        {
            NhanVien nhanVien=(NhanVien) data.getSerializableExtra("NHANVIEN");
            insertNV(nhanVien);
        } else if (requestCode==2&resultCode==RESULT_OK) {
            NhanVien nhanVien=(NhanVien) data.getSerializableExtra("NHANVIEN");
            updateKH(nhanVien);
        }
    }

    private void getNhanVien()
    {
        String token = getIntent().getStringExtra("token");
        volleyUtils.makeGetRequestWithToken("getallNV", token,new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    dsNhanVien.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String id = jsonObject.getString("manv");
                        String ten = jsonObject.getString("tennv");
                        String DC = jsonObject.getString("diachinv");
                        int sdt = jsonObject.getInt("sdtnv");
                        String email = jsonObject.getString("emailnv");


                        dsNhanVien.add(new NhanVien(id, ten,DC,sdt,email));
                    }



                    adapterNV=new adapter_nhanvien(Activity_NhanVien.this,R.layout.adapter_nhanvien,dsNhanVien);
                    lsNhanVien.setAdapter(adapterNV);
                    adapterNV.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_NhanVien.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }
    private void insertNV(NhanVien nv)
    {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"manv\":\"" + nv.getManv() + "\"," +
                "\"tennv\":\"" + nv.getTennv() + "\"," +
                "\"diachinv\":\"" + nv.getDiachinv() + "\"," +
                "\"sdtnv\":\"" + nv.getSđt() + "\"," +
                "\"emailnv\":\"" + nv.getEmail() + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("insertNV", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_NhanVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                getNhanVien();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void deleteKH(String id) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"manv\":\"" + id + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("deleteNV/" + id, jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_NhanVien.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getNhanVien();
            }

            @Override
            public void onError(String errorMessage) {

                Log.e("VolleyError", errorMessage);


                if (!errorMessage.contains("Ràng buộc ngoại khóa với bảng phieunhap")) {
                    showToast("Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuNhap");
                } else if (errorMessage.contains("Ràng buộc ngoại khóa với bảng phieuxuat")) {
                    showToast("Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuXuat");
                } else {
                    showToast("Lỗi máy chủ nội bộ");
                }
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(Activity_NhanVien.this, message, Toast.LENGTH_SHORT).show();
    }
    private void updateKH(NhanVien nv) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"manv\":\"" + nv.getManv() + "\"," +
                "\"tennv\":\"" + nv.getTennv() + "\"," +
                "\"diachinv\":\"" + nv.getDiachinv() + "\"," +
                "\"sdtnv\":\"" + nv.getSđt() + "\"," +
                "\"emailnv\":\"" + nv.getEmail() + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("updateNV/"+nv.getManv()+"", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_NhanVien.this, "update thành công", Toast.LENGTH_SHORT).show();
                getNhanVien();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
}