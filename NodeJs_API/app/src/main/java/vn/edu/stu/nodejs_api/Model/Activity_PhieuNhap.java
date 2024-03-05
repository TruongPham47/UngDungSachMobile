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
import vn.edu.stu.nodejs_api.Xuly.XuLyDonHang;
import vn.edu.stu.nodejs_api.Xuly.XuLyPhieuNhap;
import vn.edu.stu.nodejs_api.adapter.adapter_donhang;
import vn.edu.stu.nodejs_api.adapter.adapter_phieunhap;
import vn.edu.stu.nodejs_api.control.DonHang;
import vn.edu.stu.nodejs_api.control.PhieuNhap;

public class Activity_PhieuNhap extends AppCompatActivity {
    ListView lsPhieuNhap;
    FloatingActionButton fab;
    adapter_phieunhap adapterPN;
    ArrayList<PhieuNhap> dsPhieuNhap=new ArrayList<>();
    VolleyUtils volleyUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_nhap);

        volleyUtils=new VolleyUtils(Activity_PhieuNhap.this,"http://192.168.110.15:3000/api/");
        addControl();
        addEvent();
        getPhieuNhap();
    }

    private void addEvent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Activity_PhieuNhap.this, XuLyPhieuNhap.class), 1);
            }
        });
        lsPhieuNhap.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PhieuNhap phieuNhap=dsPhieuNhap.get(position);
                deleteDH(phieuNhap.getSopn()+"");
                return false;
            }
        });
        lsPhieuNhap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Activity_PhieuNhap.this, XuLyPhieuNhap.class);
                PhieuNhap up=adapterPN.getItem(position);
                intent.putExtra("UPDATE",up);
                startActivityForResult(intent,2);
            }
        });
    }

    private void getPhieuNhap() {
        String token = getIntent().getStringExtra("token");
        volleyUtils.makeGetRequestWithToken("getallPN", token,new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dsPhieuNhap.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Parse data from JSON
                        String sopn = jsonObject.getString("sopn");
                        String ngaypn = jsonObject.getString("ngaypn");

                        int soluongpn = jsonObject.getInt("soluongpn");
                        String masp = jsonObject.getString("masp");
                        String manv = jsonObject.getString("manv");

                        try {

                            Date ngaydathangDate = DateFormatUtils.dateFromDateTimeString(ngaypn);


                            String formattedDateTime = DateFormatUtils.formatDate(ngaydathangDate);


                            dsPhieuNhap.add(new PhieuNhap(sopn, manv, masp, formattedDateTime, soluongpn));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Activity_PhieuNhap.this, "Error parsing date: " + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }

                    adapterPN = new adapter_phieunhap(Activity_PhieuNhap.this, R.layout.adapter_phieunhap, dsPhieuNhap);
                    lsPhieuNhap.setAdapter(adapterPN);
                    adapterPN.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_PhieuNhap.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();

                }




            }

            @Override
            public void onError(String errorMessage) {

            }
        });


    }

    private void addControl()
    {
        lsPhieuNhap=findViewById(R.id.lsMC);
        fab=findViewById(R.id.btnfab);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {

            PhieuNhap phieuNhap = (PhieuNhap) data.getSerializableExtra("PHIEUNHAP");
            insertPN(phieuNhap);
        } else if(requestCode==2&&resultCode==RESULT_OK)
        {

            PhieuNhap phieuNhap=(PhieuNhap) data.getSerializableExtra("PHIEUNHAP");
            updatePN(phieuNhap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertPN(PhieuNhap pn)
    {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sopn\":\"" + pn.getSopn() + "\"," +
                "\"ngaypn\":\"" + pn.getNgaypn() + "\"," +
                "\"soluongpn\":\"" + pn.getSoluongpn() + "\"," +
                "\"masp\":\"" + pn.getMasp() + "\"," +
                "\"manv\":\"" + pn.getManv() + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("insertPN", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_PhieuNhap.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                getPhieuNhap();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void updatePN(PhieuNhap pn) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"sopn\":\"" + pn.getSopn() + "\"," +
                "\"ngaypn\":\"" + pn.getNgaypn() + "\"," +
                "\"soluongpn\":\"" + pn.getSoluongpn() + "\"," +
                "\"masp\":\"" + pn.getMasp() + "\"," +
                "\"manv\":\"" + pn.getManv() + "\"" +
                "}";



        volleyUtils.makePostRequestWithToken("updatePN/"+pn.getSopn()+"", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_PhieuNhap.this, "update thành công", Toast.LENGTH_SHORT).show();
                getPhieuNhap();
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
                "\"sopn\":\"" + id + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("deletePN/" + id, jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_PhieuNhap.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getPhieuNhap();
            }

            @Override
            public void onError(String errorMessage) {
                if (errorMessage.contains("Ràng buộc ngoại khóa")) {
                    Log.d("VolleyError", errorMessage);

                    Toast.makeText(Activity_PhieuNhap.this, "Đã xảy ra lỗi khi xóa DonHang", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(Activity_PhieuNhap.this, "Không thể xóa vì có ràng buộc ngoại khóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}