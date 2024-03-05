package vn.edu.stu.nodejs_api.Xuly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.edu.stu.nodejs_api.R;
import vn.edu.stu.nodejs_api.control.KhachHang;

public class XulyKhachHang extends AppCompatActivity {

    EditText ma_kh,ten_kh,dchi_kh,sdt_kh,emal_kh;
    Button btnluu;
    KhachHang chon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuly_khach_hang);
        addControl();
        addEvent();
        if(getIntent().hasExtra("UPDATE"))
        {
            KhachHang edit=(KhachHang)getIntent().getSerializableExtra("UPDATE");
            ma_kh.setText(edit.getMakh());
            ten_kh.setText(edit.getTenkh());
            dchi_kh.setText(edit.getDiachikh());
            sdt_kh.setText(edit.getSđt()+"");
            emal_kh.setText(edit.getEmail());

        }else{
            chon=new KhachHang();
        }
    }
    private void addControl()
    {
        ma_kh=findViewById(R.id.editMakh);
        ten_kh=findViewById(R.id.editTenKh);
        dchi_kh=findViewById(R.id.editdiachi);
        sdt_kh=findViewById(R.id.editsđt);
        emal_kh=findViewById(R.id.editemail);
        btnluu=findViewById(R.id.btnluu);

    }
    private void addEvent()
    {
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String maKh = ma_kh.getText().toString();
                String tenKh = ten_kh.getText().toString();
                String diaChiKh = dchi_kh.getText().toString();
                int sdtKh = Integer.parseInt(sdt_kh.getText().toString());
                String emailKh = emal_kh.getText().toString();
                KhachHang khachHang=new KhachHang( maKh,tenKh,diaChiKh,sdtKh,emailKh);

                Intent intent = new Intent();
                intent.putExtra("KHACHHANG", khachHang);
                setResult(RESULT_OK, intent);
                finish();



            }
        });
    }
}