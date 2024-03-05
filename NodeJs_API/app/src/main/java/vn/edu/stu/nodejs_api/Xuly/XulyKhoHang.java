package vn.edu.stu.nodejs_api.Xuly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.KhoHang;

public class XulyKhoHang extends AppCompatActivity {
    EditText sokho,tongsl,slnhap,slxuat;
    Button btnLuu;
    KhoHang chon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuly_kho_hang);
        addControl();
        addEvent();
        if (getIntent().hasExtra("UPDATE")) {
            KhoHang edit = (KhoHang) getIntent().getSerializableExtra("UPDATE");
            sokho.setText(edit.getSokho());
            tongsl.setText(edit.getTong()+"");
            slnhap.setText(edit.getNhap()+"");
            slxuat.setText(edit.getXuat() + "");

        } else {
            chon = new KhoHang();
        }
    }
    private void addControl()
    {
        sokho=findViewById(R.id.editSoKho);
        tongsl=findViewById(R.id.editTongSL);
        slnhap=findViewById(R.id.editSLNhap);
        slxuat=findViewById(R.id.editSLXuat);
        btnLuu=findViewById(R.id.btnluu);

    }
    private void addEvent()
    {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soKho = sokho.getText().toString();
                int tongSL = Integer.parseInt(tongsl.getText().toString());
                int SLnhap = Integer.parseInt(slnhap.getText().toString());
                int SLxuat = Integer.parseInt(slxuat.getText().toString());

                KhoHang khoHang=new KhoHang( soKho,tongSL,SLnhap,SLxuat);

                Intent intent = new Intent();
                intent.putExtra("KHOHANG", khoHang);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}