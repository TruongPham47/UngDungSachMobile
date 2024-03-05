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

public class XulyNhanVien extends AppCompatActivity {

    EditText  ma_nv,ten_nv,dchi_nv,sdt_nv,emal_nv;
    Button btnluu;
    NhanVien chon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuly_nhan_vien);
        addControls();
        addEvent();
        if(getIntent().hasExtra("UPDATE")){
            NhanVien edit=(NhanVien)getIntent().getSerializableExtra("UPDATE");
            ma_nv.setText(edit.getManv());
            ten_nv.setText(edit.getTennv());
            dchi_nv.setText(edit.getDiachinv());
            sdt_nv.setText(edit.getSđt()+"");
            emal_nv.setText(edit.getEmail());

        }else{
            chon=new NhanVien();
        }
    }
    private void addControls()
    {
        ma_nv=findViewById(R.id.editManv);
        ten_nv=findViewById(R.id.editTennv);
        dchi_nv=findViewById(R.id.editdiachinv);
        sdt_nv=findViewById(R.id.editsđtnv);
        emal_nv=findViewById(R.id.editemailnv);
        btnluu=findViewById(R.id.btnluu);


    }
    private void addEvent()
    {
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maKh = ma_nv.getText().toString();
                String tenKh = ten_nv.getText().toString();
                String diaChiKh = dchi_nv.getText().toString();
                int sdtKh = Integer.parseInt(sdt_nv.getText().toString());
                String emailKh = emal_nv.getText().toString();
                NhanVien nhanVien=new NhanVien( maKh,tenKh,diaChiKh,sdtKh,emailKh);

                Intent intent = new Intent();
                intent.putExtra("NHANVIEN", nhanVien);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}