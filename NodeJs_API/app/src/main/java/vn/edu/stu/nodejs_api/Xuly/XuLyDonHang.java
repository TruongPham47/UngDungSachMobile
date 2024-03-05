package vn.edu.stu.nodejs_api.Xuly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.DonHang;
import vn.edu.stu.nodejs_api.control.KhoHang;

public class XuLyDonHang extends AppCompatActivity {
    EditText madh,ngaygh,ngaydh,soluong,tongtien,makh,masp;
    Button btnLuu;
    DonHang chon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_ly_don_hang);

        addControl();
        addEvent();
        if (getIntent().hasExtra("UPDATE")) {
            DonHang edit = (DonHang) getIntent().getSerializableExtra("UPDATE");
            madh.setText(edit.getMadh());
            ngaydh.setText(edit.getNgaydathang());
            ngaygh.setText(edit.getNgaygiaohang());
            soluong.setText(edit.getSoluong() + "");
            tongtien.setText(edit.getTongtien() + "");
            makh.setText(edit.getMakh());
            masp.setText(edit.getMasp());

        } else {
            chon = new DonHang();
        }
    }

    private void addEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDH = madh.getText().toString();
                String ngayDH = ngaydh.getText().toString();
                String ngayGH = ngaygh.getText().toString();
                int soLuong = Integer.parseInt(soluong.getText().toString());
                int tongTien = Integer.parseInt(tongtien.getText().toString());
                String maKH = makh.getText().toString();
                String maSP = masp.getText().toString();

                DonHang donHang=new DonHang( maDH,ngayDH,ngayGH,soLuong,tongTien,maKH,maSP);

                Intent intent = new Intent();
                intent.putExtra("DONHANG", donHang);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void addControl()
    {
        madh=findViewById(R.id.editMaDH);
        ngaydh=findViewById(R.id.editNgayDH);
        ngaygh=findViewById(R.id.editNgayGH);
        soluong=findViewById(R.id.editSoLuong);
        tongtien=findViewById(R.id.editTongTien);
        makh=findViewById(R.id.editMaKH);
        masp=findViewById(R.id.editMaSP);
        btnLuu=findViewById(R.id.btnluu);

    }
}