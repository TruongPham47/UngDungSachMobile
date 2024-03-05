package vn.edu.stu.nodejs_api.Xuly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.PhieuNhap;
import vn.edu.stu.nodejs_api.control.PhieuXuat;

public class XuLyPhieuXuat extends AppCompatActivity {
    EditText sopx,ngaypx,soluongpx,masp, manv;
    Button btnLuu;
    PhieuXuat chon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_phieu_xuat);

        addControl();
        addEvent();
        if (getIntent().hasExtra("UPDATE")) {
            PhieuXuat edit = (PhieuXuat) getIntent().getSerializableExtra("UPDATE");
            sopx.setText(edit.getSopx());
            ngaypx.setText(edit.getNgaypx());
            soluongpx.setText(edit.getSoluongpx() + "");
            masp.setText(edit.getMasp());
            manv.setText(edit.getManv());

        } else {
            chon = new PhieuXuat();
        }
    }

    private void addEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soPX = sopx.getText().toString();
                String ngayPX = ngaypx.getText().toString();
                int soLuongPX = Integer.parseInt(soluongpx.getText().toString());
                String maSP = masp.getText().toString();
                String maNV = manv.getText().toString();

                PhieuXuat phieuXuat=new PhieuXuat(soPX,maNV,maSP,ngayPX,soLuongPX);

                Intent intent = new Intent();
                intent.putExtra("PHIEUXUAT", phieuXuat);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void addControl()
    {
        sopx=findViewById(R.id.editSoPX);
        ngaypx=findViewById(R.id.editNgayPX);
        soluongpx=findViewById(R.id.editSoLuongPX);
        masp=findViewById(R.id.editMaSP);
        manv=findViewById(R.id.editMaNV);
        btnLuu=findViewById(R.id.btnluu);

    }
}