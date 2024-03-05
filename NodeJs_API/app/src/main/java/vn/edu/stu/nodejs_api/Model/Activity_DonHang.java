package vn.edu.stu.nodejs_api.Model;

import static vn.edu.stu.nodejs_api.DAO.DateFormatUtils.formatDateTime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.nodejs_api.DAO.DateFormatUtils;
import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.Xuly.XuLyDonHang;
import vn.edu.stu.nodejs_api.Xuly.XulyKhoHang;
import vn.edu.stu.nodejs_api.adapter.adapter_donhang;
import vn.edu.stu.nodejs_api.adapter.adapter_khohang;
import vn.edu.stu.nodejs_api.adapter.adapter_nhanvien;
import vn.edu.stu.nodejs_api.control.DonHang;
import vn.edu.stu.nodejs_api.control.KhoHang;
import vn.edu.stu.nodejs_api.control.NhanVien;

public class Activity_DonHang extends AppCompatActivity {
    ListView lsDonHang;
    FloatingActionButton fab;
    adapter_donhang adapterDH;
    ArrayList<DonHang> dsDonHang=new ArrayList<>();
    VolleyUtils volleyUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        volleyUtils=new VolleyUtils(Activity_DonHang.this,"http://192.168.110.15:3000/api/");
        addControl();
        addEvent();
        getDonHang();
    }

    private void addEvent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Activity_DonHang.this, XuLyDonHang.class), 1);

            }
        });
        lsDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Activity_DonHang.this, XuLyDonHang.class);
                DonHang up=adapterDH.getItem(position);
                intent.putExtra("UPDATE",up);
                startActivityForResult(intent,2);
            }
        });

        lsDonHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DonHang donHang=dsDonHang.get(position);
                deleteDH(donHang.getMadh()+"");
                return false;
            }
        });
    }

    private void addControl()
    {
        lsDonHang=findViewById(R.id.lsMC);
        fab=findViewById(R.id.btnfab);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {

            DonHang donHang = (DonHang) data.getSerializableExtra("DONHANG");
            insertDH(donHang);
        } else if(requestCode==2&&resultCode==RESULT_OK)
        {

            DonHang donhang=(DonHang) data.getSerializableExtra("DONHANG");
            updateDH(donhang);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


//private  void gẹtWT()
//{
//    volleyUtils.makegetRequesttoken("getallDH",);
//}
    private void getDonHang() {
        String token = getIntent().getStringExtra("token");
        volleyUtils.makeGetRequestWithToken("getallDH", token,new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dsDonHang.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Parse data from JSON
                        String madh = jsonObject.getString("madh");

                        String ngaydathangString = jsonObject.getString("ngaydathang");
                        String ngaygiaohang = jsonObject.getString("ngaygiaohang");
                        int soluong = jsonObject.getInt("soluong");
                        int tongtien = jsonObject.getInt("tongtien");
                        String makh = jsonObject.getString("makh");
                        String masp = jsonObject.getString("masp");

                        try {

                            Date ngaydathangDate = DateFormatUtils.dateFromDateTimeString(ngaydathangString);


                            String formattedDateTime = DateFormatUtils.formatDateTime(ngaydathangDate);


                            dsDonHang.add(new DonHang(madh ,ngaygiaohang,formattedDateTime,  tongtien,soluong, makh, masp));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Activity_DonHang.this, "Error parsing date: " + e.toString(), Toast.LENGTH_LONG).show();

                        }
                    }

                    adapterDH = new adapter_donhang(Activity_DonHang.this, R.layout.adapter_donhang, dsDonHang);
                    lsDonHang.setAdapter(adapterDH);
                    adapterDH.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_DonHang.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();

                }




            }

            @Override
            public void onError(String errorMessage) {

            }
        });


    }
    private void insertDH(DonHang dh)
    {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"madh\":\"" + dh.getMadh() + "\"," +
                "\"ngaydathang\":\"" + dh.getNgaydathang() + "\"," +
                "\"ngaygiaohang\":\"" + dh.getNgaygiaohang() + "\"," +
                "\"soluong\":\"" + dh.getSoluong() + "\"," +
                "\"tongtien\":\"" + dh.getTongtien() + "\"," +
                "\"makh\":\"" + dh.getMakh() + "\"," +
                "\"masp\":\"" + dh.getMasp() + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("insertDH", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_DonHang.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                getDonHang();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }
    private void updateDH(DonHang dh) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"madh\":\"" + dh.getMadh() + "\"," +
                "\"ngaydathang\":\"" + dh.getNgaydathang() + "\"," +
                "\"ngaygiaohang\":\"" + dh.getNgaygiaohang() + "\"," +
                "\"soluong\":\"" + dh.getSoluong() + "\"," +
                "\"tongtien\":\"" + dh.getTongtien() + "\"," +
                "\"makh\":\"" + dh.getMakh() + "\"," +
                "\"masp\":\"" + dh.getMasp() + "\"" +
                "}";



        volleyUtils.makePostRequestWithToken("updateDH/"+dh.getMadh()+"", jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_DonHang.this, "update thành công", Toast.LENGTH_SHORT).show();
                getDonHang();
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
                "\"madh\":\"" + id + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("deleteDH/" + id, jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Activity_DonHang.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getDonHang();
            }

            @Override
            public void onError(String errorMessage) {
                if (errorMessage.contains("Ràng buộc ngoại khóa")) {
                    Log.d("VolleyError", errorMessage);

                    Toast.makeText(Activity_DonHang.this, "Đã xảy ra lỗi khi xóa DonHang", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(Activity_DonHang.this, "Không thể xóa vì có ràng buộc ngoại khóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}