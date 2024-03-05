package vn.edu.stu.nodejs_api.Model;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.DAO.jwtUtill;
import vn.edu.stu.nodejs_api.R;

public class menu extends AppCompatActivity {
ImageButton  ImgBtnKH,ImgBtnNV,ImgBtnSP,ImgBtnWH,ImgBtnDH,ImgBtnPN,ImgBtnPX;
VolleyUtils volleyUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        addControls();
        addEvent();

    }
    private void addControls()
    {
        ImgBtnNV=findViewById(R.id.btnNV);
        ImgBtnKH=findViewById(R.id.btnKH);
        ImgBtnSP=findViewById(R.id.btnSP);
        ImgBtnWH=findViewById(R.id.btnWH);
        ImgBtnDH=findViewById(R.id.btnDH);
        ImgBtnPN=findViewById(R.id.btnPN);
        ImgBtnPX=findViewById(R.id.btnPX);
    }



    private void addEvent()
    {
        ImgBtnKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                Intent intent = new Intent(menu.this, MainActivity.class);
                String token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
        ImgBtnNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this,Activity_NhanVien.class);
                String token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
        ImgBtnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this,Activity_SanPham.class);
                String token = getIntent().getStringExtra("token");
//                Toast.makeText(menu.this, token, Toast.LENGTH_SHORT).show();
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
        ImgBtnWH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this,Activity_KhoHang.class);
                String token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
        ImgBtnDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this, Activity_DonHang.class);
                String token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
        ImgBtnPN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this, Activity_PhieuNhap.class);                String token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
        ImgBtnPX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(menu.this, Activity_PhieuXuat.class);
                String token = getIntent().getStringExtra("token");
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }
}