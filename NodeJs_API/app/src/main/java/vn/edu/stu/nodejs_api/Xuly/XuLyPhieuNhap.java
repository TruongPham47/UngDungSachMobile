package vn.edu.stu.nodejs_api.Xuly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.DonHang;
import vn.edu.stu.nodejs_api.control.PhieuNhap;

public class XuLyPhieuNhap extends AppCompatActivity {
    EditText sopn,ngaypn,soluongpn,masp, manv;
    Button btnLuu;
    PhieuNhap chon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_phieu_nhap);

        addControl();
        addEvent();
        if (getIntent().hasExtra("UPDATE")) {
            PhieuNhap edit = (PhieuNhap) getIntent().getSerializableExtra("UPDATE");
            sopn.setText(edit.getSopn());
            ngaypn.setText(edit.getNgaypn());
            soluongpn.setText(edit.getSoluongpn() + "");
            masp.setText(edit.getMasp());
            manv.setText(edit.getManv());

        } else {
            chon = new PhieuNhap();
        }
    }

    private void addEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soPN = sopn.getText().toString();
                String ngayPN = ngaypn.getText().toString();
                int soLuongPN = Integer.parseInt(soluongpn.getText().toString());
                String maSP = masp.getText().toString();
                String maNV = manv.getText().toString();

                PhieuNhap phieuNhap=new PhieuNhap(soPN,maNV,maSP,ngayPN,soLuongPN);

                Intent intent = new Intent();
                intent.putExtra("PHIEUNHAP", phieuNhap);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void addControl()
    {
        sopn=findViewById(R.id.editSoPN);
        ngaypn=findViewById(R.id.editNgayPN);
        soluongpn=findViewById(R.id.editSoLuongPN);
        masp=findViewById(R.id.editMaSP);
        manv=findViewById(R.id.editMaNV);
        btnLuu=findViewById(R.id.btnluu);

    }
}