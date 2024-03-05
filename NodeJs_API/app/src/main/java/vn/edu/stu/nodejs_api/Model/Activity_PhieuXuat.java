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
import java.util.Date;

import vn.edu.stu.nodejs_api.DAO.DateFormatUtils;
import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.Xuly.XuLyPhieuNhap;
import vn.edu.stu.nodejs_api.Xuly.XuLyPhieuXuat;
import vn.edu.stu.nodejs_api.adapter.adapter_phieunhap;
import vn.edu.stu.nodejs_api.adapter.adapter_phieuxuat;
import vn.edu.stu.nodejs_api.control.PhieuNhap;
import vn.edu.stu.nodejs_api.control.PhieuXuat;

public class Activity_PhieuXuat extends AppCompatActivity {
    ListView lsPhieuXuat;
    FloatingActionButton fab;
    adapter_phieuxuat adapterPX;
    ArrayList<PhieuXuat> dsPhieuXuat=new ArrayList<>();
    VolleyUtils volleyUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_xuat);

        volleyUtils=new VolleyUtils(Activity_PhieuXuat.this,"http://192.168.110.15:3000/api/");
        addControl();
        addEvent();
        getPhieuXuat();
    }

    private void addEvent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Activity_PhieuXuat.this, XuLyPhieuXuat.class), 1);
            }
        });
        lsPhieuXuat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Activity_PhieuXuat.this, XuLyPhieuXuat.class);
                PhieuXuat up=adapterPX.getItem(position);
                intent.putExtra("UPDATE",up);
                startActivityForResult(intent,2);
            }
        });
        lsPhieuXuat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PhieuXuat phieuXuat=dsPhieuXuat.get(position);
                deleteDH(phieuXuat.getSopx()+"");
                return false;
            }
        });
    }

    private void getPhieuXuat() {
        String token = getIntent().getStringExtra("token");
        volleyUtils.makeGetRequestWithToken("getallPX", token,new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dsPhieuXuat.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Parse data from JSON
                        String sopx = jsonObject.getString("sopx");
                        String ngaypx = jsonObject.getString("ngaypx");

                        int soluongpx = jsonObject.getInt("soluongpx");
                        String masp = jsonObject.getString("masp");
                        String manv = jsonObject.getString("manv");

                        try {

                            Date ngaydathangDate = DateFormatUtils.dateFromDateTimeString(ngaypx);


                            String formattedDateTime = DateFormatUtils.formatDate(ngaydathangDate);


                            dsPhieuXuat.add(new PhieuXuat(sopx, manv, masp, formattedDateTime, soluongpx));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Activity_PhieuXuat.this, "Error parsing date: " + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }

                    adapterPX = new adapter_phieuxuat(Activity_PhieuXuat.this, R.layout.adapter_phieuxxuat, dsPhieuXuat);
                    lsPhieuXuat.setAdapter(adapterPX);
                    adapterPX.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_PhieuXuat.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();

                }




            }

            @Override
            public void onError(String errorMessage) {

            }
        });


    }

    private void addControl()
    {
        lsPhieuXuat=findViewById(R.id.lsMC);
        fab=findViewById(R.id.btnfab);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {

            PhieuXuat phieuXuat = (PhieuXuat) data.getSerializableExtra("PHIEUXUAT");
            insertPX(phieuXuat);
        } else if(requestCode==2&&resultCode==RESULT_OK)
        {

            PhieuXuat phieuXuat=(PhieuXuat) data.getSerializableExtra("PHIEUXUAT");
            updatePX(phieuXuat);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertPX(PhieuXuat px)
    {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sopx\":\"" + px.getSopx() + "\"," +
                "\"ngaypx\":\"" + px.getNgaypx() + "\"," +
                "\"soluongpx\":\"" + px.getSoluongpx() + "\"," +
                "\"masp\":\"" + px.getMasp() + "\"," +
                "\"manv\":\"" + px.getManv() + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("insertPX", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_PhieuXuat.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                getPhieuXuat();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void updatePX(PhieuXuat px) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sopx\":\"" + px.getSopx() + "\"," +
                "\"ngaypx\":\"" + px.getNgaypx() + "\"," +
                "\"soluongpx\":\"" + px.getSoluongpx() + "\"," +
                "\"masp\":\"" + px.getMasp() + "\"," +
                "\"manv\":\"" + px.getManv() + "\"" +
                "}";



        volleyUtils.makePostRequestWithToken("updatePX/"+px.getSopx()+"", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_PhieuXuat.this, "update thành công", Toast.LENGTH_SHORT).show();
                getPhieuXuat();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void deleteDH  (String id) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sopx\":\"" + id + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("deletePX/" + id, jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_PhieuXuat.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getPhieuXuat();
            }

            @Override
            public void onError(String errorMessage) {
                if (errorMessage.contains("Ràng buộc ngoại khóa")) {
                    Log.d("VolleyError", errorMessage);

                    Toast.makeText(Activity_PhieuXuat.this, "Đã xảy ra lỗi khi xóa DonHang", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(Activity_PhieuXuat.this, "Không thể xóa vì có ràng buộc ngoại khóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}