package vn.edu.stu.nodejs_api.Xuly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.KhachHang;
import vn.edu.stu.nodejs_api.control.NhanVien;
import vn.edu.stu.nodejs_api.control.SanPham;

public class XulySanPham extends AppCompatActivity {
EditText ma_sp,ten_sp,gia_sp,soluong_sp,loai_sp,author_sp,kho_sp;
Button btnluu;
SanPham chon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuly_san_pham);
        addCOntrols();
        addEvent();
        if(getIntent().hasExtra("UPDATE"))
        {
            SanPham edit=(SanPham) getIntent().getSerializableExtra("UPDATE");
            ma_sp.setText(edit.getMasp());
            ten_sp.setText(edit.getTensp());
            gia_sp.setText(edit.getGiasp()+"");
            soluong_sp.setText(edit.getSoluong()+"");
            loai_sp.setText(edit.getLoaisach());
            author_sp.setText(edit.getTentacgia());
            kho_sp.setText(edit.getSokho());
        }else{
            chon=new SanPham();
        }
    }
    private void addCOntrols()
    {
        ma_sp=findViewById(R.id.editMasp);
        ten_sp=findViewById(R.id.editTensp);
        gia_sp=findViewById(R.id.editgiasp);
        soluong_sp=findViewById(R.id.editsoluong);
        loai_sp=findViewById(R.id.editloaisach);
        author_sp=findViewById(R.id.edittacgia);
        kho_sp=findViewById(R.id.editKho);
        btnluu=findViewById(R.id.btnluu);

    }
    private void addEvent()
    {
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=ma_sp.getText().toString();
                String name=ten_sp.getText().toString();
                float price=Float.parseFloat(gia_sp.getText().toString());
                int total=Integer.parseInt(soluong_sp.getText().toString());
                String category=loai_sp.getText().toString();
                String author=author_sp.getText().toString();
                String wH= kho_sp.getText().toString();
                SanPham sanPham=new SanPham(id,name,price,total,category,author,wH);
                Intent intent = new Intent();
                intent.putExtra("SANPHAM", sanPham);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}