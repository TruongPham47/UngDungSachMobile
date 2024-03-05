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
import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.Xuly.XulyKhachHang;
import vn.edu.stu.nodejs_api.control.KhachHang;
import vn.edu.stu.nodejs_api.adapter.adapter_khachhang;


public class MainActivity extends AppCompatActivity {
    ListView lsKhachHang;
     adapter_khachhang adapterKH;
     ArrayList<KhachHang>dsKhachHang=new ArrayList<>();
     FloatingActionButton fab;
VolleyUtils volleyUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volleyUtils=new VolleyUtils(MainActivity.this,"http://192.168.110.15:3000/api/");
        addControl();
        addEvent();
       // getKhachHang();


       getjWT();
    }
    private void addControl()
    {
        lsKhachHang=findViewById(R.id.lsMC);
        fab=findViewById(R.id.btnfab);
    }
    private void addEvent()
    {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, XulyKhachHang.class), 1);
            }
        });
        lsKhachHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                KhachHang del=dsKhachHang.get(position);
                deleteKH(del.getMakh()+"");
                return false;
            }
        });
        lsKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,XulyKhachHang.class);
                KhachHang up=adapterKH.getItem(position);
                intent.putExtra("UPDATE",up);
                startActivityForResult(intent,2);
            }
        });
    }

    //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
    private void getjWT(  )
    {


        String token = getIntent().getStringExtra("token");

        volleyUtils.makeGetRequestWithToken("getallKH", token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    dsKhachHang.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Parse data from JSON and create a MonAn object
                        String id = jsonObject.getString("makh");
                        String ten = jsonObject.getString("tenkh");
                        String DC = jsonObject.getString("diachikh");
                        int sdt = jsonObject.getInt("sdtkh");
                        String email = jsonObject.getString("emailkh");


                        dsKhachHang.add(new KhachHang(id, ten, DC, sdt, email));
                    }


                    adapterKH = new adapter_khachhang(MainActivity.this, R.layout.adapter_khachhang, dsKhachHang);
                    lsKhachHang.setAdapter(adapterKH);
                    adapterKH.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();


                }
            }


                @Override
            public void onError(String errorMessage) {
                // Handle the error here
            }
        });




    }

    private void getKhachHang()
    {

        volleyUtils.makeGetRequest("getallKH", new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    dsKhachHang.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // Parse data from JSON and create a MonAn object
                        String id = jsonObject.getString("makh");
                        String ten = jsonObject.getString("tenkh");
                        String DC = jsonObject.getString("diachikh");
                        int sdt = jsonObject.getInt("sdtkh");
                        String email = jsonObject.getString("emailkh");


                        dsKhachHang.add(new KhachHang(id, ten,DC,sdt,email));
                    }



                    adapterKH=new adapter_khachhang(MainActivity.this,R.layout.adapter_khachhang,dsKhachHang);
                    lsKhachHang.setAdapter(adapterKH);
                    adapterKH.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==RESULT_OK)
        {

            KhachHang khachHang=(KhachHang)data.getSerializableExtra("KHACHHANG");
//            insertKH(khachHang);
            insertJWT(khachHang);
        }else if(requestCode==2&&resultCode==RESULT_OK)
        {

            KhachHang khachHang=(KhachHang)data.getSerializableExtra("KHACHHANG");
            updateKH(khachHang);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
private void insertJWT(KhachHang kh)
{
    String token = getIntent().getStringExtra("token");
    String jsonBody = "{" +
            "\"makh\":\"" + kh.getMakh() + "\"," +
            "\"tenkh\":\"" + kh.getTenkh() + "\"," +
            "\"diachikh\":\"" + kh.getDiachikh() + "\"," +
            "\"sdtkh\":\"" + kh.getSđt() + "\"," +
            "\"emailkh\":\"" + kh.getEmail() + "\"" +
            "}";
    volleyUtils.makePostRequestWithToken("insertKH", jsonBody, token, new VolleyUtils.VolleyResponseListener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            getjWT();
        }

        @Override
        public void onError(String errorMessage) {

        }
    });
}
    private void insertKH(KhachHang kh) {
        // Convert the KhachHang object to a JSON-like string
        String jsonBody = "{" +
                "\"makh\":\"" + kh.getMakh() + "\"," +
                "\"tenkh\":\"" + kh.getTenkh() + "\"," +
                "\"diachikh\":\"" + kh.getDiachikh() + "\"," +
                "\"sdtkh\":\"" + kh.getSđt() + "\"," +
                "\"emailkh\":\"" + kh.getEmail() + "\"" +
                "}";


        volleyUtils.makePostRequest("insertKH", jsonBody, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                getKhachHang();
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
                "\"makh\":\"" + id + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("deleteKH/" + id, jsonBody,token, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                getjWT();
            }

            @Override
            public void onError(String errorMessage) {

                Log.e("VolleyError", errorMessage);
                if (!errorMessage.contains("Ràng buộc ngoại khóa với bảng DonHang")) {
                    Toast.makeText(MainActivity.this, "Không thể xóa vì có ràng buộc ngoại khóa với bảng DonHang", Toast.LENGTH_SHORT).show();
                } else if (errorMessage.contains("Không tìm thấy khách hàng để xóa")) {
                    Toast.makeText(MainActivity.this, "Không tìm thấy khách hàng để xóa", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle other errors
                    Toast.makeText(MainActivity.this, "Lỗi máy chủ nội bộ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateKH(KhachHang kh) {
        String token = getIntent().getStringExtra("token");
        String jsonBody = "{" +
                "\"makh\":\"" + kh.getMakh() + "\"," +
                "\"tenkh\":\"" + kh.getTenkh() + "\"," +
                "\"diachikh\":\"" + kh.getDiachikh() + "\"," +
                "\"sdtkh\":\"" + kh.getSđt() + "\"," +
                "\"emailkh\":\"" + kh.getEmail() + "\"" +
                "}";


        volleyUtils.makePostRequestWithToken("updateKH/"+kh.getMakh()+"",token, jsonBody, new VolleyUtils.VolleyResponseListener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "update thành công", Toast.LENGTH_SHORT).show();
                getjWT();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("VolleyError", errorMessage);
            }
        });
    }





}