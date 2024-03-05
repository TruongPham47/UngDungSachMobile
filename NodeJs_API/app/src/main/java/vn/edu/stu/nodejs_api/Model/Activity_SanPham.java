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

import java.util.ArrayList;

import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.Xuly.XulyNhanVien;
import vn.edu.stu.nodejs_api.Xuly.XulySanPham;
import vn.edu.stu.nodejs_api.adapter.adapter_nhanvien;
import vn.edu.stu.nodejs_api.adapter.adapter_sanpham;
import vn.edu.stu.nodejs_api.control.NhanVien;
import vn.edu.stu.nodejs_api.control.SanPham;

public class Activity_SanPham extends AppCompatActivity {

    ListView lsSanPham;
    FloatingActionButton fab;
    adapter_sanpham adapterSP;
    ArrayList<SanPham>dsSanPham=new ArrayList<>();
    VolleyUtils volleyUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        //192.168.110.15
        volleyUtils=new VolleyUtils(Activity_SanPham.this,"http://192.168.110.15:3000/api/");
        addControl();
        addEvent();
        getSanPham();
    }
    private void addControl()
    {
        lsSanPham=findViewById(R.id.lsMC);
        fab=findViewById(R.id.btnfab);
    }
    private void addEvent()
    {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Activity_SanPham.this, XulySanPham.class), 1);
            }
        });
        lsSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham del=dsSanPham.get(position);
                deleteSP(del.getMasp()+"");
                return false;
            }
        });
        lsSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Activity_SanPham.this, XulySanPham.class);
                SanPham up=adapterSP.getItem(position);
                intent.putExtra("UPDATE",up);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&resultCode==RESULT_OK)
        {
            SanPham sanpham=(SanPham) data.getSerializableExtra("SANPHAM");
           insertNV(sanpham);
        } else if (requestCode==2&resultCode==RESULT_OK) {

            SanPham sanpham=(SanPham) data.getSerializableExtra("SANPHAM");
            updateSP(sanpham);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getSanPham() {
        String token = getIntent().getStringExtra("token");
        String ep="khoA";

            volleyUtils.makeGetRequestWithToken("getallSP/"+ep, token,new  VolleyUtils.VolleyResponseListener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        dsSanPham.clear();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            // Parse data from JSON and create a MonAn object
                            String id = jsonObject.getString("masp");
                            String ten = jsonObject.getString("tensp");
                            float gia = jsonObject.getInt("giasp");
                            int sluong = jsonObject.getInt("soluong");
                            String loai = jsonObject.getString("loaisach");
                            String tentg = jsonObject.getString("tentacgia");
                            String wkho = jsonObject.getString("sokho");


                            // Add the MonAn object to your list
                            dsSanPham.add(new SanPham(id, ten,gia,sluong,loai,tentg,wkho));
                        }

                        adapterSP=new adapter_sanpham(Activity_SanPham.this,R.layout.apdater_sanpham,dsSanPham);
                        lsSanPham.setAdapter(adapterSP);
                        adapterSP.notifyDataSetChanged();


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Activity_SanPham.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();


                    }

                }

                @Override
                public void onError(String errorMessage) {

                }
            });


    }
    private void insertNV(SanPham sp)
    {String token = getIntent().getStringExtra("token");
        // Convert the KhachHang object to a JSON-like string
        String jsonBody = "{" +
                "\"masp\":\"" + sp.getMasp() + "\"," +
                "\"tensp\":\"" + sp.getTensp() + "\"," +
                "\"giasp\":\"" + sp.getGiasp() + "\"," +
                "\"soluong\":\"" + sp.getSoluong() + "\"," +
                "\"loaisach\":\"" + sp.getLoaisach() + "\"," +
                "\"tentacgia\":\"" + sp.getTentacgia() + "\"," +
                "\"sokho\":\"" + sp.getSokho() + "\"" +
                "}";

        // Make the POST request using VolleyUtils
        volleyUtils.makePostRequestWithToken("insertSP", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_SanPham.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                getSanPham();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void deleteSP(String id) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"masp\":\"" + id + "\"" +
                "}";

        // Make the POST request using VolleyUtils
        volleyUtils.makePostRequestWithToken("deleteSP/" + id, jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_SanPham.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getSanPham();
            }

            @Override
            public void onError(String errorMessage) {


                Log.e("VolleyError", errorMessage);




                if (!errorMessage.contains("Ràng buộc ngoại khóa với bảng DonHang")) {

                    Toast.makeText(Activity_SanPham.this, "Không thể xóa vì có ràng buộc ngoại khóa với bảng DonHang", Toast.LENGTH_SHORT).show();
                } else if (errorMessage.contains("Ràng buộc ngoại khóa với bảng PhieuNhap")) {

                    Toast.makeText(Activity_SanPham.this, "Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuNhap", Toast.LENGTH_SHORT).show();
                } else if (errorMessage.contains("Ràng buộc ngoại khóa với bảng PhieuXuat")) {

                    Toast.makeText(Activity_SanPham.this, "Không thể xóa vì có ràng buộc ngoại khóa với bảng PhieuXuat", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(Activity_SanPham.this, "Error:đã sãy ra lỗi khi xoá " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    private void updateSP(SanPham sp) {
        String token = getIntent().getStringExtra("token");
        // Convert the KhachHang object to a JSON-like string
        String jsonBody = "{" +
                "\"masp\":\"" + sp.getMasp() + "\"," +
                "\"tensp\":\"" + sp.getTensp() + "\"," +
                "\"giasp\":\"" + sp.getGiasp() + "\"," +
                "\"soluong\":\"" + sp.getSoluong() + "\"," +
                "\"loaisach\":\"" + sp.getLoaisach() + "\"," +
                "\"tentacgia\":\"" + sp.getTentacgia() + "\"," +
                "\"sokho\":\"" + sp.getSokho() + "\"" +
                "}";


        // Make the POST request using VolleyUtils
        volleyUtils.makePostRequestWithToken("updateSP/"+sp.getMasp()+"", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_SanPham.this, "update thành công", Toast.LENGTH_SHORT).show();
                getSanPham();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }


}